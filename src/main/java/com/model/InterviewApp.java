package com.model;

import java.util.ArrayList;

/**
 * Facade class for the Interview Helper system.
 * Provides simplified access to questions, users,
 * solutions, and comments.
 */
public class InterviewApp {

	private User currentUser;
	private Question currentQuestion;

	public InterviewApp() { }

	public InterviewApp(User user) {
		this.currentUser = user;
	}

	public User login(String username, String password) {
		currentUser = UserList.getInstance().getUser(username, password);
		return currentUser;
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
        QuestionList.getInstance().getQuestions().add(question);
    }

	public ArrayList<Question> getQuestions() {
		return QuestionList.getInstance().getQuestions();
	}

	public ArrayList<Question> getQuestions(
			ArrayList<String> tagFilter,
			Integer minDifficulty,
			Integer maxDifficulty,
			boolean onlySolved,
			ArrayList<User> authors) {

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

	public Question setCurrentQuestion(Question question) {

		QuestionList.getInstance().setCurrentQuestion(question);
		currentQuestion = question;

		return currentQuestion;
	}

	public void bookmarkQuestion(Question question) {

		if (currentUser != null) {
			currentUser.bookmarkQuestion(question);
		}
	}

	public ArrayList<Question> getBookmarkedQuestions(User currentUser) {
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

	public ArrayList<Solution> getBookmarkedSolutions(User currentUser) {
		return currentUser.getBookmarkedSolutions();
	}

	public ArrayList<Solution> getSubmittedSolutions(User currentUser) {
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
}