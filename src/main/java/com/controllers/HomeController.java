package com.controllers;

import java.io.IOException;

import com.interview.App;

import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void goToLogin() throws IOException {
        App.setRoot("Login");
    }
}
