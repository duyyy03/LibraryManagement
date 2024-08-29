
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ManageLoans implements Manageable {

    private Map<String, Map<String, Object>> loans = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private ManageBooks manageBooks;
    private ManageUsers manageUsers;
    private static int loanIDCounter = 1;
    private static final String filename = "loans.dat";

    private static final String TRANSACTION_ID = "^T\\d{5}$";
    private static final String USER_ID_PATTERN = "^U\\d{5}$";
    private static final String BOOK_ID_PATTERN = "^B\\d{5}$";

    public ManageLoans(ManageBooks manageBooks, ManageUsers manageUsers) {
        this.manageBooks = manageBooks;
        this.manageUsers = manageUsers;
        loadLoans();
    }

    // Method to load loans from a file
    public void loadLoans() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            loans = (Map<String, Map<String, Object>>) ois.readObject();
            loanIDCounter = loans.size() + 1;
            //       System.out.println("Loans data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            //       System.out.println("Error loading loans data: " + e.getMessage());
        }
    }

    // Method to save loans to a file
    public void saveLoans() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(loans);
         //   System.out.println("Loans data saved successfully.");
        } catch (IOException e) {
         //   System.out.println("Error saving loans data: " + e.getMessage());
        }
    }

    private boolean checkDuplicatedTransactionID(String transactionID) {
        return loans.containsKey(transactionID);
    }

    @Override
    public void add() {
        String transactionID;
        // Enter transaction ID

        /*
         if (loans.containsKey(transactionID)) {
            System.out.println("Transaction ID already exists.");
            return;
        }
        
         */
        do {
            transactionID = MyTool.readPattern("Enter transaction ID: ", TRANSACTION_ID);
        } while (checkDuplicatedTransactionID(transactionID));

        // Enter and validate User ID
        String userID = MyTool.readPattern("Enter User ID (Uxxxxx): ", USER_ID_PATTERN);
        Map<String, Object> user = manageUsers.getUser(userID);
        if (user == null || !(boolean) user.get("activeUser")) {
            System.out.println("Invalid or inactive User ID.");
            return;
        }

        // Enter and validate Book ID
        String bookID = MyTool.readPattern("Enter Book ID (Bxxxxx): ", BOOK_ID_PATTERN);
        Map<String, Object> book = manageBooks.getBook(bookID);
        if (book == null || !(boolean) book.get("activeBook")) {
            System.out.println("Invalid or inactive Book ID.");
            return;
        }

        // Enter borrow date and expected return date
        Date borrowDate = MyTool.readValidDateNotEmpty("Enter borrow date (dd/MM/yyyy): ", "dd/MM/yyyy");
        Date returnDate = MyTool.readValidDateNotEmpty("Enter expected return date (dd/MM/yyyy): ", "dd/MM/yyyy");

        // Create loan and update loans map
        Loan loan = new Loan(transactionID, bookID, userID, borrowDate, returnDate);
        loans.put(transactionID, loan.toMap());


        System.out.println("Book borrowed successfully.");
        saveLoans();
        
                // Update book status to inactive
        // book.put("activeBook", false);
        //  manageBooks.update();  // Assuming updateBook method exists in ManageBooks class
    }

    @Override
    public void update() {
        String loanID = MyTool.readPattern("Enter Loan ID (Txxxxx): ", TRANSACTION_ID);
        Map<String, Object> loanMap = loans.get(loanID);
        if (loanMap == null) {
            System.out.println("Loan ID does not exist.");
            return;
        }

        Loan loan = Loan.fromMap(loanMap);

        Date returnDate = MyTool.readValidDate("Enter new return date (dd/MM/yyyy): ", "dd/MM/yyyy", false);
        loan.setReturnDate(returnDate);

        loans.put(loanID, loan.toMap());
        System.out.println("Loan information updated successfully.");
        saveLoans();
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
        /*
         loans.values().forEach(loanMap -> {
            Loan loan = Loan.fromMap(loanMap);
            Map<String, Object> book = manageBooks.getBook(loan.getBookID());
            if (book != null && !(boolean) book.get("available")) {
                System.out.println(loan);
            }
        });
         */

        loans.keySet().stream()
                .sorted()
                .map(loans::get)
                .map(Loan::fromMap)
                .forEach(System.out::println);

    }

    public Map<String, Map<String, Object>> getLoans() {
        return loans;
    }

    // Report on currently borrowed books
    public void reportBorrowedBooks() {
        System.out.println("Currently Borrowed Books:");
        loans.values().stream()
                .map(Loan::fromMap)
                .sorted((loan1, loan2) -> loan2.getBorrowDate().compareTo(loan1.getBorrowDate()))
                .forEach(System.out::println);
    }

    // Report on overdue books
    public void reportOverdueBooks() {
        System.out.println("Overdue Books:");
        Date currentDate = new Date();
        loans.entrySet().stream()
                .filter(entry -> {
                    Loan loan = Loan.fromMap(entry.getValue());
                    return loan.getReturnDate().before(currentDate);
                })
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(Loan::fromMap)
                .forEach(System.out::println);
    }

    // Report all borrowing activities within a specific period
    public void reportBorrowingActivities(Date startDate, Date endDate) {
        System.out.println("Borrowing Activities from " + startDate + " to " + endDate + ":");
        loans.entrySet().stream()
                .filter(entry -> {
                    Loan loan = Loan.fromMap(entry.getValue());
                    return !loan.getBorrowDate().before(startDate) && !loan.getBorrowDate().after(endDate);
                })
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(Loan::fromMap)
                .forEach(System.out::println);
    }

// Additional helper method to read a date range
    public void reportBorrowingActivities() {
        Date startDate = MyTool.readValidDateNotEmpty("Enter start date (dd/MM/yyyy): ", "dd/MM/yyyy");
        Date endDate = MyTool.readValidDateNotEmpty("Enter end date (dd/MM/yyyy): ", "dd/MM/yyyy");
        reportBorrowingActivities(startDate, endDate);
    }

}
