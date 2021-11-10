package Duke.Parser;
import Duke.DukeLogic.DukeException;
import Duke.Models.*;
import Duke.Checker.FileLineChecker;
import Duke.Checker.InputChecker;
import Duke.DukeLogic.TaskList;
import Duke.DukeLogic.Ui;
import Duke.Duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that make sense of inputs and retrieved lines.
 */
public class Parser {

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
            AddTodo(input);
        } else if (input.startsWith("deadline ")) {
            AddDeadline(input);
        } else if (input.startsWith("event ")) {
            AddEvent(input);
        } else if (input.startsWith("done ")) {
            MarkDone(input);
        } else if (input.startsWith("delete ")) {
            DeleteTask(input);
        } else if (input.startsWith("find ")) {
            findTask(input);
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
    public static void AddTodo(String input) {
        try {
            if (InputChecker.CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                TaskList.addTaskToList(newTodo);
                addPriorityToTask(newTodo);
                Ui.PrintTaskAdded(newTodo);
                Ui.PrintTaskCount();
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
    public static void AddDeadline(String input) {
        try {
            if (InputChecker.CheckValidDeadline(input)) {
                Deadline newDeadline = buildDeadline(input);
                TaskList.addTaskToList(newDeadline);
                addPriorityToTask(newDeadline);
                Ui.PrintTaskAdded(newDeadline);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * If input contains valid information for an "event" object,
     * method creates the "event" Object from the input
     * and adds it to duke task list
     * @param input
     */
    public static void AddEvent(String input) {
        try {
            if (InputChecker.CheckValidEvent(input)) {
                Event newEvent = buildEvent(input);
                TaskList.addTaskToList(newEvent);
                addPriorityToTask(newEvent);
                Ui.PrintTaskAdded(newEvent);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
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
    public static void MarkDone(String input) {
        try {
            if (InputChecker.CheckValidDone(input)) {
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
    public static void DeleteTask(String input) {
        try {
            if (InputChecker.CheckValidDelete(input)) {
                TaskList.DeleteIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    /**
     * Takes and parse a line from the storage file,
     * uses the information from the line to create
     * and return the respective task object based on the line.
     * @param FileLine
     * @return Task object created from file line.
     * @throws DukeException
     */
    public static Task ParseStorageLine(String FileLine) throws DukeException {
        String[] parts = FileLine.split("\\|");
        if (parts[0].trim().equals("T")) {
            if(FileLineChecker.CheckTodoLine(parts)) {
                Todo newTodo = getTodoFromLine(parts);
                return newTodo;
            }
        } else if (parts[0].trim().equals("D")) {
            if(FileLineChecker.CheckDeadlineLine(parts)) {
                Deadline newDeadline = getDeadlineFromLine(parts);
                return newDeadline;
            }
        } else if (parts[0].trim().equals("E")) {
            if (FileLineChecker.CheckEventLine(parts)) {
                Event newEvent = getEventFromLine(parts);
                return newEvent;
            }
        } else {
            System.out.println("Line is invalid");
        }
        throw new DukeException("A line from storage file is invalid and will not be added to Duke.");
    }

    /**
     * Takes in the parsed parts of a line from the storage file
     * and return a new "To do" object created from the parts.
     * @param parts
     * @return "To do" object created from information parsed from a file line.
     */
    public static Todo getTodoFromLine(String[] parts) {
        Todo newTodo = new Todo(parts[2].trim());
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newTodo);
        }
        newTodo.setPriority(parts[3]);
        return newTodo;
    }

    /**
     * Takes in the parsed parts of a line from the storage file,
     * checks whether it has a date and time and creates and returns
     * a new "Deadline" object using the correct constructor based on the checks.
     * @param parts
     * @return Deadline object created from information parsed from a file line.
     */
    public static Deadline getDeadlineFromLine(String[] parts) {
        String[] datetime = parts[3].trim().split(" ");
        Deadline newDeadline;
        if (datetime.length > 2 ) {
            newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
        } else {
            LocalDate newDate = null;
            LocalTime newTime = null;
            try {
                newDate = LocalDate.parse(datetime[0]);
                newTime = LocalTime.parse(datetime[1]);
            } catch (DateTimeParseException e) {
                e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
            }
            if (newDate != null && newTime != null) {
                newDeadline = new Deadline(parts[2].trim(), newDate, newTime);
            } else if (newDate != null) {
                newDeadline = new Deadline(parts[2].trim(), newDate);
            } else {
                newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
            }
        }
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newDeadline);
        }
        newDeadline.setPriority(parts[4]);
        return newDeadline;
    }

    /**
     * Takes in the parsed parts of a line from the storage file,
     * checks whether it has a date and time and creates and returns
     * a new "Event" object using the correct constructor based on the checks.
     * @param parts
     * @return an Event object created from information parsed from a file line.
     */
    public static Event getEventFromLine(String[] parts) {
        String[] datetime = parts[3].trim().split(" ");
        Event newEvent;
        if (datetime.length > 2 ) {
            newEvent = new Event(parts[2].trim(), parts[3].trim());
        } else {
            LocalDate newDate = null;
            LocalTime newTime = null;
            try {
                newDate = LocalDate.parse(datetime[0]);
                newTime = LocalTime.parse(datetime[1]);
            } catch (DateTimeParseException e) {
                e.getMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.getMessage();
            }
            if (newDate != null && newTime != null) {
                newEvent = new Event(parts[2].trim(), newDate, newTime);
            } else if (newDate != null) {
                newEvent = new Event(parts[2].trim(), newDate);
            } else {
                newEvent = new Event(parts[2].trim(), parts[3].trim());
            }
        }
        if (parts[1].trim().equals("1")) {
            TaskList.MarkTask(newEvent);
        }
        newEvent.setPriority(parts[4]);
        return newEvent;
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
     * Takes in a valid input with information on a deadline,
     * parse the parts of the input
     * and returns a new Deadline object created using the relevant constructor.
     * @param input
     * @return Deadline object created from a valid input with deadline information.
     */
    public static Deadline buildDeadline(String input) {
        String[] parts = input.substring(8).split("/by");
        String[] datetime = parts[1].trim().split(" ");
        Deadline newDeadline = null;
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
     * Takes in a valid input with information on an event,
     * parse the parts of the input
     * and returns a new Event object created using the relevant constructor.
     * @param input
     * @return a Event object created from a valid input with event information.
     */
    public static Event buildEvent(String input) {
        String[] parts = input.substring(5).split("/at");
        String[] datetime = parts[1].trim().split(" ");
        Event newEvent = null;
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

    public static void findTask(String input) {
        String[] parts = input.split(" ");
        try {
            if (InputChecker.checkValidFind(parts)) {
                TaskList.printTaskWithDesc(parts[1]);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

}

