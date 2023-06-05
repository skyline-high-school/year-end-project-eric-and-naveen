package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class CreateProjectController {
    // initialize variables defined in FXML class
    @FXML
    private Button projectInformation;
    @FXML
    private Button addMembers;
    @FXML
    private Button beginMatchmaking;

    @FXML
    /**
     * Handles the click event for the "Project Information" button.
     * Loads the ProjectInfo.fxml file and displays it in a new scene.
     * Sets the title of the stage and shows the scene.
     */
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
    /**
     * Handles the click event for the "Add Members" button.
     * Loads the AddMembers.fxml file and displays it in a new scene.
     * Sets the title of the stage and shows the scene.
     */
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

    /**
     * Handles the click event for the "Begin Matchmaking" button.
     * Calls the CalculateMatchingGroups() method in the BeginMatchMakingController class.
     * Loads the BeginMatchMaking.fxml file and displays it in a new scene.
     * Sets the title of the stage and shows the scene.
     */
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
    /**
     * Initializes the controller.
     * This method is called after the FXML file has been loaded.
     * It can be used to perform any necessary initialization tasks.
     */
    @FXML
    public void initialize() {
    }

}
