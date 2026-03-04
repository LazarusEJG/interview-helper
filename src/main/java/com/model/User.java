package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages the changes made to users, acquisition of user data, and tracks
 * progression
 * 
 * @author
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
	private ArrayList<UUID> submittedSolutions;
	private ArrayList<UUID> bookmarkedQuestions;
	private ArrayList<UUID> bookmarkedSolutions;
	private ArrayList<String> completedCourses;
	private LocalDate lastStreakDate = null;
	private int receivedVotes;

	/**
	 * 
	 * @param eMail
	 * @param username
	 * @param password
	 */
	public User(String eMail, String username, String password) {

	}

	/**
	 * 
	 * @param id
	 * @param userType
	 * @param eMail
	 * @param username
	 * @param password
	 * @param interests
	 * @param currentStreak
	 * @param longestStreak
	 * @param submittedSolutions
	 * @param bookmarkedQuestions
	 * @param bookmarkedSolutions
	 * @param completedCourses
	 * @param lastStreakDate
	 * @param receivedVotes
	 */
	public User(UUID id, UserType userType, String eMail, String username, String password, ArrayList<String> interests,
			int currentStreak, int longestStreak, ArrayList<UUID> submittedSolutions,
			ArrayList<UUID> bookmarkedQuestions, ArrayList<UUID> bookmarkedSolutions,
			ArrayList<String> completedCourses, LocalDate lastStreakDate, int receivedVotes) {

		this.id = id;
		this.type = userType;
		this.eMail = eMail;
		this.username = username;
		this.password = password;
		this.interests = interests;
		this.currentStreak = currentStreak;
		this.longestStreak = longestStreak;
		this.submittedSolutions = submittedSolutions;
		this.bookmarkedQuestions = bookmarkedQuestions;
		this.bookmarkedSolutions = bookmarkedSolutions;
		this.completedCourses = completedCourses;
		this.lastStreakDate = lastStreakDate;
		this.receivedVotes = receivedVotes;
	}

	/**
	 * 
	 * @param question
	 */
	public void comment(Question question) {

	}

	/**
	 * Tracks the login and increases streak by 1
	 */
	public void incrementStreak() {

	}

	/**
	 * Acquires a user's answered questions
	 * 
	 * @return Returns null
	 */
	public ArrayList<Question> getAnsweredQuestions() {
		return null; // temporary return statement
	}

	/**
	 * 
	 * @param question
	 */
	public void bookmarkQuestion(Question question) {

	}

	/**
	 * 
	 * @param solution
	 */
	public void bookmarkSolution(Solution solution) {

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<UUID> getBookmarkedQuestions() {
		return bookmarkedQuestions;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<UUID> getBookmarkedSolutions() {
		return bookmarkedSolutions;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<UUID> getSubmittedSolutions() {
		return submittedSolutions;
	}

	/**
	 * 
	 */
	public void receiveUpvote() {

	}

	/**
	 * 
	 */
	public void receiveDownvote() {

	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("User <" + username + ">" + "\n");
		sb.append("id: " + id + "\n");
		sb.append("type: " + type + "\n");
		sb.append("eMail: " + eMail + "\n");
		sb.append("username: " + username + "\n");
		sb.append("password: " + password + "\n");
		sb.append("interests: " + "\n");
		for (String interest : interests) {
			sb.append("  " + interest + "\n");
		}
		sb.append("longestStreak: " + longestStreak + "\n");
		sb.append("currentStreak: " + currentStreak + "\n");
		sb.append("submittedSolutions: " + "\n");
		for (UUID solution : submittedSolutions) {
			sb.append("  " + solution + "\n");
		}
		sb.append("bookmarkedQuestions: " + "\n");
		for (UUID question : bookmarkedQuestions) {
			sb.append("  " + question + "\n");
		}
		sb.append("bookmarkedSolutions: " + "\n");
		for (UUID solution : bookmarkedSolutions) {
			sb.append("  " + solution + "\n");
		}
		sb.append("lastStreakDate: " + lastStreakDate + "\n");
		sb.append("receivedVotes: " + receivedVotes + "\n");

		return sb.toString();
	}

	public UUID getId() {
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public UserType getType() {
		return type;
	}

	/**
	 * 
	 * @return
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public ArrayList<String> getInterests() {
		return interests;
	}

	public int getCurrentStreak() {
		return currentStreak;
	}

	public int getLongestStreak() {
		return longestStreak;
	}

	public ArrayList<String> getCompletedCourses() {
		return completedCourses;
	}

	/**
	 * Returns the last streak date
	 * 
	 * @return the last streak date
	 */
	public LocalDate getLastStreakDate() {
		return lastStreakDate;
	}

	public int getReceivedVotes() {
		return receivedVotes;
	}
}
