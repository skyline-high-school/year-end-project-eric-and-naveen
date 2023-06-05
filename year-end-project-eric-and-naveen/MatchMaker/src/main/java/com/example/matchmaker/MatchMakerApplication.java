package com.example.matchmaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import java.util.ArrayList;

public class MatchMakerApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
public void start(Stage primaryStage) {
    primaryStage.setTitle("Match Maker");

    Button addMemberButton = new Button("Add Member");
    Button createGroupBox = new Button("Create Group");

    addMemberButton.setOnAction(e -> {
        openNewPage("Add Member Page");
        primaryStage.setTitle("Display Text and TextField Example");

        Text text = new Text("Enter Name");
        TextField nameTextField = new TextField();
        Text text2 = new Text("Enter ID");
        TextField idTextField = new TextField();

        VBox container = new VBox(text, nameTextField, text2, idTextField);
        Button submitButton = new Button("Submit");
        Label resultLabel = new Label();
        submitButton.setOnAction(event -> {
            String studentName = nameTextField.getText();
            int studentId = Integer.parseInt(idTextField.getText());
            Student student = new Student(studentName, studentId);
            studentList.add(student);
            System.out.println(student);
            System.out.println(studentList.size());
            resultLabel.setText("Added");
            container.getChildren().add(resultLabel); // Add resultLabel to the container after setting its text
        });

        container.getChildren().add(submitButton);

        Scene scene = new Scene(container, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    });

    createGroupBox.setOnAction(e -> {
        openNewPage("Create Group Page");
        groupPage(primaryStage);
    });

    VBox buttonBox = new VBox(10);
    Button homeButton = new Button("Home");
    homeButton.setOnAction(e -> {
        primaryStage.setTitle("Match Maker");
        buttonBox.getChildren().clear();
        buttonBox.getChildren().addAll(addMemberButton, createGroupBox);
    });

    buttonBox.getChildren().addAll(homeButton, addMemberButton, createGroupBox);

    Scene scene = new Scene(buttonBox, 1000, 1000);
    primaryStage.setScene(scene);
    primaryStage.show();
}

private void openNewPage(String pageName) {
    Stage newStage = new Stage();
    newStage.setTitle(pageName);

    Button closeButton = new Button("Close");
    closeButton.setOnAction(event -> {
        newStage.close();
        // Revert back to the home page when the close button is clicked
        start(new Stage());
    });

    VBox pageContent = new VBox(new Button("This is the " + pageName), closeButton);
    Scene newScene = new Scene(pageContent, 400, 300);
    newStage.setScene(newScene);
    newStage.show();
}

public void groupPage(Stage primaryStage) {
    primaryStage.setTitle("Display Text and TextField Example");

    Text text = new Text("Enter Project Name");
    TextField projectNameField = new TextField();
    Text text2 = new Text("Enter Group Number");
    TextField groupNumberField = new TextField();
    Text text3 = new Text("Enter Individuals Per Group");
    TextField individualsPerGroup = new TextField();

    VBox container = new VBox(text, projectNameField, text2, groupNumberField, text3, individualsPerGroup);
    Button submitButton = new Button("Submit");
    Label resultLabel = new Label();
    submitButton.setOnAction(event -> {
        String projectName = projectNameField.getText();
        int groupNumber = Integer.parseInt(groupNumberField.getText());
        int indiPerGroup = Integer.parseInt(individualsPerGroup.getText());
        Group group = new Group(projectName, groupNumber, indiPerGroup, studentList);
        group.formGroups();
        resultLabel.setText("added");
        container.getChildren().add(resultLabel); // Add resultLabel to the container after setting its text
    });

    container.getChildren().add(submitButton);

    Scene scene = new Scene(container, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
}



// Additional Code
ArrayList<Student> studentList = new ArrayList<>();

public class Student {
    private String name;
    private int id;

    // Constructor
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // toString method to display the student's profile information
    @Override
    public String toString() {
        return "Name: " + name +
                "\nID: " + id;
    }
}


//additional code part 2 

public class Group {
    private String projectName;
    private int totalGroups;
    private int individualsPerGroup;
    private ArrayList<Student> studentList;

    // Constructor
    public Group(String projectName, int totalGroups, int individualsPerGroup, ArrayList<Student> studentList) {
        this.projectName = projectName;
        this.totalGroups = totalGroups;
        this.individualsPerGroup = individualsPerGroup;
        this.studentList = studentList;
    }

    // Getters and Setters
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getTotalGroups() {
        return totalGroups;
    }

    public void setTotalGroups(int totalGroups) {
        this.totalGroups = totalGroups;
    }

    public int getIndividualsPerGroup() {
        return individualsPerGroup;
    }

    public void setIndividualsPerGroup(int individualsPerGroup) {
        this.individualsPerGroup = individualsPerGroup;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    // Method to form groups based on project requirements
    public void formGroups() {
        int totalStudents = studentList.size();
        int studentsPerGroup = totalGroups * individualsPerGroup;
    
        if (studentsPerGroup > totalStudents) {
            System.out.println("Not enough students to form the specified number of groups.");
            return;
        }
    
        Stage newStage = new Stage();
        newStage.setTitle("Group Formation");
    
        VBox container = new VBox();
        Text headingText = new Text("Forming groups for project: " + projectName);
        Text totalGroupsText = new Text("Total groups: " + totalGroups);
        Text individualsPerGroupText = new Text("Individuals per group: " + individualsPerGroup);
        Text separatorText = new Text("------------------------------------");
        container.getChildren().addAll(headingText, totalGroupsText, individualsPerGroupText, separatorText);
    
        for (int i = 0; i < totalGroups; i++) {
            Text groupHeaderText = new Text("Group " + (i + 1) + ":");
            container.getChildren().add(groupHeaderText);
            for (int j = 0; j < individualsPerGroup; j++) {
                Student student = studentList.get(i * individualsPerGroup + j);
                Text studentText = new Text("  " + student.getName() + " (ID: " + student.getId() + ")");
                container.getChildren().add(studentText);
               }
            container.getChildren().add(new Text()); // Add an empty line between groups
        }
    
        Scene scene = new Scene(container, 400, 300);
        newStage.setScene(scene);
        newStage.show();
    }
    
}

}


