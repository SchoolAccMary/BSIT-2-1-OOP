import java.util.*;

public class GradeTracker {
    private static final double[] CUTOFF = {90, 80, 70, 60};
    private static final char[] LETTERS = {'A', 'B', 'C', 'D'};

    private static String letterFor(double grade) {
        for (int i = 0; i < CUTOFF.length; i++) {
            if (grade >= CUTOFF[i]) {
                return String.valueOf(LETTERS[i]);
            }
        }
        return "F";
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> roster = new ArrayList<>();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readMenuChoice(input);

            switch (choice) {
                case 1:
                    addStudent(input, roster);
                    break;
                case 2:
                    viewAllStudents(roster);
                    break;
                case 3:
                    printClassAverage(roster);
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Unknown option. Try again.");
        }
    }
        input.close();
}

    static class Student {
        private final String name;
        private final double grade;

        public Student(String name, double grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public double getGrade() {
            return grade;
        }
    }

    private static void printMenu() {
        System.out.println("=== Student Grade Tracker ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Class Average");
        System.out.println("4. Exit");
        System.out.print("Choose an option (1-4): ");
    }

    private static int readMenuChoice(Scanner input) {
        while (true) {
            try {
                int choice = input.nextInt();
                if (choice >= 1 && choice <= 4) {
                    return choice;
                }
                System.out.print("Please enter a number from 1 to 4: ");
            } catch (InputMismatchException e) {
                input.next();
                System.out.print("That's not a number. Please enter 1-4: ");
            }
        }
    }

    private static void addStudent(Scanner input, ArrayList<Student> roster) {
        System.out.print("Enter student name: ");
        String name = input.next();

        double grade = readValidGrade(input);

        roster.add(new Student(name, grade));
        System.out.println(name + " added with grade " + grade
                + " (" + letterFor(grade) + ")");
    }

    private static double readValidGrade(Scanner input) {
        while (true) {
            System.out.print("Enter grade (0-100): ");
            try {
                double grade = input.nextDouble();
                if (grade < 0 || grade > 100) {
                    System.out.println("Grade must be between 0 and 100. Try again.");
                    continue;
                }
                return grade;
            } catch (InputMismatchException e) {
                input.next(); // clear the bad token
                System.out.println("Grades must be numbers. Try again.");
            }
        }
    }

    private static void viewAllStudents(ArrayList<Student> roster) {
        if (roster.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("--- Class Roster ---");
        // for-each reads as "for each Student s in roster" — use it
        // whenever you just need to look at every item in the list.
        for (Student s : roster) {
            System.out.println(s.getName() + ": " + s.getGrade()
                    + " (" + letterFor(s.getGrade()) + ")");
        }
    }

    private static void printClassAverage(ArrayList<Student> roster) {
        if (roster.isEmpty()) {
            System.out.println("No students yet - add a student first.");
            return;
        }

        double total = 0;
        for (Student s : roster) {
            total += s.getGrade();
        }

        double average = total / roster.size();
        System.out.printf("Class Average: %.2f%n", average);
    }
}