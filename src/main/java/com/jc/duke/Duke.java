package com.jc.duke;

import java.util.Scanner;
import com.jc.duke.Exception.DukeInvalidCommandException;
import com.jc.duke.Exception.DukeTaskNotFoundException;
import com.jc.duke.Storage.Storage;
import com.jc.duke.Task.DateTime;
import com.jc.duke.Task.TaskList;
import com.jc.duke.Task.Type;
import com.jc.duke.Ui.Ui;

/**
 * Duke class is main code
 */

public class Duke {
    static String command;
    static String task;
    static String condition;
    static DateTime dateTime;
    static Type type;
    static Boolean checkCommand;
    private Ui ui;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        ui.showWelcome();
        storage = new Storage();

        String line = "";
        Scanner in = new Scanner(System.in).useDelimiter("\\n");
        TaskList tl = new TaskList();
        Boolean isAddTask = true;

        while (true) {
            line = in.next();

            if(line.equals("bye")) {
                ui.showGoodbye();
            } else if (line.equals("help")) {
                ui.showHelp();
                isAddTask = false;
            } else if (line.equals("list")) {
                tl.printTaskList();
                isAddTask = false;
            } else if (line.equals("save")) {
                tl.saveTaskList();
                isAddTask = false;
            } else if (line.equals("load")) {
                tl.loadTaskList();
                isAddTask = false;
            } else if (line.contains("find")) {
                tl.findTask(line.split(" ")[1]);
                isAddTask = false;
            } else if (line.equals("archive")) {
                tl.archiveTaskList();
                isAddTask = false;
            }  else if (line.equals("archive show")) {
                tl.getArchiveList();
                isAddTask = false;
            } else if (line.contains("done")) {
                //System.out.println(line);
                isAddTask = false;
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    tl.setDone(index);
                } catch (DukeTaskNotFoundException ex) {
                    ui.showException(ex.toString());
                }

            } else if (line.contains("delete")) {
                isAddTask = false;
                try {
                    int index = Integer.parseInt(line.split(" ")[1]);
                    tl.deleteTask(index);
                } catch (DukeTaskNotFoundException ex) {
                    ui.showException(ex.toString());
                }

            }

            if(isAddTask) {
                try {
                    parseAddTask(line);
                    if(checkCommand) {
                        tl.addTask(task,type, dateTime);
                    }
                } catch (DukeInvalidCommandException ex) {
                    ui.showException(ex.toString());
                }

            }
            isAddTask = true;
        }

    }

    public static void parseAddTask (String input) throws DukeInvalidCommandException {
        String temp = "";

        temp = input.split(" ")[0];
        checkCommand = true;
        switch(temp) {
            case "todo":
                type = Type.todo;
                command = temp;
                break;
            case "deadline":
                type = Type.deadline;
                command = temp;
                break;
            case "event":
                type = Type.event;
                command = temp;
                break;
            default:
                //System.out.println("invalid task command");
                checkCommand = false;
                throw new DukeInvalidCommandException("Invalid add command. Try 'todo' 'event' 'deadline'");
        }

        //System.out.println("Command:" + command);
        //remove command + space
        temp = input.replaceFirst(temp + " ", "");

        task = temp.split(" /")[0];

        //System.out.println("Task.Task :" + task);
        try {
            condition = temp.split(" /")[1];
            parseCondition(condition);
        } catch (Exception ex) {
            throw new DukeInvalidCommandException("Missing Description/Date Time");
        }

        //System.out.println("Condition :" + condition);
    }

    public static void parseCondition(String input) {
        String temp[] = input.split(" ");
        String time = input.substring(input.indexOf(" ") + 1);
        dateTime = new DateTime(temp[0], time);
    }

    public static void main(String[] args) throws DukeTaskNotFoundException {

        new Duke();


    }
}