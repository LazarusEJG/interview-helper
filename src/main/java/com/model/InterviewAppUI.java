package com.model;

import java.util.Scanner;

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

		System.out.println("Playboi Carti is now logged in");
		System.out.println(user.toString());

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

		library.close();
	}

	public static void main(String[] args) {
		InterviewAppUI libraryInterface = new InterviewAppUI();
		libraryInterface.run();

	}
}
