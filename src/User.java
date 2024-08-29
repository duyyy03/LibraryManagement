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

public class User implements Serializable {

    private String userID;
    private String name;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private boolean activeUser;

    public User(String userID, String name, Date dateOfBirth, String phoneNumber, String email, boolean activeUser) {
        this.userID = userID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    // Create from Map
    public static User fromMap(Map<String, Object> userMap) {
        return new User(
                (String) userMap.get("userID"),
                (String) userMap.get("name"),
                (Date) userMap.get("dateOfBirth"),
                (String) userMap.get("phoneNumber"),
                (String) userMap.get("email"),
                (boolean) userMap.get("activeUser")
        );
    }
    
     public Map<String, Object> toMap() {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("userID", userID);
        userMap.put("name", name);
        userMap.put("dateOfBirth", dateOfBirth);
        userMap.put("phoneNumber", phoneNumber);
        userMap.put("email", email);
        userMap.put("activeUser", activeUser);
        return userMap;
    }

    @Override
    public String toString() {
        return "User{"
                + "userId='" + userID + '\''
                + ", name='" + name + '\''
                + ", dateOfBirth=" + dateOfBirth
                + ", phoneNumber='" + phoneNumber + '\''
                + ", email='" + email + '\''
                + ", activeUser=" + activeUser
                + '}';
    }
}
