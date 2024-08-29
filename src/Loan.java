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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Loan implements Serializable {

    private String transactionID;
    private String bookID;
    private String userID;
    private Date borrowDate;
    private Date returnDate;

    public Loan(String transactionID, String bookID, String userID, Date borrowDate, Date returnDate) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
    
     // Create from Map
    public static Loan fromMap(Map<String, Object> loanMap) {
        return new Loan(
                (String) loanMap.get("transactionID"),
                (String) loanMap.get("bookID"),
                (String) loanMap.get("userID"),
                (Date) loanMap.get("borrowDate"),
                (Date) loanMap.get("returnDate")
        );
    }

    // Convert to Map
    public Map<String, Object> toMap() {
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("transactionID", transactionID);
        loanMap.put("bookID", bookID);
        loanMap.put("userID", userID);
        loanMap.put("borrowDate", borrowDate);
        loanMap.put("returnDate", returnDate);
        return loanMap;
    }

    @Override
    public String toString() {
        return "Loan{"
                + "loanId='" + transactionID + '\''
                + ", bookId='" + bookID + '\''
                + ", userId='" + userID + '\''
                + ", borrowDate=" + borrowDate
                + ", returnDate=" + returnDate
                + '}';
    }
}
