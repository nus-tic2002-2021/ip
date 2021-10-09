import java.util.Scanner;

public class UI {
    private String lineBreak = "\t_______________________________________________________";

    public void printIntro(){
        printLine();
        System.out.println("\tHello! This are the implemented actions.\n\t" +
                " /at & /by are currently case sensitive"); // cannot implement with what we learnt yet.
        System.out.println("\t1.) Todo (text)--- to add todo task");    //
        System.out.println("\t2.) Deadline (text /by text)--- to add deadline task");
        System.out.println("\t3.) Event (text /at text)--- to add event task");
        System.out.println("\tEnter 'list' without quotes show list. Maximum size set to 100");
        System.out.println("\tEnter 'bye' without quotes to exit program\n");
        System.out.println("\tWelcome. Please enter your instruction.");
        printLine();
    }
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printLine(){
        System.out.println(lineBreak);
    }
}
