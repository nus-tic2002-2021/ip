package src.java.ui;

import src.java.task.TaskList;
import src.java.task.TaskType;

import java.io.*;

public class Message implements Ui {

    // Starting Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Override
    public void msgGreet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?");
        System.out.println("_________________________________");
    }

    // Assignment Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgAssignTask(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    public void msgAssignTaskDeadline(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateString = myList.getTaskDeadlineDateString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println(
                "      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(by: " + dateString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    public void msgAssignTaskEvent(TaskList myList, int taskNumber) {

        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateTimeString = myList.getTaskEventDateTimeString(taskNumber);

        System.out.println("    Got it. I've added this task: ");
        System.out.println("      [" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(at: "
                + dateTimeString + ")");
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    public void msgEcho(String s) {
        System.out.println("    Task Added: " + s);
        System.out.println("_________________________________");
    }

    // FileAccess Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgSave() {
        System.out.println("    Progress Saved!");
        System.out.println("_________________________________");
    }

    public void msgSLoad() {
        System.out.println("    Progress loaded!");
        System.out.println("_________________________________");
    }

    // Other Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgMarkDone(TaskList myList, int taskNumber) {
        System.out.println("    Naisuuuu! This task is marked as done: ");
        msgBlankBeforeTaskDetail();
        msgTaskDetail(myList, taskNumber);
        System.out.println("_________________________________");
    }

    public void msgList(TaskList myList) {
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            System.out.print("    " + Integer.toString(i + 1) + ".");
            msgTaskDetail(myList, i);
        }
        System.out.println("_________________________________");
    }

    public void msgBlankBeforeTaskDetail() {
        System.out.print("      ");
    }

    public void msgTaskDetail(TaskList myList, int taskNumber) {
        TaskType taskType = myList.getTaskType(taskNumber);
        String taskTypeInString = TaskType.taskTypeToString(taskType);

        boolean isDone = myList.getTaskDoneStatus(taskNumber);
        String isDoneInString = (isDone ? "X" : " ");

        String taskDetail = myList.getTaskDetail(taskNumber);

        String dateTimeString = "";

        switch (taskType) {
            case TODOS:
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail);
                break;
            case DEADLINE:
                dateTimeString = myList.getTaskDeadlineDateString(taskNumber);
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(by: "
                        + dateTimeString + ")");
                break;
            case EVENT:
                dateTimeString = myList.getTaskEventDateTimeString(taskNumber);
                System.out.println("[" + taskTypeInString + "][" + isDoneInString + "] " + taskDetail + "(at: "
                        + dateTimeString + ")");
                break;
        }
    }

    // Error Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgError(Exception e) {
        System.out.println("Error Occurs: " + e);
        System.out.println("_________________________________");
    }

    public void msgInvalidInput() {
        System.out.println("    Sorry :(   Invalid Input. Try Again ~ ");
        System.out.println("_________________________________");
    }

    public void msgInvalidInputMissingDescription() {
        System.out.println("    ☹  OOPS!!! The description cannot be empty.");
        System.out.println("_________________________________");
    }

    public void msgInvalidInputMissingDay() {
        System.out.println("    ☹  OOPS!!! The day cannot be empty.");
        System.out.println("_________________________________");
    }

    public void msgInvalidInputMissingTime() {
        System.out.println("    ☹  OOPS!!! The time cannot be empty.");
        System.out.println("_________________________________");
    }

    public void msgRemoveItem(TaskList myList, int taskNumber) {

        System.out.println("    Noted. I've removed this task:");
        msgBlankBeforeTaskDetail();
        msgTaskDetail(myList, taskNumber);
        System.out.println("    Now you have " + myList.getNumOfItem() + " tasks in the list.");
        System.out.println("_________________________________");
    }

    public void msgWrongTaskNumber() {
        System.out.println("    ☹  OOPS!!! The task number is invalid.");
        System.out.println("_________________________________");
    }

    // Ending Messages <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void msgBye() throws IOException {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_________________________________");
        msgBuddahProtectMe();
    }

    public void msgBuddahProtectMe() throws IOException {
        String everything = "";

        // ... \Duke\src\main\resources\buddha.txt
        // File.separator == " \ "

        String pathRoot = System.getProperty("user.dir");
        // pathRoot = D:\My Files\School Documents\Repository\Duke

        String pathRssFolder = "src" + File.separator + "resources";
        // pathRssFolder = src\main\resources

        String pathFileName = "buddha.txt";
        // pathFileName = buddha.txt

        String filePath = pathRoot + File.separator + pathRssFolder + File.separator + pathFileName;
        // filePath = D:\My Files\School
        // Documents\Repository\Duke\src\main\resources\buddha.txt

        try {
            FileReader fr = new FileReader(filePath);
            // FileReader fr = new FileReader("\\My Files\\School
            // Documents\\Repository\\Duke\\src\\main\\resources\\buddha.txt");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n >>> Buddah Protection is not in forced <<<\n");
            System.out.println(e);
        } finally {
            System.out.println(everything);
        }
    }
}
