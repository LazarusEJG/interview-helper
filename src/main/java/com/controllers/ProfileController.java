package com.controllers;

import java.io.IOException;

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
        //top rectangle
        User user = library.getCurrentUser();
        welcomeLabel.setText("@" + user.getUsername());
        interestsLabel.setText(String.valueOf("Interests: "+user.getInterests()));

        //middle image
        Image img = new Image(
        getClass().getResourceAsStream("/com/interview/images/avatar-placeholder.jpg"),
        140, 140,
        false,
        true
        );
        avatarImage.setImage(img);
        Platform.runLater(() -> {
        Circle clip = new Circle(100);
        clip.setCenterX(100);
        clip.setCenterY(100);
        avatarImage.setClip(clip);
        });

        //bottom recangle
        votesLabel.setText(String.valueOf(user.getReceivedVotes()));
        streakLabel.setText(String.valueOf(user.getCurrentStreak()));
        longestStreakLabel.setText(String.valueOf(user.getLongestStreak()));
        lastStreakLabel.setText(String.valueOf(user.getLastStreakDate()));

        //right side (start here)
    }

    @FXML
    private void goToLogin() throws IOException {
        App.setRoot("Login");
    }

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        App.setRoot("Home");
    }

}
