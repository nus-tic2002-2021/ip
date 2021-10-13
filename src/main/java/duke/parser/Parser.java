package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.ui.Printer;
import duke.tasklist.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Parser {

    //protected static ArrayList<Task> taskList = new ArrayList<>();

    public static Command parse(String fullCommand) throws DukeException {

        String[] taskFullDesc = fullCommand.split(" ", 2);
        String taskType = taskFullDesc[0].toUpperCase();
        String taskDesc = taskFullDesc[1];
        EnumCommand enumCommand = null;

        try {
            enumCommand = EnumCommand.valueOf(taskType);
        } catch(IllegalArgumentException e) {
            //Use ui to display error later
            //return new InvalidCommand("Command <" + taskType.toLowerCase() + "> not found :(");
        }

        try {
            switch (enumCommand) {
                case BYE:
                    //return parseBye();
                case LIST:
                    //return parseList(cmd);
                case TODO:
                    Task task = new Todo(taskDesc);
                    return new AddCommand(task);
                default:
                    //return CmdsParser.parse(cmd);
                    throw new DukeException();
            }
        }

        catch (DukeException e) {
            throw new DukeException();
        }

    }

    public static boolean parseBye () {
        Printer.printBye();
        return false;
    }

    public static boolean parseList(String cmd) {
        //if (taskList.size() == 0) Printer.printNoTask();
        //else Printer.printList();
        return true;
    }

}
