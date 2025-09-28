package com.tl.framework.hooks;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tl.framework.selenium.api.base.WaitConditions;

public class TestNGHooks extends WaitConditions {
	
	@BeforeMethod
	public void beforeMethod() {
		browserLaunch();
		setWait();
		loadUrl("https://www.saucedemo.com/");
	}
	
	@AfterMethod
	public void afterMethod() {
		quit();
	}

}