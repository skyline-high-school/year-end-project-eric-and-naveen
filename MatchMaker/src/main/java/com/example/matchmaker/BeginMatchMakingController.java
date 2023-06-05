package com.example.matchmaker;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class BeginMatchMakingController implements Initializable {

    @FXML
    ListView groupList;

    @FXML
    Button doneButton;
    // initializing the various studentsets for each possible combination
    static private Set<Student> studentSet = new HashSet<Student>();
    static private Set<Student> studentsWithMutualPreferences;
    static private Set<Student> studentsWithMutualInterests;

    static private Set<Student> studentsWithDiverseInterests;
    static private Set<Student> studentsWithRandomGrouping;

    static private List<Set<Student>> groups = new ArrayList<Set<Student>>();
    static private int groupCount;

    static private int groupSize;

    static private ArrayList<String> groupListStrings = new ArrayList<String>();
    // getters and setters for each variable defined earlier
    public static Set<Student> getStudentSet() {
        return studentSet;
    }

    public static void setStudentSet(Set<Student> studentSet) {
        BeginMatchMakingController.studentSet = studentSet;
    }

    public static List<Set<Student>> getGroups() {
        return groups;
    }

    public static void setGroups(List<Set<Student>> groups) {
        BeginMatchMakingController.groups = groups;
    }

    public static int getGroupCount() {
        return groupCount;
    }

    public static void setGroupCount(int groupCount) {
        BeginMatchMakingController.groupCount = groupCount;
    }
    // make sure student isn't already in a group
    private static boolean IsAlreadyInAGroup(Student student) {
        for(Set<Student> currentGroup : groups) {
            if (currentGroup.contains(student)) {
                return true;
            }
        }
        return false;
    }
    private static void BuildMutualPreferenceGroups() {
        System.out.println("Begin BuildMutualPreferenceGroups()");
        var students = studentSet.toArray();
        System.out.format("BuildMutualPreferenceGroups(): StudentSet count: %d\n", students.length);
        for (int i = 0; i < students.length; i++) {
            Student studentI = ((Student)students[i]);
            if (IsAlreadyInAGroup(studentI)) {
                continue;
            }
            boolean groupUpdated = false;
            for (int j = i+ 1; j < students.length; j++) {
                Student studentJ = ((Student)students[j]);
                if (IsAlreadyInAGroup(studentJ)) {
                    continue;
                }
                System.out.format("Comparing %s with %s\n", studentI.getName(), studentJ.getName());
                System.out.format("Name: %s Preferred Name: %s\n", studentI.getName(), studentI.getStudentPreference());
                System.out.format("Name: %s Preferred Name: %s\n", studentJ.getName(), studentJ.getStudentPreference());
                if (studentI.getStudentPreference().compareTo(studentJ.getName()) == 0
                    && studentJ.getStudentPreference().compareTo(studentI.getName()) == 0) {
                    System.out.format("Success with comparison %s with %s\n", studentI.getName(), studentJ.getName());
                    for(Set<Student> currentGroup : groups) {
                        if (currentGroup.size() <= groupSize-2) {
                            currentGroup.add(studentI);
                            System.out.format("BuildMutualPreferenceGroups(): Added Student to group: %s\n", studentI.getName());
                            currentGroup.add(studentJ);
                            System.out.format("BuildMutualPreferenceGroups(): Added Student to group: %s\n", studentJ.getName());
                            studentSet.remove(studentI);
                            studentSet.remove(studentJ);
                            groupUpdated = true;
                            break;
                        }
                    }
                }
                if (groupUpdated) {
                    break;
                }
            }
        }
        System.out.println("End BuildMutualPreferenceGroups()");
    }
    /* Construct groups on the following criteria:
    1. If students mutually request each other, group them together
    2. If students mutually interest each other (artistic requests technological, technological requests artist), group them together
    3. If students' interests complement each other (artistic, technological) group them together
    4. If all else fails, randomize
     */
    private static void BuildMutualInterestGroups()
    {
        System.out.println("Begin BuildMutualInterestGroups()");
        var students = studentSet.toArray();
        System.out.format("BuildMutualInterestGroups(): StudentSet count: %d\n", students.length);
        for (int i = 0; i < students.length; i++) {
            Student studentI = ((Student)students[i]);
            if (IsAlreadyInAGroup(studentI)) {
                continue;
            }
            Set<Student> iSkills = new HashSet<Student>();
            iSkills.addAll(studentI.getDesiredSkills());
            boolean groupUpdated = false;
            for (int j = i+ 1; j < students.length; j++) {
                Student studentJ = ((Student)students[j]);
                if (IsAlreadyInAGroup(studentJ)) {
                    continue;
                }
                System.out.format("Comparing %s with %s\n", studentI.getName(), studentJ.getName());
                iSkills.retainAll(studentJ.getSkills());
                if (iSkills.size() > 0) {
                    for(Set<Student> currentGroup : groups) {
                        if (currentGroup.size() <= groupSize-2) {
                            currentGroup.add(studentI);
                            System.out.format("BuildMutualInterestGroups(): Added Student to group: %s\n", studentI.getName());
                            currentGroup.add(studentJ);
                            System.out.format("BuildMutualInterestGroups(): Added Student to group: %s\n", studentJ.getName());
                            studentSet.remove(studentI);
                            studentSet.remove(studentJ);
                            groupUpdated = true;
                            break;
                        }
                    }
                }
                if (groupUpdated) {
                    break;
                }
            }
        }
        System.out.println("End BuildMutualInterestGroups()");
    }

    private static void BuildDiverseInterestGroups()
    {
        System.out.println("Begin BuildDiverseInterestGroups()");
        var students = studentSet.toArray();
        System.out.format("BuildDiverseInterestGroups(): StudentSet count: %d\n", students.length);
        for (int i = 0; i < students.length; i++) {
            Student studentI = ((Student)students[i]);
            if (IsAlreadyInAGroup(studentI)) {
                continue;
            }
            Set<Student> iSkills = new HashSet<Student>();
            iSkills.addAll(studentI.getSkills());
            boolean groupUpdated = false;
            for (int j = i+ 1; j < students.length; j++) {
                Student studentJ = ((Student)students[j]);
                if (IsAlreadyInAGroup(studentJ)) {
                    continue;
                }
                System.out.format("Comparing %s with %s\n", studentI.getName(), studentJ.getName());
                iSkills.addAll(studentJ.getSkills());
                if (iSkills.size() > studentI.getSkills().size()) {
                    for(Set<Student> currentGroup : groups) {
                        if (currentGroup.size() <= groupSize-2) {
                            currentGroup.add(studentI);
                            System.out.format("BuildDiverseInterestGroups(): Added Student to group: %s\n", studentI.getName());
                            currentGroup.add(studentJ);
                            System.out.format("BuildDiverseInterestGroups(): Added Student to group: %s\n", studentJ.getName());
                            studentSet.remove(studentI);
                            studentSet.remove(studentJ);
                            groupUpdated = true;
                            break;
                        }
                    }
                }
                if (groupUpdated) {
                    break;
                }
            }
        }
        System.out.println("End BuildDiverseInterestGroups()");
    }

    private static void BuildRandomGroups()
    {
        System.out.println("Begin BuildRandomGroups()");
        var students = studentSet.toArray();
        System.out.format("BuildRandomGroups(): StudentSet count: %d\n", students.length);
        for (int i = 0; i < students.length; i++) {
            Student studentI = ((Student)students[i]);
            if (IsAlreadyInAGroup(studentI)) {
                continue;
            }
            System.out.format("Student %s \n", studentI.getName());
            for(Set<Student> currentGroup : groups) {
                if (currentGroup.size() < groupSize) {
                    currentGroup.add(studentI);
                    System.out.format("BuildRandomGroups(): Added Student to group: %s\n", studentI.getName());
                    studentSet.remove(studentI);
                    break;
                }
            }
        }
        System.out.println("End BuildRandomGroups()");
    }

    public static void CalculateMatchingGroups() {
        System.out.println("Begin CalculateMatchingGroups()");
        groupSize = studentSet.size()/groupCount;
        if (studentSet.size()%groupCount > 0) {
            groupSize++;
        }
        for (int i = 0; i < groupCount; i++) {
            groups.add(new HashSet<Student>());
        }
        System.out.format("Group Count: %d\n", groupCount);
        System.out.format("Group Size: %d\n", groupSize);
        System.out.println("Begin studentSet listing");
        for(Student s: studentSet) {
            System.out.format("%d %s %s %s %s\n", s.getId(), s.getName(), s.getSkills(), s.getDesiredSkills(), s.getStudentPreference());
        }
        System.out.println("End studentSet listing");

        BuildMutualPreferenceGroups();
        BuildMutualInterestGroups();
        BuildDiverseInterestGroups();
        BuildRandomGroups();
        System.out.println("Begin......GroupList");
        for(Set<Student> group: groups) {
            StringBuilder groupString = new StringBuilder();
            for(Student s:group) {
                groupString.append(s.getName() + "; ");
            }
            groupListStrings.add(groupString.toString());
            System.out.println(groupString.toString());
        }
        System.out.println("End......GroupList");
        System.out.println("End CalculateMatchingGroups()");
    }

    @FXML
    protected void onDoneClick() {
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
    public void initialize() {
        System.out.println("BeginMatchMakingController: initialize()");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("BeginMatchMakingController: inherited implemented initialize()");
       groupList.getItems().addAll(groupListStrings.toArray());
    }
}
