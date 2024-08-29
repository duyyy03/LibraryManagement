
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MSI
 */
public class ManageBooks implements Manageable {

    private Scanner scanner = new Scanner(System.in);
    private Map<String, Map<String, Object>> books = new HashMap<>();
    private static final String filename = "books.dat";
    private static final String BOOKID_PATTERN = "^B\\d{5}$";
    private static final String ISBN_PATTERN = "^0\\d{10}$";

    public Map<String, Object> getBook(String bookID) {
        return books.get(bookID);
    }

    public ManageBooks() {
        books = new HashMap<>();
        loadBooks();
    }

    public void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            books = (Map<String, Map<String, Object>>) ois.readObject();
            //    System.out.println("Books data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            //    System.out.println("Error loading books data: " + e.getMessage());
        }
    }

    public void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
            System.out.println("Books data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving books data: " + e.getMessage());
        }
    }

    private boolean checkDuplicatedBookID(String bookID) {
        return books.containsKey(bookID);
    }

    public void add() {
        String bookID = MyTool.readPattern("Enter Book ID (Bxxxxx): ", BOOKID_PATTERN);
        if (books.containsKey(bookID)) {
            System.out.println("Book ID already exists.");
            return;
        }

        String title = MyTool.readNonBlank("Enter title: ");
        String author = MyTool.readNonBlank("Enter author: ");
        int year = MyTool.readInt("Enter publication year: ");
        String publisher = MyTool.readNonBlank("Enter publisher: ");
        String isbn = MyTool.readPattern("Enter ISBN: ", ISBN_PATTERN);

        Book book = new Book(bookID, title, author, year, publisher, isbn, true);
        books.put(bookID, book.toMap());
        System.out.println("Book added successfully.");
        saveBooks();
    }

    @Override
    public void update() {
        String bookID = MyTool.readPattern("Enter Book ID (Bxxxxx): ", BOOKID_PATTERN);
        if (!books.containsKey(bookID)) {
            System.out.println("Book ID does not exist.");
            return;
        }

        Map<String, Object> bookMap = books.get(bookID);
        Book book = Book.fromMap(bookMap);

        String title = MyTool.readOptionalBlank("Enter new title (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(title, book::setTitle);

        String author = MyTool.readOptionalBlank("Enter new author (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(author, book::setAuthor);

        Integer year = MyTool.readOptionalInt("Enter new publication year (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(year, book::setPublicationYear);

        String publisher = MyTool.readOptionalBlank("Enter new publisher (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(publisher, book::setPublisher);

        String isbn = MyTool.readPatternWithOptionalInput("Enter new ISBN: ", ISBN_PATTERN);
        MyTool.updateIfNotNullOrEmpty(isbn, book::setIsbn);

        Boolean activeBook = MyTool.readOptionalBoolean("Is the book active (true/false) (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(activeBook, book::setActiveBook);

        // Update the book map with the new book data
        books.put(bookID, book.toMap());

        System.out.println("Book updated successfully.");
        saveBooks();
    }

    @Override
    public void delete() {
        String bookID = MyTool.readPattern("Enter Book ID (Bxxxxx): ", BOOKID_PATTERN);
        if (!books.containsKey(bookID)) {
            System.out.println("Book ID does not exist.");
            return;
        }

        String confirmation = MyTool.readYesNo("Are you sure you want to delete this book? (Y/N): ");
        if (confirmation.equalsIgnoreCase("Y")) {
            Map<String, Object> bookMap = books.get(bookID);
            bookMap.put("activeBook", false);
            books.put(bookID, bookMap);
            System.out.println("Book marked as inactive.");
            saveBooks();
            displayActive();
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    @Override
    public void display() {
        books.keySet().stream()
                //.sorted()
                .map(books::get)
                .map(Book::fromMap)
                .sorted((book1, book2) -> book1.getTitle().compareTo(book2.getTitle()))
                .forEach(System.out::println);
    }

    public void displayActive() {
        books.keySet().stream()
                .sorted()
                .map(books::get)
                .filter(bookMap -> (boolean) bookMap.get("activeBook"))
                .map(Book::fromMap)
                .forEach(System.out::println);
    }

}
