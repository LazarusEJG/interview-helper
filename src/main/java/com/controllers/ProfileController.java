package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Arrays;
import com.model.Question;
import com.model.Solution;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ProfileController {

	@FXML
	private ComboBox<String> categorySelector;

	@FXML
	private VBox contentContainer;

	@FXML
	private Label interestsLabel;

	@FXML
	private Label votesLabel;

	@FXML
	private Label streakLabel;

	@FXML
	private Label longestStreakLabel;

	@FXML
	private Label lastStreakLabel;

	@FXML
	private ImageView avatarImage;

	@FXML
	private Circle avatarClip;

	@FXML
	private Label welcomeLabel;

	@FXML
	public void initialize() {
		InterviewApp library = App.getInterviewApp();
		// top rectangle
		User user = library.getCurrentUser();
		welcomeLabel.setText("@" + user.getUsername());
		interestsLabel.setText(String.valueOf("Interests: " + user.getInterests()));

		// middle image
		Image img = new Image(
				getClass().getResourceAsStream("/com/interview/images/avatar-placeholder.jpg"),
				140, 140,
				false,
				true);
		avatarImage.setImage(img);
		Platform.runLater(() -> {
			Circle clip = new Circle(150);
			clip.setCenterX(150);
			clip.setCenterY(150);
			avatarImage.setClip(clip);
		});

		// bottom recangle
		votesLabel.setText(String.valueOf(user.getReceivedVotes()));
		streakLabel.setText(String.valueOf(user.getCurrentStreak()));
		longestStreakLabel.setText(String.valueOf(user.getLongestStreak()));
		lastStreakLabel.setText(String.valueOf(user.getLastStreakDate()));

		// right side
		categorySelector.getSelectionModel().selectFirst();
		categorySelector.setOnAction(e -> updateRightSide());
		updateRightSide();
	}

	/**
	 * this is where the controller changes the visible list
	 * add more if equals for "Bookmarked Solutions", "Submitted Solutions",
	 * "Submitted Questions"
	 */
	private void updateRightSide() {
		contentContainer.getChildren().clear();

		String selected = categorySelector.getValue();

		if (selected.equals("Bookmarked Questions")) {
			loadBookmarkedQuestions();
		}

		if (selected.equals("Bookmarked Solutions")) {
			loadBookmarkedSolutions();
		}

		if (selected.equals("Submitted Solutions")) {
			loadSubmittedSolutions();
		}

		if (selected.equals("Submitted Questions")) {
			loadSubmittedQuestions();
		}
	}

	/**
	 * creates the card for the question list on the profile
	 * (change as needed)
	 * 
	 * @param title    title of the question
	 * @param subtitle subtitle of the question
	 * @return card added to the list
	 */
	private HBox createCard(String title, String subtitle) {
		HBox card = new HBox();
		card.setSpacing(10);
		card.setStyle(
				"-fx-background-color: white;" +
						"-fx-background-radius: 12;" +
						"-fx-padding: 12 16;" +
						"-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.2, 0, 2);");

		VBox textBox = new VBox();
		textBox.setSpacing(4);

		Label titleLabel = new Label(title);
		titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		Label subtitleLabel = new Label(subtitle);
		subtitleLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #555;");

		textBox.getChildren().addAll(titleLabel, subtitleLabel);
		card.getChildren().add(textBox);

		return card;
	}

	/**
	 * This is where the questions should populate
	 * this is only the one for the BookmarkedQuestions
	 * and it has a placeholder not the actual code for the question
	 */
	private void loadBookmarkedQuestions() {
		ArrayList<UUID> bookmarks = App.getInterviewApp().getCurrentUser().getBookmarkedQuestions();

		for (UUID id : bookmarks) {
			Question q = App.getInterviewApp().getQuestionByUUID(id);
			contentContainer.getChildren().add(
				createCard(q.getTitle(), q.getContent()));
		}
	}

	private void loadBookmarkedSolutions() {
		ArrayList<UUID> bookmarks = App.getInterviewApp().getCurrentUser().getBookmarkedSolutions();

		for (UUID id : bookmarks) {
			Solution s = App.getInterviewApp().getSolutionByUUID(id);
			String authorName = App.getInterviewApp().getUser(s.getAuthor()).getUsername();
			String content = s.getExplanation();
			contentContainer.getChildren().add(createCard(authorName, content));
		}
	}

	private void loadSubmittedQuestions() {
		ArrayList<UUID> authorID = new ArrayList<>(Arrays.asList(App.getInterviewApp().getCurrentUser().getId()));

		ArrayList<Question> bookmarks = App.getInterviewApp().getQuestions(null, null, null, false, authorID);

		for (Question q : bookmarks) {
			contentContainer.getChildren().add(
				createCard(q.getTitle(), q.getContent()));
		}
	}

	private void loadSubmittedSolutions() {
		ArrayList<UUID> bookmarks = App.getInterviewApp().getCurrentUser().getSubmittedSolutions();

		for (UUID id : bookmarks) {
			Solution s = App.getInterviewApp().getSolutionByUUID(id);
			String authorName = App.getInterviewApp().getUser(s.getAuthor()).getUsername();
			String content = s.getExplanation();
			contentContainer.getChildren().add(createCard(authorName, content));
		}
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

}
