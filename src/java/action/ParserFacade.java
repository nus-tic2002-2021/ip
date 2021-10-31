package src.java.action;

import src.java.storage.FileAccess;

/**
 * Create an instance of Duke Program
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */
public class ParserFacade {

    private FileAccess fileAccess;
    private Parser parser;

    public ParserFacade() {
        fileAccess = new FileAccess();
        parser = new Parser(fileAccess);
    }

    /**
     * Initialize the Duke Program.
     */
    public void StartDuke() {
        parser.ShowGreetMessage();
    }


    /**
     * Run the Duke Program
     */
    public void RunDuke() {
        parser.OnCreateDuke();
    }

    /**
     * End the Duke Program
     */
    public void EndDuke() {
        parser.ShowByeMessage();
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
    public Parser getDukeObject() {
        return parser;
    }
}
