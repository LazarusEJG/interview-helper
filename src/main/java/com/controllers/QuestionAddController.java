package com.controllers;

import java.io.IOException;

import com.interview.App;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class QuestionAddController {
    
    @FXML
    private void goToLogin() throws IOException {
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
