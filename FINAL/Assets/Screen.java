package FINAL.Assets;
import java.util.Scanner;

// This class is used to manipulate the screen of the CLI or Command Prompt such as clearing the screen or sleeping the screen
public final class Screen {
    public static void clearScreen() {

        // Clear screen => Windows CLI 
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // this forces the command prompt to clear the screen with process builder object and inheritIO() method to redirect the output to the console and start() method to start the process of clearing the screen
        } 

        // Will catch ANY exception that occurs when trying to clear the screen
        catch (Exception e) {
            System.out.println("Error clearing screen handled: " + e);
        }
    }

    // user defined method to sleep the screen with a specified amount of time through the parameter
    public static void screenSleep(int time) { // whatever the value of the parameter will be passed through the time variable from the method call thread.sleep()
        // Sleep screen for a specified amount of time in the system
        try {
            Thread.sleep(time); // e.g. 1000 milliseconds = 1 second then it will pause the screen for 1 second
        } 
        
        // Will catch InterruptedException when trying to sleep the screen
        catch (InterruptedException e) {
            System.out.println("Error sleeping screen handled: " + e);
        }
    }

    // Similar to C++'s system("pause") function but we implemented it in Java
    public static void screenPause() {
        @SuppressWarnings("resource")   // Suppresses or ignores the warning that scanner is never closed
        Scanner scanner = new Scanner(System.in); // Create a new scanner object to accept user input
        System.out.print("Press any key to go back..."); // Print a message to the user to press any key to continue 
        scanner.nextLine(); // Wait for the user to press any key to continue
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

        String[] lines = text.split("\r\n"); // Split the text into lines so we can center it vertically on the screen (split by the newline character)

        int totalTextHeight = lines.length; // Calculate the total height of the text (number of lines)
        int verticalPadding = (CLI_HEIGHT - totalTextHeight) / 2; // Calculate the vertical padding to center the text 24 - 6 (hibank height) = 18 lines of padding

        // Print vertical padding (empty lines before the text)
        for (int i = 0; i < verticalPadding; i++) { // Loop through the vertical padding
            System.out.println(); // Print empty lines for padding
        }

        // Print the centered text
        for (String line : lines) {
            int padding = (CLI_WIDTH - line.length()) / 2; // Calculate the horizontal padding to center the text
            String centeredLine = " ".repeat(padding) + line + " ".repeat(padding); // Add padding to the left and right of the text
            System.out.println(centeredLine); // finally, print the centered text which is our hi?bank logo
        }

        // Loading bar below the title
        String blockChar = "\u2588"; // Unicode block character for the loading bar
        int totalBarLength = 40; // Set total length of the loading bar to 40 characters
        // int currentLength = 0;  => Initial length of the loading bar is 0

        // Print an initial empty loading bar to position it
        String initialLoadingBar = " ".repeat(totalBarLength) + " 0%"; // 40 spaces + 0% => 40 characters of padding on the left
        int loadingBarPadding = (CLI_WIDTH - initialLoadingBar.length()) / 2; // 120 - 40 = 80 characters of padding on the left
        String centeredInitialLoadingBar = " ".repeat(loadingBarPadding) + initialLoadingBar; // 80 characters of padding on the left + initial loading bar
        System.out.println(centeredInitialLoadingBar); // Print the initial loading bar with padding

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
            String loadingBarWithPercentage = loadingBar + " " + percentage + "%"; // loading bar + percentage 

            // Calculate horizontal padding for centering the loading bar with percentage
            loadingBarPadding = (CLI_WIDTH - loadingBarWithPercentage.length()) / 2; // 120 - 43 = 77 characters of padding on the left
            String centeredLoadingBar = " ".repeat(loadingBarPadding) + loadingBarWithPercentage; // 77 characters of padding on the left + loading bar with percentage

            // Move the cursor up to overwrite the previous loading bar
            System.out.print("\033[F"); // Moves the cursor up one line
            System.out.println(centeredLoadingBar); // Print the updated loading bar

            // Wait for 100 milliseconds to simulate loading progression
            try {
                Thread.sleep(32); // Sleep for 32 milliseconds or the loading bar progresses every 0.032 seconds
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
    
        @SuppressWarnings("unused") // Suppresses or ignores the warning that choice is never used, no bother
        String choice; 
        @SuppressWarnings("resource") // Suppresses or ignores the warning that scanner is never closed, no bother
        Scanner scanner = new Scanner(System.in);

        // empty input to continue

        System.out.print("");
        choice = scanner.nextLine();
    }

    // basically the exit screen of the application and the credits of the developers of the application
    // this will progress reversed form the loading animation
    public static void appExit() {
        String title = "██╗  ██╗██╗██████╗ ██████╗  █████╗ ███╗   ██╗██╗  ██╗\r\n" + 
                   "██║  ██║██║╚════██╗██╔══██╗██╔══██╗████╗  ██║██║ ██╔╝\r\n" + 
                   "███████║██║  ▄███╔╝██████╔╝███████║██╔██╗ ██║█████╔╝ \r\n" + 
                   "██╔══██║██║  ▀▀══╝ ██╔══██╗██╔══██║██║╚██╗██║██╔═██╗ \r\n" + 
                   "██║  ██║██║  ██╗   ██████╔╝██║  ██║██║ ╚████║██║  ██╗\r\n" + 
                   "╚═╝  ╚═╝╚═╝  ╚═╝   ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝";

        int CLI_WIDTH = 120;
        int CLI_HEIGHT = 24;

        String[] lines = title.split("\r\n");
        int totalTextHeight = lines.length;
        int verticalPadding = (CLI_HEIGHT - totalTextHeight) / 2;

        for (int i = 0; i < verticalPadding; i++) {
            System.out.println();
        }

        for (String line : lines) {
            int padding = (CLI_WIDTH - line.length()) / 2;
            System.out.println(" ".repeat(padding) + line);
        }

        hi();
    }
    
    private static void hi() {
        final int CLI_WIDTH = 128; // added 8 for extra padding (centering text)

        String thanksMessage = "Thank you for using " + Color.GRAY_TEXT + "A Virtual Banking Solutions Developed with Object-Oriented Principles" + Color.RESET;
        String exitingMessage = Color.ORANGE_TEXT + "Hi? " + Color.GRAY_TEXT + "is now exiting..." + Color.RED_TEXT + " Goodbye!" + Color.RESET;

        String[] groupMembers = {
            Color.YELLOW_TEXT + "Capapas, Naomie Feona G." + Color.RESET,
            Color.PURPLE_TEXT + "Cunanan, Louise Veronica L." + Color.RESET,
            Color.MAGENTA_TEXT + "Giron, Petercen Nikolai R." + Color.RESET,
            Color.CYAN_TEXT + "Montesa, Elaine Bernadette" + Color.RESET
        };

        int padding = (CLI_WIDTH - thanksMessage.length()) / 2;
        System.out.println(" ".repeat(padding) + thanksMessage);
        System.out.println();

        for (String member : groupMembers) {
            padding = (CLI_WIDTH - member.length()) / 2;
            System.out.println(" ".repeat(padding) + member);
        }

        System.out.println();
        padding = (CLI_WIDTH - exitingMessage.length()) / 2 + 5; // + 5 for extra padding (centering)
        System.out.println(" ".repeat(padding) + exitingMessage);

        @SuppressWarnings("unused") // Suppresses or ignores the warning that choice is never used, no bother
        String choice; 
        @SuppressWarnings("resource") // Suppresses or ignores the warning that scanner is never closed, no bother
        Scanner scanner = new Scanner(System.in);

        // empty input to continue

        System.out.print("");
        choice = scanner.nextLine();
        System.exit(-1);
    }
}