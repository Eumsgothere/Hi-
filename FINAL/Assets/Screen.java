package FINAL.Assets;

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
}