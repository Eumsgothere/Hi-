package FINAL.Database;

import java.util.*;

import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Directories.Menu;

// final int x => the x-coordinate of the box
// final int y => the y-coordinate of the box
// final int width => the width of the box
// final int height => the height of the box
// final String content => the content of the box
// final String textColor => the text color of the box
// final String bgColor => the background color of the box

public class Authentication {
    private static List<User> users = User.loadUser(); // load the user data from the file into the users list array, impelementation is in the User class @line 64 col 40
    private static User currentUser; // create a private static variable currentUser of type User for the current user logged in

    // method for registering a user
    public static void registerUser(Scanner scanner) {
        Design.drawBox(
            33, 5, 50, 4,
            "Hi?Bank's Account Registration",
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 10, 50, 2, 
            "Enter username: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        
        String username = scanner.nextLine();
    
        // Design.drawBox(
        //     33, 13, 50, 2, 
        //     "Enter password: ", 
        //     Design.GREEN_TEXT, 
        //     Design.BLACK_BG  
        // );

        String password = ""; // we initialize password as an empty string

        // control structure for the password input validation (invalid, weak, moderate, strong)
        while(true) {
            Design.drawBox(
                33, 13, 50, 2, 
                "Enter password: ", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG  
            );
            password = scanner.nextLine();

            if (password.length() <= 3) {
                Design.drawBox(
                    33, 16, 50, 2, 
                    Design.RED_TEXT + "Invalid password (1 - 3 characters)" + Design.RESET, 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG  
                );
            } else if (password.length() <= 7) {
                Design.drawBox(
                    33, 16, 50, 2, 
                    Design.GREEN_TEXT+ "Weak password (4 - 7 characters)" + Design.RESET, 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG  
                );
                Screen.screenSleep(1000);
            } else if (password.length() <= 10) {
                Design.drawBox(
                    33, 16, 50, 2, 
                    Design.ORANGE_TEXT+ "Moderate password (8 - 10 characters)" + Design.RESET, 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG  
                );
                Screen.screenSleep(1000);
            } else {
                Design.drawBox(
                    33, 16, 50, 2, 
                    Design.BLUE_TEXT + "Strong password (8 - 10 characters)" + Design.RESET, 
                    Design.GREEN_TEXT, 
                    Design.BLACK_BG  
                );
                Screen.screenSleep(1000);
            }
            if (password.length() >= 4) {
                break;
            }
        }
        
        // confirm password => must match the password entered above
        Design.drawBox(
            33, 16, 50, 2, 
            "Confirm password: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        
        String confirmPassword = scanner.nextLine();
        if (!password.equals(confirmPassword)) {   // validat password and confirm password
            Design.drawBox(
                33, 19, 50, 2, 
                "Password do not match!", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            // pause the screen
            Design.drawBox(
                33, 22, 0, 0, 
                "", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Screen.screenPause();
            return;
        }
    
        String accId = "2024-" + String.format("%04d", users.size() + 1); // generate a unique account ID for the user based on the number of users in the list array and following the format of 2024-xxxx
    
        User newUser = new User(username, password, accId, 0.0, ""); //finally adds the new user to the users list array and saves the user data to the file see @line 120
        users.add(newUser);
        newUser.saveUser();
    
        Design.drawBox(
            58, 20, 0, 0, 
            "Registration successful. Redirecting you back to the main menu...",  
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Screen.screenSleep(1500);
    }

    // method for logging in a user to the system and authenticating the user based on their credentials from the database
    public static void loginUser(Scanner scanner) {
        Design.drawBox(
            33, 5, 50, 4, 
            "Hi?Bank's Account Login", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Design.drawBox(
            33, 10, 50, 2, 
            "Enter username: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        String username = scanner.nextLine();

        Design.drawBox(
            33, 13, 50, 2, 
            "Enter password: ", 
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );

        Screen.screenSleep(1000);

        String password = scanner.nextLine();
        currentUser = authenticate(username, password); // authenticate the user based on the username and password entered

        Design.drawBox(
            57, 16, 0, 0, 
            "Logging you in...",  
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenSleep(1250);

        Design.drawBox(
            57, 16, 0, 0, 
            "Login successful!",
            Design.GREEN_TEXT, 
            Design.BLACK_BG
        );
        Screen.screenSleep(750);

        if (currentUser != null) {
            Menu menu = new Menu();
            Screen.clearScreen();
            menu.setCurrentUser(currentUser);
            Menu.mainMenu(scanner, currentUser);
        } else {
            Design.drawBox(
                22, 5, 75, 4, 
                "Invalid username or password. Redirecting you back to the main menu...", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Design.drawBox(
                33, 19, 0, 0, 
                "", 
                Design.GREEN_TEXT, 
                Design.BLACK_BG
            );

            Screen.screenPause();
        }
    }

    // method for authenticating the user based on the username and password entered
    public static User authenticate (String username, String password) {
        for (User user : users) { // this will iterate over the users list array to check if the user credential exists in the list array and return the user if it does
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // return null if the user credential does not exist in the list array
    }
}