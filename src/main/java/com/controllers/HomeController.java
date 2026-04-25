package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class HomeController {

	@FXML
	private Button SignInButton;

	@FXML
	private Button SignInButton2;

	@FXML
	public void initialize() throws IOException {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user != null) {
			SignInButton.setText("Log Out");
			SignInButton2.setText("Profile");
		} else {
			SignInButton.setText("Sign In");
			SignInButton2.setText("Sign In");
		}
	}

	@FXML
	private void goToLogin() throws IOException {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user != null) {
			library.logout();
			App.setRoot("Login");
		} else {
			App.setRoot("Login");
		}
	}

	@FXML
	private void goToLogin2() throws IOException {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user != null) {
			App.setRoot("Profile");
		} else {
			library.logout();
			App.setRoot("Login");
		}
	}

	@FXML
	private void goToQuestionList() throws IOException {
		App.setRoot("QuestionList");
	}

	@FXML
	private void goToQuestions(MouseEvent event) throws IOException {
		App.setRoot("QuestionList");
	}
}
