package com.tl.framework.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ExtentReportListener extends SeleniumBase implements ISuiteListener, ITestListener {
	
	private static ExtentSparkReporter sparkReporter;
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> testSuite = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> testCase = new ThreadLocal<>();
	
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
		ExtentTest node = testSuite.get().createNode(result.getName());
		testCase.set(node);
	}

	public void onTestSuccess(ITestResult result) {
		testCase.get().log(Status.PASS, "Test case is PASSED: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {		
		if (getDriver() != null) {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(src, new File("./screenshots/" + result.getName() + "_" + timestamp + ".png"));
				System.out.println("Screenshot saved for test: " + result.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		testCase.get().log(Status.FAIL, "Test case is FAILED: " + result.getName());
	}

}