package com.model.Persistence;

import com.model.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class UserWriter extends DataConstants {
	public static void saveUsers(ArrayList<User> users) {
		try {
			FileWriter writer = new FileWriter(USER_FILE_NAME);
			JSONParser parser = new JSONParser();
			for (User user : users) {
				JSONObject userJSON = getUserJSON(user);
			}
		} catch (Exception e) {

		}
		return;
	}

	private static JSONObject getUserJSON(User user) {
		JSONObject userJSON = new JSONObject();
		userJSON.put(USER_ID, user.getId());
		userJSON.put(USER_TYPE, user.getType());
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
		for (Solution solution : user.getSubmittedSolutions()) {
			submittedSolutions.add(solution.getId());
		}
		userJSON.put(USER_INTERESTS, submittedSolutions);

		JSONArray bookmarkedQuestions = new JSONArray();
		for (Question question : user.getBookmarkedQuestions()) {
			bookmarkedQuestions.add(question.getId());
		}
		userJSON.put(USER_INTERESTS, bookmarkedQuestions);

		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());
		userJSON.put(USER_USER_NAME, user.getUsername());

		return userJSON;
	}
}
