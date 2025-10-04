package com.tl.framework.selenium.api.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitConditions extends SynchronizationWait {
	
	public WaitConditions(WebDriver driver) {
		setWait(driver);
	}

	public WebElement waitUnitlVisibilityOfElement(WebElement element) {
		return getWait().until(ExpectedConditions.visibilityOf(element));
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

}