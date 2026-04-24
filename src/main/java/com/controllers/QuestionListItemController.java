package com.controllers;

import java.io.IOException;
import java.util.UUID;

import com.interview.App;
import com.model.InterviewApp;
import com.model.Question;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class QuestionListItemController {
	@FXML
	private Label title;

	@FXML
	private Label text;

	@FXML
	private Label author;

	@FXML
	private Label difficulty;

	@FXML
	private Button answerButton;

	private Question question;

	// @FXML
	// public void initilize() {
	// }

	public void setData(Question question) {
		InterviewApp library = App.getInterviewApp();

		title.setText(question.getTitle());
		System.out.println(question.getTitle());
		text.setText(question.getContent());
		author.setText(library.getUser(question.getAuthor()).getUsername());
		difficulty.setText("Difficulty: " + question.getDifficulty() + "/5");

		this.question = question;
	}

	@FXML
	public void openQuestion() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/SelectedQuestion.fxml"));

		Parent root = loader.load();
		SelectedQuestionController controller = loader.getController();

		controller.setData(question);

		Scene scene = title.getScene();
		scene.setRoot(root);
	}
}
