package com.controllers;

import java.io.IOException;

import com.interview.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CAccountController {
    
    @FXML
    private Button SignUpButton;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_username;

    @FXML
    void backToHome(MouseEvent event) throws IOException {
        App.setRoot("Home");
    }

    @FXML
    private void goToQuestions(MouseEvent event) throws IOException {
        App.setRoot("QuestionList");
    }

    @FXML //use fx:id="SignUpButton" should make use of txt_password, txt_username, and txt_email
    private void SignUp() throws IOException {

    }

    /**
     * Link Question button to question list page
     */
}
