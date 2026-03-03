package com.model.Persistence;

import com.model.*;
import com.model.Persistence.QuestionDataConstants;

import java.io.FileReader;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class QuestionLoader extends QuestionDataConstants {

	public static ArrayList<Question> getQuestions() {
		ArrayList<Question> questions = null;
		try {
			FileReader reader = new FileReader(Paths.get(QUESTION_FILE_PATH, QUESTION_FILE_NAME).toString());
			JSONParser parser = new JSONParser();
			JSONArray questionsJSON = (JSONArray) parser.parse(reader);
			questions = new ArrayList<Question>(questionsJSON.size());

			for (Object questionObject : questionsJSON) {
				JSONObject questionJSON = (JSONObject) questionObject;

				UUID id = UUID.fromString((String) questionJSON.get(QUESTION_ID));
				String title = (String) questionJSON.get(QUESTION_TITLE);
				int difficulty = (int) questionJSON.get(QUESTION_DIFFICULTY);
				int score = (int) questionJSON.get(QUESTION_SCORE);
				String content = (String) questionJSON.get(QUESTION_CONTENT);
				// Categories
				JSONArray categoriesJSON = (JSONArray) questionJSON.get(QUESTION_CATEGORIES);
				ArrayList<String> categories = getCategories(categoriesJSON);

				UUID authorUUID = UUID.fromString((String) questionJSON.get(QUESTION_AUTHOR));
				User author = UserList.getInstance().getUser(authorUUID);
				LocalDateTime publishTime = LocalDateTime.parse((String) questionJSON.get(QUESTION_PUBLISH_TIME));
				// Hints
				JSONArray hintsJSON = (JSONArray) questionJSON.get(QUESTION_HINTS);
				ArrayList<String> hints = getHints(hintsJSON);

				// Solutions
				JSONArray solutionsJSON = (JSONArray) questionJSON.get(QUESTION_SOLUTIONS);
				ArrayList<Solution> solutions = getSolutions(solutionsJSON);

				// Comments
				JSONArray commentsJSON = (JSONArray) questionJSON.get(QUESTION_COMMENTS);
				ArrayList<Comment> comments = getComments(commentsJSON);

				questions.add(new Question(id, author, content, title, difficulty, categories, solutions, comments, hints,
						publishTime, score));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	private static ArrayList<Solution> getSolutions(JSONArray solutionsJSON) {
		ArrayList<Solution> solutions = new ArrayList<Solution>(solutionsJSON.size());
		for (Object _solutionJSON : solutionsJSON) {
			JSONObject solutionJSON = (JSONObject) _solutionJSON;

			UUID id = UUID.fromString((String) solutionJSON.get(QUESTION_SOLUTIONS_ID));

			UUID authorUUID = UUID.fromString((String) solutionJSON.get(QUESTION_SOLUTIONS_AUTHOR));
			User author = UserList.getInstance().getUser(authorUUID);

			LocalDateTime publishTime = LocalDateTime.parse((String) solutionJSON.get(QUESTION_SOLUTIONS_PUBLISH_TIME));
			int score = (int) solutionJSON.get(QUESTION_SOLUTIONS_SCORE);
			String filename = (String) solutionJSON.get(QUESTION_SOLUTIONS_FILE);
			String explanation = (String) solutionJSON.get(QUESTION_SOLUTIONS_EXPLANATION);
			boolean verified = ((String) solutionJSON.get(QUESTION_SOLUTIONS_VERIFIED)).equalsIgnoreCase("true");

			// Comments
			JSONArray commentsJSON = (JSONArray) solutionJSON.get(QUESTION_COMMENTS);
			ArrayList<Comment> comments = getComments(commentsJSON);

			solutions.add(new Solution(id, author, publishTime, score, filename, explanation, verified, comments));
		}
		return solutions;
	}

	private static ArrayList<Comment> getComments(JSONArray commentsJSON) {
		ArrayList<Comment> comments = new ArrayList<Comment>(commentsJSON.size());
		for (Object _commentJSON : commentsJSON) {
			JSONObject commentJSON = (JSONObject) _commentJSON;

			UUID id = UUID.fromString((String) commentJSON.get(QUESTION_COMMENTS_ID));

			UUID authorUUID = UUID.fromString((String) commentJSON.get(QUESTION_COMMENTS_AUTHOR));
			User author = UserList.getInstance().getUser(authorUUID);

			LocalDateTime publishTime = LocalDateTime.parse((String) commentJSON.get(QUESTION_COMMENTS_PUBLISH_TIME));
			int score = (int) commentJSON.get(QUESTION_COMMENTS_SCORE);
			String content = (String) commentJSON.get(QUESTION_COMMENTS_CONTENT);

			JSONArray repliesJSON = (JSONArray) commentJSON.get(QUESTION_COMMENTS);
			ArrayList<Comment> replies = getComments(repliesJSON);

			comments.add(new Comment(id, author, publishTime, score, replies, content));
		}
		return comments;
	}

	private static ArrayList<String> getCategories(JSONArray categoriesJSON) {
		ArrayList<String> categories = new ArrayList<String>(categoriesJSON.size());
		for (Object category : categoriesJSON) {
			categories.add((String) category);
		}
		return categories;
	}

	private static ArrayList<String> getHints(JSONArray hintsJSON) {
		ArrayList<String> hints = new ArrayList<String>(hintsJSON.size());
		for (Object hint : hintsJSON) {
			hints.add((String) hint);
		}
		return hints;
	}
}
