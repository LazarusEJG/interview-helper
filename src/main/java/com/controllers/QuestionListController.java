package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.interview.App;
import com.model.InterviewApp;
import com.model.Question;
import com.model.User;
import com.model.UserType;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuestionListController {

	@FXML
	private Button SignInButton;

	@FXML
	private Button addQuestionBtn;

	@FXML
	private VBox questionsContainer;

	@FXML
	public void initialize() throws IOException {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user != null) {
			SignInButton.setText("Log Out");
			if (user.getType() != UserType.CONTRIBUTOR) {
				addQuestionBtn.setVisible(false);
			} else {
				addQuestionBtn.setVisible(true);
			}
		} else {
			addQuestionBtn.setVisible(false);
		}

		ShowQuestions();

	}

	private void ShowQuestions() throws IOException {
		InterviewApp library = App.getInterviewApp();
		// TODO: use filters if any present
		ArrayList<Question> questions = library.getAllQuestions();

		for (Question question : questions) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/QuestionListItem.fxml"));
			Parent root = loader.load();

			QuestionListItemController questionListItem = loader.getController();
			questionListItem.setData(question);

			System.out.println(questionListItem.getClass());

			questionsContainer.getChildren().add(root);
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
	void backToHome(MouseEvent event) throws IOException {
		App.setRoot("Home");
	}

	@FXML
	private void handleAddQuestion() throws IOException {
		App.setRoot("QuestionAdd");
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
