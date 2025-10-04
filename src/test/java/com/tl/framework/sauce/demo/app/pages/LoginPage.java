package com.tl.framework.sauce.demo.app.pages;

import com.tl.framework.hooks.TestNGHooks;
import com.tl.framework.selenium.api.base.WaitConditions;
import com.tl.framework.selenium.api.constants.Locators;

public class LoginPage extends TestNGHooks {
	
	private WaitConditions wait;
	
	public LoginPage() {
		wait = new WaitConditions(getDriver());
	}
	
	public LoginPage enterUserName(String username) {
		type(wait.waitUnitlVisibilityOfElement(locateElement(Locators.ID, "user-name")), username);
		return this;
	}
	
	public LoginPage enterPassword(String password) {
		type(wait.waitUnitlVisibilityOfElement(locateElement(Locators.ID, "password")), password);
		return this;
	}
	
	public InventoryPage clickLoginButton() {
		click(wait.waitUntilElementClickable(locateElement(Locators.ID, "login-button")));
		return new InventoryPage();
	}

}