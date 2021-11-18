package com.tic2002.app;

import com.tic2002.task.TaskCommands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.tic2002.app.DateTime.isValidDate;
import static com.tic2002.app.DateTime.isValidTime;
import static com.tic2002.app.DateTime.toDate;
import static com.tic2002.app.DateTime.toTime;
import static com.tic2002.task.Find.findWord;
import static com.tic2002.task.Priority.isValidPriority;
import static com.tic2002.task.TaskCommands.addEvent;
import static com.tic2002.task.TaskCommands.addTodo;
import static com.tic2002.task.TaskCommands.addDeadline;
import static com.tic2002.task.TaskCommands.updatePriority;

public class Parser {
    /**
     * Set enumerations of valid commands
     */
    public enum validCommands {
        LIST, DONE, UNDONE, DELETE, HELP, BYE, TODO, EVENT, DEADLINE, ON, PRIORITY, FIND
    }

    /**
     * To check if user input String check is valid
     * @param checkCommand takes user input as String
     *              check if checkCommand matches enum
     * @return true if match, false if doesn't match
     */
    public static boolean isValidInput (String checkCommand) {
        for (validCommands a : validCommands.values()) {
            if(a.name().equals(checkCommand)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Takes in input from user and makes sense of input for Event
     */
    public static void eventParse(String userEntry, String description){
        String timeDescription = userEntry.split("/at ")[1];
        if(description.equals("")) {
            System.out.println("include /at");
        }
        else {
            description = description.split(" /")[0];
            addEvent(description,timeDescription);
        }
    }

    /**
    * Takes in input from user and makes sense of input for Deadline
     */
    public static void deadlineParse(String userEntry, String description){
        String timeDescription = userEntry.split("/by ")[1];
        description = description.split(" /")[0];
        String date = timeDescription.split(", ")[0];
        String time = timeDescription.split(", ")[1];

        if(description.equals("")) {
            System.out.println("include /by");
        }
        else if(!isValidDate(date) || !isValidTime(time)) {
            System.out.println("Please use an exact date time, yyyy-mm-dd, hh:mm");
        }
        else{
            try {
                LocalDate dateDate = toDate(date);
                LocalTime timeTime = toTime(time);
                addDeadline(description, dateDate, timeTime);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter in yyyy-mm-dd, hh:mm");
            }
        }
    }

    public static void priorityParse(String description){
        int ref = Integer.parseInt(description.split(" ")[0]);
        int priority = Integer.parseInt(description.split(" ")[1]);
        if(isValidPriority(priority)) {
                updatePriority(ref-1, priority);
        }
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    /**
     * Takes user input as String and parse input
     * @param userEntry Takes user entry as String
     *                  Creates String timeDescription for Deadline & Event cases
     *                  Creates String description to store description of task
     *                  Creates String command to store first word of String userEntry, which is the command
     *                  Create int size to check length of String userEntry
     *                  String commandUpper to change String command to upper case
     *                  check if user entered only command without description, except for LIST & BYE
     *                  Check if matches validCommands
     * @throws DukeException when error occurs
     * switch for commandUpper
     * done - Set task as done
     * undone - Set task as not done
     * delete - delete a task
     * help - prints out instructions
     * bye - ends Duke
     * todo - creates todo task
     * event - creates event
     * deadline - creates task with deadline
     * on - returns task with matching date
     * priority - sets task priority
     * find - find tasks that matches user input
     */
    public static void parseCommand(String userEntry) throws DukeException {
        String description;
        String command = userEntry.split(" ")[0];
        description = userEntry.replaceFirst(command + " ", "");
        int size = userEntry.length();
        String commandUpper = command.toUpperCase();
        if(size == 1 && !commandUpper.equals("LIST") && !commandUpper.equals("BYE")) {
            throw new DukeException();
        }
        if(!isValidInput(commandUpper)) {
            throw new DukeException();
        }

        switch (commandUpper) {
        case "LIST":
            TaskCommands.printList();
            break;

        case"DONE":
            int ref = Integer.parseInt(description);
            TaskCommands.setDone(ref-1);
            break;

        case"UNDONE":
            ref = Integer.parseInt(description);
            TaskCommands.setUnDone(ref-1);
            break;

        case"DELETE":
            ref = Integer.parseInt(description);
            TaskCommands.deleteTask(ref-1);
            break;

        case"HELP":
            UI.instructions();
            break;

        case"BYE":
            UI.endDuke();
            break;

        case"TODO":
            addTodo(description);
            break;

        case"EVENT":
            eventParse(userEntry, description);
            break;

        case"DEADLINE":
            deadlineParse(userEntry, description);
            break;

        case"ON":
            if(isValidDate(description) || (!description.equals(""))) {
                LocalDate checkDate = toDate(description);
                TaskCommands.printDateList(checkDate);
            }
            break;

        case"PRIORITY":
            try {
                priorityParse(description);
            } catch (NumberFormatException e) {
            throw new DukeException();
        }
            break;

        case"FIND":
            findWord(description);
            break;

        default:
            throw new DukeException();
        }
    }
}




