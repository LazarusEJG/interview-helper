package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.interview.App;
import com.model.Comment;
import com.model.InterviewApp;
import com.model.Solution;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SelectedQuestionSolutionController {
	@FXML
	private Label title;

	@FXML
	private Label upvotes;

	@FXML
	private Text explanation;

	@FXML
	private Text code;

	@FXML
	private Label author;

	@FXML
	private Label verified;

	@FXML
	private Button viewCommentsBtn;

	@FXML
	private VBox commentsContainer;

	@FXML
	private HBox addCommentBox;

	@FXML
	private Button addCommentBtn;

	@FXML
	private TextArea newCommentInput;

	private Solution solution;
	private int replyCount;
	private User currentUser;

	public void setData(Solution solution, int i) throws IOException {
		InterviewApp library = App.getInterviewApp();

		author.setText(library.getUser(solution.getAuthor()).getUsername());
		title.setText("Solution " + i);
		upvotes.setText(String.valueOf(solution.getScore()));
		explanation.setText(solution.getExplanation());
		verified.setText(solution.isVerified() ? "✓" : "Not" + " Verified");

		ArrayList<Comment> replies = solution.getReplies();
		replyCount = replies.size();
		if (replyCount > 0) {
			for (Comment reply : replies) {
				showComment(reply);
			}
			viewCommentsBtn.setText("View Commnts (" + replyCount + ")");
		} else {
			viewCommentsBtn.setVisible(false);
			viewCommentsBtn.setManaged(false);
		}

		this.currentUser = library.getCurrentUser();
		this.solution = solution;
	}

	private void showComment(Comment comment) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/SelectedQuestionComment.fxml"));

		Parent root = loader.load();
		SelectedQuestionCommentController controller = loader.getController();

		controller.setData(comment);

		commentsContainer.getChildren().add(root);
	}

	@FXML
	public void toggleComments() {
		if (commentsContainer.isVisible()) {
			viewCommentsBtn.setText("View Commnts (" + replyCount + ")");
			commentsContainer.setVisible(false);
			commentsContainer.setManaged(false);
			addCommentBtn.setVisible(false);
			addCommentBtn.setManaged(false);
		} else {
			viewCommentsBtn.setText("Hide Commnts (" + replyCount + ")");
			commentsContainer.setVisible(true);
			commentsContainer.setManaged(true);
			addCommentBtn.setVisible(currentUser != null);
			addCommentBtn.setManaged(currentUser != null);
		}
	}

	@FXML
	public void toggleCommentBox() {
		if (addCommentBox.isVisible()) {
			addCommentBox.setVisible(false);
			addCommentBox.setManaged(false);
		} else {
			addCommentBox.setVisible(true);
			addCommentBox.setManaged(true);
		}
	}

	@FXML
	public void toggleReply() {
		if (addCommentBox.isVisible()) {
			addCommentBox.setVisible(false);
			addCommentBox.setManaged(false);
			addCommentBtn.setText("Add Comment");
		} else {
			addCommentBox.setVisible(true);
			addCommentBox.setManaged(true);
			addCommentBtn.setText("Cancel");
		}
	}

	@FXML
	public void addComment() throws IOException {
		InterviewApp library = App.getInterviewApp();

		Comment comment = library.addComment(solution, currentUser.getId(), newCommentInput.getText());
		showComment(comment);
		toggleCommentBox();
		newCommentInput.setText("");

	}
}
