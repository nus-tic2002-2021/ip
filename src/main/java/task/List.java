package task;

import error.DukeException;
import error.FileException;
import error.NotFoundException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;


public class List {
    private static ArrayList<Task> taskArrayList;
    private static ArrayList<String> taskSave;
    public static Task recentDelete;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    /**
     * Create new a new empty List
     */
    public List() {
        taskArrayList = new ArrayList<>();
        taskSave = new ArrayList<>();
    }

    /**
     * Create new a new loaded List
     *
     * @param loadFile loaded txt file
     */
    public List(ArrayList<String> loadFile) throws FileException {
        taskArrayList = new ArrayList<>();
        taskSave = new ArrayList<>();
        String[] taskArr;
        loadFile.listIterator();
        for (String task : loadFile) {
            taskArr = task.split(" \\| ");
            String taskType, taskDone, taskDescription;
            LocalDateTime date;
            LocalTime time;
            Boolean isDone = false;
            taskType = taskArr[0];
            taskDone = taskArr[1];
            taskDescription = taskArr[2];
            if (taskDone.equals("1")) {
                isDone = true;
            }
            switch (taskType) {
            case "T":
                taskArrayList.add(new Todo(taskDescription, isDone));
                break;
            case "D":
                try {
                    date = LocalDateTime.parse(taskArr[3], DATE_TIME_FORMATTER);
                    taskArrayList.add(new Deadline(taskDescription, date, isDone));
                } catch (Exception e) {
                    throw new FileException();
                }
                break;

            case "E":
                try {
                    date = LocalDateTime.parse(taskArr[3], DATE_TIME_FORMATTER);
                    time = LocalTime.parse(taskArr[4], TIME_FORMATTER);
                    taskArrayList.add(new Event(taskDescription, date, time, isDone));
                } catch (Exception e) {
                    throw new FileException();
                }
                break;

            }
        }

    }

    /**
     * Adds new to do task to taskList as a TODO object
     * The inputMsg contains the instruction all input is part of the description
     * "description"
     * On successful adding of task, it prints the task added
     *
     * @param inputMsg description to be added
     * @throws DukeException if inputMsg is empty
     */
    public void addTodo(String inputMsg) throws DukeException {
        if (inputMsg.isEmpty()) {
            throw new DukeException("TODO_DESCRIPTION_ERROR");
        }
        taskArrayList.add(new Todo(inputMsg));
        printAdd();
    }

