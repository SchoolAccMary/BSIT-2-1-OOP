public class Student {
    private String studentID = "";
    private String fullName = "";
    private String program = "";
    private int yearLevel = 0;

    public Student(String studentID, String fullName, String program, int yearLevel) {
        this.studentID = studentID;
        this.fullName = fullName;
        this.program = program;
        this.yearLevel = yearLevel;
    }
    public String getStudentID() {
        return studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getProgram() {
        return program;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public String describe() {
        return studentID + " | " + fullName + " | " + program + " | Year " + yearLevel;
    }
}