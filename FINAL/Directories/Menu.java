package FINAL.Directories;

import java.util.*;

import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Database.Authentication;
import FINAL.Database.User;

// final int x => the x-coordinate of the box
// final int y => the y-coordinate of the box
// final int width => the width of the box
// final int height => the height of the box
// final String content => the content of the box
// final String textColor => the text color of the box
// final String bgColor => the background color of the box

public class Menu {
    // private static List<User> users = User.loadUser();
    private static User currentUser;

    public static void startProgram() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        Screen.clearScreen();
        Welcome.welcomeMenu();
        
        // control structure for the main menu
        while (true) {
            Screen.clearScreen();
            Welcome.welcomeMenu();

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Screen.clearScreen();
                    Authentication.registerUser(scanner);
                    break;
                case 2:
                Screen.clearScreen();
                    Authentication.loginUser(scanner);
                    break;
                case 3:
                Screen.clearScreen(); 
                    System.out.println("Thank you for using Hi?Bank: Where Banking Meets Innovation!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }
        }
    }
    /**
     * Main menu for the application
     * @param scanner => accepts user input through parameters
     * @param user => accepts the user object
     */
    public static void mainMenu(Scanner scanner, User user) {
        int choice;

        while (true) {
            Screen.clearScreen();

            Design.drawBox(
                33, 5, 50, 4, 
                "Welcome to Hi?Bank, " + currentUser.getUsername() + "!", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );
            
            Design.drawBox(
                33, 10, 50, 2, 
                "[1] Balance", 
                Design.GREEN_TEXT, Design.BLACK_BG
            );

            Design.drawBox(
                33, 12, 50, 2, 
                "[2] Deposit", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 14, 50, 2, 
                "[3] Withdraw", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 16, 50, 2, 
                "[4] Account Details", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            /* <deprecated>
            Design.drawBox(
                33, 18, 50, 2, 
                "[5] Transfer (DEPRACATED)", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );*/

            Design.drawBox(
                33, 18, 50, 2, 
                "[5] Currency Converter", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 20, 50, 2, 
                "[6] Transaction History", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 22, 50, 2, 
                "[7] Log out", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 24, 50, 2, 
                "Choice: ", 
                Design.GREEN_TEXT,
                Design.BLACK_BG
            );

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Screen.clearScreen();
                    Design.drawBox(
                        33, 5, 50, 4, 
                        "ACCOUNT BALANCE", 
                        Design.GREEN_TEXT, 
                        Design.BLACK_BG
                    );

                    Design.drawBox(
                        33, 10, 50, 2, 
                        "Your balance is: " + user.getBalance(), 
                        Design.GREEN_TEXT, 
                        Design.BLACK_BG
                    );

                    Design.drawBox(
                        33, 13, 0, 0, 
                        "", 
                        Design.GREEN_TEXT, 
                        Design.BLACK_BG
                    );
                    Screen.screenPause();
                    break;
                case 2:
                    Dashboard.deposit(scanner, user);
                    break;
                case 3:
                    Dashboard.withdraw(scanner, user);
                    break;
                case 4:
                    Dashboard.accountDetails(user);
                    break;
                /* case 5: <depracated>
                    Dashboard.transfer(scanner, user);
                    break; */
                case 5:
                    Dashboard.currencyConverter(scanner);
                    break;
                case 6:
                    Screen.clearScreen();
                    Design.drawBox(
                        33, 5, 50, 4, 
                        "Transaction History:", 
                        Design.GREEN_TEXT, 
                        Design.BLACK_BG
                    );

                    int yPosition = 10;

                    List<String> transactions = user.getTransactionHistoryList();

                    for (String transaction : transactions) {
                        Design.drawBox(
                            33, yPosition, 50, 2, 
                            transaction, 
                            Design.GREEN_TEXT, 
                            Design.BLACK_BG
                        );
                        yPosition += 2;
                    }

                    yPosition += 1;
                    Design.drawBox(
                        33, yPosition, 0, 0, 
                        "", 
                        Design.GREEN_TEXT, 
                        Design.BLACK_BG
                    );
                    Screen.screenPause();
                    break;
                case 7:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}