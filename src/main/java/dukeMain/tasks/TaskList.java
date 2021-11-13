package dukeMain.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import dukeMain.exceptions.*;
import dukeMain.Ui;
import dukeMain.Parser;

// contains the task list
// e.g., it has operations to add/delete dukeMain.tasks in the list
public class TaskList {
    public static Ui ui;
    private static ArrayList<Task> taskListing = new ArrayList<>();

    public TaskList() {

        //create storage file;

    }
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
    // Add Task command
    public static void addTask(String command,String[] args,boolean isFile) throws DukeException {
        if(command.equalsIgnoreCase("Todo") || command.equalsIgnoreCase("T"))
        {
            if ((isFile && args.length != 2) || (!isFile && args.length != 1)){
                throw new ToDosBodyException("OOPS!!! The description of a todo cannot be empty");
            }

            if (isFile) taskListing.add(new ToDos(Boolean.getBoolean(args[0]), args[1]));
            else taskListing.add(new ToDos(args[0]));

        }
        else if(command.equalsIgnoreCase("Deadline") || command.equalsIgnoreCase("D"))
        {
            if(args.length == 0)
                throw new DeadlineBodyException("OOPS!!! The description of a dukeMain.tasks.Deadline cannot be empty");
            if(args.length == 1)
                throw new DeadlineByException("OOPS!!! When is the dukeMain.tasks.Deadline ?");

            if (isFile) taskListing.add(new Deadline(Boolean.getBoolean(args[0]), args[1],args[2]));
            else taskListing.add(new Deadline(args[0],args[1]));


        }
        else if(command.equalsIgnoreCase("Event") || command.equalsIgnoreCase("E"))
        {
            if(args.length == 0)
                throw new EventBodyException("OOPS!!! The description of a dukeMain.tasks.Event cannot be empty");

            if(args.length == 1)
                throw new EventAtException("OOPS!!! When is the dukeMain.tasks.Event ?");

            if (isFile) taskListing.add(new Event(Boolean.getBoolean(args[0]), args[1],args[2]));
            else taskListing.add(new Event(args[0],args[1]));
        }
    }

    public static void addFileTask(String[] str) throws DukeException {
        String command = str[0];
        String[] str_2 = new String[str.length-1];
        for (int i = 1; i < str.length; i++){
            str_2[i-1] = str[i];
        }

        addTask(command,str_2,true);

    }

    // auto -1 from the user input
    public static void deleteTask(int index) {
        taskListing.remove(index - 1 );
    }

    public static void markTask(int index){
        taskListing.get(index-1).setDone();
    }

//    public static void printTaskList(){
//        int c = 1;
//        ui.printThis(ui.lineBreak,0);
//        for (Task tk : taskListing) {
//            ui.printThis(c+") "+tk.toString(),0);
//            c ++;
//        }
//        ui.printThis(ui.lineBreak,0);
//    }

    public static ArrayList<Task> getTaskList(){
        return taskListing;
    }
}
