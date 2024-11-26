package FINAL.Directories;

import java.util.*;

import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Database.Authentication;
import FINAL.Database.User;

public class Menu {
    private static List<User> users = User.loadUser();
    private static User currentUser;

    public static void startProgram() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        Screen.clearScreen();
        Welcome.welcomeMenu();
        
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

    public static void mainMenu(Scanner scanner, User user) {
        int choice;

        while (true) {
            Screen.clearScreen();
            Design.drawBox(33, 7, 50, 3, "Welcome to Hi?Bank, " + currentUser.getUsername() + "!", Design.GREEN_TEXT, Design.BLACK_BG);
            
            Design.drawBox(33, 10, 50, 2, "[1] Balance", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 12, 50, 2, "[2] Deposit", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 14, 50, 2, "[3] Withdraw", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 16, 50, 2, "[4] Account Details", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 18, 50, 2, "[5] Transfer", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 20, 50, 2, "[6] Currency Converter", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 22, 50, 2, "[7] Transaction History", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 24, 50, 2, "[8] Log out", Design.GREEN_TEXT, Design.BLACK_BG);
            Design.drawBox(33, 26, 50, 2, "Choice: ", Design.GREEN_TEXT, Design.BLACK_BG);

            choice = scanner.nextInt();
            Design.drawBox(33, 26, 50, 2, scanner.nextLine(), Design.GREEN_TEXT, Design.BLACK_BG);


            switch (choice) {
                case 1:
                    System.out.println("Balance: " + currentUser.getBalance());
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
                case 5:
                    Dashboard.transfer(scanner, user);
                    break;
                case 6:
                    Dashboard.currencyConverter(scanner);
                    break;
                case 7:
                    System.out.println("Transaction History: \n" + currentUser.getTransactionHistory());
                    break;
                case 8:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }
}