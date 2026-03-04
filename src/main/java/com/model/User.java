package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents a user in the Interview Helper system.
 * Handles user data, bookmarks, submitted solutions,
 * login streaks, and vote tracking.
 */
public class User {

	private UUID id;
	private UserType type;
	private String eMail;
	private String username;
	private String password;

	private ArrayList<String> interests;
	private int currentStreak;
	private int longestStreak;

	private ArrayList<Solution> submittedSolutions;
	private ArrayList<Question> bookmarkedQuestions;
	private ArrayList<Solution> bookmarkedSolutions;

	private ArrayList<String> completedCourses;

	private LocalDate lastStreakDate = null;

	private int receivedVotes;

	/**
	 * Creates a newly registered user.
	 * @param eMail The user's email
	 * @param username The username
	 * @param password The password
	 */
	public User(String eMail, String username, String password) {

		this.id = UUID.randomUUID();
		this.type = UserType.REGISTERED;

		this.eMail = eMail;
		this.username = username;
		this.password = password;

		this.interests = new ArrayList<>();
		this.submittedSolutions = new ArrayList<>();
		this.bookmarkedQuestions = new ArrayList<>();
		this.bookmarkedSolutions = new ArrayList<>();
		this.completedCourses = new ArrayList<>();

		this.currentStreak = 0;
		this.longestStreak = 0;
		this.receivedVotes = 0;
	}

	/**
	 * Creates a fully initialized user.
	 */
	public User(UUID id, UserType userType, String eMail, String username,
				String password, ArrayList<String> interests,
				int currentStreak, int longestStreak,
				ArrayList<Solution> submittedSolutions,
				ArrayList<Question> bookmarkedQuestions,
				ArrayList<Solution> bookmarkedSolutions,
				ArrayList<String> completedCourses,
				LocalDate lastStreakDate,
				int receivedVotes) {

		this.id = id;
		this.type = userType;
		this.eMail = eMail;
		this.username = username;
		this.password = password;

		this.interests = interests != null ? interests : new ArrayList<>();
		this.submittedSolutions = submittedSolutions != null ? submittedSolutions : new ArrayList<>();
		this.bookmarkedQuestions = bookmarkedQuestions != null ? bookmarkedQuestions : new ArrayList<>();
		this.bookmarkedSolutions = bookmarkedSolutions != null ? bookmarkedSolutions : new ArrayList<>();
		this.completedCourses = completedCourses != null ? completedCourses : new ArrayList<>();

		this.currentStreak = currentStreak;
		this.longestStreak = longestStreak;
		this.lastStreakDate = lastStreakDate;
		this.receivedVotes = receivedVotes;
	}

	/**
	 * Allows a user to comment on a question.
	 * @param question The question being commented on
	 */
	public void comment(Question question) {
		// handled through InterviewApp
	}

	/**
	 * Updates the user's login streak.
	 */
	public void incrementStreak() {

		LocalDate today = LocalDate.now();

		if (lastStreakDate == null) {
			currentStreak = 1;
		}
		else if (lastStreakDate.plusDays(1).equals(today)) {
			currentStreak++;
		}
		else if (!lastStreakDate.equals(today)) {
			currentStreak = 1;
		}

		lastStreakDate = today;

		if (currentStreak > longestStreak) {
			longestStreak = currentStreak;
		}
	}

	/**
	 * Returns questions answered by the user.
	 * @return List of answered questions
	 */
	public ArrayList<Question> getAnsweredQuestions() {
		return new ArrayList<>();
	}

	/**
	 * Adds a question to bookmarks.
	 * @param question Question to bookmark
	 */
	public void bookmarkQuestion(Question question) {
		if (question != null && !bookmarkedQuestions.contains(question)) {
			bookmarkedQuestions.add(question);
		}
	}

	/**
	 * Adds a solution to bookmarks.
	 * @param solution Solution to bookmark
	 */
	public void bookmarkSolution(Solution solution) {
		if (solution != null && !bookmarkedSolutions.contains(solution)) {
			bookmarkedSolutions.add(solution);
		}
	}

	/**
	 * Gets bookmarked questions.
	 */
	public ArrayList<Question> getBookmarkedQuestions() {
		return bookmarkedQuestions;
	}

	/**
	 * Gets bookmarked solutions.
	 */
	public ArrayList<Solution> getBookmarkedSolutions() {
		return bookmarkedSolutions;
	}

	/**
	 * Gets submitted solutions.
	 */
	public ArrayList<Solution> getSubmittedSolutions() {
		return submittedSolutions;
	}

	/**
	 * Increases received vote count.
	 */
	public void receiveUpvote() {
		receivedVotes++;
	}

	/**
	 * Decreases received vote count.
	 */
	public void receiveDownvote() {
		receivedVotes--;
	}

	/**
	 * Returns a string representation of the user.
	 * 
	 * @return String representation of the user
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("User <" + username + ">");
		sb.append("id: " + id);
		sb.append("type: " + type);
		sb.append("eMail: " + eMail);
		sb.append("username: " + username);
		sb.append("password: " + password);
		sb.append("interests: ");
		for (String interest : interests) {
			sb.append("  " + interest);
		}
		sb.append("longestStreak: " + longestStreak);
		sb.append("currentStreak: " + currentStreak);
		sb.append("submittedSolutions: ");
		for (Solution solution : submittedSolutions) {
			sb.append("  " + solution);
		}
		sb.append("bookmarkedQuestions: ");
		for (Question question : bookmarkedQuestions) {
			sb.append("  " + question);
		}
		sb.append("bookmarkedSolutions: ");
		for (Solution solution : bookmarkedSolutions) {
			sb.append("  " + solution);
		}
		sb.append("lastStreakDate: " + lastStreakDate);
		sb.append("receivedVotes: " + receivedVotes);

		return sb.toString();

	}

	/**
 * Returns the unique ID of the user.
 * @return The user's UUID
 */
	public UUID getId() {
    return id;
	}

	/**
 	* Returns the type of user.
 	* @return The user's type (REGISTERED or CONTRIBUTOR)
 	*/
	public UserType getType() {
   		return type;
	}

	/**
 	* Returns the user's email address.
 	* @return The user's email
 	*/
	public String geteMail() {
    	return eMail;
	}

	/**
 	* Returns the user's username.
 	* @return The username
 	*/
	public String getUsername() {
    	return username;
	}

	/**
 	* Returns the user's password.
 	* @return The user's password
 	*/
	public String getPassword() {
    	return password;
	}

	/**
 	* Returns the list of user interests.
 	* @return ArrayList of interests
 	*/
	public ArrayList<String> getInterests() {
    	return interests;
	}

	/**
 	* Returns the user's current login streak.
 	* @return The current streak value
 	*/
	public int getCurrentStreak() {
    	return currentStreak;
	}

	/**
 	* Returns the user's longest login streak.
 	* @return The longest streak value
 	*/
	public int getLongestStreak() {
    	return longestStreak;
	}

	/**
 	* Returns the courses completed by the user.
 	* @return ArrayList of completed courses
 	*/
	public ArrayList<String> getCompletedCourses() {
    	return completedCourses;
	}

	/**
 	* Returns the last date the user logged in for streak tracking.
 	* @return The last streak date
 	*/
	public LocalDate getLastStreakDate() {
    	return lastStreakDate;
	}

	/**
 	* Returns the total votes received by the user.
 	* @return The number of received votes
 	*/
	public int getReceivedVotes() {
    	return receivedVotes;
	}
}