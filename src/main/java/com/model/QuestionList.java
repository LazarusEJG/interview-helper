package com.model;

import com.model.Persistence.QuestionWriter;
import com.model.Persistence.QuestionLoader;

import java.util.ArrayList;
import java.util.UUID;
import java.time.LocalDate;

/**
 * Singleton class for managing all questions
 * 
 * @author tsitnik1
 */
public class QuestionList {
	private ArrayList<Question> questions;
	private static QuestionList instance;
	private Question currentQuestion; // added for setCurrentQuestion

	private QuestionList() {
		questions = QuestionLoader.getQuestions();
		instance = this;
	}

	/**
	 * Returns the instance of the QuestionList object. If null, it constructs a new
	 * one
	 * 
	 * @return
	 */
	public static QuestionList getInstance() {
		if (instance == null) {
			instance = new QuestionList();
		}
		return instance;
	}

	/**
	 * Returns the daily question
	 * 
	 * @return A Question selected from the list of questions based on the day
	 */
	public Question getDailyQuestion() {
		if (questions == null || questions.size() == 0) {
			return null;
		}
		int index = (int) (LocalDate.now().toEpochDay()) % questions.size();
		return questions.get(index);
	}

	/**
	 * Sets the current question
	 * 
	 * @param question The question being set
	 */
	public void setCurrentQuestion(Question question) {
		this.currentQuestion = question;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	/**
	 * Adds a question to the question list
	 * 
	 * @param author  The author of the question, must be a contributor
	 * @param content The content of the question
	 * @return True if the method was successful, false otherwise
	 */
	public boolean addQuestion(User author, String title, String content) {
		if (author.getType() == UserType.CONTRIBUTOR) {
			questions.add(new Question(author.getId(), title, content));
			return true;
		}
		return false;
	}

	/**
	 * Removes a question from the list
	 * 
	 * @param question The question being removed
	 */
	public void removeQuestion(Question question) {
		questions.remove(question);
	}

	/**
	 * Gets the ArrayList of questions
	 * 
	 * @return The ArrayList of questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Finds and returns the question with the specified ID
	 * 
	 * @param id The ID being searched for
	 * @return The question with the specified ID
	 */
	public Question getQuestion(UUID id) {
		for (Question question : questions) {
			if (question.getId() == id) {
				return question;
			}
		}
		return null;
	}

	/**
	 * Finds and returns the solution with the specified ID
	 * 
	 * @param id The ID being searched for
	 * @return The solution with the specified ID
	 */
	public Solution getSolution(UUID id) {
		for (Question question : questions) {
			for (Solution solution : question.getSolutions()) {
				if (solution.getId() == id) {
					return solution;
				}
			}
		}
		return null;
	}

	/**
	 * Gets the questions with the specified filters. If no filter, leave as null
	 * 
	 * @param tagFilter     Filter for questions with certain tags
	 * @param minDifficulty Filter for minimum difficulty. Must be Integer()
	 * @param maxDifficulty Filter for maximum difficulty. Must be Integer()
	 * @param onlySolved    Filter for only solved questions
	 * @param authors       Filter for specific authors
	 * @return ArrayList with filtered questions
	 */
	public ArrayList<Question> getQuestions(
			ArrayList<String> tagFilter,
			Integer minDifficulty, // Using object not primitive so you can call the method with various null args
			Integer maxDifficulty,
			boolean onlySolved,
			ArrayList<UUID> authors) {

		ArrayList<Question> filtered = new ArrayList<Question>();

		for (Question question : questions) {
			// Category tags
			if (tagFilter != null) {
				boolean exclude = true;
				for (String tag : tagFilter) {
					if (question.getCategories().contains(tag)) {
						exclude = false;
						break;
					}
				}
				if (exclude) {
					continue;
				}
				// Work on later
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
			if (authors != null && authors.size() > 0 && authors.contains(question.getAuthor()) == false) {
				continue;
			}
			filtered.add(question);
		}

		return filtered;
	}

	/**
	 * Saves the questions to the JSON file
	 */
	public void save() {
		QuestionWriter.saveQuestions(questions);
	}
}
