package FINAL.Assets;

// Superclass for Design subclass => contains the color codes for the CLI or Command Prompt
public abstract class Color {
    public static final String RESET = "\033[0m"; // Reset all colors
    public static final String BLACK_TEXT = "\033[30m";
    public static final String RED_TEXT = "\033[31m";
    public static final String GREEN_TEXT = "\033[32m";
    public static final String YELLOW_TEXT = "\033[33m";
    public static final String BLUE_TEXT = "\033[34m";
    public static final String MAGENTA_TEXT = "\033[35m";
    public static final String CYAN_TEXT = "\033[36m";
    public static final String WHITE_TEXT = "\033[37m";
    public static final String BLACK_BG = "\033[40m";
    public static final String RED_BG = "\033[41m";
    public static final String GREEN_BG = "\033[42m";
    public static final String YELLOW_BG = "\033[43m";
    public static final String BLUE_BG = "\033[44m";
    public static final String MAGENTA_BG = "\033[45m";
    public static final String CYAN_BG = "\033[46m";
    public static final String WHITE_BG = "\033[47m";
}