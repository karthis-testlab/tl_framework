package com.tl.framework.sauce.demo.app.tests;

import org.testng.annotations.Test;

import com.tl.framework.hooks.TestNGHooks;
import com.tl.framework.sauce.demo.app.pages.LoginPage;

public class TC001_LoginTest extends TestNGHooks {
	
	@Test
	public void validLoginDetials() {
		new LoginPage()
		    .enterUserName("standard_user")
		    .enterPassword("secret_sauce")
		    .clickLoginButton();
	}

}