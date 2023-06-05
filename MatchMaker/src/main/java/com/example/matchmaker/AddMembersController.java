package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AddMembersController {
    @FXML
    private TextField CSVTextFile;
    @FXML
    private Button loadFile;

    @FXML
    protected void onLoadFileClick() {
        try {
            readStudentsFromCSV(CSVTextFile.getText());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void readStudentsFromCSV(String fileName) throws Exception {
        Set<Student> students = BeginMatchMakingController.getStudentSet();
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            String nextLine = sc.nextLine();
            System.out.format("Processing line: %s\n", nextLine);
            students.add(createStudent(nextLine.split(",")));
        }
        sc.close();  //closes the scanner
    }

    private static Set<String> getSkills(String[] skillData) {
        Set<String> skills = new HashSet<String>();
        for(String skill: skillData) {
            skills.add(skill);
        }
        return skills;
    }
    private static Student createStudent(String[] studentData) {
        System.out.format("Begin: Tokens from line.........\n");
        for(String s:studentData) {
            System.out.println(s);
        }
        System.out.format("End: Tokens from line end*****\n");
        int id = Integer.parseInt(studentData[0]);
        String name = studentData[1];
        Set<String> skills = getSkills(studentData[2].split(";"));
        Set<String> desiredSkills = getSkills(studentData[3].split(";"));

        String studentPreference = "";
        if (studentData.length == 5) {
            studentPreference = studentData[4];
        };

        System.out.format("Created Student: %s\n", name);
        return new Student(id, name, skills, desiredSkills, studentPreference);
    }
}

