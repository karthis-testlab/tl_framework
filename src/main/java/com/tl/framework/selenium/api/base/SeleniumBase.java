package com.tl.framework.selenium.api.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.tl.framework.selenium.api.constants.Browsers;
import com.tl.framework.selenium.api.constants.Locators;
import com.tl.framework.selenium.api.design.Browser;
import com.tl.framework.selenium.api.design.Element;


public class SeleniumBase extends DriverManager implements Browser, Element {	

	@Override
	public void click(WebElement ele) {		
		ele.click();	
	}

	@Override
	public void click(WebElement ele, String jsExpression) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript(jsExpression, ele);
	}

	@Override
	public void type(WebElement ele, String data) {
		ele.sendKeys(data);
	}

	@Override
	public void typeAndEnter(WebElement ele, String data) {
		ele.sendKeys(data, Keys.ENTER);		
	}

	@Override
	public void dropdownSelectByValue(WebElement ele, String value) {		
		new Select(ele).selectByValue(value);		
	}

	@Override
	public String getElementText(WebElement ele) {		
		return ele.getText();
	}

	@Override
	public String getAttributeValue(WebElement ele, String value) {		
		return ele.getAttribute(value);
	}

	@Override
	public void browserLaunch() {	
		setDriver(Browsers.CHROME);
	}

	@Override
	public void browserLaunch(Browsers bowserName) {		
		setDriver(bowserName);		
	}

	@Override
	public void loadUrl(String url) {
		getDriver().get(url);		
	}

	@Override
	public WebElement locateElement(Locators locatorType, String value) {
		switch (locatorType) {
		case ID:
			return getDriver().findElement(By.id(value));
		case NAME:
			return getDriver().findElement(By.name(value));
		case CLASS_NAME:
			return getDriver().findElement(By.className(value));
		case TAG_NAME:
			return getDriver().findElement(By.className(value));
		case LINK_TEXT:
			return getDriver().findElement(By.linkText(value));
		case PARTIAL_LINK_TEXT:
			return getDriver().findElement(By.partialLinkText(value));
		case CSS_SELECTOR:
			return getDriver().findElement(By.cssSelector(value));
		case XPATH:
			return getDriver().findElement(By.xpath(value));
		default:
			throw new IllegalArgumentException("Given locator type was not support.");
		}
	}

	@Override
	public List<WebElement> locateElements(Locators locatorType, String value) {
		switch (locatorType) {
		case ID:
			return getDriver().findElements(By.id(value));
		case NAME:
			return getDriver().findElements(By.name(value));
		case CLASS_NAME:
			return getDriver().findElements(By.className(value));
		case TAG_NAME:
			return getDriver().findElements(By.className(value));
		case LINK_TEXT:
			return getDriver().findElements(By.linkText(value));
		case PARTIAL_LINK_TEXT:
			return getDriver().findElements(By.partialLinkText(value));
		case CSS_SELECTOR:
			return getDriver().findElements(By.cssSelector(value));
		case XPATH:
			return getDriver().findElements(By.xpath(value));
		default:
			throw new IllegalArgumentException("Given locator type was not support.");
		}
	}

	@Override
	public void close() {
		getDriver().close();		
	}

	@Override
	public void quit() {
		getDriver().quit();		
	}

}