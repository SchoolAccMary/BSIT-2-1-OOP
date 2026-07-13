public class Book {
    private String title = "";
    private String author = "";
    private boolean isBorrowed;

    public Book(String title, String author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if(author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public boolean borrow() {
        if (isBorrowed) {
            return false;
        }
        isBorrowed = true;
        return true;
    }

    public boolean returnBook() {
        if (!isBorrowed) {
            return false;
        }
        isBorrowed = false;
        return true;
    }

    public String describe() {
        String status = isBorrowed ? "Borrowed" : "Available";
        return "\"" + title + "\" by " + author + " - " + status;
    }

    @Override
    public String toString() {
        return describe();
    }
}

