package com.model;

import java.util.ArrayList;
import java.util.UUID;

public class InterviewApp {

	private User currentUser;
	private Question currentQuestion;

	public InterviewApp() {
	}

	// Constructor 2
	public InterviewApp(User user) {
		this.currentUser = user;
	}

	// Methods Stubs

	// Sets Current User to a user for given list and returns the user.
	public User login(String username, String password) {
		currentUser = UserList.getInstance().getUser(username, password);
		return currentUser;
	}

	// Checks if there is a current user and logs out by setting current user to
	// null. Returns true if logout is successful, false otherwise.
	public boolean logout() {
		if (currentUser != null) {
			currentUser = null;
			return true;
		}
		return false;
	}

	// Registers a new user by adding them to list with email, username and
	// password.
	public void registerUser(String eMail, String username, String password) {
		UserList.getInstance().addUser(eMail, username, password);
	}

	// Adds Question to QuestionList.getInstance() with author and content.
	public void addQuestion(Question question) {
		QuestionList.getInstance().addQuestion(getUser(question.getAuthor()), question.getContent());
	}

	public User getUser(UUID id) {
		return UserList.getInstance().getUser(id);
	}

	// Overloaded getQuestions method that takes in filters and returns a list of
	// questions that match the filters.
	public ArrayList<Question> getQuestions(
			ArrayList<String> tagFilter,
			Integer minDifficulty,
			Integer maxDifficulty,
			boolean onlySolved,
			ArrayList<UUID> authors) {

		return QuestionList.getInstance().getQuestions(
				tagFilter,
				minDifficulty,
				maxDifficulty,
				onlySolved,
				authors);
	}

	// getter for question list
	public ArrayList<Question> getQuestions() {
		return QuestionList.getInstance().getQuestions();
	}

	// getter for daily question
	public Question getDailyQuestion() {
		return QuestionList.getInstance().getDailyQuestion();
	}

	// Sets current question to a question.
	public void setCurrentQuestion(Question question) {
		QuestionList.getInstance().setCurrentQuestion(question);
	}

	// Checks if there is a current user and bookmarks a question for the user.
	public void bookmarkQuestion(Question question) {
		if (currentUser != null) {
			currentUser.bookmarkQuestion(question);
		}
	}

	// Getter for Bookmarked Questions for the current user.
	public ArrayList<UUID> getBookmarkedQuestions(User currentUser) {
		return currentUser.getBookmarkedQuestions();
	}

	// Getter for Answered Questions for the current user.
	public ArrayList<Question> getAnsweredQuestions(User currentUser) {
		return currentUser.getAnsweredQuestions();
	}

	// Checks if there is a currentQuestion and if the question has hints.
	// Returns the first hint. Otherwise, returns an empty string.
	public String getHints() {
		if (currentQuestion != null && !currentQuestion.getHints().isEmpty()) {
			return currentQuestion.getHints().get(0);
		}
		return "";
	}

	// Checks if there is a currentQuestion and adds a solution
	// with currentUser and the solution.
	public void addSolution(User currentUser, Solution solution) {
		if (this.currentQuestion != null) {
			this.currentQuestion.addSolution(currentUser, solution);
		}
	}

	// Checks for currentQuestion and returns the list of solutions.
	// If there is no currentQuestion, returns an empty list.
	public ArrayList<Solution> getSolutions() {
		if (currentQuestion != null) {
			return currentQuestion.getSolutions();
		}
		return new ArrayList<>();
	}

	// Getter for bookmarked solutions.
	public ArrayList<UUID> getBookmarkedSolutions(User currentUser) {
		return currentUser.getBookmarkedSolutions();
	}

	// Getter for submitted solutions.
	public ArrayList<UUID> getSubmittedSolutions(User currentUser) {
		return currentUser.getSubmittedSolutions();
	}

	// Adds comment to the parent content (question or solution) using a comment.
	public void addComment(Commentable parent, Comment comment) {
		parent.addComment(comment);
	}

	// Changed from UML to take in Response to uphold the facade design.
	public void report(Response response) {
		response.report();
	}

	// Adds an upvote to the content
	public void upvote(Commentable content) {
		content.upVote();
	}

	// Downvotes given content.
	public void downvote(Commentable content) {
		content.downVote();
	}

	public void close() {
		UserList.getInstance().save();
		QuestionList.getInstance().save();
	}
}
