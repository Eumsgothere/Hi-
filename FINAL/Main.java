package FINAL;

// import FINAL.Assets.Design;
import FINAL.Assets.Screen;
import FINAL.Directories.Menu;

/*  Group Name: Hi?

    Hi?'s official made application,
    Application Name => Hi?Bank: Where Banking Meets Innovation!
    Hi?Bank: A Virtual Banking Solutions Developed with Object-Oriented Principles

    Version: 1.04

    In Partial Fulfillment of the Final Requirements for the Course Subject Object-Oriented Programming (CCS0023) under the Degree of Bachelor of Science in Information Technology at Far Eastern University, Institute of Technology.

    Submitted by:
        CAPAPAS, Naomie Feona G.
        CUNANAN, Louise Veronica L.
        GIRON, Petercen Nikolai R.
        MONTESA, Elaine Bernadette

    Submitted to:
        MENDOZA, Jabes A.

    Presented on:
        November 28, 2024
*/

/**
     * import java.io.* => this imports ALL (indicated by asterisk (*)) of the classes in the java.io package.
     * But in this case of this program we are using the following classes:
     * - BufferedReader => Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
     * - FileReader => Convenience class for reading character files.
     * - FileWriter => Convenience class for writing character files.
     * - IOException => Signals that an I/O exception of some sort has occurred.
     */

    /**
     * import java.util.* => this imports ALL (indicated by asterisk (*)) of the classes in the java.util package.
     * But in this case of this program we are using the following classes:
     * - ArrayList => The ArrayList class implements the List interface. It uses a dynamic array to store the duplicate element of different data types.
     * - List => The List interface extends Collection and declares the behavior of a collection that stores a sequence of elements.
     * - Scanner => A simple text scanner which can parse primitive types and strings using regular expressions.
     * - Collections => This class consists exclusively of static methods that operate on or return collections.
     * - Arrays => This class contains various methods for manipulating arrays (such as sorting and searching).
     * - HashMaps => This is for key pairing values. This is used in our convert currency method where we store the currency and its pair value.
     */

public class Main {
    public static void main(String[] args) {
        Screen.appLoading();
        Menu.startProgram();
    }
}

/**
 * Java and TXT Files
 * Main.java => This is the main file that runs the entire program. It calls the startProgram method from the Menu class.
 * Menu.java => This file contains the main menu and the start program method. It also contains the main menu method.
 * Dashboard.java => This file contains the methods for deposit, withdraw, account details, and transfer.
 * Screen.java => This file contains the methods for clearing the screen, sleeping the screen, and pausing the screen.
 * User.java => This file contains the User class that extends the Account class and implements the Transactionable interface.
 * Authentication.java => This file contains the methods for registering and logging in users.
 * Account.java => This file contains the Account class that has the basic attributes of a user account.
 * Transactionable.java => This file contains the Transactionable interface that has the methods for getting and saving transaction history.
 * Design.java => This file contains the methods for drawing boxes and text designs.
 * Welcome.java => This file contains the welcome menu method.
 * ConvertCurrency.java => This file contains the method for converting currency.
 * hibank_user.txt => This is the text file where the user data is stored.
 * hibank_transaction.txt => This is the text file where the transaction data is stored.
 */

/**
 * @Group: Hi? 
 * Hi?Bank: A Virtual Banking Solutions Developed with Object-Oriented Principles
 */