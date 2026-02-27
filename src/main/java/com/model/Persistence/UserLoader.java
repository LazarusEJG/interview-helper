package com.model.Persistence;

import com.model.*;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class UserLoader extends DataConstants {

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

				ArrayList<UUID> submittedSolutionsUUID = (ArrayList<UUID>) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<UUID> bookmarkedQuestionsUUID = (ArrayList<UUID>) userJSON.get(USER_SUBMITTED_SOLUTIONS);
				ArrayList<UUID> bookmarkedSolutionsUUID = (ArrayList<UUID>) userJSON.get(USER_SUBMITTED_SOLUTIONS);

				ArrayList<String> completedCourses = (ArrayList<String>) userJSON.get(USER_COMPLETED_COURSES);
				LocalDate lastStreakDate = (LocalDate) userJSON.get(USER_LAST_STREAK_DAY);
				int receivedVotes = (int) userJSON.get(USER_RECEIVED_VOTES);

				ArrayList<Solution> submittedSolutions = new ArrayList<Solution>(submittedSolutionsUUID.size());
				for (UUID solutionID : submittedSolutionsUUID) {
					// TODO: requires QuestionList
				}
				ArrayList<Question> bookmarkedQuestions = new ArrayList<Question>(bookmarkedQuestionsUUID.size());
				for (UUID questionID : bookmarkedQuestionsUUID) {
					// TODO: requires QuestionList
				}
				ArrayList<Solution> bookmarkedSolutions = new ArrayList<Solution>(bookmarkedSolutionsUUID.size());
				for (UUID solutionID : bookmarkedSolutionsUUID) {
					// TODO: requires QuestionList
				}

				User user = new User(id, eMail, username, password, interests, currentStreak, longestStreak, submittedSolutions,
						bookmarkedQuestions, bookmarkedSolutions, completedCourses, lastStreakDate, receivedVotes);
				users.add(user);
			}

		} catch (Exception e) {
		}
		return users;
	}
}
