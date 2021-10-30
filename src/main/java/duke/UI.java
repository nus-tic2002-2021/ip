package duke;

import java.util.Scanner;

public class UI {
    private final String LINE_BREAK = "\t_______________________________________________________";
    private final String INPUT_FORMAT_ERROR = "Please do not use '|' as it breaks the program.";
    private final String TODO_DESCRIPTION_ERROR = "Todo is missing a description.";
    private final String DEADLINE_DESCRIPTION_ERROR = "Deadline command is missing a description and/or deadline.";
    private final String DEADLINE_LENGTH_ERROR = "Deadline command has too many /by.";
    private final String EVENT_DESCRIPTION_ERROR = "Event command is missing a description and/or time.";
    private final String EVENT_LENGTH_ERROR = "Event command has too many /at.";
    private final String INVALID_DATE_FORMAT = "Incorrect Date formatting.";

    public void printIntro(){
        printLine();
        System.out.println("\tHello!");
        printInstruction();
        System.out.println("\tWelcome. Please enter your instruction.");
        printLine();
    }
    public void printInstruction(){
        System.out.println("\t1.) Enter *todo 'text'*--- to add todo task");    //
        System.out.println("\t2.) Enter *deadline 'text' /by 'text'*to add deadline task");
        System.out.println("\t3.) Enter *event 'text' /at 'text'* to add event task");
        System.out.println("\t4.) Enter *list* to show list of tasks.");
        System.out.println("\t5.) Enter *done 'integer'* mark task as done.");
        System.out.println("\t6.) Enter *delete 'integer'* to delete task.");
        System.out.println("\t7.) Enter *bye* to exit program");
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
        System.out.println(LINE_BREAK);
    }

    public void showError(String errorMessage){
        System.out.print("\tError occurred: ");
        switch(errorMessage){
            case "INPUT_FORMAT_ERROR":
                System.out.println(INPUT_FORMAT_ERROR);
                break;
            case "TODO_DESCRIPTION_ERROR":
                System.out.println(TODO_DESCRIPTION_ERROR);
                break;
            case "DEADLINE_DESCRIPTION_ERROR":
                System.out.println(DEADLINE_DESCRIPTION_ERROR);
                break;
            case "DEADLINE_LENGTH_ERROR":
                System.out.println(DEADLINE_LENGTH_ERROR);
                break;
            case "EVENT_DESCRIPTION_ERROR":
                System.out.println(EVENT_DESCRIPTION_ERROR);
                break;
            case "EVENT_LENGTH_ERROR":
                System.out.println(EVENT_LENGTH_ERROR);
                break;
            case "INVALID_DATE_FORMAT":
                System.out.println(INVALID_DATE_FORMAT);
                break;
            default:
                System.out.println("Unrecognized error");

        }
    }
}
