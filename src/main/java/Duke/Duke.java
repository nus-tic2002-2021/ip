package Duke;

import Exception_Handler.*;
import TaskList.*;
import UI.*;
import Storage.*;
import java.util.Scanner;
import java.io.*;
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private static TaskList taskList = new TaskList();
    private static ReturnMessages returnMessage = new ReturnMessages();
    public static void addCommand(String command) throws UnknownSyntaxException, EmptyDescriptionException, TimeManagementException {
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

    public static void reply(String command) throws EmptyTaskListException, UnknownSyntaxException, TaskNotFoundException {
        if(command.contains("done")){
            try {
                String[] doneCmd = command.split(" ");
                taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).setDone();
                //taskList[Integer.parseInt(doneCmd[1]) - 1].setDone();
                returnMessage.taskDoneFeedback();
                System.out.println("     " + taskList.getTask(Integer.parseInt(doneCmd[1]) - 1).toString());
                returnMessage.separator();
                return;
            }
            catch(NumberFormatException nf){
                throw new TaskNotFoundException();
            }
        }
        returnMessage.taskAddFeedback();
        try {
            System.out.println("     " + taskList.getLastTask().toString());
            System.out.println("     Now you have " + taskList.getListSize() + " tasks in the list.");
            returnMessage.separator();
        }
        catch(ArrayIndexOutOfBoundsException a){
            throw new EmptyTaskListException();
        }
    }

    public Duke(String fileName) throws DukeException, FileNotFoundException, IOException {
        ui = new UI();
        storage = new Storage(fileName);
        tasks = new TaskList();
        storage.load(tasks);
    }
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        boolean end = false;
        String command;
        Scanner in = new Scanner(System.in);
        returnMessage.welcomeFeedback();
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
                    returnMessage.exception_feedback_emptyTaskList();
                }
                command = in.nextLine();
            }
            else if(command.contains("done")){
                try {
                    reply(command);
                }
                catch(EmptyTaskListException etl){
                    returnMessage.exception_feedback_emptyTaskList();
                }
                catch(UnknownSyntaxException us){
                    returnMessage.exception_feedback_unknownSyntax(command);
                }
                catch(TaskNotFoundException tnf){
                    returnMessage.exception_feedback_taskNotFound(command.split(" ", 2)[1]);
                }
                command = in.nextLine();
            }
            else if(command.contains("delete")){
                try{
                    taskList.deleteTask(Integer.parseInt(command.split(" ", 2)[1]));
                }
                catch(TaskNotFoundException tnf){
                    returnMessage.exception_feedback_taskNotFound(command.split(" ", 2)[1]);
                }
                catch(NumberFormatException e){
                    returnMessage.exception_feedback_unknownSyntax(command);
                }
                catch(EmptyTaskListException etl){
                    returnMessage.exception_feedback_emptyTaskList();
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
                    returnMessage.exception_feedback_unknownSyntax(command);
                }
                catch(EmptyDescriptionException ed){
                    success = false;
                    returnMessage.exception_feedback_emptyDescription(command);
                }
                catch(TimeManagementException tm){
                    success = false;
                    returnMessage.exception_feedback_noTimeConcept();
                }
                if(success) {
                    try {
                        reply(command);
                    } catch (EmptyTaskListException etl) {
                        returnMessage.exception_feedback_emptyTaskList();
                    }
                    catch(UnknownSyntaxException | TaskNotFoundException exception){
                        returnMessage.exception_feedback_unknownSyntax(command);
                    }
                }
                command = in.nextLine();
            }
        }
        returnMessage.exitFeedback();
    }
}
