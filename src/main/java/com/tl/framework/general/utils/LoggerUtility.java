package com.tl.framework.general.utils;

import java.util.logging.Logger;

public class LoggerUtility {

	private static Logger logger = Logger.getLogger(LoggerUtility.class.getName());

	private LoggerUtility() {} // Prevent direct instantiation
	
	public static void info(String message) {
		logger.fine(message);
	}

	public static void pass(String message) {
		logger.info(message);
	}

	public static void error(String message) {
		logger.severe(message);
	}

	public static void warn(String message) {
		logger.warning(message);
	}

}