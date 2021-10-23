package Duke.ui;


import Duke.exception.*;

import java.util.Scanner;

public class UI{

    private static ReturnMessages returnMessage = new ReturnMessages();

    private Scanner in;

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        returnMessage.welcomeFeedback();
    }

    public void showLoadingError(){
        returnMessage.exception_feedback_loadingError();
    }

    /**
     * read cmd user input
     * @return - command read from user Input
     */
    public String readCommand() {
        boolean end = false;
        String command;
        in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public void showLine() {
        returnMessage.separator();
    }

    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * all the error msg will output by this
     * All exceptions inherit DukeException
     * @param e the exception object
     */
    public void showError(DukeException e) {
        if (e instanceof EmptyDescriptionException) {
            returnMessage.exception_feedback_emptyDescription(e.getMessage());
        } else if (e instanceof EmptyTaskListException) {
            returnMessage.exception_feedback_emptyTaskList();
        } else if (e instanceof SavingException) {
            returnMessage.exception_feedback_cantSave();
        } else if (e instanceof TaskNotFoundException) {
            returnMessage.exception_feedback_taskNotFound(e.getMessage());
        } else if (e instanceof TimeManagementException) {
            returnMessage.exception_feedback_noTimeConcept();
        } else if (e instanceof UnknownSyntaxException){
            returnMessage.exception_feedback_unknownSyntax(e.getMessage());
        } else if (e instanceof TimeParseException){
            returnMessage.exception_feedback_timeParse(e.getMessage());
        } else {
            returnMessage.exception_feedback_unknownError();
        }
    }

    public void showExit() {
        returnMessage.exitFeedback();
    }
}
