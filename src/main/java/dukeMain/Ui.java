package dukeMain;

import dukeMain.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

/** Interacts with the users by
 * reading their command and displaying the content to the user.
 *
 * Where the programmes start to run.
 * */

public class Ui {
    public final static String lineBreak = "---------------------------------------------------------";
    private String logo = "  _       _ _      _ _______________________________________ \n"
            + " | |  _  | | |    | |  __  |        |  __  |  __  |___   ___|\n"
            + " | | | | | | |----| | |__| |  ------| |__| | |__|/    | |    \n"
            + " | |_| |_| | |----| | |  | |------  | |  | | |__|\\ ___| |___\n"
            + " |_________|_|    |_|_|  |_|________|_|  |_|______|_________|\n";
    public Ui() {

    }

    /**
     * Read Command from users
     */
    public String readCommand(){
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
    /**
     * Print loading Error Message
     */
    public static void showLoadingError(){
        printThis("There seems to be some issue loading your file.\n We have create a new file name : data.txt ",3);
    }

    /**
     * Print invalid Storage Error
     */
    public static void showInvalidStorageError(){
        printThis("Storage file should end with '.txt'",3);
    }

    /**
     * Print String values given from st. In different styles as follows :
     *     // 1 = Linebreak above
     *     // 2 = Linebreak below
     *     // 3 = Linebreak above and below
     *     // 0 = no linebreak
     * @param st String
     * @param style int
     */
    public static void printThis(String st, int style) {
        if (style == 1) System.out.println(lineBreak + "\n" + st);
        else if (style == 2) System.out.println(st + "\n" + lineBreak);
        else if (style == 3) System.out.println(lineBreak + "\n" + st + "\n" + lineBreak);
        else System.out.println(st);
    }

    /**
     * Print lineBreak ------------------------------
     */
    public void showLine() {
        System.out.println(lineBreak);
    }

    /**
     * Print Welcome Message
     */
    public void showWelcome(){
        printThis("Hello from\n" + logo , 0);
        printThis("What can do for you?",0);
    }

    /**
     * print exit Message
     */
    public void showExitMessage(){
        printThis("Bye. Hope to see you again soon!",0);
    }

    /**
     * Print Error Message
     *
     * @param message String
     */
    public void showError(String message) {
        System.out.println(message);
    }


    /**
     * Print Message
     *
     * @param text String
     */
    public void print(String text){
        System.out.println(text);
    }


    /**
     * Print TaskList
     *
     * @param list ArrayList<Task>
     */
    public void printList(ArrayList<Task> list){
        for (Task tk : list)
            print((list.indexOf(tk)+1)+") "+tk.toString());
    }
}
