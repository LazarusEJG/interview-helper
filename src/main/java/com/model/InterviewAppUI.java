package com.model;

import java.util.Scanner;

public class InterviewAppUI {
	private InterviewApp library;
	private Scanner keyboard;

	InterviewAppUI() {
		library = new InterviewApp();
	}

	class Options {
		public static final int LOGIN = 1;
		public static final int CREATE_ACCOUNT = 2;
		public static final int SHOW_ALL_QUESTIONS = 3;
		public static final int SHOW_ALL_USERS = 4;
		public static final int SHOW_MY_ACCOUNT = 5;
		public static final int LOGOUT = 6;
		public static final int VIEW_CURRENT_QUESTION = 7;
		public static final int VIEW_CURRENT_QUESTION_COMMENTS = 8;
		public static final int ADD_COMMENT_TO_QUESTION = 9;
		public static final int REPLY_TO_COMMENT = 10;

		public static final int EXIT = -1;
		public static final int INVALID = 0;
	}

	public void showOptions(boolean currentQuestion, boolean loggedIn) {
		System.out.println(Options.LOGIN + ": Login");
		System.out.println(Options.CREATE_ACCOUNT + ": Create account");
		System.out.println(Options.SHOW_ALL_USERS + ": Show all users");
		if (loggedIn) {
			System.out.println(Options.SHOW_MY_ACCOUNT + ": Show my account");
			System.out.println(Options.LOGOUT + ": Logout");
		}
		System.out.println("----------------------------------------");
		System.out.println(Options.SHOW_ALL_QUESTIONS + ": Show all questions");
		if (currentQuestion) {
			System.out.println(Options.VIEW_CURRENT_QUESTION + ": View current question");
			System.out.println(Options.VIEW_CURRENT_QUESTION_COMMENTS + ": View current question comments");
			System.out.println(Options.ADD_COMMENT_TO_QUESTION + ": Add comment to question");
			System.out.println(Options.REPLY_TO_COMMENT + ": Reply to comment");
			// System.out.println(Options. + ": ");
		}
		System.out.println(Options.EXIT + ": Exit");
	}

	public int getOption() {
		String input = keyboard.nextLine();
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public void run() {
		System.out.println("Welcome to InterviewApp!");
		showOptions(library.getCurrentQuestion() != null, library.getCurrentUser() != null);
		keyboard = new Scanner(System.in);
		int option = getOption();

		while (option != Options.EXIT) {
			switch (option) {

				case Options.LOGIN:
					System.out.println("Enter username:");
					String username = keyboard.nextLine();
					System.out.println("Enter password:");
					String password = keyboard.nextLine();

					if (library.containsUser(username, password)) {
						library.login(username, password);
						System.out.println("Logged in as " + username);
					} else {
						System.out.println("Account not found");
					}

					break;

				case Options.CREATE_ACCOUNT:
					System.out.println("Enter email:");
					String email = keyboard.nextLine();
					System.out.println("Enter username:");
					username = keyboard.nextLine();
					System.out.println("Enter password:");
					password = keyboard.nextLine();

					if (library.isValidEmail(email) == false) {
						System.out.println("Invalid email");
					}
					if (library.isValidUsername(username) == false) {
						System.out.println("Invalid username");
					}
					if (library.isValidPassword(password) == false) {
						System.out.println("Invalid password");
					}

					if (library.isValidEmail(email) && library.isValidPassword(password) &&
							library.isValidUsername(username))
						library.registerUser(email, username, password);

					break;

				case Options.SHOW_ALL_QUESTIONS:
					for (Question question : library.getAllQuestions()) {
						System.out.println(question.getTitle());
						System.out.println(question.getContent());
						System.out.println();
					}
					break;

				case Options.SHOW_ALL_USERS:
					for (User user : library.getAllUsers()) {
						System.out.println(user.getUsername());
						System.out.println();
					}
					break;

				case Options.SHOW_MY_ACCOUNT:
					if (library.isLoggedIn()) {
						System.out.println(library.getCurrentUser().toString());
					} else {
						System.out.println("Please login to see your account");
					}
					break;

				case Options.LOGOUT:
					if (library.logout() == true) {
						System.out.println("Logout successful");
					} else {
						System.out.println("Logout failed or not logged in");
					}
					break;
			}

			showOptions(library.getCurrentQuestion() != null, library.getCurrentUser() != null);
			option = getOption();
		}
	}

	public static void main(String[] args) {
		InterviewAppUI libraryInterface = new InterviewAppUI();
		libraryInterface.run();

	}
}
