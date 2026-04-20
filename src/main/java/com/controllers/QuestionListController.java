package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class QuestionListController {

    @FXML
    private void goToLogin() throws IOException {
        App.setRoot("Login");
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
