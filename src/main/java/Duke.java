import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);

    public static void printTaskList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskList.DukeList.size(); i++) {
            System.out.println((i + 1) + "." + TaskList.DukeList.get(i).getTaskInfo());
        }
    }

    public static void markTaskAtIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(4).trim()) - 1;
        if (index < TaskList.DukeList.size() && index > -1) {
            MarkTask(TaskList.DukeList.get(index));
            System.out.println("Nice! I've marked this task as done:\n  "
                    + TaskList.DukeList.get(index).getTaskInfo());
        } else {
            throw new DukeException("☹ OOPS!!! " +
                    "The index number of the task to be done is invalid!");
        }
    }

    public static void MarkTask(Task inputTask) {
        inputTask.markCompleted();
    }

    public static void ExtendTaskList() {
        while (true) {
            String input = in.nextLine();
            System.out.println(Ui.line);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Ui.line);
                break;
            } else {
                Parser.parseInput(input);
            }
            System.out.println(Ui.line + "\n");
        }
    }

    public static void AddTodo(String input) {
        try {
            if (Parser.CheckValidTodo(input)) {
                String newTask = input.substring(4).trim();
                Todo newTodo = new Todo(newTask);
                TaskList.addTaskToList(newTodo);
                Ui.PrintTaskAdded(newTodo);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddDeadline(String input) {
        try {
            if (Parser.CheckValidDeadline(input)) {
                String[] parts = input.substring(8).split("/by");
                Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
                TaskList.addTaskToList(newDeadline);
                Ui.PrintTaskAdded(newDeadline);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void AddEvent(String input) {
        try {
            if (Parser.CheckValidEvent(input)) {
                String[] parts = input.substring(5).split("/at");
                Event newEvent = new Event(parts[0].trim(), parts[1].trim());
                TaskList.addTaskToList(newEvent);
                Ui.PrintTaskAdded(newEvent);
                Ui.PrintTaskCount();
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void MarkDone(String input) {
        try {
            if (Parser.CheckValidDone(input)) {
                markTaskAtIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void DeleteTask(String input) {
        try {
            if (Parser.CheckValidDelete(input)) {
                DeleteIndex(input);
            }
        } catch (DukeException e) {
            e.printErrMsg();
        }
    }

    public static void DeleteIndex(String input) throws DukeException {
        int index = Integer.parseInt(input.substring(6).trim()) - 1;
        if (index < TaskList.DukeList.size() && index > -1) {
            String DeletedInfo = TaskList.DukeList.get(index).getTaskInfo();
            TaskList.RemoveTask(index);
            System.out.println("Noted! I've removed this task:\n  " + DeletedInfo);
            Ui.PrintTaskCount();
        } else {
            throw new DukeException("☹ OOPS!!! The index number of the task to delete is invalid!");
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
            ArrayList<Task> list = TaskList.DukeList;
            for (int i = 0; i < list.size(); i++) {
                Task taskAtIndex = list.get(i);
                String typeCheck = taskAtIndex.getTaskType();
                String newLine = "";
                if (typeCheck.equals("T")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (typeCheck.equals("D")) {
                    newLine = buildStorageLine(taskAtIndex);
                } else if (typeCheck.equals("E")) {
                    newLine = buildStorageLine(taskAtIndex);
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
            File StorageFile = Storage.OpenStorageFile();
            Storage.ReadFileToArray(StorageFile);
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
            writeListToFile(Storage.OpenStorageFile());
        } catch (DukeException e) {
            System.out.println("Failed to write to file");
        }
    }
}
