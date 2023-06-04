public class Student {
    private String name;
    private int id;
    private String studentPreference;
    private String strengths;
    private String groupMemberPreference;

    // Constructor
    public Student(String name, int id, String studentPreference, String strengths, String groupMemberPreference) {
        this.name = name;
        this.id = id;
        this.studentPreference = studentPreference;
        this.strengths = strengths;
        this.groupMemberPreference = groupMemberPreference;
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

    public String getStudentPreference() {
        return studentPreference;
    }

    public void setStudentPreference(String studentPreference) {
        this.studentPreference = studentPreference;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getGroupMemberPreference() {
        return groupMemberPreference;
    }

    public void setGroupMemberPreference(String groupMemberPreference) {
        this.groupMemberPreference = groupMemberPreference;
    }

    // toString method to display the student's profile information
    @Override
    public String toString() {
        return "Name: " + name +
                "\nID: " + id +
                "\nStudent Preference: " + studentPreference +
                "\nStrengths: " + strengths +
                "\nGroup Member Preference: " + groupMemberPreference;
    }
}
