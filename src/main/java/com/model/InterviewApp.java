package com.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Facade class for the Interview Helper system.
 * Provides simplified access to questions, users,
 * solutions, and comments.
 */
public class InterviewApp {

	private User currentUser;
	private ArrayList<Question> searchResults;
	private static final String SPECIALS = "!@#$%^&?*";
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

	public InterviewApp() {
	}

	public InterviewApp(User user) {
		this.currentUser = user;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public Question getCurrentQuestion() {
		return QuestionList.getInstance().getCurrentQuestion();
	}

	public ArrayList<Question> getSearchResults() {
		return searchResults;
	}

	public User login(String username, String password) {
		currentUser = UserList.getInstance().getUser(username, password);
		if (currentUser != null) {
			currentUser.incrementStreak();
		}
		return currentUser;
	}

	public boolean isLoggedIn() {
		return (currentUser != null);
	}

	boolean isValidUsername(String username) {
		boolean hasLetter = false;
		boolean hasNumber = false;

		if (username.length() < 6 || username.length() > 12 || this.getUserFromUsername(username) != null)
			return false;

		for (char c : username.toCharArray()) {
			if (Character.isAlphabetic(c))
				hasLetter = true;
			else if (Character.isDigit(c))
				hasNumber = true;
			else
				return false;
		}

		return (hasLetter && hasNumber);

	}

	boolean isValidPassword(String password) {
		boolean hasLetter = false;
		boolean hasNumber = false;
		boolean hasSpecial = false;

		if (password.length() < 8)
			return false;

		for (char c : password.toCharArray()) {
			if (Character.isAlphabetic(c))
				hasLetter = true;
			else if (Character.isDigit(c))
				hasNumber = true;
			if (hasSpecial == false) {
				for (char v : SPECIALS.toCharArray()) {
					if (c == v) {
						hasSpecial = true;
					}
				}
			}
		}
		return (hasLetter && hasNumber && hasSpecial);
	}

	boolean isValidEmail(String email) {
		return EMAIL_PATTERN.matcher(email).find();
	}

	public boolean containsUser(String username, String password) {
		return UserList.getInstance().containsUser(username, password);
	}

	public ArrayList<User> getAllUsers() {
		return UserList.getInstance().getUsers();
	}

	public User getUserFromUsername(String username) {
		return UserList.getInstance().getUserFromUsername(username);
	}

	public boolean logout() {

		if (currentUser != null) {
			currentUser = null;
			return true;
		}
		return false;
	}

	public void registerUser(String eMail, String username, String password) {
		if (containsUser(username, password)) {
			return;
		}
		UserList.getInstance().addUser(eMail, username, password);
	}

	public void addQuestion(User author, String title, String content) {
		QuestionList.getInstance().addQuestion(author, title, content);
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

		searchResults = QuestionList.getInstance().getQuestions(
				tagFilter,
				minDifficulty,
				maxDifficulty,
				onlySolved,
				authors);

		return searchResults;
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
	public ArrayList<UUID> getBookmarkedQuestions() {
		return currentUser.getBookmarkedQuestions();
	}

	public ArrayList<Question> getAnsweredQuestions() {
		return currentUser.getAnsweredQuestions();
	}

	/**
	 * Calls upon getQuestions in QuestionList
	 * 
	 * @return returns all questions
	 */
	public ArrayList<Question> getAllQuestions() {
		searchResults = QuestionList.getInstance().getQuestions();
		return searchResults;
	}

	public String getHints() {
		Question currentQuestion = QuestionList.getInstance().getCurrentQuestion();

		if (currentQuestion != null &&
				currentQuestion.getHints() != null &&
				!currentQuestion.getHints().isEmpty()) {

			return currentQuestion.getHints().get(0);
		}

		return "";
	}

	public void addSolution(String explanation, String filename) {
		Question currentQuestion = QuestionList.getInstance().getCurrentQuestion();

		if (currentQuestion != null) {
			currentQuestion.addSolution(currentUser, explanation, filename);
		}
	}

	public ArrayList<Solution> getSolutions() {
		Question currentQuestion = QuestionList.getInstance().getCurrentQuestion();

		if (currentQuestion == null)
			return new ArrayList<>();

		ArrayList<Solution> solutions = currentQuestion.getSolutions();

		return solutions != null ? solutions : new ArrayList<>();
	}

	// Getter for bookmarked solutions.
	public ArrayList<UUID> getBookmarkedSolutions() {
		return currentUser.getBookmarkedSolutions();
	}

	// Getter for submitted solutions.
	public ArrayList<UUID> getSubmittedSolutions() {
		return currentUser.getSubmittedSolutions();
	}

	public void addComment(Commentable parent, UUID author, String content) {
		parent.addComment(new Comment(author, content));
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
