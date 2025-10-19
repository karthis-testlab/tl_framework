package com.tl.framework.selenium.api.base;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tl.framework.selenium.api.constants.Browsers;

public class DriverManager {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public void setDriver(Browsers browserName, String environment) {
		switch (browserName) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			if(environment.toUpperCase().equals("GRID")) {
				try {
					driver.set(new RemoteWebDriver(new URI("").toURL(), options));
				} catch (MalformedURLException | URISyntaxException e) {					
					e.printStackTrace();
				}
			} else {				
				driver.set(new ChromeDriver(options));
			}
			break;
		case EDGE:
			driver.set(new EdgeDriver());
			break;
		case FIREFOX:
			driver.set(new FirefoxDriver());
			break;				
		default:
			throw new IllegalArgumentException("Currently, given browser option wasn't support by this framework");
		}
	}
	
	public void setDriver(Browsers browserName) {
		switch (browserName) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--start-maximized");
			driver.set(new ChromeDriver(options));
			break;
		case EDGE:
			driver.set(new EdgeDriver());
			break;
		case FIREFOX:
			driver.set(new FirefoxDriver());
			break;				
		default:
			throw new IllegalArgumentException("Currently, given browser option wasn't support by this framework");
		}
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}

}