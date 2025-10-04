package com.tl.framework.selenium.api.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SynchronizationWait {

	private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<WebDriverWait>();

	public WebDriverWait getWait() {
		return wait.get();
	}

	public void setWait(WebDriver driver) {
		wait.set(new WebDriverWait(driver, Duration.ofSeconds(15)));
	}

}