    /**
     * Adds new deadline task to taskList as a DEADLINE object.
     * The inputMsg contains the instruction where only the format
     * "description" /by "DateTime" is accepted
     * On successful adding of task, it prints the task added.
     *
     * @param inputMsg description and /by to be added
     * @throws DukeException if inputMsg is does not contain /by or more than 1 /at OR date is not valid
     */
    public void addDeadline(String inputMsg) throws DukeException {
        String[] input = inputMsg.split(" /by ");
        LocalDateTime date;
        if (input.length < 2) {
            throw new DukeException("DEADLINE_DESCRIPTION_ERROR");
        } else if (input.length > 2) {
            throw new DukeException("DEADLINE_LENGTH_ERROR");
        }
        try {
            date = LocalDateTime.parse(input[1], DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new DukeException("INVALID_DATETIME_FORMAT");
        }
        taskArrayList.add(new Deadline(input[0], date));
        printAdd();
    }

    /**
     * Adds new event task to taskList as a EVENT object
     * The inputMsg contains the instruction where only the format
     * "description" /by "DateTime" /for "Time" is accepted
     * It checks if there is any existing task with clashing timings and
     * on successful adding of task, it prints the task added.
     *
     * @param inputMsg description and /at to be added
     * @throws DukeException if inputMsg does not contain /at or more than 1 /at
     */
    public void addEvent(String inputMsg) throws DukeException {
        String[] input = inputMsg.split(" /at ");
        String[] event;
        LocalDateTime date;
        LocalTime time;
        Boolean clash = false;
        assert input.length == 2;
        if (input.length < 2) {
            throw new DukeException("EVENT_DESCRIPTION_ERROR");
        }
        if (input.length > 2) {
            throw new DukeException("EVENT_LENGTH_ERROR");
        }
        event = input[1].split(" /for ");
        if (event.length > 2) {
            throw new DukeException("EVENT_LENGTH_ERROR");
        }
        try {
            date = LocalDateTime.parse(event[0], DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new DukeException("INVALID_DATETIME_FORMAT");
        }
        try {
            time = LocalTime.parse(event[1], TIME_FORMATTER);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("EVENT_LENGTH_ERROR");
        } catch (Exception e) {
            throw new DukeException("INVALID_TIME_FORMAT");
        }
        //anomaly checker
        EventDateTime addEvent = new EventDateTime(date, time);
        for (Task task : taskArrayList) {
            if (task.getType().equals(Action.EVENT)) {
                EventDateTime existEvent = new EventDateTime(task.getDateTime(), task.getTime());
                if (addEvent.isAnomaly(existEvent)) {
                    printClash(task);
                    clash = true;
                    break;
                }
            }
        }
        if (!clash) {
            taskArrayList.add(new Event(input[0], date, time));
            printAdd();
        }
    }

    /**
     * Modify the task to be set as done.
     * taskNumber is parsed into an integer and modifies the task in the array
     * of parsed integer to be set as done.
     *
     * @param taskNumber number to determine which task to modify
     * @throws NotFoundException if the taskNumber is not within array size
     */
    public void taskDone(String taskNumber) throws NotFoundException {
        Integer inputNumber = Integer.parseInt(taskNumber);
        assert inputNumber > 0;
        if (taskArrayList.size() < inputNumber) {
            throw new NotFoundException();
        }
        taskArrayList.get(inputNumber - 1).setDone();
    }

    /**
     * Deletes the task.
     * taskNumber is parsed into an integer and gets the task in the array
     * of parsed integer to be deleted and stores it first.
     * Upon successful deletion, the deleted task in printed.
     *
     * @param taskNumber number to determine which task to delete
     * @throws NotFoundException if the taskNumber is not within array size
     */
    public void taskDelete(String taskNumber) throws NotFoundException {
        Integer inputNumber = Integer.parseInt(taskNumber);
        if (taskArrayList.size() < inputNumber) {
            throw new NotFoundException();
        }
        recentDelete = taskArrayList.get(inputNumber - 1);
        taskArrayList.remove(inputNumber - 1);
        printDelete();
    }

    /**
     * Prints out all the task in the taskArrayList.
     */
    public void printList() {
        if (taskArrayList.size() == 0) { //0 items in list
            System.out.println("List is empty!"); //throw empty list
        } else {
            int count = 1;
            for (Task task : taskArrayList) {
                System.out.println("" + (count) + "." + task);
                count++;
            }
        }
    }

    /**
     * Prints out all the task that contains the search keyword(non-case sensitive).
     *
     * @param search the keyword to search for in description
     */
    public void printSearchList(String search) {
        int count = 1;
        for (Task task : taskArrayList) {
            if (task.getDescription().toLowerCase().contains(search.toLowerCase())) {
                System.out.println("" + (count) + "." + task);
                count++;
            }
        }
        if (count == 1) {
            System.out.println("Nothing found");
        }
    }

    /**
     * Prints out all the task that contains the search keyword(non-case sensitive)
     * excluding those without a valid DateTime.
     *
     * @param searchDate the keyword to search for in date
     */
    public void printSchedule(String searchDate) throws DukeException {
        int count = 1;
        try {
            LocalDate date = LocalDate.parse(searchDate, DATE_FORMATTER);
            for (Task task : taskArrayList) {
                if (task.getDateTime() != null) { //excludes task without date
                    if (task.getDateTime().toLocalDate().equals(date)) {
                        System.out.println("" + (count) + "." + task);
                        count++;
                    }
                }
            }
            if (count == 1) {
                System.out.println("Nothing found");
            }
        } catch (Exception e) {
            throw new DukeException("INVALID_DATE_FORMAT");
        }
    }

    public void saveList() {
        for (Task task : taskArrayList) {
            taskSave.add(task.getSave());
        }
    }

    public ArrayList<String> getSave() {
        return taskSave;
    }

    /**
     * Print out the most recent task added.
     */
    public void printAdd() {
        System.out.println("Got it. Item successfully added to the list: ");
        taskArrayList.get(taskArrayList.size() - 1).print();
        System.out.println("Now you have " + (taskArrayList.size()) + " task(s) in the list");
    }

    /**
     * Print out the task that clash with the event trying to be added.
     */
    public void printClash(Task clashTask) {
        System.out.println("Event clashes with an existing event.");
        clashTask.print();
        System.out.println("Delete existing event or change the timing.");
    }

    /**
     * Print out the most recent task deleted.
     */
    public void printDelete() {
        System.out.println("Noted. I have removed the task: ");
        recentDelete.print();
        System.out.println("Now you have " + (taskArrayList.size()) + " task(s) in the list");
    }

    public int getArraySize() {
        return taskArrayList.size();
    }


}

