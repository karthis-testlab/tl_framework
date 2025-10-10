package com.tl.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer {

	private int maxTry = 3;
	private int count = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) {
			count++;
			System.out.println("Retrying test: " + result.getName() + " | Attempt #" + (count + 1));
			return true;
		}
		return false;
	}

}