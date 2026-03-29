package com.model.Persistence;

public abstract class DataConstants {
	protected static String getFilePath() {
		throw new UnsupportedOperationException();
	}

	public static boolean isJUnitTest() {
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
			if (element.getClassName().startsWith("org.junit.")) {
				return true;
			}
		}
		return false;
	}
}
