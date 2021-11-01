package java;

import java.action.ParserFacade;

/**
 * Begin the program here.
 *
 * @author  Kang Teng
 * @version 8.0
 * @since   2021-09-01
 */
public class Duke {

    /**
     * Starting point of the program
     */
    public static void main(String[] args) {

        ParserFacade parserFacade = new ParserFacade();

        parserFacade.startDuke();
        parserFacade.runDuke();
        parserFacade.endDuke();
    }
}
