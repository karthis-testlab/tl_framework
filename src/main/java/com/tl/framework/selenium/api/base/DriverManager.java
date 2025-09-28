package com.tl.framework.selenium.api.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tl.framework.selenium.api.constants.Browsers;

public class DriverManager {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public void setDriver(Browsers browserName) {
		switch (browserName) {
		case CHROME:
			driver.set(new ChromeDriver());
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