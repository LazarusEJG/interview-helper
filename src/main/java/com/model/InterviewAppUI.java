package com.model;

import java.util.Scanner;

public class InterviewAppUI {
	private InterviewApp library;

	InterviewAppUI() {
		library = new InterviewApp();
	}

	public void showOptions() {
		System.out.println("1: Login");
		System.out.println("2: Create account");
		System.out.println("3: Show all questions");
		System.out.println("4: Show all users");
		System.out.println("5: Show my account");
		System.out.println("6: Logout");
		System.out.println("-1: Exit");
	}

	public void run() {
		System.out.println("Welcome to InterviewApp!");
		showOptions();
		Scanner keyboard = new Scanner(System.in);
		String input = keyboard.nextLine();

		while (input.equals("-1") == false) {
			switch(input) {

				case "1": //login
					System.out.println("Enter username:");
					String username = keyboard.nextLine();
					System.out.println("Enter password:");
					String password = keyboard.nextLine();

					if (library.containsUser(username, password)) {
						library.login(username, password);
						System.out.println("Logged in as " + username);
					}

					else {
						System.out.println("Account not found");
					}

					break;

				case "2": //create account
					System.out.println("Enter email:");
					String email = keyboard.nextLine();
					System.out.println("Enter username:");
					username = keyboard.nextLine();
					System.out.println("Enter password:");
					password = keyboard.nextLine();

					if (library.isValidUsername(username) && 
						library.isValidPassword(password) &&
						library.isValidEmail(email)) {
						library.registerUser(email, username, password);
					}
					break;
				
				case "3": //show all questions
					for (Question question : library.getAllQuestions()) {
						System.out.println(question.getTitle());
						System.out.println(question.getContent());
						System.out.println();
					}
					break;
				
				case "4": //show all users
					for (User user : library.getAllUsers()) {
						System.out.println(user.getUsername());
						System.out.println();
					}
					break;
				
				case "5": //show my account
					if (library.isLoggedIn()) {
						System.out.println(library.getCurrentUser().toString());
					}
					break;
				
				case "6": //logout
					library.logout();
					break;
				
			}
			showOptions();
			input = keyboard.nextLine();
		}
		

	}
	public static void main(String[] args) {
		InterviewAppUI libraryInterface = new InterviewAppUI();
		libraryInterface.run();

	}
}
