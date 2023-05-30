import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to store students
        ArrayList<Student> studentList = new ArrayList<>();

        // Add students to the list
        studentList.add(new Student("John Smith", 123456, "Male", "Programming, Communication", "Reliable, Good communication skills"));
        studentList.add(new Student("Emma Johnson", 789012, "Female", "Problem-solving, Leadership", "Motivated, Team player"));
        studentList.add(new Student("Michael Brown", 345678, "Male", "Data analysis, Research", "Detail-oriented, Analytical"));

        // Display the profile information for each student
        for (Student student : studentList) {
            System.out.println(student);
            System.out.println("-----------------------------");
        }
    }
}
