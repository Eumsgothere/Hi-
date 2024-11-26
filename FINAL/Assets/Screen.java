package FINAL.Assets;
import java.util.Scanner;

// This class is used to manipulate the screen of the CLI or Command Prompt such as clearing the screen or sleeping the screen
public final class Screen {
    public static void clearScreen() {

        // Clear screen => Windows CLI 
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } 

        // Will catch ANY exception that occurs when trying to clear the screen
        catch (Exception e) {
            System.out.println("Error clearing screen handled: " + e);
        }
    }

    public static void screenSleep(int time) {

        // Sleep screen for a specified amount of time in the system
        try {
            Thread.sleep(time);
        } 
        
        // Will catch InterruptedException when trying to sleep the screen
        catch (InterruptedException e) {
            System.out.println("Error sleeping screen handled: " + e);
        }
    }

    // Similar to C++'s system("pause") function but we implemented it in Java
    public static void screenPause() {
        @SuppressWarnings("resource")   // Suppresses or ignores the warning that scanner is never closed
        Scanner scanner = new Scanner(System.in);
        System.out.print("Press any key to go back...");
        scanner.nextLine();
        // scanner.close(); => this line is commented out because it will cause an error when the user tries to input a value
    }

    // Loading animation for the application when it starts up
    public static void appLoading() {
        String text = "██╗  ██╗██╗██████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗\r\n" + 
                      "██║  ██║██║╚════██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝\r\n" + 
                      "███████║██║  ▄███╔╝██████╔╝███████║██╔██╗ ██║█████╔╝ \r\n" + 
                      "██╔══██║██║  ▀▀══╝ ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ \r\n" + 
                      "██║  ██║██║  ██╗   ██████╔╝██║  ██║██║ ╚████║██║  ██╗\r\n" + 
                      "╚═╝  ╚═╝╚═╝  ╚═╝   ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝";

        int CLI_WIDTH = 120; // Set the width of the CLI to 120 characters
        int CLI_HEIGHT = 24;  // Set the height of the CLI to 24 characters

        String[] lines = text.split("\r\n"); // Split the text into lines

        int totalTextHeight = lines.length;
        int verticalPadding = (CLI_HEIGHT - totalTextHeight) / 2; // Calculate the vertical padding to center the text 24 - 6 (hibank height) = 18 lines of padding

        // Print vertical padding (empty lines before the text)
        for (int i = 0; i < verticalPadding; i++) {
            System.out.println();
        }

        // Print the centered text
        for (String line : lines) {
            int padding = (CLI_WIDTH - line.length()) / 2;
            String centeredLine = " ".repeat(padding) + line + " ".repeat(padding);
            System.out.println(centeredLine);
        }

        // Loading bar below the title
        String blockChar = "\u2588"; // Unicode block character for the loading bar
        int totalBarLength = 40; // Set total length of the loading bar to 40 characters
        // int currentLength = 0;  => Initial length of the loading bar is 0

        // Print an initial empty loading bar to position it
        String initialLoadingBar = " ".repeat(totalBarLength) + " 0%"; 
        int loadingBarPadding = (CLI_WIDTH - initialLoadingBar.length()) / 2; 
        String centeredInitialLoadingBar = " ".repeat(loadingBarPadding) + initialLoadingBar;
        System.out.println(centeredInitialLoadingBar);

        /**
         * Simulation of the loop:
         */
        // Start the loading animation
        for (int i = 1; i <= totalBarLength; i++) {
            // Generate the loading bar
            String loadingBar = blockChar.repeat(i) + " ".repeat(totalBarLength - i); // bloackChar.repeat(i) => repeat the blockChar i times and up to 40 characters

            // Calculate the percentage
            int percentage = (int) ((double) i / totalBarLength * 100); // type casted to double to get the decimal value of the percentage 1 through 40 / 40 * 100 = 2.5% through 100%

            // format the loading bar and percentage text
            String loadingBarWithPercentage = loadingBar + " " + percentage + "%";

            // Calculate horizontal padding for centering the loading bar with percentage
            loadingBarPadding = (CLI_WIDTH - loadingBarWithPercentage.length()) / 2; // 120 - 43 = 77 characters of padding on the left
            String centeredLoadingBar = " ".repeat(loadingBarPadding) + loadingBarWithPercentage; // 77 characters of padding on the left + loading bar with percentage

            // Move the cursor up to overwrite the previous loading bar
            System.out.print("\033[F"); // Moves the cursor up one line
            System.out.println(centeredLoadingBar); // Print the updated loading bar

            // Wait for 100 milliseconds to simulate loading progression
            try {
                Thread.sleep(75); // Sleep for 75 milliseconds or the loading bar progresses every 0.075 seconds
            } catch (InterruptedException e) {
                System.out.println("Error during loading animation handled: " + e);
            }
        }

        privateWelcome();
    }
    private static void privateWelcome() {
        final String text = "Hi?Bank: A Virtual Banking Solutions Developed with Object-Oriented Principles";
        final int CLI_WIDTH = 120;  
        // final int CLI_HEIGHT = 24;

        // centralized the text after loading progresses
        int subtitlePadding = (CLI_WIDTH - text.length()) / 2;
        String centeredSubtitle = " ".repeat(subtitlePadding) + text;

        // print the text after loading progresses
        System.out.println(centeredSubtitle);
        String choice; 
        Scanner scanner = new Scanner(System.in);

        // empty input to continue

        System.out.print("");
        choice = scanner.nextLine();
    }
}