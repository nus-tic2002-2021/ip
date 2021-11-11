package duke;

import duke.storage.FileAccess;
import duke.ui.Message;
import duke.exception.UnableToLoadBuddhaException;

public class EndDuke {

    private static FileAccess fileAccess;

    public EndDuke(FileAccess fileAccess){
        this.fileAccess = fileAccess;
    }

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
            Message.msgUnableToLoadBuddah();
        }
    }
}
