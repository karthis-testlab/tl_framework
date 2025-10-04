package com.tl.framework.hooks;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tl.framework.selenium.api.base.SeleniumBase;

public class TestNGHooks extends SeleniumBase {
	
	@BeforeMethod
	public void beforeMethod() {
		browserLaunch();		
		loadUrl("https://www.saucedemo.com/");
	}
	
	@AfterMethod
	public void afterMethod() {
		quit();
	}

}