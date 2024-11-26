package FINAL.Database;

import java.util.*;

import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Directories.Menu;

public class Authentication {
    private static List<User> users = User.loadUser();
    private static User currentUser;

    public static void registerUser(Scanner scanner) {
        Design.drawBox(33, 7, 50, 2, "Hi?Bank's Account Registration", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 10, 50, 2, "Enter username: ", Design.GREEN_TEXT, Design.BLACK_BG);
        String username = scanner.nextLine();

        Design.drawBox(33, 13, 50, 2, "Enter password: ", Design.GREEN_TEXT, Design.BLACK_BG);
        String password = scanner.nextLine();

        Design.drawBox(33, 16, 50, 2, "Confirm password: ", Design.GREEN_TEXT, Design.BLACK_BG);
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return;
        }

        String accId = "2024-" + String.format("%04d", users.size() + 1);

        User newUser = new User(username, password, accId, 0.0, "");
        newUser.saveUser();
        users.add(newUser);

        Design.drawBox(58, 20, 0, 0, "Registration successful. Redirecting you back to the main menu...", Design.GREEN_TEXT, Design.BLACK_BG);
        Screen.screenSleep(1500);
    }

    public static void loginUser(Scanner scanner) {
        Design.drawBox(33, 7, 50, 2, "Hi?Bank's Account Login", Design.GREEN_TEXT, Design.BLACK_BG);
    Design.drawBox(33, 10, 50, 2, "Enter username: ", Design.GREEN_TEXT, Design.BLACK_BG);
    String username = scanner.nextLine();
    Design.drawBox(33, 13, 50, 2, "Enter password: ", Design.GREEN_TEXT, Design.BLACK_BG);
    String password = scanner.nextLine();

    currentUser = authenticate(username, password);

    Design.drawBox(33, 16, 0, 0, "Logging you in...", Design.GREEN_TEXT, Design.BLACK_BG);
    Screen.screenSleep(1500);

    if (currentUser != null) {
        Menu menu = new Menu();
        Screen.clearScreen();
        Design.drawBox(33, 7, 50, 3, "Welcome " + currentUser.getUsername() + "!", Design.GREEN_TEXT, Design.BLACK_BG);
        // Update Menu's currentUser
        menu.setCurrentUser(currentUser);
        Menu.mainMenu(scanner, currentUser);
    } else {
        Design.drawBox(33, 7, 50, 2, "Invalid username or password. Redirecting you back to the main menu...", Design.GREEN_TEXT, Design.BLACK_BG);
        Screen.screenSleep(1500);
    }
    }

    public static User authenticate (String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}