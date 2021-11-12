package duke;

import duke.ui.Message;

/**
 * Duke class that executes when Duke is initialized
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */
public class StartDuke {

    public static void run() {
        showGreetMessage();
    }

    /**
     * Show the opening Greet Message in System.out.println
     */
    public static void showGreetMessage() {
        Message.msgGreet();
    }
}
