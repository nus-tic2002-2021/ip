package dukeMain;

import dukeMain.tasks.Task;
import dukeMain.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {
    public final static String lineBreak = "---------------------------------------------------------";
    private String logo = "  _       _ _      _ _______________________________________ \n"
            + " | |  _  | | |    | |  __  |        |  __  |  __  |___   ___|\n"
            + " | | | | | | |----| | |__| |  ------| |__| | |__|/    | |    \n"
            + " | |_| |_| | |----| | |  | |------  | |  | | |__|\\ ___| |___\n"
            + " |_________|_|    |_|_|  |_|________|_|  |_|______|_________|\n";
    private static String loadingError;
    private String userCommand;
    public Ui() {

    }


    public String readCommand(){
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void showLoadingError(){
        printThis("There seems to be some issue loading your file.\n We have create a new file name : data.txt ",3);
    }

    public static void showInvalidStorageError(){
        printThis("Storage file should end with '.txt'",3);
    }
    // 1 = Linebreak above
    // 2 = Linebreak below
    // 3 = Linebreak above and below
    // 0 = no linebreak
    public static void printThis(String st, int style){
        if(style == 1) System.out.println(lineBreak+"\n"+st);
        else if(style == 2) System.out.println(st+"\n"+lineBreak);
        else if(style == 3) System.out.println(lineBreak+"\n"+st+"\n"+lineBreak);
        else System.out.println(st);
    }

    public static void printSaveResult(boolean saveResult){
        String txt = "Oops something when wrong when saving result"; //
        if(saveResult) txt = "File have been saved";
        printThis(txt,3);
    }

    public void showLine() {
        System.out.println(lineBreak);
    }
    public void showWelcome(){
        printThis("Hello from\n" + logo , 0);
        printThis("What can do for you?",0);
    }
    public void showExitMessage(){
        printThis("Bye. Hope to see you again soon!",0);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void print(String text){
        System.out.println(text);
    }

    public void printList(ArrayList<Task> list){
        for (Task tk : list)
            print((list.indexOf(tk)+1)+") "+tk.toString());
    }
}
