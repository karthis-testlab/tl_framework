package com.tl.framework.selenium.api.base;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitConditions extends SynchronizationWait {
	
	public WaitConditions(WebDriver driver) {
		setWait(driver);
	}

	public WebElement waitUnitlVisibilityOfElement(WebElement element) {
		WebElement webElement = null;
		try {
			webElement = getWait().until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			throw new RuntimeException("⚠️ TimeoutException: Element not found within the specified time.");
		}
		return webElement;		
	}

	public Boolean waitUnitlInVisibilityOfElement(WebElement element) {
		return getWait().until(ExpectedConditions.invisibilityOf(element));
	}

	public WebElement waitUntilElementClickable(WebElement element) {
		return getWait().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public WebDriver waitUntilFrameVisibleAndThenSwitchIt(WebElement element) {
		return getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}
	
	public Boolean waitUntilUrlContainsSpecifiedPath(String urlPath) {
		return getWait().until(ExpectedConditions.urlContains(urlPath));
	}

}