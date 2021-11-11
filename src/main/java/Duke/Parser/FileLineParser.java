package Duke.Parser;

import Duke.Checker.FileLineChecker;

import Duke.DukeLogic.DukeException;
import Duke.DukeLogic.TaskList;
import Duke.Models.Deadline;
import Duke.Models.Event;
import Duke.Models.Task;
import Duke.Models.Todo;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class FileLineParser {
    /**
     * Takes and parse a line from the storage file,
     * uses the information from the line to create
     * and return the respective task object based on the line.
     * @param FileLine
     * @return Task object created from file line.
     * @throws DukeException
     */
    public static Task parseLineFromFile(String FileLine) throws DukeException {
        String[] parts = FileLine.split("\\|");
        if (parts[0].trim().equals("T")) {
            if(FileLineChecker.isValidTodoLine(parts)) {
                Todo newTodo = getTodoFromLine(parts);
                return newTodo;
            }
        } else if (parts[0].trim().equals("D")) {
            if(FileLineChecker.isValidDeadlineLine(parts)) {
                Deadline newDeadline = getDeadlineFromLine(parts);
                return newDeadline;
            }
        } else if (parts[0].trim().equals("E")) {
            if (FileLineChecker.isValidEventLine(parts)) {
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
            TaskList.markTask(newTodo);
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
            TaskList.markTask(newDeadline);
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
            TaskList.markTask(newEvent);
        }
        newEvent.setPriority(parts[4]);
        return newEvent;
    }
}
