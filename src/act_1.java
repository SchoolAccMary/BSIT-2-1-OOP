import java.util.Scanner;

public class act_1 {

    public static final int MAX_STUDENTS = 10;

    public static int[] studentID = new int[MAX_STUDENTS];
    public static String[] fullNames = new String[MAX_STUDENTS];
    public static int[] ages = new int[MAX_STUDENTS];
    public static String[] courses = new String[MAX_STUDENTS];
    public static double[] grades = new double[MAX_STUDENTS];
    public static boolean[] enrolledStatus = new boolean[MAX_STUDENTS];

    public static int studentCount = 0;

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudentById();
                    break;
                case 4:
                    viewStatistics();
                    break;
                case 5:
                    System.out.println("Thank you for using the Student Information System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(">> Invalid choice. Please enter 1-5.");
            }
            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("===== STUDENT INFORMATION SYSTEM =====");
        System.out.println("[1] Add Student");
        System.out.println("[2] View All Students");
        System.out.println("[3] Search Student by ID");
        System.out.println("[4] View Statistics");
        System.out.println("[5] Exit");
    }

    public static void addStudent() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println(">> Student list is full. Cannot add more records.");
            return;
        }

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Full Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Course: ");
        sc.nextLine(); // consume leftover newline
        String course = sc.nextLine();

        System.out.print("Enter Grade: ");
        double grade = sc.nextDouble();

        System.out.print("Is the student enrolled? (true/false): ");
        boolean isEnrolled = sc.nextBoolean();
        sc.nextLine(); // consume leftover newline

        if (age <= 0 || grade < 0 || grade > 100) {
            System.out.println(">> Invalid age/grade. Age must be positive. Grade must be between 0 and 100. Student not added.");
            return;
        }

        studentID[studentCount] = id;
        fullNames[studentCount] = name;
        ages[studentCount] = age;
        courses[studentCount] = course;
        grades[studentCount] = grade;
        enrolledStatus[studentCount] = isEnrolled;
        studentCount++;

        System.out.println(">> Student added successfully!");
    }

    public static void viewAllStudents() {
        if (studentCount == 0) {
            System.out.println(">> No records yet. Add a student first.");
            return;
        }

        System.out.println("--- STUDENT RECORDS ---");
        System.out.printf("%-6s%-15s%-6s%-10s%-8s%-15s%n",
                "ID", "NAME", "AGE", "COURSE", "GRADE", "STANDING");

        for (int i = 0; i < studentCount; i++) {
            System.out.printf("%-6d%-15s%-6d%-10s%-8.2f%-15s%n",
                    studentID[i], fullNames[i], ages[i], courses[i], grades[i], standing(grades[i]));
        }
    }

    public static String standing(double g) {
        return g >= 90 ? "Dean's Lister" : g >= 75 ? "Passed" : "Failed";
    }

    public static void searchStudentById() {
        int searchId = readInt("Enter ID to search: ");
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentID[i] == searchId) {
                found = true;
                System.out.println("--- STUDENT FOUND ---");
                System.out.println("ID: " + studentID[i]);
                System.out.println("Name: " + fullNames[i]);
                System.out.println("Age: " + ages[i]);
                System.out.println("Course: " + courses[i]);
                System.out.println("Grade: " + grades[i]);
                System.out.println("Enrolled: " + enrolledStatus[i]);
                break;
            }
        }
        if (!found) {
            System.out.println(">> No student found with ID " + searchId);
        }
    }

    public static void viewStatistics() {
        if (studentCount == 0) {
            System.out.println(">> No records yet. Add a student first.");
            return;
        }
        int total = studentCount;
        double sum = 0;
        int topIndex = 0;

        for (int i = 0; i < studentCount; i++) {
            sum += grades[i];
            if (grades[i] > grades[topIndex]) {
                topIndex = i;
            }
        }
        double average = sum / studentCount;
        System.out.println("--- STATISTICS ---");
        System.out.println("Total Students: " + total);
        System.out.println("Average Grade: " + String.format("%.2f", average));
        System.out.println("Top Student: " + fullNames[topIndex] + " (" + String.format("%.2f", grades[topIndex]) + ")");
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println(">> Please enter a valid number.");
            sc.next();
            System.out.print(prompt);
        }
        int value = sc.nextInt();
        return value;
    }
}