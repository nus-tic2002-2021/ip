package functions;

import Common.Help;
import Common.Message;
import data.DeadLine;
import data.Event;
import data.Task;
import data.Acronym;
import data.Commands;
import storage.Storage;
import ui.Ui.*;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.time.DateTimeException;
import java.util.*;

import static ui.Ui.*;

public class ListTask
{

    private List<Task> itemList;
    private int indx = 0;

    public ListTask(){
        itemList = new ArrayList<>();
    }

    public void init() throws IOException
    {
        try{
            Storage.load(itemList);
            indx = itemList.size();
        }catch (InvalidPathException p){
            System.out.println(BUFFER +p.getMessage() + NEWLINE_BUFFER + Message.showFilePathErrorMessage());
        }catch (IOException o){
            System.out.println(BUFFER+o.getMessage() + NEWLINE_BUFFER + Message.showFileIOErrorMessage());
        }

    }
    /**
     * user input into command for execution.
     *
     * @param item full user input string
     * @param s the storage passed to invoke saving task to external file based on the user input
     */
    public void addTask(String item, Storage s)
    {
        String[] tmp = item.trim().split(" "), tmp_string;
        final String command = tmp[0].trim().toUpperCase();
        Commands commandKey = null;
        try{
            commandKey = Commands.valueOf(command);
        }catch (IllegalArgumentException e){
            System.out.println(BUFFER + Message.getIncorrectCommand());
            commandKey = Commands.BREAKTHROUGH;
        }
        switch (commandKey){
            case DONE:
                tmp_string = item.split("(?i)done");
                try {
                    setTaskDone(tmp_string[1].trim());
                    s.appendTaskListToExternal(itemList);
                }catch (NumberFormatException e){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + e.getMessage()
                            + Message.getNumberException() +  NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }catch (ArrayIndexOutOfBoundsException i){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER  + i.getMessage() + Message.getArrayindexoutofbound() + NEWLINE + showList());
                }catch (Error t){
                    System.out.println(t.getMessage());
                }
                break;

            case LIST:
                System.out.println(showList());
                break;
            case TODO:
                tmp_string = item.split("(?i)todo");
                try{
                    itemList.add(new Task(tmp_string[1],Acronym.T));
                    indx++;
                    Message.taskAddedinMessage(itemList);
                    s.appendSingleTaskToExternal(itemList.get(itemList.size()-1));
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                            + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }
                break;
            case EVENT:
                tmp_string = item.split("(?i)event | (?i)/at"); // (?i) ignore case sensitivity
                try{
                    itemList.add(new Event(tmp_string[1],Acronym.E,tmp_string[2].trim()));
                    indx++;
                    Message.taskAddedinMessage(itemList);
                    s.appendSingleTaskToExternal(itemList.get(itemList.size()-1));
                }catch (DateTimeException d){
                    System.out.println(d.getMessage() + Message.getDateTimeFormatError());
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                            + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }

                break;
            case DEADLINE:
                tmp_string = item.split("(?i)deadline | (?i)/by"); // (?i) ignore case sensitivity
                try{
                    itemList.add(new DeadLine(tmp_string[1],Acronym.D,tmp_string[2].trim()));
                    indx++;
                    Message.taskAddedinMessage(itemList);
                    s.appendSingleTaskToExternal(itemList.get(itemList.size()-1));
                }catch (DateTimeException d){
                    System.out.println(d.getMessage() + Message.getDateTimeFormatError());
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                            + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }
                break;
            case SAVE: //for archiving
                tmp_string = item.split("(?i)save");
                DoArchiveFile(tmp_string,s);
                break;
            case DELETE:
                tmp_string = item.trim().split("(?i)delete");
                DoDeleteCommand(tmp_string,s);
                break;
            case HELP:
                String[] tmp_str = item.split(" ");
                Help.HelpCommand(tmp_str);
                break;
            case FIND:
                tmp_string = item.split("(?i)FIND");
                try{
                    Set<String> key = new HashSet<>(Arrays.asList(tmp_string[1].trim().split(" ")));
                    functions.FindCommand.FindKeywordCommand(key,itemList);
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                            + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }
                break;
            case VIEW:
                tmp_string = item.split("(?i)VIEW");
                try{
                    SearchFilteredTaskByDateTime.ViewScheduleByDate(tmp_string[1].trim(),itemList);
                }catch (ArrayIndexOutOfBoundsException a){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                            + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }catch (DateTimeException e){
                    System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER+e.getMessage() + Message.getDateTimeFormatError() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                }
                break;
            case BYE:
                if(tmp.length > 1){
                    System.out.println(Message.getByeError());
                }
                break;
            case BREAKTHROUGH:
                System.out.println(BUFFER + item.trim() + " is not a command!" + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
                break;
        }
    }


    public String showList()
    {
        StringBuilder output = new StringBuilder(BUFF_PLUS_HORRIZONTALLINE);

        for(int i = 0; i < indx; i++){
            String tem = itemList.get(i).getDescription().trim();
            Acronym acro = itemList.get(i).getAcronym();
            String status = itemList.get(i).getStatusIcon();
            int indx = i + 1;
            output.append(NEWLINE).append(BUFFER).append(indx).append(".[").append(acro).append("]")
                    .append("[").append(status).append("] ").append(tem);
            if(acro.equals(Acronym.E)){
                output.append(" (at: ").append(itemList.get(i).displayDateTime()).append(")");
            }else if(acro.equals(Acronym.D)){
                output.append(" (by: ").append(itemList.get(i).displayDateTime()).append(")");
            }
        }

        output.append(NEWLINE).append(BUFF_PLUS_HORRIZONTALLINE);
        return output.toString();
    }

    /**
     * setTaskDone Method to set individual Task as DONE.
     * setTaskDone method can detect if the number specify by user is not within the range of list, it will throw new error
     * setTaskDone can also detect if the task is already marked as done before, if it's already marked, it will not call task done setter.
     * This method also called 3 different methods from Message Class to display information to User
     * @param str should be + integer within the range of list Task holder, (specify by User)
     */
    public void setTaskDone(String str)
    {
        int idxToSetAsDone = (Integer.parseInt(str) - 1);
        if ( idxToSetAsDone < (itemList.size()) && idxToSetAsDone >= 0) {
            if(!itemList.get(idxToSetAsDone).getDone()){
                itemList.get(idxToSetAsDone).setDone(true);
                Message.setTaskDoneMessage(itemList,idxToSetAsDone);
            }else{
                Message.getTaskDoneMessage(itemList,idxToSetAsDone);
            }
        }else{
            throw new Error(Message.getOutOfRange());
        }
    }

    public void DoDeleteCommand(String[] tmp, Storage s){
        try{
            DeleteCommand.deleteTask(itemList,Integer.parseInt(tmp[1].trim()));
            indx--;
            s.appendTaskListToExternal(itemList);
        }catch (NumberFormatException e){
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + e.getMessage()
                    + Message.getNumberException() +  NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }catch (NullPointerException f){
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + f.getMessage()+ showList());
        }catch (ArrayIndexOutOfBoundsException a){
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + a.getMessage() + NEWLINE_BUFFER
                    + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }
    }

    public void DoArchiveFile(String[] s,Storage st){
        try{
            if(!(s.length > 2) && s[1].contains(".txt")){
                st.getNewFilePathForArchiving(s[1].trim());
                st.archiveTaskListToNewFIle(itemList);
            }else{
                System.out.println(Message.getFileExtensionError());
            }
        }catch(ArrayIndexOutOfBoundsException o){
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER + o.getMessage() + NEWLINE_BUFFER
                    + Message.getArrayindexoutofbound() + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }
    }
}
