package app;

import task.TaskCommands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static app.DateTime.*;
import static task.Find.findWord;
import static task.Priority.isValidPriority;
import static task.TaskCommands.*;


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
    public static boolean validInput (String checkCommand){
        for (validCommands a : validCommands.values()){
            if(a.name().equals(checkCommand)){
                return true;
            }
        }
        return false;
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
     */
    public static void parseCommand(String userEntry) throws DukeException {
        String timeDescription;
        String description;
        String command = userEntry.split(" ")[0];
        description = userEntry.replaceFirst(command + " ", "");
        int size = userEntry.length();
        String commandUpper = command.toUpperCase();
        if(size == 1 && !commandUpper.equals("LIST") && !commandUpper.equals("BYE")){
            throw new DukeException();
        }
        if(!validInput(commandUpper)){
            throw new DukeException();
        }
        /**
         * switch for commandUpper
         */
        {
                switch (commandUpper){
                    /**
                     * LIST - calls TaskCommands.printList() to print existing listing
                     * break to end command switch
                     */
                    case "LIST":
                        TaskCommands.printList();
                        break;
                    /**
                     * DONE - Get int ref of list = 0, marks task as done by calling TaskCommands.setDone(ref-1)
                     * break to end command switch
                     */
                    case"DONE":
                        int ref = Integer.valueOf(description);
                        TaskCommands.setDone(ref-1);
                        break;
                    /**
                     * UNDONE - Get int ref of list, marks task as undone
                     * break to end command switch
                     */
                    case"UNDONE":
                        ref = Integer.valueOf(description);
                        TaskCommands.setUnDone(ref-1);
                        break;
                    /**
                     * DELETE - Get ref of list = 0, finds task and delete by calling TaskCommands.deleteTask(ref-1)
                     * break to end command switch
                     */
                    case"DELETE":
                        ref = Integer.valueOf(description);
                        TaskCommands.deleteTask(ref-1);
                        break;
                    /**
                     * HELP - Calls UI.instructions()
                     * break to end command switch
                     */
                    case"HELP":
                        UI.instructions();
                        break;
                    /**
                     * BYE - Exits Duke, calls UI.endDuke()
                     * break to end command switch
                     */
                    case"BYE":
                        UI.endDuke();
                        break;
                    /**
                     * TODO - adds new task todo
                     * break to end command switch
                     */
                    case"TODO":
                        addTodo(description);
                        break;
                    /**
                     * EVENT - split String userEntry into time description as String timeDescription
                     * prints error if cannot split "/at "
                     * break to end command switch
                     */
                    case"EVENT":
                        timeDescription = userEntry.split("/at ")[1];
                        if(description.equals("")){
                            System.out.println("include /at");
                            break;
                        }
                        /**
                         * split description to remove timeDescription
                         * add to Event task
                         */
                        else{
                            description = description.split(" /")[0];
                            addEvent(description,timeDescription);
                        }
                        break;
                    /**
                     * DEADLINE - split String userEntry at "/by " into String timeDescription
                     *            split description to remove timeDescription
                     * @param date take first part of timeDescription split at "/"
                     * @param time take second part of timeDescription split at "/"
                     */
                    case"DEADLINE":
                        timeDescription = userEntry.split("/by ")[1];
                        description = description.split(" /")[0];
                        String date = timeDescription.split(", ")[0];
                        String time = timeDescription.split(", ")[1];
                    /**
                     * if only command available, print error
                     * break command switch
                     */
                        if(description.equals("")){
                            System.out.println("include /by");
                            break;
                        }
                        /**
                         * if DateTime check on date or time format is false, return error
                         * print error line
                         * break command switch
                         */
                        else if(!isValidDate(date) || !isValidTime(time)) {
                            System.out.println("Please use an exact date time, yyyy-mm-dd, hh:mm");
                            break;
                        }
                        /**
                         * convert String date to LocalDate date
                         * convert String time to LocalTime time
                         * add to Deadline task
                         */
                        else{
                            try{
                            LocalDate dateDate = toDate(date);
                            LocalTime timeTime = toTime(time);
                            addDeadline(description, dateDate, timeTime);
                            } catch (DateTimeParseException e){
                                System.out.println("Please enter in yyyy-mm-dd, hh:mm");
                            }
                        }
                        break;
                    /**
                     * ON - check if date is in correct format or if description is empty, return false
                     * print error line
                     * break switch
                     */
                    case"ON":
                        if(!isValidDate(description) || (description.equals(""))){
                            System.out.println("Please use an exact date: yyyy-mm-dd");
                            break;
                        }
                        /**
                         * convert String description to LocalDate date
                         * check if date exists in list and return list
                         */
                        else{
                            LocalDate checkDate = toDate(description);
                            TaskCommands.printDateList(checkDate);
                        }
                        break;
                    case"PRIORITY":
                        try {
                            ref = Integer.valueOf(description.split(" ")[0]);
                            int priority = Integer.valueOf(description.split(" ")[1]);

                            if(isValidPriority(priority)){
                                updatePriority(ref-1, priority);
                            }
                        } catch (NumberFormatException e){
                            throw new DukeException();
                        }
                        break;
                    case"FIND":
                        findWord(description);
                        break;

                    default: throw new DukeException();
                }
            }
        }

    }



