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

    public Ui(){

    }

    public String requestUserInput(Scanner in){

        String userInput = "";

        try {
            System.out.print(">> ");
            userInput = in.nextLine();
        } catch (Exception e) {
            Message.msgError(e);
        }
        return userInput;
    }
}
