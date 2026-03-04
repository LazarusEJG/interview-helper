package com.model.Persistence;

import com.model.*;
import com.model.Persistence.UserDataConstants;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class UserWriter extends UserDataConstants {
	public static void saveUsers(ArrayList<User> users) {
		try {
			JSONArray usersJSON = new JSONArray();
			for (User user : users) {
				JSONObject userJSON = getUserJSON(user);
				usersJSON.add(userJSON);
			}

			FileWriter writer = new FileWriter(Paths.get(USER_FILE_PATH, "TEST_" + USER_FILE_NAME).toString());
			usersJSON.writeJSONString(writer);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private static JSONObject getUserJSON(User user) {
		JSONObject userJSON = new JSONObject();
		userJSON.put(USER_ID, user.getId().toString());
		userJSON.put(USER_TYPE, user.getType().name());
		userJSON.put(USER_EMAIL, user.geteMail());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_PASSWORD, user.getPassword());

		JSONArray interests = new JSONArray();
		for (String interest : user.getInterests()) {
			interests.add(interest);
		}
		userJSON.put(USER_INTERESTS, interests);

		userJSON.put(USER_CURRENT_STREAK, user.getCurrentStreak());
		userJSON.put(USER_LONGEST_STREAK, user.getLongestStreak());

		JSONArray submittedSolutions = new JSONArray();
		for (UUID solution : user.getSubmittedSolutions()) {
			submittedSolutions.add(solution.toString());
		}
		userJSON.put(USER_INTERESTS, submittedSolutions);

		JSONArray bookmarkedQuestions = new JSONArray();
		for (UUID question : user.getBookmarkedQuestions()) {
			bookmarkedQuestions.add(question.toString());
		}
		userJSON.put(USER_INTERESTS, bookmarkedQuestions);

		JSONArray bookmarkedSolutions = new JSONArray();
		for (UUID solution : user.getBookmarkedSolutions()) {
			bookmarkedSolutions.add(solution.toString());
		}
		userJSON.put(USER_INTERESTS, bookmarkedSolutions);

		JSONArray courses = new JSONArray();
		for (String course : user.getCompletedCourses()) {
			interests.add(course);
		}
		userJSON.put(USER_COMPLETED_COURSES, courses);

		userJSON.put(USER_LAST_STREAK_DAY, user.getLastStreakDate().toString());
		userJSON.put(USER_RECEIVED_VOTES, user.getReceivedVotes());

		return userJSON;
	}
}
