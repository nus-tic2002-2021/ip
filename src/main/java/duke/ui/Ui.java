package duke.ui;


import java.util.Scanner;

/**
 * Interface class for User Interface
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class Ui {

    public Ui() {

    }

    /**
     * request user input and read the input string
     *
     * @param scanner Scanner that read user input
     */
    public String requestUserInput(Scanner scanner) {

        String userInput = "";

        try {
            Message.msgArrowHead();
            userInput = scanner.nextLine();
        } catch (Exception e) {
            Message.msgError(e);
        }
        return userInput;
    }
}
