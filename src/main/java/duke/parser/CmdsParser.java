package duke.parser;

import duke.command.Command;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.Todo;
import duke.exception.DukeException;
import duke.ui.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CmdsParser {

    protected static String[] cmds = new String[3];
    protected static String cmdType;
    protected static String cmdTask;
    protected static String cmdTaskType;

    public static void parse(String cmd) throws DukeException, FileNotFoundException, java.io.IOException {

        cmds = cmd.split(" ");
        cmdType = cmds[0];

        try {
            switch (cmdType) {
                case "done":
                    parseDone();
                    break;
                case "delete":
                    parseDelete();
                    break;
                case "todo":
                    //parseTodo(cmd);
                    parseSave(cmd);
                    break;
                case "event":
                    //parseEvent(cmd);
                    parseSave(cmd);
                    break;
                case "deadline":
                    //parseDeadline(cmd);
                    parseSave(cmd);
                    break;
                default:
                    throw new DukeException();
            }
        }

        catch (IndexOutOfBoundsException e) {
            //duke.ui.Printer.printInvalidFile();
            System.out.println("File not found");
        }

        catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        //return true;

    }

    public static void parseDone() {
        try {
            cmdTask = cmds[1];
            int id = Integer.parseInt(String.valueOf(cmdTask));
            //Task task = taskList.get(id-1);
            //task.setDone();
            //Printer.printDone(task);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDone();
        }
    }

    public static void parseDelete() {
        try {
            cmdTask = cmds[1];
            int id = Integer.parseInt(String.valueOf(cmdTask));
            //Task task = taskList.get(id-1);
            //taskList.remove(id-1);
            //Task.totalCount--;
            //Printer.printDelete(task);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDelete();
        }
    }

    public static void parseSave(String taskToAdd) throws FileNotFoundException, java.io.IOException {
        try {
            String filePath = "src/main/java/duke.data/tasks.txt";
            //Files.delete(Paths.get(filePath));
            File f = new File(filePath);
            //System.out.println("full path: " + f.getAbsolutePath());
            //System.out.println("file exists?: " + f.exists());
            //System.out.println("is Directory?: " + f.isDirectory());
            //Scanner s = new Scanner(f); // create a Scanner using the File as the source
            //while (s.hasNext()) {
                //System.out.println(s.nextLine());
            //}
            FileWriter fw = new FileWriter(f, true);
            fw.write("\n"+taskToAdd);
            fw.close();
        } catch (IndexOutOfBoundsException e) {
            //duke.ui.Printer.printInvalidFile();
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /*
    public static void parseTodo(String cmd) {
        try {
            cmds = cmd.split("todo ", 2);
            cmdTask = cmds[1];
            Task newTask = new Todo(cmdTask);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidTodo();
        }
    }

    public static void parseEvent(String cmd) {
        try {
            cmds = cmd.split("event |/at", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Event(cmdTask, cmdTaskType);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidEvent();
        }
    }

    public static void parseDeadline(String cmd) {
        try {
            cmds = cmd.split("deadline |/by", 3);
            cmdTask = cmds[1];
            cmdTaskType = cmds[2];
            Task newTask = new Deadline(cmdTask, cmdTaskType);
            taskList.add(newTask);
            Printer.printTask(taskList);
        } catch (IndexOutOfBoundsException e) {
            Printer.printInvalidDeadline();
        }
    }
    */

}
