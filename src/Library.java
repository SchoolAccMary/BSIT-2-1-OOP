import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<Book>();
    }

    public void addBook(Book book) {
        if (book == null) {
            System.out.println("Cannot add an empty book.");
            return;
        }
        if (findBook(book.getTitle()) != null) {
            System.out.println("A book titled \"" + book.getTitle() + "\" already exists. Choose a different title.");
            return;
        }
        books.add(book);
        System.out.println("Added: " + book.describe());
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("The library has no books yet.");
            return;
        }

        System.out.println("--- Library Catalog ---");
        for (int i = 0; i < books.size(); i++) {
            Book getBook = books.get(i);
            String status = getBook.isBorrowed() ? "Borrowed" : "Available";
            System.out.printf("%d. %-30s %-20s %s%n",
                    i + 1, getBook.getTitle(), getBook.getAuthor(), status);
        }
    }

    private Book findBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            return null;
        }
        String target = title.trim();
        for (Book getBook : books) {
            if (getBook.getTitle().equalsIgnoreCase(title)) {
                return getBook;
            }
        }
        return null;
    }

    public void borrowBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Book getBook = findBook(title);
        if (getBook == null) {
            System.out.println("No book found with title: " + title);
        } else if (getBook.isBorrowed()) {
            System.out.println("\"" + getBook.getTitle() + "\" is already borrowed.");
        } else {
            getBook.borrow();
            System.out.println("You have borrowed \"" + getBook.getTitle() + "\".");
        }
    }

    public void returnBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Book getBook = findBook(title);
        if (getBook == null) {
            System.out.println("No book found with title: " + title);
        } else if (!getBook.isBorrowed()) {
            System.out.println("\"" + getBook.getTitle() + "\" was not borrowed.");
        } else {
            getBook.returnBook();
            System.out.println("Thank you for returning \"" + getBook.getTitle() + "\".");
        }
    }

    public void searchBook(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        Book getBook = findBook(title);
        if (getBook == null) {
            System.out.println("\"" + title + "\" was not found in the library.");
        } else {
            System.out.println("Found: " + getBook.describe());
        }
    }
}
