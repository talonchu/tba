package com.perficient.bcten.util.log4j;

import org.apache.log4j.Logger;

public final class LogProvider {
	private LogProvider() {

	}

	public static void info(String className, Object message) {
		Logger.getLogger(className).info(message);
	}

	public static void error(String className, Object message) {
		Logger.getLogger(className).error(message);
	}

	public static void debug(String className, Object message) {
		Logger.getLogger(className).debug(message);
	}
}
