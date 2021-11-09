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

    public String requestUserInput(){

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        in.close();
        return  line;

    }

}
