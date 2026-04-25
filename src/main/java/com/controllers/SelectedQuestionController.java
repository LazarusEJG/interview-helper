package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.interview.App;
import com.model.Comment;
import com.model.InterviewApp;
import com.model.Question;
import com.model.Solution;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SelectedQuestionController {

	@FXML
	private Button SignInButton;
	@FXML
	private VBox workspaceContainer;
	@FXML
	private TextArea answerTextArea;
	@FXML
	private TextArea answerCodeTextArea;
	@FXML
	private HBox addCommentBox;
	@FXML
	private Button postCommentBtn;
	@FXML
	private TextArea newCommentInput;

	@FXML
	private ScrollPane solutionsScrollPane;
	@FXML
	private Button descriptionBtn;
	@FXML
	private ScrollPane descriptionScrollPane;
	@FXML
	private Button solutionsBtn;

	@FXML
	private Label questionTitle;

	@FXML
	private Label questionAuthor;

	@FXML
	private Text questionContent;

	@FXML
	private Label difficultyLabel;

	@FXML
	private HBox categoriesContainer;

	@FXML
	private VBox hintsContainer;

	@FXML
	private Label hintsLabel;

	@FXML
	private VBox solutionsContainer;

	@FXML
	private VBox commentsContainer;

	@FXML
	private TitledPane commentsPane;

	private Question question;
	private int commentCount;
	private int solutionCount;

	public void setData(Question question) throws IOException {
		InterviewApp library = App.getInterviewApp();

		questionTitle.setText(question.getTitle());
		questionAuthor.setText(library.getUser(question.getAuthor()).getUsername());
		questionContent.setText(question.getContent());
		difficultyLabel.setText(question.getDifficulty() + "/5");
		// categories
		categoriesContainer.getChildren().clear();
		for (String category : question.getCategories()) {
			Label label = new Label(category);
			label.getStyleClass().add("category-tag");
			categoriesContainer.getChildren().add(label);
		}
		// hints
		ArrayList<String> hints = question.getHints();
		if (hints != null && hints.isEmpty() == false) {
			hintsContainer.getChildren().clear();
			for (String hint : hints) {
				Text HintText = new Text(hint);
				HintText.setFill(Paint.valueOf("WHITE"));
				TextFlow hintTextFlow = new TextFlow(HintText);
				hintTextFlow.getStyleClass().add("hint-text");
				hintsContainer.getChildren().add(hintTextFlow);
			}
		} else {
			hintsContainer.setVisible(false);
			hintsLabel.setVisible(false);
		}
		// solutions
		solutionsContainer.getChildren().clear();
		ArrayList<Solution> solutions = question.getSolutions();
		solutionCount = solutions.size();
		for (int i = 0; i < solutionCount; i++) {
			showSolution(solutions.get(i), i);
		}
		// comments
		// commentsContainer.getChildren().clear();
		ArrayList<Comment> comments = question.getComments();
		commentCount = comments.size();
		if (commentCount > 0 || library.getCurrentUser() != null) {
			for (Comment comment : comments) {
				showComment(comment);
			}
			commentsPane.setText("View Comments (" + commentCount + ")");
		} else {
			commentsPane.setVisible(false);
			commentsPane.setManaged(false);
		}

		this.question = question;
	}

	private void showSolution(Solution solution, int i) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/SelectedQuestionSolution.fxml"));

		Parent root = loader.load();
		SelectedQuestionSolutionController controller = loader.getController();

		controller.setData(solution, i);

		solutionsContainer.getChildren().add(root);
	}

	private void showComment(Comment comment) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/SelectedQuestionComment.fxml"));

		Parent root = loader.load();
		SelectedQuestionCommentController controller = loader.getController();

		controller.setData(comment);

		commentsContainer.getChildren().add(root);

	}

	@FXML
	public void initialize() {
		InterviewApp library = App.getInterviewApp();
		User user = library.getCurrentUser();
		if (user != null) {
			SignInButton.setText("Log Out");
		} else {
			workspaceContainer.setVisible(false);
			workspaceContainer.setManaged(false);
			addCommentBox.setVisible(false);
			addCommentBox.setManaged(false);
		}
	}

	@FXML
	public void showSolutions() {
		solutionsBtn.getStyleClass().remove("strip-button");
		descriptionBtn.getStyleClass().remove("strip-button-active");

		solutionsBtn.getStyleClass().add("strip-button-active");
		descriptionBtn.getStyleClass().add("strip-button");
		solutionsScrollPane.setVisible(true);
		solutionsScrollPane.setManaged(true);
		descriptionScrollPane.setVisible(false);
		descriptionScrollPane.setManaged(false);
	}

	@FXML
	public void showDescription() {
		solutionsBtn.getStyleClass().remove("strip-button-active");
		descriptionBtn.getStyleClass().remove("strip-button");

		solutionsBtn.getStyleClass().add("strip-button");
		descriptionBtn.getStyleClass().add("strip-button-active");
		solutionsScrollPane.setVisible(false);
		solutionsScrollPane.setManaged(false);
		descriptionScrollPane.setVisible(true);
		descriptionScrollPane.setManaged(true);
	}

	@FXML
	private void postComment() throws IOException {
		InterviewApp library = App.getInterviewApp();

		Comment reply = library.addComment(question, library.getCurrentUser().getId(), newCommentInput.getText());
		showComment(reply);
		commentCount++;
		commentsPane.setText("View Comments (" + commentCount + ")");
		newCommentInput.setText("");
	}

	@FXML
	private void postSolution() throws IOException {
		InterviewApp library = App.getInterviewApp();
		String explanation = answerTextArea.getText();
		String code = answerCodeTextArea.getText();

		Solution solution = library.addSolution(explanation, null, code);

		showSolution(solution, solutionCount++);

		answerTextArea.setText("");
		answerCodeTextArea.setText("");
		// TODO
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
		InterviewApp library = App.getInterviewApp();
		App.setRoot("Home");
		library.setCurrentQuestion(null);
	}

	@FXML
	private void goToQuestions(MouseEvent event) throws IOException {
		InterviewApp library = App.getInterviewApp();
		App.setRoot("QuestionList");
		library.setCurrentQuestion(null);
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
		library.setCurrentQuestion(null);
	}
}
