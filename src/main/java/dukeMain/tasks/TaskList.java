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
     * Also Handles adding of task from the source file
     * For type
     * - Todos : Checks if description is there else throw ToDosBodyException
     * - Deadline : Check if description is there else throw DeadlineBodyException.
     *              Also Checks if date/time is given else throw DeadlineByException.
     * - Event : Check if description is there else throw EventBodyException.
     *           Also Checks if date/time is given else throw EventAtException.
     *
     * @param command String Also known as the type of task
     * @param args String[]
     * @param isFile  boolean
     * @throws DukeException if error while adding task since all classes is child of Dukeexception.
     * */
    public static void addTask(String command,String[] args,boolean isFile) throws DukeException {
        LocalDate lDate;
        String time;
        if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_1))
        {
            if ((isFile && (args.length != 2 || args[1].isEmpty())) || (!isFile && (args.length != 1 || args[0].isEmpty()))){
                throw new ToDosBodyException("OOPS!!! The description of a todo cannot be empty");
            }

            if (isFile) taskListing.add(new ToDos(Boolean.getBoolean(args[0]), args[1]));
            else taskListing.add(new ToDos(args[0]));

        }
        else if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_2) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_2))
        {
            if((!isFile && (args.length == 0 || args[0].isEmpty())) || (isFile && (args.length == 1 || args[1].isEmpty())))
                throw new DeadlineBodyException("OOPS!!! The description of a dukeMain.tasks.Deadline cannot be empty");
            if((!isFile && (args.length == 1 || args[1].isEmpty())) || (isFile && (args.length == 2 || args[2].isEmpty())))
                throw new DeadlineByException("OOPS!!! When is the dukeMain.tasks.Deadline ?");

            if (isFile) {
                lDate = parser.parseLDT(args[2]);
                time = parser.getTime(args[2]);
                taskListing.add(new Deadline(((args[0].equals("0")) ? false : true), args[1],lDate,time));
            }else{
                lDate = parser.parseLDT(args[1]);
                time = parser.getTime(args[1]);
                taskListing.add(new Deadline(args[0],lDate,time));
            }
        }
        else if(command.equalsIgnoreCase(AddCommand.COMMAND_WORD_3) || command.equalsIgnoreCase(AddCommand.COMMAND_WORD_SHORT_3))
        {
            if((!isFile && (args.length == 0 || args[0].isEmpty())) || (isFile && (args.length == 1 || args[1].isEmpty())))
                throw new EventBodyException("OOPS!!! The description of a dukeMain.tasks.Event cannot be empty");
            if((!isFile && (args.length == 1 || args[1].isEmpty())) || (isFile && (args.length == 2 || args[2].isEmpty())))
                throw new EventAtException("OOPS!!! When is the dukeMain.tasks.Event ?");

            if (isFile) {
                lDate = parser.parseLDT(args[2]);
                time = parser.getTime(args[2]);
                taskListing.add(new Event(((args[0].equals("0")) ? false : true), args[1],lDate,time));
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
        Task tk = taskListing.get(index-1);
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

    /** Getter of task from tasklist
     * @return task Task
     * */
    public static Task getTask(int index){
        return taskListing.get(index);
    }

    /** Find the list of Task with description that matches the given description
     *
     * @return ArrayList<Task> of the Task that matches it
     * */
    public static ArrayList<Task> findList(String description){
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task tk : taskListing){
            if (tk.getDescription().contains(description)) taskArrayList.add(tk);
        }
        return taskArrayList;
    }
}
