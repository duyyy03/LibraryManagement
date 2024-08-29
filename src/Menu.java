
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
public class Menu {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ManageBooks manageBooks = new ManageBooks();
        ManageUsers manageUsers = new ManageUsers();
        ManageLoans manageLoans = new ManageLoans(manageBooks, manageUsers);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Loans");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    manageBooksMenu(manageBooks);
                    break;
                case 2:
                    manageUsersMenu(manageUsers);
                    break;
                case 3:
                    manageLoansMenu(manageLoans);
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageBooksMenu(ManageBooks manageBooks) {
        int choice;
        do {
            System.out.println("\nManage Books");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Display Books");
            System.out.println("5. Back to Main Menu");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                boolean success = false;
                switch (choice) {
                    case 1:
                        manageBooks.add();
                        success = true;
                        break;
                    case 2:
                        manageBooks.update();
                        success = true;
                        break;
                    case 3:
                        manageBooks.delete();
                        success = true;
                        break;
                    case 4:
                        manageBooks.display();
                        success = true;
                        break;
                    case 5:
                        break;

                    case 6:
                        System.out.println("Exiting system.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" -> Invalid choice. Please try again. ");
                }

                if (success && !promptReturnToMenu()) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(" -> Invalid input. Please enter again. ");
                choice = -1;  // Set to an invalid choice to continue the loop
            }
        } while (choice != 5);
    }

    private static void manageUsersMenu(ManageUsers manageUsers) {
        int choice;
        do {
            System.out.println("\nManage Users");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Display Users");
            System.out.println("5. Back to Main Menu");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                boolean success = false;

                switch (choice) {
                    case 1:
                        manageUsers.add();
                        success = true;
                        break;
                    case 2:
                        manageUsers.update();
                        success = true;
                        break;
                    case 3:
                        manageUsers.delete();
                        success = true;
                        break;
                    case 4:
                        manageUsers.display();
                        success = true;
                        break;
                    case 5:
                        break;

                    case 6:
                        System.out.println("Exiting system.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" -> Invalid choice. Please try again. ");
                }

                if (success && !promptReturnToMenu()) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(" -> Invalid input. Please enter again. ");
                choice = -1;  // Set to an invalid choice to continue the loop
            }
        } while (choice != 5);
    }

    private static void manageLoansMenu(ManageLoans manageLoans) {
        int choice;
        do {
            System.out.println("\nManage Loans");
            System.out.println("1. Borrow Book");
            System.out.println("2. Update Loan");
            System.out.println("3. Display Borrowed Books");
            System.out.println("4. Report on Borrowed Books");
            System.out.println("5. Report on Overdue Books");
            System.out.println("6. Report on Total Borrowing Activities");
            System.out.println("7. Back to Main Menu");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());

                boolean success = false;

                switch (choice) {
                    case 1:
                        manageLoans.add();
                        success = true;
                        break;
                    case 2:
                        manageLoans.update();
                        success = true;
                        break;
                    case 3:
                        manageLoans.display();
                        success = true;
                        break;
                    case 4:
                        manageLoans.reportBorrowedBooks();
                        success = true;
                        break;
                    case 5:
                        manageLoans.reportOverdueBooks();
                        success = true;
                        break;
                    case 6:
                        manageLoans.reportBorrowingActivities();
                        success = true;
                        break;
                    case 7:
                        break;

                    case 8:
                        System.out.println("Exiting system.");
                        System.exit(0);
                        break;
                    default:
                        System.out.println(" -> Invalid choice. Please try again. ");
                }

                if (success && !promptReturnToMenu()) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(" -> Invalid input. Please enter again. ");
                choice = -1;  // Set to an invalid choice to continue the loop
            }
        } while (choice != 7);
    }

    private static boolean promptReturnToMenu() {
        while (true) {
            System.out.print(" -> Do you want go back to main menu (Y/N): ");
            String backToMenu = sc.nextLine().trim().toLowerCase();
            if (backToMenu.equals("y")) {
                return true;
            } else if (backToMenu.equals("n")) {
                return false;
            } else {
                System.out.print(" -> Invalid input. Please enter 'Y' 'N' : ");
            }
        }
    }

}


