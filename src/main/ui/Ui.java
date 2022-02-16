package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    /**
     * This is a place for improving User experience and  the basic structure of DUKE Application
     */
    public static final String BUFF_PLUS_HORRIZONTALLINE = "\t-----------------------------------------------------------------------------------------";
    public static final String BUFFER = "\t";
    public static final String NEWLINE = "\n";
    public static final String NEWLINE_BUFFER = "\n\t";
    private final PrintStream out;
    private final Scanner in;
    private final String GREETING_MESSAGE =
            BUFF_PLUS_HORRIZONTALLINE
                    + NEWLINE + BUFFER +
                    " ____        _        " + NEWLINE_BUFFER
                    + "|  _ \\ _   _| | _____ " + NEWLINE_BUFFER
                    + "| | | | | | | |/ / _ \\" + NEWLINE_BUFFER
                    + "| |_| | |_| |   <  __/" + NEWLINE_BUFFER
                    + "|____/ \\__,_|_|\\_\\___|" + NEWLINE + NEWLINE_BUFFER
                    + "Hello! I'm Duke" + NEWLINE_BUFFER
                    + "What can I do for you?" + NEWLINE_BUFFER
                    + "This is a Improved version done by Sam" + NEWLINE
                    + BUFF_PLUS_HORRIZONTALLINE;

    private final String VALEDICTION = BUFFER + "Bye. Hope to see you again soon!"
            + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;


    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Method to read from the System.in
     * @return return String type
     */
    public String readUserInput()
    {
        out.println(BUFFER +"Enter your query: ");
        return in.nextLine();
    }

    public void showWelcomeMessage()
    {
        out.println(GREETING_MESSAGE);
    }

    public void showValedicMessage()
    {
        out.println(VALEDICTION);
    }


}
