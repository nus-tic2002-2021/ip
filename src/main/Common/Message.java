package Common;

import static ui.Ui.*;

import data.Acronym;
import data.DeadLine;
import data.Event;
import data.Task;
import parser.Parser;
import storage.Storage;

import java.util.List;

public class Message {

    /*
    *A collection of System Messages
    */
    private final static String FILE_PATH_ERROR = BUFFER + " File Path Error!";
    private final static String FILE_IO_ERROR = BUFFER + " File IO ERROR while loading data!";
    private final static String FILE_EXTENSION_ERROR = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER
            + "Set file extention to .txt to archive the data from current session." + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    private final static String DATA_LOADED_FROM_FILE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "User Data file exists in the "
            + Storage.getAbsFilePath(true) + NEWLINE_BUFFER +"Data loaded into the DUKE Application successfully!" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    private final static String NO_DATA_FILE_FOUND = NEWLINE_BUFFER +"File do not exist! Unable load data from "
            + Storage.getAbsFilePath(true) + " to the ArrayList." + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    private final  static String NO_ARCHIVE_FILE = NEWLINE_BUFFER +" Unable to write to archive file upon user Save command: "
            + Storage.getAbsFilePath(false) + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;

    private final static String NULL_POINTER_ERROR = NEWLINE_BUFFER + "Invalid Selection! "
            + NEWLINE_BUFFER + "Choose correct the number from the list " + NEWLINE;
    private final static String NUMBER_EXCEPTION = NEWLINE_BUFFER +"This is not a number! or Single Digit number! Please Enter a Number";
    private final static String INCORRECT_COMMAND = "Incorrect Command! Use HELP to find out more";
    private final static String DATE_TIME_FORMAT_ERROR = NEWLINE_BUFFER +" error in date time format " +
            "example usage 01/01/2021 0000 or 21/12/2022 2359" +NEWLINE+ BUFF_PLUS_HORRIZONTALLINE;
    private final static String ARRAYINDEXOUTOFBOUND = NEWLINE_BUFFER + "Missing Argument! "
            + NEWLINE_BUFFER + "Command is expecting argument " + NEWLINE;
    private final static String BYE_ERROR = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER +
            "BYE Command do not take any arguments" + NEWLINE_BUFFER
            + "eg BYE or bye" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    private final static String OUT_OF_RANGE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER
            + "OUT OF RANGE! Please check the task list again" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    /*
    * A collection of USER HELP Command messages
    */
    private final static String LIST_OF_COMMAND_FOR_USER = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Supported Command for HELP:"
            + NEWLINE_BUFFER + "DONE, LIST, TODO, EVENT,DEADLINE, SAVE, DELETE, HELP, FIND, VIEW, BYE"
            + NEWLINE_BUFFER + "Example use of HELP command: HELP DONE or HELP LIST" + NEWLINE
            + NEWLINE_BUFFER +"The User command listed above are case insensitive so user can use them without worries " + NEWLINE_BUFFER +
            "but arguments which follows after the user command has specific format to follow. " + NEWLINE_BUFFER +
            "You may familiarise the usage of command through HELP usercommand"+ NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_DONE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of DONE command: "
            + NEWLINE_BUFFER + "done 'Task No' Task No is a positive index number from tasklist. A tasklist can be invoke using LIST command." + NEWLINE_BUFFER
            + "eg done 1 or done 2" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_TODO = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of TODO command: "
            + NEWLINE_BUFFER + "todo 'Task Description' eg todo meeting or Todo cut hair" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_LIST = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of LIST command: "
            + NEWLINE_BUFFER + "LIST is a standalone command which takes no arguments. eg LIST or list" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_DEADLINE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of DEADLINE command: "
            + NEWLINE_BUFFER + "deadline 'Task description' /by 'timestamp' " + NEWLINE_BUFFER
            + "eg deadline 'project submission' /by '01/01/2022 1900'" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_EVENT = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of EVENT command: "
            + NEWLINE_BUFFER + "event 'Task description' /at 'timestamp' " + NEWLINE_BUFFER
            + "eg event 'food fare' /at '01/03/2022 0900'" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_FIND = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of FIND command: "
            + NEWLINE_BUFFER + "FIND 'keyword'. A Keyword search looks for words in the description of the task in the Tasklist" + NEWLINE_BUFFER
            + "eg find 'food' or Find 'meeting'" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_SAVE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of SAVE command: "
            + NEWLINE_BUFFER + "SAVE 'new file name'.txt " + NEWLINE_BUFFER
            + "Save command is a way to archive the tasklist to a new file location in the current session, " + NEWLINE_BUFFER +
            "rather than keeping them in the current data file." + NEWLINE_BUFFER
            + "eg Save 'userdatabackup.txt' or SAVE 'data_backup.txt'" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_DELETE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of DELETE command: "
            + NEWLINE_BUFFER + "DELETE 'Task No'. Task No is a positive index number from tasklist. A tasklist can be invoke using LIST command." + NEWLINE_BUFFER
            + "eg DELETE 1 or delete 2" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_VIEW = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of VIEW command: "
            + NEWLINE_BUFFER + "VIEW 'TIMESTAMP'. VIEW is a command to look up a specific date schedule." + NEWLINE_BUFFER
            + "eg VIEW '01/02/2022' or VIEW '12/01/2021'" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;
    public final static String HELP_BYE = BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + "Usage of BYE command: "
            + NEWLINE_BUFFER + "BYE is a standalone command to end the current session of this DUKE application." + NEWLINE_BUFFER
            + "eg BYE or bye" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE;

