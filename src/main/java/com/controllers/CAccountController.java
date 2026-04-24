package com.controllers;

import java.io.IOException;

import com.interview.App;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CAccountController {

	@FXML
	private Button SignUpButton;

	@FXML
	private TextField txt_email;

	@FXML
	private Label errorLabel;

	@FXML
	private PasswordField txt_password;

	@FXML
	private TextField txt_username;

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		App.setRoot("Home");
	}

	@FXML
	private void goToQuestions(MouseEvent event) throws IOException {
		App.setRoot("QuestionList");
	}

	@FXML
	private void SignUp(ActionEvent event) throws IOException {

		errorLabel.setText("");

		String username = txt_username.getText();
		String password = txt_password.getText();
		String email = txt_email.getText();

		if (email.isEmpty() || App.getInterviewApp().isValidEmail(email) == false) {
			errorLabel.setText("Enter a valid email");
			return;
		}

		if (username.isEmpty() || App.getInterviewApp().isValidUsername(username) == false) {
			errorLabel.setText("Enter a valid username\nUsername must:\n-Be unique 6-12 characters\n-Contain at least 1 number and 1 letter");
			return;
		}

		if (password.isEmpty() || App.getInterviewApp().isValidPassword(password) == false) {
			errorLabel.setText("Enter a valid password\nPassword must:\n-Be at least 8 characters\n-Contain at least 1 number, 1 letter, and 1 special character (!@#$%^&?*)");
			return;
		}

		if (App.getInterviewApp().isValidPassword(password) &&
	        App.getInterviewApp().isValidEmail(email) &&
		    App.getInterviewApp().isValidUsername(username)) {
			App.getInterviewApp().registerUser(email, username, password); //help
			App.setRoot("Login");
		}
	}
}
