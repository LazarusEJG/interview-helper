package com.controllers;

import java.io.IOException;
import java.util.ArrayList;

import com.interview.App;
import com.model.Comment;
import com.model.InterviewApp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SelectedQuestionCommentController {
	@FXML
	private Label author;

	@FXML
	private Label time;

	@FXML
	private Button upvoteBtn;

	@FXML
	private Label score;

	@FXML
	private Button downvoteBtn;

	@FXML
	private Label content;

	@FXML
	private Button replyBtn;

	@FXML
	private VBox commentsContainer;

	@FXML
	private HBox addCommentBox;
	@FXML
	private TextArea newCommentInput;

	private Comment comment;

	@FXML
	private void initialize() {
		InterviewApp library = App.getInterviewApp();
		if (library.getCurrentUser() == null) {
			replyBtn.setVisible(false);
			replyBtn.setManaged(false);
		}
	}

	public void setData(Comment comment) throws IOException {
		InterviewApp library = App.getInterviewApp();

		author.setText(library.getUser(comment.getAuthor()).getUsername());
		time.setText(comment.getPublishTime().toString());
		score.setText(String.valueOf(comment.getScore()));
		content.setText(comment.getContent());

		ArrayList<Comment> replies = comment.getReplies();

		for (Comment reply : replies) {
			showComment(reply);
		}

		this.comment = comment;
	}

	private void showComment(Comment comment) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/interview/SelectedQuestionComment.fxml"));

		Parent root = loader.load();
		SelectedQuestionCommentController controller = loader.getController();

		controller.setData(comment);

		commentsContainer.getChildren().add(root);
	}

	@FXML
	public void toggleReplyBox() {
		if (addCommentBox.isVisible()) {
			addCommentBox.setVisible(false);
			addCommentBox.setManaged(false);
			replyBtn.setText("Reply");
		} else {
			addCommentBox.setVisible(true);
			addCommentBox.setManaged(true);
			replyBtn.setText("Cancel");
		}
	}

	@FXML
	public void addReply() throws IOException {
		InterviewApp library = App.getInterviewApp();

		Comment reply = library.addComment(comment, library.getCurrentUser().getId(), newCommentInput.getText());
		showComment(reply);
		toggleReplyBox();
		newCommentInput.setText("");
	}
}
