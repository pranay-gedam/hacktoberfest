import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Book {
    private String name;
    private String author;
    private String issuedTo;
    private Date issuedOn;

    // Constructor
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.issuedTo = null;
        this.issuedOn = null;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public Date getIssuedOn() {
        return issuedOn;
    }

    public void setIssuedOn(Date issuedOn) {
        this.issuedOn = issuedOn;
    }

    public boolean isIssued() {
        return issuedTo != null;
    }

    // Display book details
    public void displayBook() {
        System.out.println("Book Name: " + name);
        System.out.println("Author: " + author);
        if (isIssued()) {
            System.out.println("Issued To: " + issuedTo);
            System.out.println("Issued On: " + issuedOn);
        } else {
            System.out.println("Status: Available");
        }
        System.out.println("------------------------");
    }
}

class Library {
    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getName());
    }

    // Issue a book to a student
    public void issueBook(String bookName, String studentName) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(bookName) && !book.isIssued()) {
                book.setIssuedTo(studentName);
                book.setIssuedOn(new Date());
                System.out.println("Book \"" + bookName + "\" issued to " + studentName);
                return;
            }
        }
        System.out.println("Book is either not available or already issued.");
    }

    // Return a book
    public void returnBook(String bookName, String studentName) {
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(bookName) && book.getIssuedTo() != null 
                && book.getIssuedTo().equalsIgnoreCase(studentName)) {
                book.setIssuedTo(null);
                book.setIssuedOn(null);
                System.out.println("Book \"" + bookName + "\" returned by " + studentName);
                return;
            }
        }
        System.out.println("Book is either not issued to " + studentName + " or does not exist.");
    }

    // Display all books
    public void displayBooks() {
        for (Book book : books) {
            book.displayBook();
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Dummy data
        library.addBook(new Book("Java Programming", "James Gosling"));
        library.addBook(new Book("Effective Java", "Joshua Bloch"));
        library.addBook(new Book("Design Patterns", "Erich Gamma"));

        while (true) {
            System.out.println("\n1. Add Book\n2. Issue Book\n3. Return Book\n4. Display Books\n5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book name: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(bookName, author));
                    break;
                case 2:
                    System.out.print("Enter book name: ");
                    String issueBookName = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    library.issueBook(issueBookName, studentName);
                    break;
                case 3:
                    System.out.print("Enter book name: ");
                    String returnBookName = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String returnStudentName = scanner.nextLine();
                    library.returnBook(returnBookName, returnStudentName);
                    break;
                case 4:
                    library.displayBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
