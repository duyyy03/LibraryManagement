/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MSI
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Book implements Serializable {

    private String bookID;
    private String title;
    private String author;
    private int publicationYear;
    private String publisher;
    private String isbn;
    private boolean activeBook;

    public Book(String bookID, String title, String author, int publicationYear, String publisher, String isbn, boolean activeBook) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = isbn;
        this.activeBook = activeBook;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isActiveBook() {
        return activeBook;
    }

    public void setActiveBook(boolean activeBook) {
        this.activeBook = activeBook;
    }

    // Create from Map
    public static Book fromMap(Map<String, Object> bookMap) {
        return new Book(
                (String) bookMap.get("bookID"),
                (String) bookMap.get("title"),
                (String) bookMap.get("author"),
                (int) bookMap.get("publicationYear"),
                (String) bookMap.get("publisher"),
                (String) bookMap.get("isbn"),
                (boolean) bookMap.get("activeBook")
        );
    }

    // Convert to Map
    public Map<String, Object> toMap() {
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("bookID", bookID);
        bookMap.put("title", title);
        bookMap.put("author", author);
        bookMap.put("publicationYear", publicationYear);
        bookMap.put("publisher", publisher);
        bookMap.put("isbn", isbn);
        bookMap.put("activeBook", activeBook);
        return bookMap;
    }

    @Override
    public String toString() {
        return "Book{"
                + "bookId='" + bookID + '\''
                + ", title='" + title + '\''
                + ", author='" + author + '\''
                + ", publicationYear=" + publicationYear
                + ", publisher='" + publisher + '\''
                + ", isbn='" + isbn + '\''
                + ", activeBook=" + activeBook
                + '}';
    }
}


/*
  

    
*/
