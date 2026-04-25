package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class QuestionAddController {

	@FXML
	private TextField titleField;

	@FXML
	private TextField descriptionField;

	@FXML
	private TextArea contentArea;

	@FXML
	private Button submitBtn;

	@FXML
	private Label errorLabel;

	@FXML
	private void Submit(ActionEvent event) throws IOException {
		User currentUser = App.getInterviewApp().getCurrentUser();
		String title = titleField.getText();
		String desc = descriptionField.getText();
		String content = contentArea.getText();

		if (title.isBlank() || title.isEmpty()) {
			errorLabel.setText("Enter a title");
			return;
		}

		if (desc.isBlank() || desc.isEmpty()) {
			errorLabel.setText("Enter a description");
			return;
		}

		if (content.isBlank() || content.isEmpty()) {
			errorLabel.setText("Enter the content of your quesiton");
			return;
		}

		App.getInterviewApp().addQuestion(currentUser, title, desc, content);
		App.setRoot("QuestionList");
	}

	@FXML
	private void goToLogin() throws IOException {
		InterviewApp library = App.getInterviewApp();
		library.logout();
		App.setRoot("Login");
	}

	@FXML
	void backToHome(MouseEvent event) throws IOException {
		App.setRoot("Home");
	}

	@FXML
	private void goToQuestions(MouseEvent event) throws IOException {
		App.setRoot("QuestionList");
	}

	@FXML
	void goToProfile(MouseEvent event) throws IOException {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user == null) {
			App.setRoot("Login");
		} else {
			App.setRoot("Profile");
		}
	}
}
