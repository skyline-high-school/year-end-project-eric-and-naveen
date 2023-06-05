package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MatchMakerController {
    @FXML
    private Button createNewProject;
    @FXML
    private Button viewExistingProjects;

    @FXML
    protected void onCreateNewProjectClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(MatchMakerApplication.class.getResource("CreateProject.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = (Stage)Stage.getWindows().stream().filter(Window::isShowing).findFirst().orElse(null);
            stage.setTitle("Project Matchmaker App!!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    protected void onViewExistingProjectsClick() {

    }
}