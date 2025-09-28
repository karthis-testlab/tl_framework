package com.tl.framework.sauce.demo.app.pages;

import com.tl.framework.hooks.TestNGHooks;
import com.tl.framework.selenium.api.constants.Locators;

public class LoginPage extends TestNGHooks {
	
	public LoginPage enterUserName(String username) {
		waitUnitlVisibilityOfElement(locateElement(Locators.ID, "user-name")).sendKeys(username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		waitUnitlVisibilityOfElement(locateElement(Locators.ID, "password")).sendKeys(password);
		return this;
	}
	
	public InventoryPage clickLoginButton() {
		waitUntilElementClickable(locateElement(Locators.ID, "login-button")).click();
		return new InventoryPage();
	}

}