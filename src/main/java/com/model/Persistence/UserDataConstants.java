package com.model.Persistence;

import java.nio.file.Paths;

public abstract class UserDataConstants extends DataConstants {
	protected static final String USER_FILE_PATH = "json";
	protected static final String USER_FILE_NAME = "users.json";
	protected static final String USER_ID = "id";
	protected static final String USER_TYPE = "type";
	protected static final String USER_EMAIL = "email";
	protected static final String USER_USER_NAME = "username";
	protected static final String USER_PASSWORD = "password";
	protected static final String USER_INTERESTS = "interests";
	protected static final String USER_CURRENT_STREAK = "current_streak";
	protected static final String USER_LONGEST_STREAK = "longest_streak";
	protected static final String USER_SUBMITTED_SOLUTIONS = "submitted_solutions";
	protected static final String USER_BOOKMARKED_QUESTIONS = "bookmarked_questions";
	protected static final String USER_BOOKMARKED_SOLUTIONS = "bookmarked_solutions";
	protected static final String USER_COMPLETED_COURSES = "completed_courses";
	protected static final String USER_LAST_STREAK_DAY = "last_streak_date";
	protected static final String USER_RECEIVED_VOTES = "received_votes";

	protected static String getFilePath(String databaseName) {
		if (isJUnitTest()) {
			if (databaseName != null && databaseName.equals("") == false) {
				return Paths.get(USER_FILE_PATH + "_test", databaseName, USER_FILE_NAME).toString();
			} else {
				return Paths.get(USER_FILE_PATH + "_test", "default", USER_FILE_NAME).toString();
			}
		} else {
			return Paths.get(USER_FILE_PATH, USER_FILE_NAME).toString();
		}
	}
}
