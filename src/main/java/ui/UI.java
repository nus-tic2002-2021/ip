package ui;

//import java.util.Scanner;

public class UI {
    private final String LINE_BREAK = "_______________________________________________________";
    private final String INPUT_FORMAT_ERROR = "'|' is not allowed as it is part of the save format.";
    private final String TODO_DESCRIPTION_ERROR = "Todo is missing a description.";
    private final String DEADLINE_DESCRIPTION_ERROR = "Deadline command is missing a description and/or deadline.";
    private final String DEADLINE_LENGTH_ERROR = "Deadline command has too many /by.";
    private final String EVENT_DESCRIPTION_ERROR = "Event command is missing a description and/or time.";
    private final String EVENT_LENGTH_ERROR = "Event command has too many /at.";
    private final String INVALID_DATETIME_FORMAT = "Incorrect Date formatting. Only accepts yyyy-MM-dd HHmm format " +
            "\ne.g. 2021-10-21 1300";
    private final String INVALID_DATE_FORMAT = "Incorrect Date formatting. Only accepts yyyy-MM-dd HHmm format " +
            "\ne.g. 2021-10-21";
    private final String INVALID_TIME_FORMAT = "Incorrect Time formatting. Only accepts H:mm format " +
            "\ne.g. 1:30";
    private final String INVALID_ACTION = "Action not recognized. Please try again";

    public void printIntro() {
        printLine();
        System.out.println("Hello!");
        printInstruction();
        System.out.println("Welcome to Toh Shao Wei TIC2002 Project. Please enter your instruction.");
        printLine();
    }

    public void printInstruction() {
        System.out.println("1.) Enter *todo 'text'* to add todo task.");    //
        System.out.println("2.) Enter *deadline 'text' /by 'DateTime'*to add deadline task.");
        System.out.println("3.) Enter *event 'text' /at 'DateTime' /for 'Time'* to add event task.");
        System.out.println("4.) Enter *list* to show list of tasks.");
        System.out.println("5.) Enter *done 'integer'* mark task as done.");
        System.out.println("6.) Enter *delete 'integer'* to delete task.");
        System.out.println("7.) Enter *find 'text'* to search for the word.");
        System.out.println("8.) Enter *view 'date'* to search all task on specific date.");
        System.out.println("9.) Enter *bye* to save and exit program.");
        System.out.println("10.) Enter *help* To see all the possible commands.");
        printFormats();
    }
    public void printFormats(){
        System.out.println("Program accepts date format : yyyy-MM-dd HHmm " +
                "\ne.g. 2021-10-21 1300");
        System.out.println("Program accepts time format : H:mm " +
                "\ne.g. 01:30");
    }
    /* Unused after JavaFX implementation
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
    */
    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("Updating files.");
    }

    public void printNotFound() {
        System.out.println("Task cannot be found.");
    }

    public void printInvalidEntry() {
        System.out.println("Invalid task number entry.");
    }


    public void printLine() {
        System.out.println(LINE_BREAK);
    }

    public void showError(String errorMessage) {
        System.out.print("Error occurred: ");
        switch (errorMessage) {
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
        case "INVALID_DATETIME_FORMAT":
            System.out.println(INVALID_DATETIME_FORMAT);
            break;
        case "INVALID_DATE_FORMAT":
            System.out.println(INVALID_DATE_FORMAT);
            break;
        case "INVALID_TIME_FORMAT":
            System.out.println(INVALID_TIME_FORMAT);
            break;
        case "INVALID_ACTION":
            System.out.println(INVALID_ACTION);
            break;
        default:
            System.out.println("Unrecognized error");

        }
    }
}
