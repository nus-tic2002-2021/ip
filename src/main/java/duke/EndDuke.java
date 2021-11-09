package duke;

import duke.ui.Message;

import java.io.IOException;

public class EndDuke {

    public static void run() {
        showByeMessage();
    }

    /**
     * Show the Bye Message
     * <p>
     * called when Duke program ends
     */
    public static void showByeMessage() {
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }
}
