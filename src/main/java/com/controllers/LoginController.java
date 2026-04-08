package com.controllers;

import java.io.IOException;

import com.interview.App;
import com.model.InterviewApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML
    private Button LoginButton;

    @FXML
    private Button secondaryButton;

    @FXML
    private PasswordField txt_password;

    @FXML
    private PasswordField txt_username;

    @FXML
    void Login(ActionEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText();

        InterviewApp library = new InterviewApp();

        library.login(username, password);
    }

    @FXML
    void backToHome(ActionEvent event) throws IOException {
        App.setRoot("Home");
    }

}
