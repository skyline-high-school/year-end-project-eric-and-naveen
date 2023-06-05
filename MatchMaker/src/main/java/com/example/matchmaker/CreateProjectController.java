package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreateProjectController {
    @FXML
    private Button projectInformation;
    @FXML
    private Button addMembers;
    @FXML
    private Button beginMatchmaking;

    @FXML
    protected void onProjectInformationClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(MatchMakerApplication.class.getResource("ProjectInfo.fxml"));
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
    protected void onAddMembersClick() {
        FXMLLoader fxmlLoader = new FXMLLoader(MatchMakerApplication.class.getResource("AddMembers.fxml"));
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
    protected void onBeginMatchmakingClick() {
        BeginMatchMakingController.CalculateMatchingGroups();
        FXMLLoader fxmlLoader = new FXMLLoader(MatchMakerApplication.class.getResource("BeginMatchMaking.fxml"));
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
    public void initialize() {
    }

}
