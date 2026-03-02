package com.model.Persistence;

import com.model.*;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class UserLoader extends UserDataConstants {

	public static ArrayList<User> getUsers() {
		ArrayList<User> users = null;
		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
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
				int currentStreak = (int) userJSON.get(USER_CURRENT_STREAK);
				int longestStreak = (int) userJSON.get(USER_LONGEST_STREAK);

				JSONArray submittedSolutionsUUID = (JSONArray) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<Solution> submittedSolutions = getSubmittedSolutions(submittedSolutionsUUID);
				JSONArray bookmarkedQuestionsUUID = (JSONArray) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<Question> bookmarkedQuestions = getBookmarkedQuestions(bookmarkedQuestionsUUID);
				JSONArray bookmarkedSolutionsUUID = (JSONArray) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<Solution> bookmarkedSolutions = getBookmarkedSolutions(bookmarkedSolutionsUUID);

				ArrayList<String> completedCourses = (ArrayList<String>) userJSON.get(USER_COMPLETED_COURSES);
				LocalDate lastStreakDate = LocalDate.parse((String) userJSON.get(USER_LAST_STREAK_DAY));
				int receivedVotes = (int) userJSON.get(USER_RECEIVED_VOTES);

				User user = new User(id, userType, eMail, username, password, interests, currentStreak, longestStreak,
						submittedSolutions,
						bookmarkedQuestions, bookmarkedSolutions, completedCourses, lastStreakDate, receivedVotes);
				users.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	private static ArrayList<Solution> getSubmittedSolutions(JSONArray submittedSolutionsUUID) {
		ArrayList<Solution> submittedSolutions = new ArrayList<Solution>(submittedSolutionsUUID.size());
		QuestionList questionList = QuestionList.getInstance();
		for (Object solutionID : submittedSolutionsUUID) {
			UUID uuid = UUID.fromString((String) solutionID);
			submittedSolutions.add(questionList.getSolution(uuid));
		}
		return submittedSolutions;
	}

	private static ArrayList<Question> getBookmarkedQuestions(JSONArray bookmarkedQuestionsUUID) {
		ArrayList<Question> bookmarkedQuestions = new ArrayList<Question>(bookmarkedQuestionsUUID.size());
		QuestionList questionList = QuestionList.getInstance();
		for (Object questionID : bookmarkedQuestionsUUID) {
			UUID uuid = UUID.fromString((String) questionID);
			bookmarkedQuestions.add(questionList.getQuestion(uuid));
		}
		return bookmarkedQuestions;
	}

	private static ArrayList<Solution> getBookmarkedSolutions(JSONArray bookmarkedSolutionsUUID) {
		ArrayList<Solution> bookmarkedSolutions = new ArrayList<Solution>(bookmarkedSolutionsUUID.size());
		QuestionList questionList = QuestionList.getInstance();
		for (Object questionID : bookmarkedSolutions) {
			UUID uuid = UUID.fromString((String) questionID);
			bookmarkedSolutions.add(questionList.getSolution(uuid));
		}
		return bookmarkedSolutions;
	}
}
