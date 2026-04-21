package com.controllers;

import java.io.IOException;

import com.interview.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CAccountController {

	@FXML
	private Button SignUpButton;

	@FXML
	private TextField txt_email;

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

	@FXML // use fx:id="SignUpButton" should make use of txt_password, txt_username, and
				// txt_email
	private void SignUp() throws IOException {
		if (App.getInterviewApp().isValidPassword(txt_password.getText()) &&
	        App.getInterviewApp().isValidEmail(txt_email.getText()) &&
		    App.getInterviewApp().isValidUsername(txt_username.getText())) {
			App.getInterviewApp().registerUser(txt_email.getText(), txt_username.getText(), txt_password.getText());
			App.setRoot("Login");
		}

	}
}
