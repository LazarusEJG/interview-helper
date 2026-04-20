package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;
import com.model.User;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class QuestionAddController {
    
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
