package duke.action;

import duke.storage.FileAccess;

/**
 * Create an instance of Duke Program
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */
public class ParserFacade {

    private FileAccess fileAccess;
    private ParserOld parserOld;

    /**
     * Constructor
     */
    public ParserFacade() {
        fileAccess = new FileAccess();
        parserOld = new ParserOld(fileAccess);
    }

    /**
     * Initialize the Duke Program.
     */
    public void startDuke() {
        parserOld.showGreetMessage();
    }


    /**
     * Run the Duke Program
     */
    public void runDuke() {
        parserOld.onCreateDuke();
    }

    /**
     * End the Duke Program
     */
    public void endDuke() {
        parserOld.showByeMessage();
    }

    /**
     * Return a FileAccess object
     *
     * @return FileAccess
     */
    public FileAccess getFileAccess() {
        return fileAccess;
    }

    /**
     * Return a Parser object
     *
     * @return Parser
     */
    public ParserOld getDukeObject() {
        return parserOld;
    }
}
