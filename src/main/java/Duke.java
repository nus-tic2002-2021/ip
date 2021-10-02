import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static PrintHelper printer = new PrintHelper();

    public static void addCommand(String command) throws UnknownSyntaxException, EmptyDescriptionException, TimeManagementException{
        String keyword;
        String detail;
        Task temp_task;
        try {
            String[] toWords = command.split(" ", 2);
            keyword = toWords[0];
            detail = toWords[1];
        }
        catch(ArrayIndexOutOfBoundsException aio) {
            if(command.contains("todo") || command.contains("deadline") || command.contains("event")){
                throw new EmptyDescriptionException();
            }
            else {
                throw new UnknownSyntaxException();
            }
        }
        try{
            switch (keyword) {
                case "todo":
                    temp_task = new ToDos(detail, detail);
                    break;
                case "deadline":
                    String[] dl = detail.split("/by", 2);
                    temp_task = new Deadline(dl[0], dl[1]);
                    break;
                case "event":
                    String[] dt = detail.split("/at", 2);
                    temp_task = new Events(dt[0], dt[1]);
                    break;
                default:
                    throw new UnknownSyntaxException();
            }
        }
        catch(IndexOutOfBoundsException e){
            if(command.contains("deadline") || command.contains("event")){
                throw new TimeManagementException();
            }
            throw new UnknownSyntaxException();
        }
        taskList.addTask(temp_task);
    }

    public static void reply(String command) throws EmptyTaskListException, UnknownSyntaxException,TaskNotFoundException{
        if(command.contains("done")){
            try {
                String[] doneCmd = command.split(" ");
                taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).setDone();
                //taskList[Integer.parseInt(doneCmd[1]) - 1].setDone();
                printer.taskDoneFeedback();
                System.out.println("     " + taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).toString());
                printer.separator();
                return;
            }
            catch(NumberFormatException nf){
                throw new TaskNotFoundException();
            }
        }
        printer.taskAddFeedback();
        try {
            System.out.println("     " + taskList.getLastTask().toString());
            System.out.println("     Now you have " + taskList.getListSize() + " tasks in the list.");
            printer.separator();
        }
        catch(ArrayIndexOutOfBoundsException a){
            throw new EmptyTaskListException();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean end = false;
        String command;
        Scanner in = new Scanner(System.in);
        printer.welcomeFeedback();
        command = in.nextLine();

        while(!end){
            if(command.equals("bye")){
                end = true;
            }
            else if(command.equals("list")){
                try {
                    taskList.printTaskList();
                }
                catch(EmptyTaskListException etl){
                    printer.exception_feedback_emptyTaskList();
                }
                command = in.nextLine();
            }
            else if(command.contains("done")){
                try {
                    reply(command);
                }
                catch(EmptyTaskListException etl){
                    printer.exception_feedback_emptyTaskList();
                }
                catch(UnknownSyntaxException us){
                    printer.exception_feedback_unknownSyntax(command);
                }
                catch(TaskNotFoundException tnf){
                    printer.exception_feedback_taskNotFound(command.split(" ", 2)[1]);
                }
                command = in.nextLine();
            }
            else{
                boolean success = true;
                try {
                    addCommand(command);
                }
                catch(UnknownSyntaxException us){
                    success = false;
                    printer.exception_feedback_unknownSyntax(command);
                }
                catch(EmptyDescriptionException ed){
                    success = false;
                    printer.exception_feedback_emptyDescription(command);
                }
                catch(TimeManagementException tm){
                    success = false;
                    printer.exception_feedback_noTimeConcept();
                }
                if(success) {
                    try {
                        reply(command);
                    } catch (EmptyTaskListException etl) {
                        printer.exception_feedback_emptyTaskList();
                    }
                    catch(UnknownSyntaxException | TaskNotFoundException exception){
                        printer.exception_feedback_unknownSyntax(command);
                    }
                }
                command = in.nextLine();
            }
        }


        printer.exitFeedback();
    }
}
