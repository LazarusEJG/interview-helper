package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.interview.App;
import com.model.InterviewApp;
import com.model.Question;
import com.model.User;
import com.model.UserType;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class QuestionListController {
	@FXML
	private ComboBox minDifficultyCombo;

	@FXML
	private ComboBox maxDifficultyCombo;

	@FXML
	private TextField authorFilterField;

	@FXML
	private Button SignInButton;

	@FXML
	private Button addQuestionBtn;

	@FXML
	private VBox questionsContainer;

	private ArrayList<String> authorFilter;
	private int minDifficulty = 1;
	private int maxDifficulty = 5;

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

		for (int i = 1; i <= 5; i++) {
			minDifficultyCombo.getItems().add(String.valueOf(i));
		}

		minDifficultyCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue obs, String oldValue, String newValue) {
				if (newValue != null) {
					minDifficulty = Integer.parseInt(newValue);
					System.out.println("min: " + newValue);
					try {
						showQuestions();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		minDifficultyCombo.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue obs, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					return;
				}

				ArrayList<String> options = new ArrayList<>(5);
				for (int i = 1; i <= maxDifficulty; i++) {
					options.add(String.valueOf(i));
				}
				minDifficultyCombo.setItems(FXCollections.observableList(options));
			}
		});

		maxDifficultyCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue obs, String oldValue, String newValue) {
				if (newValue != null) {
					maxDifficulty = Integer.parseInt(newValue);
					System.out.println("max: " + maxDifficulty);
					try {
						showQuestions();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		maxDifficultyCombo.showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue obs, Boolean oldValue, Boolean newValue) {
				if (newValue == false) {
					return;
				}

				ArrayList<String> options = new ArrayList<>(5);
				for (int i = minDifficulty; i <= 5; i++) {
					options.add(String.valueOf(i));
				}
				maxDifficultyCombo.setItems(FXCollections.observableList(options));
			}
		});

		showQuestions();

	}

	@FXML
	private void showQuestions() throws IOException {
		InterviewApp library = App.getInterviewApp();
		// TODO: use filters if any present
		ArrayList<UUID> authors = null;

		String authorName = authorFilterField.getText();
		if (authorName != null && authorName.isEmpty() == false) {
			User author = library.getUserFromUsername(authorName);
			if (author != null) {
				authors = new ArrayList<>();
				authors.add(author.getId());
			}
		}

		ArrayList<String> tags = null;

		ArrayList<Question> questions = library.getQuestions(tags, minDifficulty, maxDifficulty, false,
				authors);

		questionsContainer.getChildren().clear();

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
	void clearFilters() throws IOException {
		minDifficultyCombo.setValue("1");
		maxDifficultyCombo.setValue("5");
		// minDifficulty = 0;
		// maxDifficulty = 5;
		authorFilterField.setText("");
		showQuestions();
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