    /*
    *methods to call these help Strings
    */

    /**
     * Display Task removal message from tasklist when delete command is called
     * This removal message methods is called before the actual task deleted.
     * @param itemlist takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     * @param idx is a specific index from the task list to be removed
     */
    public static void taskremovedinMessage(final List<Task> itemlist, int idx)
    {
        System.out.println(BUFF_PLUS_HORRIZONTALLINE + "\n" + BUFFER +"Noted. I've removed this task: "
                + "\n" + BUFFER + "  ["+itemlist.get(idx).getAcronym()+"]["+itemlist.get(idx).getStatusIcon()+"] "
                + itemlist.get(idx).getDescription()
                + "\n"+ BUFFER +"Now you have "+ (itemlist.size()-1) +" tasks in the list");
    }

    /**
     * Display Task being added into the tasklist from ListTask class task holder
     * @param it takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     */
    public static void taskAddedinMessage(final List<Task> it)
    {
        if(it.get(it.size()-1).getAcronym().equals(Acronym.T)) {
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER+ "Got it. I've added this task: "
                    + NEWLINE_BUFFER + "  ["+it.get(it.size()-1).getAcronym()+"][ ] " + it.get(it.size()-1).getDescription()
                    + NEWLINE_BUFFER + "Now you have " + (it.size()) + " tasks in the list." + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }else{
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER+ "Got it. I've added this task: "
                    + NEWLINE_BUFFER + "  ["+it.get(it.size()-1).getAcronym()+"][ ] " + it.get(it.size()-1).getDescription() +" "
                    + it.get(it.size()-1).displayDateTime() + NEWLINE_BUFFER + "Now you have " + (it.size())
                    + " tasks in the list." + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }
    }

