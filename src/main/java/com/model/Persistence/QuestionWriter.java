package com.model.Persistence;

import com.model.*;
import com.model.Persistence.QuestionDataConstants;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public final class QuestionWriter extends QuestionDataConstants {
	public static void saveQuestions(ArrayList<Question> questions) {
		try {
			JSONArray questionsJSON = new JSONArray();
			for (Question question : questions) {
				JSONObject questionJSON = getQuestionJSON(question);
				questionsJSON.add(questionJSON);
			}

			FileWriter writer = new FileWriter(Paths.get(QUESTION_FILE_PATH, "TEST_" + QUESTION_FILE_NAME).toString());
			questionsJSON.writeJSONString(writer);
			// DEBUG
			System.out.println(questionsJSON.toJSONString());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	private static JSONObject getQuestionJSON(Question question) {
		JSONObject questionJSON = new JSONObject();
		questionJSON.put(QUESTION_ID, question.getId().toString());
		questionJSON.put(QUESTION_TITLE, question.getTitle());
		questionJSON.put(QUESTION_DIFFICULTY, question.getDifficulty());
		questionJSON.put(QUESTION_SCORE, question.getScore());
		questionJSON.put(QUESTION_CONTENT, question.getContent());
		// Categories
		JSONArray categories = new JSONArray();
		for (String category : question.getCategories()) {
			categories.add(category);
		}
		questionJSON.put(QUESTION_CATEGORIES, categories);

		questionJSON.put(QUESTION_AUTHOR, question.getAuthor().toString());
		questionJSON.put(QUESTION_PUBLISH_TIME, question.getPublishTime().toString());
		// Hints
		JSONArray hints = new JSONArray();
		for (String hint : question.getHints()) {
			hints.add(hint);
		}
		questionJSON.put(QUESTION_HINTS, hints);

		// Solutions
		JSONArray solutions = new JSONArray();
		for (Solution solution : question.getSolutions()) {
			solutions.add(getSolutionJSON(solution));
		}
		questionJSON.put(QUESTION_SOLUTIONS, solutions);

		return questionJSON;
	}

	private static JSONObject getSolutionJSON(Solution solution) {
		JSONObject solutionJSON = new JSONObject();
		solutionJSON.put(QUESTION_SOLUTIONS_ID, solution.getId().toString());
		solutionJSON.put(QUESTION_SOLUTIONS_AUTHOR, solution.getAuthor().toString());
		solutionJSON.put(QUESTION_SOLUTIONS_PUBLISH_TIME, solution.getPublishTime().toString());
		solutionJSON.put(QUESTION_SOLUTIONS_SCORE, solution.getScore());
		solutionJSON.put(QUESTION_SOLUTIONS_FILE, solution.getFile());
		solutionJSON.put(QUESTION_SOLUTIONS_EXPLANATION, solution.getExplanation());
		solutionJSON.put(QUESTION_SOLUTIONS_VERIFIED, solution.isVerified());
		// Comments
		JSONArray comments = new JSONArray();
		for (Comment comment : solution.getReplies()) {
			comments.add(getCommentJSON(comment));
		}
		solutionJSON.put(QUESTION_COMMENTS, comments);

		return solutionJSON;
	}

	private static JSONObject getCommentJSON(Comment comment) {
		JSONObject commentJSON = new JSONObject();
		commentJSON.put(QUESTION_COMMENTS_ID, comment.getId().toString());
		commentJSON.put(QUESTION_COMMENTS_AUTHOR, comment.getAuthor().toString());
		commentJSON.put(QUESTION_COMMENTS_PUBLISH_TIME, comment.getPublishTime().toString());
		commentJSON.put(QUESTION_COMMENTS_SCORE, comment.getScore());
		commentJSON.put(QUESTION_COMMENTS_CONTENT, comment.getContent());

		return commentJSON;
	}
}
