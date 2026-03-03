package com.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

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

	public User(String eMail, String username, String password) {

	}

	public User(UUID id, UserType userType, String eMail, String username, String password, ArrayList<String> interests,
			int currentStreak, int longestStreak, ArrayList<Solution> submittedSolutions,
			ArrayList<Question> bookmarkedQuestions,
			ArrayList<Solution> bookmarkedSolutions, ArrayList<String> completedCourses, LocalDate lastStreakDate,
			int receivedVotes) {

	}

	public void comment(Question question) {

	}

	public void incrementStreak() {

	}

	public ArrayList<Question> getAnsweredQuestions() {
		return null; // temporary return statement
	}

	public void bookmarkQuestion(Question question) {

	}

	public void bookmarkSolution(Solution solution) {

	}

	public ArrayList<Question> getBookmarkedQuestions() {
		return null; // temporary return statement
	}

	public ArrayList<Solution> getBookmarkedSolutions() {
		return null; // temporary return statement
	}

	public ArrayList<Solution> getSubmittedSolutions() {
		return null; // temporary return statement
	}

	public void receiveUpvote() {

	}

	public void receiveDownvote() {

	}

	public UUID getId() {
		return id;
	}

	public UserType getType() {
		return type;
	}

	public String geteMail() {
		return eMail;
	}

	public String getUsername() {
		return username;
	}

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

	public LocalDate getLastStreakDate() {
		return lastStreakDate;
	}

	public int getReceivedVotes() {
		return receivedVotes;
	}
}
