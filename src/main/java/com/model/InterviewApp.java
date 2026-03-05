package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.*;

/**
 * Facade class for the Interview Helper system.
 * Provides simplified access to questions, users,
 * solutions, and comments.
 */
public class InterviewApp {

	private User currentUser;
	private Question currentQuestion;

	public InterviewApp() {
	}

	public InterviewApp(User user) {
		this.currentUser = user;
	}

	public User login(String username, String password) {
		currentUser = UserList.getInstance().getUser(username, password);
		return currentUser;
	}

	boolean isValidUsername(String username) {
		return true;
	}

	boolean isValidPassword(String password) {
		return true;
	}

	boolean isValidEmail(String email) {
		Pattern pattern = Pattern.compile("/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/");
		return email.matches(email);
	}

	boolean containsUser(String username, String password) {
		return true;
	}

	public boolean logout() {

		if (currentUser != null) {
			currentUser = null;
			return true;
		}
		return false;
	}

	public void registerUser(String eMail, String username, String password) {
		UserList.getInstance().addUser(eMail, username, password);
	}

	public void addQuestion(Question question) {
		QuestionList.getInstance().addQuestion(getUser(question.getAuthor()), question.getContent());
	}

	public User getUser(UUID id) {
		return UserList.getInstance().getUser(id);
	}

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

	public Question getDailyQuestion() {
		return QuestionList.getInstance().getDailyQuestion();
	}

	// Sets current question to a question.
	public void setCurrentQuestion(Question question) {
		QuestionList.getInstance().setCurrentQuestion(question);
	}

	public void bookmarkQuestion(Question question) {

		if (currentUser != null) {
			currentUser.bookmarkQuestion(question);
		}
	}

	// Getter for Bookmarked Questions for the current user.
	public ArrayList<UUID> getBookmarkedQuestions(User currentUser) {
		return currentUser.getBookmarkedQuestions();
	}

	public ArrayList<Question> getAnsweredQuestions(User currentUser) {
		return currentUser.getAnsweredQuestions();
	}

	public String getHints() {

		if (currentQuestion != null &&
				currentQuestion.getHints() != null &&
				!currentQuestion.getHints().isEmpty()) {

			return currentQuestion.getHints().get(0);
		}

		return "";
	}

	public void addSolution(User currentUser, Solution solution) {

		if (currentQuestion != null) {
			currentQuestion.addSolution(currentUser, solution);
		}
	}

	public ArrayList<Solution> getSolutions() {

		if (currentQuestion == null)
			return new ArrayList<>();

		ArrayList<Solution> solutions = currentQuestion.getSolutions();

		return solutions != null ? solutions : new ArrayList<>();
	}

	// Getter for bookmarked solutions.
	public ArrayList<UUID> getBookmarkedSolutions(User currentUser) {
		return currentUser.getBookmarkedSolutions();
	}

	// Getter for submitted solutions.
	public ArrayList<UUID> getSubmittedSolutions(User currentUser) {
		return currentUser.getSubmittedSolutions();
	}

	public void addComment(Commentable parent, Comment comment) {
		parent.addComment(comment);
	}

	public void report(Response response) {
		response.report();
	}

	public void upvote(Commentable content) {
		content.upVote();
	}

	public void downvote(Commentable content) {
		content.downVote();
	}

	public void close() {
		UserList.getInstance().save();
		QuestionList.getInstance().save();
	}
}
