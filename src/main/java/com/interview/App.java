package com.interview;

import java.io.IOException;

import com.model.InterviewApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static InterviewApp interviewApp = new InterviewApp();

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("Home"), 1200, 800);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop() {
		interviewApp.close();
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static InterviewApp getInterviewApp() {
		return interviewApp;
	}

	public static void main(String[] args) {
		launch();
	}

}
