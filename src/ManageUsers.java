
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
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
public class ManageUsers implements Manageable {

    private Scanner scanner = new Scanner(System.in);
    private Map<String, Map<String, Object>> users = new HashMap<>();
    private int userIDCounter = 1;
    private static final String filename = "users.dat";
    private static final String PHONENUMBER_PATTERN = "^0\\d{9}$";
    private static final String USERID_PATTERN = "^U\\d{5}$";
    private static final String EMAIL_PATTERN = "^[\\w._%+-]+@fpt\\.edu\\.vn$";

    public ManageUsers() {
        users = new HashMap<>();
        loadUsers();
    }

    public Map<String, Object> getUser(String userID) {
        return users.get(userID);
    }

    // Method to load users from a file
    public void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            users = (Map<String, Map<String, Object>>) ois.readObject();
            userIDCounter = users.size() + 1;
            //        System.out.println("Users data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            //        System.out.println("Error loading users data: " + e.getMessage());
        }
    }

    // Method to save users to a file
    public void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
            System.out.println("Users data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users data: " + e.getMessage());
        }
    }

    private boolean checkDuplicatedUserID(String userID) {
        return users.containsKey(userID);
    }

    @Override
    public void add() {
        String userID;
        do {
            userID = "U" + String.format("%05d", userIDCounter++);
        } while (checkDuplicatedUserID(userID));

        String name = MyTool.readNonBlank("Enter name: ");
        Date dateOfBirth = MyTool.readValidDateNotEmpty("Enter date of birth (dd/MM/yyyy): ", "dd/MM/yyyy");
        String phoneNumber = MyTool.readPattern("Enter phone number: ", PHONENUMBER_PATTERN);
        String email = MyTool.readPattern("Enter email (@fpt.edu.vn): ", EMAIL_PATTERN);

        User user = new User(userID, name, dateOfBirth, phoneNumber, email, true);
        users.put(userID, user.toMap());
        System.out.println("User added successfully.");
        saveUsers();
    }

    @Override
    public void update() {
        String userID = MyTool.readPattern("Enter User ID (Uxxxxx): ", USERID_PATTERN);

        if (!users.containsKey(userID)) {
            System.out.println("User ID does not exist.");
            return;
        }

        Map<String, Object> userMap = users.get(userID);
        User user = User.fromMap(userMap);

        String name = MyTool.readNonBlank("Enter name: ");
        MyTool.updateIfNotNullOrEmpty(name, user::setName);

        Date dateOfBirth = MyTool.readValidDate("Enter date of birth (dd/MM/yyyy): ", "dd/MM/yyyy", false);
        MyTool.updateIfNotNullOrEmpty(dateOfBirth, user::setDateOfBirth);

        String phoneNumber = MyTool.readPatternWithOptionalInput("Enter phone number: ", PHONENUMBER_PATTERN);
        MyTool.updateIfNotNullOrEmpty(phoneNumber, user::setPhoneNumber);

        String email = MyTool.readPatternWithOptionalInput("Enter email (@fpt.edu.vn): ", EMAIL_PATTERN);
        MyTool.updateIfNotNullOrEmpty(email, user::setEmail);
        
        Boolean activeUser = MyTool.readOptionalBoolean("Is the book active (true/false) (or leave blank to keep current): ");
        MyTool.updateIfNotNullOrEmpty(activeUser, user::setActiveUser);


        users.put(userID, user.toMap());

        System.out.println("User updated successfully.");
        saveUsers();
    }

    @Override
    public void delete() {
        String userID = MyTool.readPattern("Enter User ID (Uxxxxx): ", USERID_PATTERN);

        if (!users.containsKey(userID)) {
            System.out.println("User ID does not exist.");
            return;
        }

        String confirmation = MyTool.readYesNo("Are you sure you want to delete this book? (Y/N): ");
        if (confirmation.equalsIgnoreCase("Y")) {
            Map<String, Object> userMap = users.get(userID);
            userMap.put("activeUser", false);
            users.put(userID, userMap);
            System.out.println("User marked as inactive.");
            saveUsers();
            displayActive();
        } else {
            System.out.println("Deletion canceled.");
        }

    }

    @Override
    public void display() {
        users.keySet().stream()
                .sorted()
                .map(users::get)
                .map(User::fromMap)
                .forEach(System.out::println);
    }

    public void displayActive() {
        users.keySet().stream()
                .sorted()
                .map(users::get)
                .filter(userMap -> (boolean) userMap.get("activeUser"))
                .map(User::fromMap)
                .forEach(System.out::println);
    }

}


