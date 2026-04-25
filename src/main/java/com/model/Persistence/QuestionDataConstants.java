package com.model.Persistence;

import java.nio.file.Paths;

public abstract class QuestionDataConstants extends DataConstants {
	private static final String QUESTION_FILE_PATH = "json";
	private static final String QUESTION_FILE_NAME = "questions.json";
	protected static final String QUESTION_ID = "id";
	protected static final String QUESTION_TITLE = "title";
	protected static final String QUESTION_DESCRIPTION = "description";
	protected static final String QUESTION_DIFFICULTY = "difficulty";
	protected static final String QUESTION_SCORE = "score";
	protected static final String QUESTION_CONTENT = "content";
	protected static final String QUESTION_CATEGORIES = "categories";
	protected static final String QUESTION_AUTHOR = "author";
	protected static final String QUESTION_PUBLISH_TIME = "publishTime";
	protected static final String QUESTION_HINTS = "hints";

	protected static final String QUESTION_SOLUTIONS = "solutions";
	protected static final String QUESTION_SOLUTIONS_ID = "id";
	protected static final String QUESTION_SOLUTIONS_AUTHOR = "author";
	protected static final String QUESTION_SOLUTIONS_PUBLISH_TIME = "publishTime";
	protected static final String QUESTION_SOLUTIONS_SCORE = "score";
	protected static final String QUESTION_SOLUTIONS_FILE = "file";
	protected static final String QUESTION_SOLUTIONS_EXPLANATION = "explanation";
	protected static final String QUESTION_SOLUTIONS_CODE = "code";
	protected static final String QUESTION_SOLUTIONS_VERIFIED = "verified";

	protected static final String QUESTION_COMMENTS = "comments";
	protected static final String QUESTION_COMMENTS_ID = "id";
	protected static final String QUESTION_COMMENTS_AUTHOR = "author";
	protected static final String QUESTION_COMMENTS_PUBLISH_TIME = "publishTime";
	protected static final String QUESTION_COMMENTS_SCORE = "score";
	protected static final String QUESTION_COMMENTS_CONTENT = "content";

	protected static String getFilePath(String databaseName) {
		if (isJUnitTest()) {
			if (databaseName != null && databaseName.equals("") == false) {
				return Paths.get(QUESTION_FILE_PATH + "_test", databaseName, QUESTION_FILE_NAME).toString();
			} else {
				return Paths.get(QUESTION_FILE_PATH + "_test", "default", QUESTION_FILE_NAME).toString();
			}
		} else {
			return Paths.get(QUESTION_FILE_PATH, QUESTION_FILE_NAME).toString();
		}
	}

	protected static String getFilePath() {
		return Paths.get(QUESTION_FILE_PATH, QUESTION_FILE_NAME).toString();
	}
}
