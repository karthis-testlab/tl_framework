package com.tl.framework.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tl.framework.selenium.api.base.SeleniumBase;

public class ExtentReportListenerV1 extends SeleniumBase implements ISuiteListener, ITestListener {
	
	private static ExtentSparkReporter sparkReporter;
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> testSuite = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> testCase = new ThreadLocal<>();
	private static ConcurrentHashMap<String, Integer> retryMap = new ConcurrentHashMap<>();
	
	public void onStart(ISuite suite) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/result.html");

		sparkReporter.config().setDocumentTitle("Selenium Automation Report");
		sparkReporter.config().setReportName("Sauce Demo Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);

		extentReports.setSystemInfo("Domain Name: ", "https://www.saucedemo.com/");
		extentReports.setSystemInfo("Environment: ", "QA");
		extentReports.setSystemInfo("Tester Name: ", "DigitalFactory24 QA Team");
		extentReports.setSystemInfo("OS Name: ", "Windows 11 Home Single Language");
		extentReports.setSystemInfo("Browser Name: ", "Google Chrome Version 140.0.7339.128 (Official Build) (64-bit)");
	}

	public void onFinish(ISuite suite) {
		extentReports.flush();
	}

	public void onStart(ITestContext context) {
		ExtentTest parent = extentReports.createTest(context.getCurrentXmlTest().getName());
		testSuite.set(parent);
	}

	public void onTestStart(ITestResult result) {
		String className = result.getTestClass().getName();
	    String methodName = result.getMethod().getMethodName();
	    String description = result.getMethod().getDescription();
	    
	    // Track retry attempt
        int attempt = retryMap.getOrDefault(methodName, 0) + 1;
        retryMap.put(methodName, attempt);

	    // Create readable test title
	    String testTitle = className + " → " + methodName + " (Attempt #" + attempt + ")";
		ExtentTest node = testSuite.get().createNode(result.getName(), description);
		testCase.set(node);
		testCase.get().log(Status.INFO, "🧪 Starting Test: " + testTitle);
	}

	public void onTestSuccess(ITestResult result) {
		 int attempt = retryMap.getOrDefault(result.getMethod().getMethodName(), 1);
		 if (attempt > 1) {
			 testCase.get().log(Status.PASS, result.getName() + " ✅ Test Passed after Retry #" + (attempt - 1));
		} else {
	        	testCase.get().log(Status.PASS, result.getName() + " ✅ Test Passed: ");
	    }		
	}	
	
    public void onTestSkipped(ITestResult result) {
    	    	testCase.get().log(Status.SKIP, "⚠️ Test Skipped: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {	
		String className = result.getTestClass().getName();
	    String methodName = result.getMethod().getMethodName();
	    String description = result.getMethod().getDescription();

	    System.out.println("Test Failed in Class: " + className);
	    System.out.println("Method: " + methodName);
	    System.out.println("Description: " + description);
	    
	    String screenshotPath = null;
	    
		if (getDriver() != null) {
			screenshotPath = "./screenshots/" + result.getName() + "_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png";
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			try {				
				FileHandler.copy(src, new File(screenshotPath));
				System.out.println("Screenshot saved for test: " + result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		testCase.get().log(Status.FAIL, "❌ Test failed in Class: <b>" + className + "</b><br>Method: <b>" + methodName + "</b>");
		testCase.get().fail(result.getThrowable());
		testCase.get().addScreenCaptureFromPath("."+screenshotPath);
	}

}