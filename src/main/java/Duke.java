import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> DukeList = new ArrayList<>();

    public static void addTaskToList(Task newEntry) {
        DukeList.add(newEntry);
    }

    public static void printTaskList() {
        System.out.println(Ui.line + "\nHere are the tasks in your list:");
        for (int i = 0; i < DukeList.size(); i++) {
            System.out.println((i + 1) + "." + DukeList.get(i).getTaskInfo());
        }
        System.out.println(Ui.line);
    }

    public static void markTaskAtIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(4).trim()) - 1;
        if (index < DukeList.size() && index > -1) {
            MarkTask(DukeList.get(index));
            System.out.println(Ui.line + "Nice! I've marked this task as done:\n  "
                    + DukeList.get(index).getTaskInfo() + "\n" + Ui.line);
        } else {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                    "The index number of the task to be done is invalid!\n" + Ui.line);
        }
    }

    public static void MarkTask(Task inputTask) {
        inputTask.markCompleted();
    }

    public static void ExtendTaskList() {
        boolean stop = false;
        while (!stop) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(Ui.line + "Bye. Hope to see you again soon!\n" + Ui.line);
                stop = true;
            } else if (input.trim().equals("list")) {
                printTaskList();
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
            } else {
                System.out.println(Ui.line + "☹ OOPS!!! " +
                        "I'm sorry, but I don't know what that means :-(\n" + Ui.line);
            }
        }
    }

    public static void AddTodo(String input) {
        try {
            if (CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                addTaskToList(newTodo);
                PrintTaskAdded(newTodo);
                PrintTaskCount();
                System.out.println(Ui.line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidTodo(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + Ui.line);
        } else if (input.substring(4).trim().equals("")) {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + Ui.line);
        } else {
            return true;
        }
    }

    public static void AddDeadline(String input) {
        try {
            if (CheckValidDeadline(input)) {
                String[] parts = input.substring(8).split("/by");
                Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
                addTaskToList(newDeadline);
                PrintTaskAdded(newDeadline);
                PrintTaskCount();
                System.out.println(Ui.line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDeadline(String input) throws DukeException {
        if (input.contains("/by")) {
            String[] parts = input.substring(8).split("/by");
            if (parts.length != 2) {
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The task description of a deadline cannot be empty.\n" + Ui.line);
            } else if (parts[1].trim().equals("")) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The due date/time of a deadline cannot be empty.\n" + Ui.line);
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding deadline.");
        }
    }

    public static void AddEvent(String input) {
        try {
            if (CheckValidEvent(input)) {
                String[] parts = input.substring(5).split("/at");
                Event newEvent = new Event(parts[0].trim(), parts[1].trim());
                addTaskToList(newEvent);
                PrintTaskAdded(newEvent);
                PrintTaskCount();
                System.out.println(Ui.line);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidEvent(String input) throws DukeException {
        if (input.contains("/at")) {
            String[] parts = input.substring(5).split("/at");
            if (parts.length != 2) {
                throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
            } else if (parts[0].trim().equals("")) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The task description of an event cannot be empty.\n" + Ui.line);
            } else if (parts[1].trim().equals("")) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The date/time of an event cannot be empty.\n" + Ui.line);
            } else {
                return true;
            }
        } else {
            throw new DukeException("☹ OOPS!!! Invalid syntax for adding event.");
        }
    }

    public static void MarkDone(String input) {
        try {
            if (CheckValidDone(input)) {
                markTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDone(String input) throws DukeException {
        if (input.length() < 5) {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                    "The index of the task to be marked as done is missing.\n"
                    + Ui.line);
        } else {
            try {
                int index = Integer.parseInt(input.substring(4).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The index of the task to mark as done has to be an integer!\n" + Ui.line);
            }
            return true;
        }
    }

    public static void PrintTaskCount() {
        System.out.println("Now you have " + DukeList.size() + " tasks in the list.");
    }

    public static void PrintTaskAdded(Task newTask) {
        System.out.println(Ui.line + "\nGot it. I've added this task:\n"
                + newTask.getTaskInfo());
    }

    public static void DeleteTask(String input) {
        try {
            if (CheckValidDelete(input)) {
                DeleteIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static boolean CheckValidDelete(String input) throws DukeException {
        if (input.length() < 7) {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                    "The index of the task to delete is missing.\n"
                    + Ui.line);
        } else {
            try {
                int test = Integer.parseInt(input.substring(6).trim()) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                        "The index of the task to delete has to be an integer!\n" + Ui.line);
            }
            return true;
        }
    }

    public static void DeleteIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(6).trim()) - 1;
        if (index < DukeList.size() && index > -1) {
            String DeletedInfo = DukeList.get(index).getTaskInfo();
            RemoveTask(index);
            System.out.println(Ui.line + "Noted! I've removed this task:\n  "
                    + DeletedInfo + "\n" + Ui.line);
            PrintTaskCount();
        } else {
            throw new DukeException(Ui.line + "\n☹ OOPS!!! " +
                    "The index number of the task to delete is invalid!\n" + Ui.line);
        }
    }

    public static void RemoveTask(int index) {
        DukeList.remove(index);
    }

    public static File OpenStorageFile() throws DukeException {
        File newFile = new File("data\\storage.txt");
        if (!newFile.exists()) {
            try {
                newFile = CreateStorageFile();
            } catch (DukeException e) {
                throw new DukeException("Duke will run without any prior stored task information");
            }
        }
        return newFile;
    }

    public static File CreateStorageFile() throws DukeException {
        File newFile = new File("data\\Storage.txt");
        try {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            System.out.println("New Storage.txt file created.");
            return newFile;
        } catch (IOException err) {
            throw new DukeException("Failed to create new Storage.txt file.");
        }
    }

    public static void ReadFileToArray(File storageFile) {
        try {
            Scanner s = new Scanner(storageFile);
            while (s.hasNext()) {
                try {
                    addTaskToList(ParseStorageLine(s.nextLine()));
                } catch (DukeException e) {
                    e.printErrMsg();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be opened, " +
                    "Duke will not have any prior stored task information");
        }
    }

    public static Task ParseStorageLine(String FileLine) throws DukeException {
        String[] parts = FileLine.split("\\|");
        if (parts[0].trim().equals("T")) {
            if(CheckTodoLine(parts)) {
                Todo newTodo = getTodoFromLine(parts);
                return newTodo;
            }
        } else if (parts[0].trim().equals("D")) {
            if(CheckDeadlineLine(parts)) {
                Deadline newDeadline = getDeadlineFromLine(parts);
                return newDeadline;
            }
        } else if (parts[0].trim().equals("E")) {
            if (CheckEventLine(parts)) {
                Event newEvent = getEventFromLine(parts);
                return newEvent;
            }
        } else {
            System.out.println("Line is invalid");
        }
        throw new DukeException("A line from storage file is invalid and will not be added to Duke.");
    }

    public static Todo getTodoFromLine(String[] parts) {
        Todo newTodo = new Todo(parts[2].trim());
        if (parts[1].trim().equals("1")) {
            MarkTask(newTodo);
        }
        return newTodo;
    }

    public static Deadline getDeadlineFromLine(String[] parts) {
        Deadline newDeadline = new Deadline(parts[2].trim(), parts[3].trim());
        if (parts[1].trim().equals("1")) {
            MarkTask(newDeadline);
        }
        return newDeadline;
    }

    public static Event getEventFromLine(String[] parts) {
        Event newEvent = new Event(parts[2].trim(), parts[3].trim());
        if (parts[1].trim().equals("1")) {
            MarkTask(newEvent);
        }
        return newEvent;
    }

    public static boolean CheckTodoLine(String[] parts) {
        if (parts.length != 3) {
            return false;
        } else if (parts[2].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean CheckDeadlineLine(String[] parts) {
        if (parts.length != 4) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static boolean CheckEventLine(String[] parts) {
        if (parts.length != 4) {
            return false;
        } else if (parts[2].trim().equals("") || parts[3].trim().equals("")) {
            return false;
        } else if (!parts[1].trim().equals("1") && !parts[1].trim().equals("0")) {
            return false;
        }
        return true;
    }

    public static void writeListToFile(File FileWrite) {
        try {
            FileWriter fw = new FileWriter(FileWrite, false);
            for (int i = 0; i < DukeList.size(); i++) {
                String typeCheck = DukeList.get(i).getTaskType();
                String newLine = "";
                if (typeCheck.equals("T")) {
                    newLine = buildStorageLine(DukeList.get(i)); // buildTodoLine(TaskList.get(i));
                } else if (typeCheck.equals("D")) {
                    newLine = buildStorageLine(DukeList.get(i)); //buildDeadlineLine(TaskList.get(i));
                } else if (typeCheck.equals("E")) {
                    newLine = buildStorageLine(DukeList.get(i)); //buildEventLine(TaskList.get(i));
                }
                fw.write(newLine + System.getProperty("line.separator"));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Write to file failed");
        }
    }

    public static String buildStorageLine(Task targetTask) {
        String divider = " | ";
        String initial = targetTask.getTaskType();
        String newLine = initial + divider;
        if (targetTask.Completed) {
            newLine = newLine + "1" + divider;
        } else {
            newLine = newLine + "0" + divider;
        }
        newLine = newLine + targetTask.Description;

        if (initial.equals("D") || initial.equals("E"))
        newLine = newLine + divider + targetTask.getAdditionalInfo();
        return newLine;
    }

    public static void RunDuke() {
        try {
            File StorageFile = OpenStorageFile();
            ReadFileToArray(StorageFile);
        } catch (DukeException e) {
            e.printErrMsg();
        }
        Ui.StartDuke();
        Ui.Greet();
    }

    public static void main(String[] args) throws IOException {
        RunDuke();
        ExtendTaskList();
        try {
            writeListToFile(OpenStorageFile());
        } catch (DukeException e) {
            System.out.println("Failed to write to file");
        }
    }
}
