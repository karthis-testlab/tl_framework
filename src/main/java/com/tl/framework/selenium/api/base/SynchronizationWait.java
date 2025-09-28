package com.tl.framework.selenium.api.base;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

public class SynchronizationWait extends SeleniumBase {

	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();

	public WebDriverWait getWait() {
		return wait.get();
	}

	public void setWait() {
		wait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(15)));
	}

}