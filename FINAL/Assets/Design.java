package FINAL.Assets;

// This class is used to manipulate the design of the CLI or Command Prompt such as moving the cursor to a specific position or drawing a box with a specified width, height, content, text color, and background color from the Color superclass
public class Design extends Color {

    /**
     * gotoxy method => move cursor to x, y position
     * @param x => x-axis position (at CLI or Command Prompt)
     * @param y => y-axis position (at CLI or Command Prompt)
     */

    public static void gotoxy(int x, int y) {
        System.out.printf("\033[%d;%dH", y, x);
    }

    /**
     * drawBox method => Draw a box with a specified width, height, content, text color, and background color (for Design purposes)
     * @param x => x-axis position (at CLI or Command Prompt)
     * @param y => y-axis position (at CLI or Command Prompt)
     * @param width => width of the box that contains the content inside 
     * @param height => height of the box that contains the content inside
     * @param content => content (e.g. double quoted String text to be displayed inside the box
     * @param textColor => color of the text (CLI) from the Color superclass
     * @param bgColor => background color of the text (CLI) from the Color superclass 
     */

    public static void drawBox(int x, int y, int width, int height, String content, String textColor, String bgColor) {
        String colorCode = bgColor + textColor;
        
        gotoxy(x, y);

        System.out.print(colorCode + "╔" + "═".repeat(width) + "╗");

        // For loop => loop through the height of the box and print the left and right side of the box with the content in the middle of the box
        for (int i = 1; i < height; i++) {
            gotoxy(x, y + i);
            System.out.print(colorCode + "║" + " ".repeat(width) + "║");
        }

        gotoxy(x, y + height);

        System.out.print(colorCode + "╚" + "═".repeat(width) + "╝");

        // It will print the content in the middle of the box if it is not empty
        if (!content.isEmpty()) {
            /**
             * @param width - content.length() => Calculate the remaining space in the box after the content is printed
             * 
             */
            int contentX = x + (width - content.length()) / 2 + 1; // Calculate the position of the content to be printed in the middle of the box horizontally (x-axis) and +1 to center it
            int contentY = y + height / 2; // Calculate the position of the content to be printed in the middle of the box vertically (y-axis) and divided by 2 to center it
            gotoxy(contentX, contentY); // Move the cursor to the (x and y) position of the content
            System.out.print(colorCode + content + RESET); // Finally, print the content in the middle of the box
        }
    }
}