package com.tl.framework.selenium.api.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WaitConditions extends SynchronizationWait {

	public WebElement waitUnitlVisibilityOfElement(WebElement element) {
		return getWait().until(ExpectedConditions.visibilityOf(element));
	}

	public Boolean waitUnitlInVisibilityOfElement(WebElement element) {
		return getWait().until(ExpectedConditions.invisibilityOf(element));
	}

	public WebElement waitUntilElementClickable(WebElement element) {
		return getWait().until(ExpectedConditions.elementToBeClickable(element));
	}

}