    /**
     * Display Task set to complete message from ListTask when DONE command is called
     * This setTaskDoneMessage methods is called after the actual task set as DONE.
     * @param it takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     * @param idx is a specific index from the task list to be set as Done
     */
    public static void setTaskDoneMessage(final List<Task> it, int idx)
    {
        if(it.get(idx).getAcronym().equals(Acronym.T)){
            System.out.println(new StringBuilder().append(BUFF_PLUS_HORRIZONTALLINE).append(NEWLINE_BUFFER)
                    .append("Nice! I've marked this task as done: ")
                    .append(NEWLINE_BUFFER).append("[").append(it.get(idx).getStatusIcon())
                    .append("] ").append(it.get(idx).getDescription()).append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE));
        }else{
            System.out.println(new StringBuilder().append(BUFF_PLUS_HORRIZONTALLINE).append(NEWLINE_BUFFER)
                    .append("Nice! I've marked this task as done: ")
                    .append(NEWLINE_BUFFER).append("[").append(it.get(idx).getStatusIcon())
                    .append("] ").append(it.get(idx).getDescription()).append(" ")
                    .append(it.get(idx).displayDateTime()).append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE));
        }

    }
    /**
     * Display Task that are already set as completed message from ListTask when DONE command is called
     * This getTaskDoneMessage methods is called when the actual task is already set to DONE.
     * @param it takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     * @param idx is a specific index from the task list to be set as Done
     */
    public static void getTaskDoneMessage(final List<Task> it, int idx)
    {
        if(it.get(idx).getAcronym().equals(Acronym.T)){
            System.out.println(new StringBuilder().append(BUFF_PLUS_HORRIZONTALLINE).append(NEWLINE_BUFFER)
                    .append("The task you selected is already marked as completed: ")
                    .append(NEWLINE_BUFFER).append("[").append(it.get(idx).getStatusIcon())
                    .append("] ").append(it.get(idx).getDescription()).append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE));
        }else{
            System.out.println(new StringBuilder().append(BUFF_PLUS_HORRIZONTALLINE).append(NEWLINE_BUFFER)
                    .append("The task you selected is already marked as completed: ")
                    .append(NEWLINE_BUFFER).append("[").append(it.get(idx).getStatusIcon())
                    .append("] ").append(it.get(idx).getDescription()).append(" ")
                    .append(it.get(idx).displayDateTime()).append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE));
        }
    }

    /**
     * View and Find Command is used to call this displayTaskAfterFiltered method through ViewScheduleByDate and FindKeywordCommand method respectively
     * Display the List of Task which already filtered by keywords Search or filtered by specific date
     * @param tks is a List of task passed FindKeywordCommand method from FindCommand Class
     * @param type can be VIEW or FIND command but not both, specified by User.
     * @param date is only use for VIEW command, FIND command will just leave a blank for date param
     */
    public static void displayTaskAfterFiltered(final List<Task> tks, String type, String date){
        String header = "";
        if(type.equalsIgnoreCase("view")){
            header = "Here are the Tasks schedule for the date: " + date;
        }
        if(type.equalsIgnoreCase("find")){
            header = "Here are the matching Task in your list: ";
        }
        StringBuilder output = new StringBuilder(BUFF_PLUS_HORRIZONTALLINE
                + NEWLINE_BUFFER + header);
        for(int i = 0; i < (tks.size()); i++){
            String desc = tks.get(i).getDescription().trim();
            Acronym acro = tks.get(i).getAcronym();
            String status = tks.get(i).getStatusIcon();
            String time = tks.get(i).displayDateTime();
            int indx = i + 1;
            output.append(NEWLINE_BUFFER).append(indx).append(".[").append(acro).append("]")
                    .append("[").append(status).append("] ").append(desc);
            if(acro.equals(Acronym.E)){
                output.append(" (at: ").append(time).append(" )");
            }else if(acro.equals(Acronym.D)){
                output.append(" (by: ").append(time).append(" )");
            }
        }
        if(tks.isEmpty()){
            output.append(NEWLINE_BUFFER).append("NO Task Matches your query!");
        }
        output.append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE);
        System.out.println(output);
    }

    public static String showFilePathErrorMessage() {
        return FILE_PATH_ERROR;
    }

    public static String showFileIOErrorMessage()
    {
       return FILE_IO_ERROR;
    }

    public static String incorrectSelection()
    {
        return NULL_POINTER_ERROR;
    }

    public static String getDateTimeFormatError()
    {
        return DATE_TIME_FORMAT_ERROR;
    }

    public static String getIncorrectCommand()
    {
        return INCORRECT_COMMAND;
    }

    public static String getNumberException()
    {
        return NUMBER_EXCEPTION;
    }

    public static String getListOfCommandForUser()
    {
        return LIST_OF_COMMAND_FOR_USER;
    }

    public static String getArrayindexoutofbound()
    {
        return ARRAYINDEXOUTOFBOUND;
    }

    public static String getByeError()
    {
        return BYE_ERROR;
    }

    public static String getFileExtensionError()
    {
        return FILE_EXTENSION_ERROR;
    }

    public static String getDataLoadedFromFile()
    {
        return DATA_LOADED_FROM_FILE;
    }

    public static String getNoDataFileFound()
    {
        return NO_DATA_FILE_FOUND;
    }

    public static String getNoArchiveFile()
    {
        return NO_ARCHIVE_FILE;
    }

    public static String getOutOfRange()
    {
        return OUT_OF_RANGE;
    }
}
