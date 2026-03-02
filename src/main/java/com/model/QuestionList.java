package com.model;

import com.model.Persistence.QuestionWriter;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;

public class QuestionList {
	private ArrayList<Question> questions;
	private static QuestionList instance;
	private Question dailyQuestion;
	private Question currentQuestion; // added for setCurrentQuestion

	private QuestionList() {
		questions = new ArrayList<Question>();
		instance = this;
	}

	public static QuestionList getInstance() {
		if (instance == null) {
			instance = new QuestionList();
		}
		return instance;
	}

	public Question getDailyQuestion() {
		if (questions == null || questions.size() == 0) {
			return null;
		}
		int index = (int) (LocalDate.now().toEpochDay()) % questions.size();
		return questions.get(index);
	}

	public Question setCurrentQuestion(Question question) {
		this.currentQuestion = question;
		return currentQuestion; // temporary statement
	}

	public boolean addQuestion(User author, String content) {
		questions.add(new Question(author, content));
		return true; // temporary statement
	}

	public void removeQuestion(Question question) {
		questions.remove(question);
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public Question getQuestion(UUID id) {
		return null;
	}

	public Solution getSolution(UUID id) {
		return null;
	}

	public ArrayList<Question> getQuestions(
			ArrayList<String> tagFilter,
			Integer minDifficulty, // Using object not primitive so you can call the method with various null args
			Integer maxDifficulty,
			boolean onlySolved,
			ArrayList<User> authors) {

		ArrayList<Question> filtered = new ArrayList<Question>();

		for (Question question : questions) {
			// Category tags
			if (tagFilter != null) {

			}

			// Difficulty
			if (minDifficulty != null && question.getDifficulty() < minDifficulty) {
				continue;
			}

			if (maxDifficulty != null && question.getDifficulty() > maxDifficulty) {
				continue;
			}

			// Solved
			if (onlySolved && question.getSolutions().size() == 0) { // Needs to be reexamined
				continue;
			}

			// Authors
			if (authors != null && authors.contains(question.getAuthor()) == false) {
				continue;
			}
			filtered.add(question);
		}

		return filtered;
	}

	public void save(String filename) {
		QuestionWriter.saveQuestions(questions);
	}
}
