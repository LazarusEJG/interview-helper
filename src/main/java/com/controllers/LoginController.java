package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {
	@FXML
	private Label errorLabel;

	@FXML
	private Button LoginButton;

	@FXML
	private PasswordField txt_password;

	@FXML
	private TextField txt_username;

	@FXML
	void Login(ActionEvent event) throws IOException {
		String username = txt_username.getText();
		String password = txt_password.getText();

		errorLabel.setText("");

		if (username.isEmpty()) {
			errorLabel.setText("Please enter your username");
			return;
		}

		if (password.isEmpty()) {
			errorLabel.setText("Please enter your password");
			return;
		}

		InterviewApp library = App.getInterviewApp();

		if (library.containsUser(username, password)) {
			library.login(username, password);
			App.setRoot("Profile");
		} else {
			errorLabel.setText("Invalid username or password");
		}

	}

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		App.setRoot("Home");
	}

	@FXML
	void toAccountCreation(MouseEvent event) throws IOException {
		App.setRoot("CAccount");
	}

	@FXML
	private void goToQuestions(MouseEvent event) throws IOException {
		App.setRoot("QuestionList");
	}

}
