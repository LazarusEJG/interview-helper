package com.model.Persistence;

import com.model.*;
import com.model.Persistence.UserDataConstants;

import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class UserLoader extends UserDataConstants {
	public static ArrayList<User> getUsers() {
		return getUsers(null);
	}

	public static ArrayList<User> getUsers(String databaseName) {
		ArrayList<User> users = null;
		try {
			FileReader reader = new FileReader(getFilePath(databaseName));
			JSONParser parser = new JSONParser();
			JSONArray usersJSON = (JSONArray) parser.parse(reader);
			users = new ArrayList<User>(usersJSON.size());

			for (Object userObject : usersJSON) {
				JSONObject userJSON = (JSONObject) userObject;

				UUID id = UUID.fromString((String) userJSON.get(USER_ID));
				UserType userType = UserType.valueOf((String) userJSON.get(USER_TYPE));
				String eMail = (String) userJSON.get(USER_EMAIL);
				String username = (String) userJSON.get(USER_USER_NAME);
				String password = (String) userJSON.get(USER_PASSWORD);
				ArrayList<String> interests = (ArrayList<String>) userJSON.get(USER_INTERESTS);
				int currentStreak = ((Long) userJSON.get(USER_CURRENT_STREAK)).intValue();
				int longestStreak = ((Long) userJSON.get(USER_LONGEST_STREAK)).intValue();

				JSONArray submittedSolutionsUUID = (JSONArray) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<UUID> submittedSolutions = getSubmittedSolutions(submittedSolutionsUUID);
				JSONArray bookmarkedQuestionsUUID = (JSONArray) userJSON.get(USER_BOOKMARKED_QUESTIONS);
				ArrayList<UUID> bookmarkedQuestions = getBookmarkedQuestions(bookmarkedQuestionsUUID);
				JSONArray bookmarkedSolutionsUUID = (JSONArray) userJSON.get(USER_BOOKMARKED_SOLUTIONS);
				ArrayList<UUID> bookmarkedSolutions = getBookmarkedSolutions(bookmarkedSolutionsUUID);

				ArrayList<String> completedCourses = (ArrayList<String>) userJSON.get(USER_COMPLETED_COURSES);
				LocalDate lastStreakDate = LocalDate.parse((String) userJSON.get(USER_LAST_STREAK_DAY));
				int receivedVotes = ((Long) userJSON.get(USER_RECEIVED_VOTES)).intValue();

				User user = new User(id, userType, eMail, username, password, interests, currentStreak, longestStreak,
						submittedSolutions, bookmarkedQuestions, bookmarkedSolutions, completedCourses, lastStreakDate,
						receivedVotes);
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	private static ArrayList<UUID> getSubmittedSolutions(JSONArray submittedSolutionsUUID) {
		// ArrayList<Solution> submittedSolutions = new
		// ArrayList<Solution>(submittedSolutionsUUID.size());

		// QuestionList questionList = QuestionList.getInstance();
		ArrayList<UUID> submittedSolutions = new ArrayList<UUID>(submittedSolutionsUUID.size());
		for (Object solutionID : submittedSolutionsUUID) {
			UUID uuid = UUID.fromString((String) solutionID);
			submittedSolutions.add(uuid);
			// submittedSolutions.add(questionList.getSolution(uuid));
		}
		return submittedSolutions;
	}

	private static ArrayList<UUID> getBookmarkedQuestions(JSONArray bookmarkedQuestionsUUID) {
		// ArrayList<Question> bookmarkedQuestions = new
		// ArrayList<Question>(bookmarkedQuestionsUUID.size());

		// QuestionList questionList = QuestionList.getInstance();
		ArrayList<UUID> bookmarkedQuestions = new ArrayList<UUID>(bookmarkedQuestionsUUID.size());
		for (Object questionID : bookmarkedQuestionsUUID) {
			UUID uuid = UUID.fromString((String) questionID);
			// bookmarkedQuestions.add(questionList.getQuestion(uuid));
			bookmarkedQuestions.add(uuid);
		}
		return bookmarkedQuestions;
	}

	private static ArrayList<UUID> getBookmarkedSolutions(JSONArray bookmarkedSolutionsUUID) {
		// ArrayList<Solution> bookmarkedSolutions = new
		// ArrayList<Solution>(bookmarkedSolutionsUUID.size());

		// QuestionList questionList = QuestionList.getInstance();
		ArrayList<UUID> bookmarkedSolutions = new ArrayList<UUID>(bookmarkedSolutionsUUID.size());
		for (Object solutionID : bookmarkedSolutionsUUID) {
			UUID uuid = UUID.fromString((String) solutionID);
			bookmarkedSolutions.add(uuid);
		}
		return bookmarkedSolutions;
	}
}
