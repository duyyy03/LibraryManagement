
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ReportManager {
 
    /*
     private ManageBooks manageBooks;
    private ManageLoans manageLoans;

    public ReportManager(ManageBooks manageBooks, ManageLoans manageLoans) {
        this.manageBooks = manageBooks;
        this.manageLoans = manageLoans;
    }

    public void reportBorrowedBooks() {
        System.out.println("Currently Borrowed Books:");
        manageLoans.getLoans().values().forEach(loanMap -> {
            Loan loan = Loan.fromMap(loanMap);
            Map<String, Object> book = manageBooks.getBook(loan.getBookID());
            if (book != null && !(boolean) book.get("available")) {
                System.out.println(loan);
            }
        });
    }
    
    // Report on overdue books
    public void reportOverdueBooks() {
        System.out.println("Overdue Books:");
        Date currentDate = new Date();
        manageLoans.getLoans().values().forEach(loanMap -> {
            Loan loan = Loan.fromMap(loanMap);
            if (loan.getReturnDate().before(currentDate)) {
                System.out.println(loan);
            }
        });
    }
    
    public void reportBorrowingActivities(Date startDate, Date endDate) {
        System.out.println("Borrowing Activities from " + startDate + " to " + endDate + ":");
        manageLoans.getLoans().values().forEach(loanMap -> {
            Loan loan = Loan.fromMap(loanMap);
            if (!loan.getBorrowDate().before(startDate) && !loan.getBorrowDate().after(endDate)) {
                System.out.println(loan);
            }
        });
    }
    
     public void reportBorrowingActivities() {
        Date startDate = readDate("Enter start date (dd/MM/yyyy): ");
        Date endDate = readDate("Enter end date (dd/MM/yyyy): ");
        reportBorrowingActivities(startDate, endDate);
    }

    private Date readDate(String prompt) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        while (true) {
            System.out.print(prompt);
            String dateStr = new Scanner(System.in).nextLine();
            try {
                return sdf.parse(dateStr);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy.");
            }
        }
    }
    */
    
}
