import java.util.ArrayList;

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

        System.out.println("Forming groups for project: " + projectName);
        System.out.println("Total groups: " + totalGroups);
        System.out.println("Individuals per group: " + individualsPerGroup);
        System.out.println("------------------------------------");

        for (int i = 0; i < totalGroups; i++) {
            System.out.println("Group " + (i + 1) + ":");
            for (int j = 0; j < individualsPerGroup; j++) {
                Student student = studentList.get(i * individualsPerGroup + j);
                System.out.println("  " + student.getName() + " (ID: " + student.getId() + ")");
            }
            System.out.println();
        }
    }
}
