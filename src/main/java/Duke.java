import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;
    private static PrintHelper printer = new PrintHelper();
    public static void addCommand(String command) throws UnknownSyntaxException, EmptyDescriptionException, TimeManagementException{
        String keyword;
        String detail;
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
                    taskList[taskCount] = new ToDos(detail, detail);
                    break;
                case "deadline":
                    String[] dl = detail.split("/by", 2);
                    taskList[taskCount] = new Deadline(dl[0], dl[1]);
                    break;
                case "event":
                    String[] dt = detail.split("/at", 2);
                    taskList[taskCount] = new Events(dt[0], dt[1]);
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
        taskCount++;
    }

    public static void reply(String command) throws EmptyTaskListException, UnknownSyntaxException,TaskNotFoundException{
        if(command.contains("done")){
            try {
                String[] doneCmd = command.split(" ");
                taskList[Integer.parseInt(doneCmd[1]) - 1].setDone();
                printer.taskDoneFeedback();
                System.out.println("     " + taskList[Integer.parseInt(doneCmd[1]) - 1].toString());
                printer.separator();
                return;
            }
            catch(NumberFormatException nf){
                throw new TaskNotFoundException();
            }
        }
        printer.taskAddFeedback();
        try {
            System.out.println("     " + taskList[taskCount - 1].toString());
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
            printer.separator();
        }
        catch(ArrayIndexOutOfBoundsException a){
            throw new EmptyTaskListException();
        }
    }

    public static void printAll() throws EmptyTaskListException{
        if(taskCount==0){
            throw new EmptyTaskListException();
        }
        printer.taskFullListFeedback(taskCount,taskList);
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
                    printAll();
                }
                catch(EmptyTaskListException etl){
                    printer.emptyTaskList();
                }
                command = in.nextLine();
            }
            else if(command.contains("done")){
                try {
                    reply(command);
                }
                catch(EmptyTaskListException etl){
                    printer.emptyTaskList();
                }
                catch(UnknownSyntaxException us){
                    printer.unknownSyntax(command);
                }
                catch(TaskNotFoundException tnf){
                    printer.taskNotFound(command.split(" ", 2)[1]);
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
                    printer.unknownSyntax(command);
                }
                catch(EmptyDescriptionException ed){
                    success = false;
                    printer.emptyDescription(command);
                }
                catch(TimeManagementException tm){
                    success = false;
                    printer.noTimeConcept();
                }
                if(success) {
                    try {
                        reply(command);
                    } catch (EmptyTaskListException etl) {
                        printer.emptyTaskList();
                    }
                    catch(UnknownSyntaxException | TaskNotFoundException exception){
                        printer.unknownSyntax(command);
                    }
                }
                command = in.nextLine();
            }
        }


        printer.exitFeedback();
    }
}
