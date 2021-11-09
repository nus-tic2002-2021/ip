package duke;

import duke.ui.Message;

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
