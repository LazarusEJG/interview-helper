package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Manages the changes made to users, acquisition of user data, and tracks progression
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
	private ArrayList<Solution> submittedSolutions;
	private ArrayList<Question> bookmarkedQuestions;
	private ArrayList<Solution> bookmarkedSolutions;
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
			int currentStreak, int longestStreak, ArrayList<Solution> submittedSolutions,
			ArrayList<Question> bookmarkedQuestions,
			ArrayList<Solution> bookmarkedSolutions, ArrayList<String> completedCourses, LocalDate lastStreakDate,
			int receivedVotes) {

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
	public ArrayList<Question> getBookmarkedQuestions() {
		return null; // temporary return statement
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Solution> getBookmarkedSolutions() {
		return null; // temporary return statement
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Solution> getSubmittedSolutions() {
		return null; // temporary return statement
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
	 * @return the last streak date
	 */
	public LocalDate getLastStreakDate() {
		return lastStreakDate;
	}

	public int getReceivedVotes() {
		return receivedVotes;
	}
}
