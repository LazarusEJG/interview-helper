package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;
import com.model.UserType;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class QuestionListController {

    @FXML
    private Button SignInButton;

    @FXML
    private Button addQuestionBtn;

    @FXML
    public void initialize() {
        InterviewApp library = App.getInterviewApp();
        User user = library.getCurrentUser();
        if ( user != null) {
            SignInButton.setText("Log Out");
            if (user.getType() != UserType.CONTRIBUTOR) {
                addQuestionBtn.setVisible(false);
            } else {
                addQuestionBtn.setVisible(true);
            }
        } else {
            addQuestionBtn.setVisible(false);
        }
    }

    @FXML
    private void goToLogin() throws IOException {
        InterviewApp library = App.getInterviewApp();
        User user = library.getCurrentUser();
        if ( user != null) {
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
        if ( user == null) {
            App.setRoot("Login");
        } else {
            App.setRoot("Profile");
        }
    }
}
