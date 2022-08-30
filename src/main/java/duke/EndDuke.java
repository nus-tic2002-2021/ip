package duke;

import duke.exception.UnableToLoadBuddhaException;
import duke.storage.FileAccess;
import duke.ui.Message;

/**
 * Duke class that executes when Duke is ending
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class EndDuke {

    private static FileAccess fileAccess;

    /**
     * Constructor
     */
    public EndDuke(FileAccess fileAccess) {
        this.fileAccess = fileAccess;
    }

    /**
     * Start the end sequence
     */
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
            String buddhaText = fileAccess.readBuddhaText();
            Message.msgBuddha(buddhaText);
        } catch (UnableToLoadBuddhaException e) {
            Message.msgUnableToLoadBuddha();
        }
    }
}
