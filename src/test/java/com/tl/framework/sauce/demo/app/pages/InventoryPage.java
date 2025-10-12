package com.tl.framework.sauce.demo.app.pages;

import org.testng.Assert;

import com.tl.framework.hooks.TestNGHooks;
import com.tl.framework.selenium.api.base.WaitConditions;
import com.tl.framework.selenium.api.constants.Locators;

public class InventoryPage extends TestNGHooks {
	
	private WaitConditions wait;
	
	public InventoryPage() {
		wait = new WaitConditions(getDriver());	
		Assert.assertTrue(wait.waitUntilUrlContainsSpecifiedPath("/inventory"));
	}	
	
	public InventoryPage addFirstProductToTheCart() {
		click(wait.waitUntilElementClickable(locateElement(Locators.XPATH, "//button[.='Add to cart']")));
		return this;
	}

}