package dukeMain.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import dukeMain.commands.AddCommand;
import dukeMain.exceptions.*;
import dukeMain.Ui;
import dukeMain.Parser;

/**
 * Represents List of Task. A <code>Tasklist</code> object includes
 * An ArrayList of task
 * e.g., <code>"This is an Event Task"</code>
 */
// contains the task list
// e.g., it has operations to add/delete dukeMain.tasks in the list
public class TaskList {
    public static Ui ui;
    public static Parser parser;
    private static ArrayList<Task> taskListing = new ArrayList<>();

    public TaskList() {
        //create storage file;
    }

    /** Constructor of TaskList
     * to retrieve all the task from the txt file given
     *
     * @param f File
     * @throws DukeException if file is not found
     * */
    public TaskList(File f) throws DukeException {
        //parse the String from the text file then use it to inside into the DB
        try{
            Scanner scan = new Scanner(f);

            while(scan.hasNextLine()){
                addFileTask(Parser.parseFileLine(scan.nextLine()));
            }
        }catch(FileNotFoundException e){
            throw new DukeException("File is not Found please, make sure you entered the correct path");
        }

    }

    /** Add task depending on the task type given.
     *
     * @param command String Also known as the type of task
     * @param args String[]
     * @param isFile  boolean
     * @throws DukeException if error while adding task.
     * */
    public static void addTask(String command,String[] args,boolean isFile) throws DukeException {
        LocalDate lDate;
        String time;
        if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_1))
        {
            if ((isFile && args.length != 2) || (!isFile && args.length != 1)){
                throw new ToDosBodyException("OOPS!!! The description of a todo cannot be empty");
            }

            if (isFile) taskListing.add(new ToDos(Boolean.getBoolean(args[0]), args[1]));
            else taskListing.add(new ToDos(args[0]));

        }
        else if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_2) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_2))
        {
            if((!isFile && args.length == 0) || (isFile && args.length == 1))
                throw new DeadlineBodyException("OOPS!!! The description of a dukeMain.tasks.Deadline cannot be empty");
            if((!isFile && args.length == 1) || (isFile && args.length == 2))
                throw new DeadlineByException("OOPS!!! When is the dukeMain.tasks.Deadline ?");

            if (isFile) {
                lDate = parser.parseLDT(args[2]);
                time = parser.getTime(args[2]);
                taskListing.add(new Deadline(Boolean.getBoolean(args[0]), args[1],lDate,time));
            }else{
                lDate = parser.parseLDT(args[1]);
                time = parser.getTime(args[1]);
                taskListing.add(new Deadline(args[0],lDate,time));
            }
        }
        else if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_3) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_3))
        {
            if(args.length == 0)
                throw new EventBodyException("OOPS!!! The description of a dukeMain.tasks.Event cannot be empty");

            if(args.length == 1)
                throw new EventAtException("OOPS!!! When is the dukeMain.tasks.Event ?");

            if (isFile) {
                lDate = parser.parseLDT(args[2]);
                time = parser.getTime(args[2]);
                taskListing.add(new Event(Boolean.getBoolean(args[0]), args[1],lDate,time));
            }else{
                lDate = parser.parseLDT(args[1]);
                time = parser.getTime(args[1]);
                taskListing.add(new Event(args[0],lDate,time));
            }
        }else{
            throw new DukeException("No such Task type Exist");
        }
    }

    /** Add task directly from file
     *
     * @param str String[]
     * @throws DukeException if error while adding task.
     * */
    public static void addFileTask(String[] str) throws DukeException {
        String command = str[0];
        String[] str_2 = new String[str.length-1];
        for (int i = 1; i < str.length; i++){
            str_2[i-1] = str[i];
        }

        addTask(command,str_2,true);

    }

    /** Delete Task from the taskListing
     * Automatically - 1 index position.
     *
     * @param index int
     * @return Task for printing purposes.
     * */
    public static Task deleteTask(int index) {
        Task tk = taskListing.get(index);
        taskListing.remove(index - 1 );
        return tk;
    }
    /** Mark Task from the taskListing
     * Automatically - 1 index position.
     *
     * @param index int
     * */
    public static void markTask(int index){
        taskListing.get(index-1).setDone();
    }

    /** Getter of taskList
     * @return taskListing ArrayList<Task>
     * */
    public static ArrayList<Task> getTaskList(){
        return taskListing;
    }

    public static Task getTask(int index){
        return taskListing.get(index);
    }
}
