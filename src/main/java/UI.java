import java.util.Scanner;

public class UI {
    private final String lineBreak = "\t_______________________________________________________";

    public void printIntro(){
        printLine();
        System.out.println("\tHello!");
        System.out.println("\tEnter 'bye' to exit program");
        //System.out.println("\tEnter 'instruction' for list of commands");
        printInstruction();
        System.out.println("\tWelcome. Please enter your instruction.");
        printLine();
    }
    public void printInstruction(){
        System.out.println("\t1.) Enter |todo 'text'|--- to add todo task");    //
        System.out.println("\t2.) Enter |deadline 'text' /by 'text'|to add deadline task");
        System.out.println("\t3.) Enter |event 'text' /at 'text'| to add event task");
        System.out.println("\t4.) Enter |list| to show list of tasks.");
        System.out.println("\t5.) Enter |done 'integer'| mark task as done.");
        System.out.println("\t6.) Enter |delete 'integer'| to delete task.");
    }
    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    public void printExit(){
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\tUpdating files.");
    }
    public void printNotFound(){
        System.out.println("\tTask cannot be found.");
    }
    public void printInvalidEntry(){
        System.out.println("\tInvalid task number entry.");
    }



    public void printLine(){
        System.out.println(lineBreak);
    }
}
