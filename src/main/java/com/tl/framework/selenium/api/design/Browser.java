package com.tl.framework.selenium.api.design;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.tl.framework.selenium.api.constants.Browsers;
import com.tl.framework.selenium.api.constants.Locators;

public interface Browser {
	
	public void browserLaunch();

	public void browserLaunch(Browsers bowserName);

	public void loadUrl(String url);

	public WebElement locateElement(Locators locatorType, String value);

	public List<WebElement> locateElements(Locators locatorType, String value);

	public void close();

	public void quit();

}