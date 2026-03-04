package com.model;

import java.util.Scanner;
import java.util.UUID;

public class InterviewAppUI {
	private InterviewApp library;

	InterviewAppUI() {
		library = new InterviewApp();
	}

	public void run() {
		scenario1();
		scenario2();
	}

	public void scenario1() {
		System.out.println();
		User user = library.login("playboicarti", "WholeLottaRed123");
		if (user == null) {
			System.out.println("Sorry we couldn't login.");
			return;
		}

		System.out.println("Playboicarti is now logged in");
		System.out.println(user.toString());

		System.out.println();

		System.out.println(user.getUsername() + "'s published the questions: ");
		for (UUID id : user.getSubmittedSolutions()) {
			System.out.println(QuestionList.getInstance().getQuestion(id));
		}

		library.close();
	}

	public void scenario2() {
		System.out.println();
		User user = library.login("travisscott", "feinfeinfein123");

		if (user == null) {
			System.out.println("Sorry we couldn't login.");
			return;
		}

		System.out.println("Travis Scott is now logged in");
		System.out.println(user.toString());

		System.out.println();

		System.out.println(user.getUsername() + "'s published the questions: ");
		for (UUID id : user.getSubmittedSolutions()) {
			System.out.println(QuestionList.getInstance().getQuestion(id));
		}

		library.close();
	}

	public static void main(String[] args) {
		InterviewAppUI libraryInterface = new InterviewAppUI();
		libraryInterface.run();

	}
}
