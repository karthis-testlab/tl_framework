package com.tl.framework.hooks;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tl.framework.selenium.api.base.SeleniumBase;
import com.tl.framework.selenium.api.constants.Browsers;

public class TestNGHooks extends SeleniumBase {
	
	@BeforeMethod
	public void beforeMethod() {
		browserLaunch(Browsers.FIREFOX);		
		loadUrl("https://www.saucedemo.com/");
	}
	
	@AfterMethod
	public void afterMethod() {
		quit();
	}

}