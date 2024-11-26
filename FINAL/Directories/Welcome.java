package FINAL.Directories;

import FINAL.Assets.Design;

// Multiple Inheritance => Design class is a subclass of Color. It inherits the properties of Design and as well as Color superclass.
public class Welcome extends Design {
    private static void displayWelcomeMenu() {
        Design.drawBox(33, 5, 50, 3, "Welcome to Hi?Bank: Where Banking Meets Innovation!", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 10, 50, 2, "[1] Register", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 13, 50, 2, "[2] Login", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 16, 50, 2, "[3] Exit", Design.GREEN_TEXT, Design.BLACK_BG);
        Design.drawBox(33, 19, 50, 2, "Enter your choice: ", Design.GREEN_TEXT, Design.BLACK_BG);
    }

    public static void welcomeMenu() {
        displayWelcomeMenu();
    }
}