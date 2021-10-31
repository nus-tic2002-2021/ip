package src.java;

import src.java.action.ParserFacade;

/**
 * Begin the program here.
 *
 * @author  Kang Teng
 * @version 8.0
 * @since   2021-09-01
 */
public class Duke {

    public static void main(String[] args) {

        ParserFacade parserFacade = new ParserFacade();

        parserFacade.StartDuke();
        parserFacade.RunDuke();
        parserFacade.EndDuke();
    }
}
