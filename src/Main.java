import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");

            String input;

            try {
                input = scanner.nextLine().trim();
            } catch(NoSuchElementException e) {
                System.out.println("\nNo more input. Exiting.");
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch(choice) {
                case 1:
                    String title = promptNonBlank(scanner, "Enter title: ");
                    String author = promptNonBlank(scanner, "Enter author: ");
                    try {
                        library.addBook(new Book(title, author));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Could not add book: " + e.getMessage());
                    }
                    break;

                case 2:
                    library.listBooks();
                    break;

                case 3:
                    library.borrowBook(promptNonBlank(scanner, "Enter the title to borrow: "));
                    break;

                case 4:
                    library.returnBook(promptNonBlank(scanner, "Enter the title to return: "));
                    break;

                case 5:
                    library.searchBook(promptNonBlank(scanner, "Enter the title to search: "));
                    break;

                case 0:
                    System.out.println("Goodbye! Thank you for using the Library Information System.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }

            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===== LIBRARY INFORMATION SYSTEM =====");
        System.out.println("1. Add a book");
        System.out.println("2. List all books");
        System.out.println("3. Borrow a book");
        System.out.println("4. Return a book");
        System.out.println("5. Search a book");
        System.out.println("0. Exit");
    }

    private static String promptNonBlank(Scanner scanner, String prompt) {
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }
}
