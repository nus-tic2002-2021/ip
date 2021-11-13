package Duke.Parser;

import Duke.Checker.InputChecker;
import Duke.Models.*;
import Duke.DukeLogic.DukeException;
import Duke.DukeLogic.TaskList;
import Duke.DukeLogic.Ui;
import Duke.Duke;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a parser that make sense of inputs and retrieved lines.
 */
public class InputParser {

    /**
     * Reads the input
     * create a task object based on the input
     * and adds it into the task list of Duke.
     * @param input
     */
    public static void parseInput(String input) {
        if (input.trim().equals("list")) {
            TaskList.printTaskList();
        } else if (input.startsWith("todo ")) {
            parseTodoInput(input);
        } else if (input.startsWith("deadline ")) {
            parseDeadlineInput(input);
        } else if (input.startsWith("event ")) {
            parseEventInput(input);
        } else if (input.startsWith("done ")) {
            parseDoneInput(input);
        } else if (input.startsWith("delete ")) {
            parseDeleteInput(input);
        } else if (input.startsWith("find ")) {
            parseFindInput(input);
        } else if (input.startsWith("dowithin ")) {
            parseDoWithinInput(input);
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * If input contains valid information for a "To do" object,
     * method creates the "to do" Object from the input
     * and adds it to duke task list
     * @param input
     */
    public static void parseTodoInput(String input) {
        try {
            if (InputChecker.isValidTodoInput(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                TaskList.addTaskToList(newTodo);
                addPriorityToTask(newTodo);
                Ui.printTaskAdded(newTodo);
                Ui.printTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * If input contains valid information for a "deadline" object,
     * method creates the "deadline" Object from the input
     * and adds it to duke task list
     * @param input
     */
    public static void parseDeadlineInput(String input) {
        try {
            if (InputChecker.isValidDeadlineInput(input)) {
                Deadline newDeadline = buildDeadline(input);
                TaskList.addTaskToList(newDeadline);
                addPriorityToTask(newDeadline);
                Ui.printTaskAdded(newDeadline);
                Ui.printTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * Takes in a valid input with information on a deadline,
     * parse the parts of the input
     * and returns a new Deadline object created using the relevant constructor.
     * @param input
     * @return Deadline object created from a valid input with deadline information.
     */
    public static Deadline buildDeadline(String input) {
        String[] parts = input.substring(8).split("/by");
        String[] datetime = parts[1].trim().split(" ");
        Deadline newDeadline;
        if (datetime.length > 2) {
            newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        } else {
            String[] dateDetails = datetime[0].split("/");
            if (checkDateInput(dateDetails)) {
                LocalDate taskDate = buildTaskDate(dateDetails);
                if (datetime.length == 2 && checkTimeInput(datetime[1])) {
                    LocalTime taskTime = buildTaskTime(datetime[1]);
                    newDeadline = new Deadline(parts[0].trim(), taskDate, taskTime);
                } else {
                    newDeadline = new Deadline(parts[0].trim(), taskDate);
                }
            } else {
                newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
            }
        }
        return newDeadline;
    }

    /**
     * If input contains valid information for an "event" object,
     * method creates the "event" Object from the input
     * and adds it to duke task list
     * @param input
     */
    public static void parseEventInput(String input) {
        try {
            if (InputChecker.isValidEventInput(input)) {
                Event newEvent = buildEvent(input);
                TaskList.addTaskToList(newEvent);
                addPriorityToTask(newEvent);
                Ui.printTaskAdded(newEvent);
                Ui.printTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * Takes in a valid input with information on an event,
     * parse the parts of the input
     * and returns a new Event object created using the relevant constructor.
     * @param input
     * @return an Event object created from a valid input with event information.
     */
    public static Event buildEvent(String input) {
        String[] parts = input.substring(5).split("/at");
        String[] datetime = parts[1].trim().split(" ");
        Event newEvent;
        if (datetime.length > 2) {
            newEvent = new Event(parts[0].trim(), parts[1].trim());
        } else {
            String[] dateDetails = datetime[0].split("/");
            if (checkDateInput(dateDetails)) {
                LocalDate taskDate = buildTaskDate(dateDetails);
                if (datetime.length == 2 && checkTimeInput(datetime[1])) {
                    LocalTime taskTime = buildTaskTime(datetime[1]);
                    newEvent = new Event(parts[0].trim(), taskDate, taskTime);
                } else {
                    newEvent = new Event(parts[0].trim(), taskDate);
                }
            } else {
                newEvent = new Event(parts[0].trim(), parts[1].trim());
            }
        }
        return newEvent;
    }

    /**
     * If input contains valid information for a "dowithin" object,
     * method creates the "dowithin" Object from the input
     * and adds it to duke task list
     * @param input
     */
    public static void parseDoWithinInput(String input) {
        try {
            if (InputChecker.isValidDoWithinInput(input)) {
                DoWithin newDoWithin = buildDoWithin(input);
                TaskList.addTaskToList(newDoWithin);
                addPriorityToTask(newDoWithin);
                Ui.printTaskAdded(newDoWithin);
                Ui.printTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * Takes in a valid input with information on
     * a "dowithin" task that has to be done within a period of time,
     * parse the parts of the input
     * and returns a new DoWithin object created using the relevant constructor.
     * @param input
     * @return a DoWithin object created from a valid input with dowithin information.
     */
    public static DoWithin buildDoWithin(String input) {
        String[] parts = input.substring(8).split("/start");
        String[] startEnd = parts[1].split("/end");
        String[] startDateTime = startEnd[0].trim().split(" ");
        String[] endDateTime = startEnd[1].trim().split(" ");
        DoWithin newDoWithin;
        LocalDate newStartDate = null, newEndDate = null;
        LocalTime newStartTime = null, newEndTime = null;
        if (startDateTime.length > 2 && endDateTime.length > 2) {
            newDoWithin = new DoWithin(parts[0], startEnd[0], startEnd[1]);
        } else {
            if (startDateTime.length < 3) {
                String[] startDateDetails = startDateTime[0].trim().split("/");
                if (checkDateInput(startDateDetails)) {
                    newStartDate = buildTaskDate(startDateDetails);
                    if (startDateTime.length == 2 && checkTimeInput(startDateTime[1])) {
                        newStartTime = buildTaskTime(startDateTime[1]);
                    }
                }
            }
            if (endDateTime.length < 3) {
                String[] endDateDetails = endDateTime[0].trim().split("/");
                if (checkDateInput(endDateDetails)) {
                    newEndDate = buildTaskDate(endDateDetails);
                    if (endDateTime.length == 2 && checkTimeInput(endDateTime[1])) {
                        newEndTime = buildTaskTime(endDateTime[1]);
                    }
                }
            }
            newDoWithin = new DoWithin(parts[0], startEnd[0], startEnd[1], newStartDate, newStartTime,
                    newEndDate, newEndTime);
        }
        return newDoWithin;
    }

    /**
     * Takes in a string and checks whether it is in an input representing time.
     * If it is in valid format, return true.
     * @param timeInput
     * @return boolean on whether input is represented in a valid time format.
     */
    public static boolean checkTimeInput(String timeInput) {
        if (timeInput.equals("")) {
            return false;
        }
        try {
            int time = Integer.parseInt(timeInput);
            int hour = time/100;
            int mins = time%100;
            if (hour < 24 && hour > -1 && mins < 60 && mins > -1) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Takes and parse a string representing time
     * and return a new LocalTime object
     * created from the parsed parts of the string.
     * @param timeInput
     * @return LocalTime object created from a valid time format input.
     */
    public static LocalTime buildTaskTime(String timeInput) {
        int time = Integer.parseInt(timeInput);
        int hour = time/100;
        int mins = time%100;
        LocalTime taskTime = LocalTime.of(hour, mins);
        return taskTime;
    }

    /**
     * Takes in a string array and checks whether it contains valid strings
     * that represent date, month and year.
     * If valid, the method returns true.
     * @param details
     * @return boolean whether input contains the correct details to represent date.
     */
    public static boolean checkDateInput(String[] details) {
        if (details.length != 3) {
            return false;
        }
        try {
            int day = Integer.parseInt(details[0].trim());
            int month = Integer.parseInt(details[1].trim());
            int year = Integer.parseInt(details[2].trim());
            if (day > 31 || day < 1) {
                return false;
            } else if (month > 12 || month < 1) {
                return false;
            } else if (year < 0) {
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /** Takes a string array with details on a specific date
     * and return a LocalDate object
     * created using the details.
     * @param details
     * @return LocalDate object created from the parts of a valid date format input.
     */
    public static LocalDate buildTaskDate(String[] details) {
        String day = details[0].trim();
        if (day.length() == 1) {
            day = "0" + day;
        }
        String month = details[1].trim();
        if (month.length() == 1) {
            month = "0" + month;
        }
        String year = details[2].trim();
        if (year.length() < 4) {
            for (int i = year.length(); i < 4; i ++) {
                year = "0" + year;
            }
        }
        String newDateFormat = year + "-" + month + "-" + day;
        LocalDate taskDate = LocalDate.parse(newDateFormat);
        return taskDate;
    }

    /**
     * Takes in a task object and request priority level of task from user.
     * Set the priority level of the task object based on the input from user.
     * @param newTask
     */
    public static void addPriorityToTask(Task newTask) {
        Ui.requestPriorityLevel();
        String level = Duke.getInput();
        newTask.setPriority(level);
    }

    /**
     * If input contain valid information to identify a task in the list,
     * mark said task object as completed.
     * @param input
     */
    public static void parseDoneInput(String input) {
        try {
            if (InputChecker.isValidDoneInput(input)) {
                TaskList.markTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * If input contain valid information to identify a task in the list,
     * delete said task object.
     * @param input
     */
    public static void parseDeleteInput(String input) {
        try {
            if (InputChecker.isValidDeleteInput(input)) {
                TaskList.deleteTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * If input contain a valid keyword to search for task,
     * print tasks with the keyword in their descriptions.
     * @param input
     */
    public static void parseFindInput(String input) {
        String[] parts = input.split(" ");
        try {
            if (InputChecker.isValidFindInput(parts)) {
                TaskList.printTaskWithDesc(parts[1]);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

}

