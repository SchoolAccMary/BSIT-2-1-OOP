import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnrollmentApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();

        String[] validPrograms = {"BSIT", "BSCS"};

        boolean running = true;

        while (running) {
            System.out.println("========================================");
            System.out.println("LICEO ENROLLMENT SYSTEM (CLI)");
            System.out.println("========================================");
            System.out.println("[1] Register Student");
            System.out.println("[2] Add Course Offering");
            System.out.println("[3] Enroll Student to Course");
            System.out.println("[4] View All Students");
            System.out.println("[5] View All Courses");
            System.out.println("[6] View Student Load (Courses + Total Units)");
            System.out.println("[0] Exit");
            System.out.println("----------------------------------------");
            System.out.print("Enter choice: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("[ERROR] Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    registerStudent(sc, students, validPrograms);
                    break;
                case 2:
                    addCourse(sc, courses);
                    break;
                case 3:
                    enrollStudent(sc, students, courses, enrollments);
                    break;
                case 4:
                    viewAllStudents(students);
                    break;
                case 5:
                    viewAllCourses(courses);
                    break;
                case 6:
                    viewStudentLoad(sc, students, courses, enrollments);
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the Liceo Enrollment System!");
                    break;
                default:
                    System.out.println("[ERROR] Invalid menu choice. Try again.");
            }
        }

        sc.close();
    }

    private static void registerStudent(Scanner sc, ArrayList<Student> students,
                                        String[] validPrograms) {
        System.out.println("--- REGISTER STUDENT ---");

        System.out.print("Student ID : ");
        String id = sc.nextLine();

        System.out.print("Full Name : ");
        String name = sc.nextLine();

        System.out.print("Program : ");
        String program = sc.nextLine();

        boolean validProgram = false;
        for (String p : validPrograms) {
            if (p.equalsIgnoreCase(program)) {
                validProgram = true;
                program = p;
                break;
            }
        }
        if (!validProgram) {
            System.out.println("[ERROR] Invalid program. Allowed: "
                    + String.join(", ", validPrograms));
            return;
        }

        System.out.print("Year Level : ");
        int year;
        if (sc.hasNextInt()) {
            year = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("[ERROR] Year level must be a number.");
            sc.nextLine();
            return;
        }

        if (year < 1 || year > 4) {
            System.out.println("[ERROR] Year level must be between 1 and 4.");
            return;
        }

        students.add(new Student(id, name, program, year));
        System.out.println("[OK] Student registered successfully!");
    }

    private static void addCourse(Scanner sc, ArrayList<Course> courses) {
        System.out.println("--- ADD COURSE OFFERING ---");

        System.out.print("Course Code : ");
        String code = sc.nextLine();

        System.out.print("Title : ");
        String title = sc.nextLine();

        System.out.print("Units : ");
        int units = sc.hasNextInt() ? sc.nextInt() : 0;
        sc.nextLine();

        System.out.print("Capacity : ");
        int capacity = sc.hasNextInt() ? sc.nextInt() : 0;
        sc.nextLine();

        courses.add(new Course(code, title, units, capacity));
        System.out.println("[OK] Course added successfully!");
    }

    private static void enrollStudent(Scanner sc, ArrayList<Student> students,
                                      ArrayList<Course> courses,
                                      HashMap<String, ArrayList<String>> enrollments) {
        System.out.println("--- ENROLL STUDENT ---");

        System.out.print("Student ID : ");
        String studentId = sc.nextLine();

        System.out.print("Course Code : ");
        String courseCode = sc.nextLine();

        Student foundStudent = findStudent(students, studentId);
        if (foundStudent == null) {
            System.out.println("[ERROR] Student not found.");
            return;
        }

        Course foundCourse = findCourse(courses, courseCode);
        if (foundCourse == null) {
            System.out.println("[ERROR] Course not found.");
            return;
        }

        if (foundCourse.isFull()) {
            System.out.println("[ERROR] Course is full.");
            return;
        }

        enrollments.putIfAbsent(studentId, new ArrayList<>());
        ArrayList<String> studentCourses = enrollments.get(studentId);

        if (studentCourses.contains(courseCode)) {
            System.out.println("[ERROR] Student is already enrolled in " + courseCode + ".");
            return;
        }

        studentCourses.add(courseCode);
        foundCourse.addOneEnrollee();

        System.out.println("[OK] " + foundStudent.getFullName() + " enrolled in "
                + courseCode + " (" + foundCourse.getTitle() + ").");
    }

    private static void viewAllStudents(ArrayList<Student> students) {
        System.out.println("--- ALL STUDENTS ---");
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : students) {
            System.out.println(s.describe());
        }
    }

    private static void viewAllCourses(ArrayList<Course> courses) {
        System.out.println("--- ALL COURSES ---");
        if (courses.isEmpty()) {
            System.out.println("No courses yet.");
            return;
        }
        for (Course c : courses) {
            System.out.println(c.getCourseCode() + " " + c.getTitle() + " "
                    + c.getUnits() + " units "
                    + c.getEnrolledCount() + "/" + c.getCapacity());
        }
    }

    private static void viewStudentLoad(Scanner sc, ArrayList<Student> students,
                                        ArrayList<Course> courses,
                                        HashMap<String, ArrayList<String>> enrollments) {
        System.out.println("--- STUDENT LOAD ---");

        System.out.print("Student ID : ");
        String studentId = sc.nextLine();

        Student foundStudent = findStudent(students, studentId);
        if (foundStudent == null) {
            System.out.println("[ERROR] Student not found.");
            return;
        }

        ArrayList<String> studentCourses = enrollments.get(studentId);
        System.out.println("--- STUDENT LOAD: " + foundStudent.getFullName() + " ---");

        if (studentCourses == null || studentCourses.isEmpty()) {
            System.out.println("No enrolled courses.");
            return;
        }

        int totalUnits = 0;
        for (String code : studentCourses) {
            Course c = findCourse(courses, code);
            if (c != null) {
                System.out.println(c.getCourseCode() + " " + c.getTitle()
                        + " " + c.getUnits() + " units");
                totalUnits += c.getUnits();
            }
        }
        System.out.println("----------------------------------------");
        System.out.println("Total Units: " + totalUnits);
    }

    private static Student findStudent(ArrayList<Student> students, String id) {
        for (Student s : students) {
            if (s.getStudentID().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private static Course findCourse(ArrayList<Course> courses, String code) {
        for (Course c : courses) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }
        return null;
    }
}