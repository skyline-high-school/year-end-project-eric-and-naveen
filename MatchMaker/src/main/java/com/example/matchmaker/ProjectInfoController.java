package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ProjectInfoController {

    @FXML
    private TextField projectNameText;
    @FXML
    private TextField numOfGroupsText;
    @FXML
    private Button saveButton;
    @FXML
    protected void onSaveButtonClick() {
        System.out.format("number of groups field value: %s\n", numOfGroupsText.getText());
        BeginMatchMakingController.setGroupCount(Integer.parseInt(numOfGroupsText.getText()));
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
}
