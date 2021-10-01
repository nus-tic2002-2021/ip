import java.util.Scanner;
import java.io.*;

public class Action {

    public static void StartDuke() {
        Message.msgGreet();
    }

    public static void RunDuke() {
        boolean bye = false;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (!bye) {
            line = in.nextLine();

            try {
                if (line.equals("list")) {
                    Message.msgList(myList);
                } else if (line.equals("bye")) {
                    bye = true;
                } else if (line.substring(0, 4).equals("done")) {
                    MarkTaskDone(myList, line);
                } else if (line.substring(0, 4).equals("todo")) {
                    AddTaskToDo(myList, line);
                } else if (line.substring(0, 5).equals("event")) {
                    AddTaskEvent(myList, line);
                } else if (line.substring(0, 6).equals("delete")) {
                    RemoveTask(myList, line);
                } else if (line.substring(0, 8).equals("deadline")) {
                    AddTaskDeadline(myList, line);
                } else {
                    Message.msgInvalidInput();
                }
            } catch (Exception e) {
                Message.msgInvalidInput();
                Message.msgError(e);
            }

        }
        in.close();

    }

    public static void MarkTaskDone(TaskList myList, String line) {
        Integer taskNumber = Integer.parseInt(line.substring(5));
        myList.setTaskDone(taskNumber - 1);
        Message.msgMarkDone(myList, taskNumber - 1);
    }

    public static void AddTaskToDo(TaskList myList, String line) {
        if (line.length() <= 5) {
            Message.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(line.substring(5));
            Message.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    public static void AddTaskEvent(TaskList myList, String line) {
        if (line.length() <= 6) {
            Message.msgInvalidInputMissingDescription();
        } else if (!line.contains("/at")) {
            Message.msgInvalidInputMissingDay();
        } else {

            try {
                String taskDetail = line.substring(6, line.indexOf("/"));
                String after_at = line.substring(line.indexOf("/") + 4);
                String day = after_at.substring(0, after_at.indexOf(" "));
                String time = line.substring(line.indexOf("/") + 8);

                myList.addItemEvent(taskDetail, after_at); // temporary for Duke Level 4
                Message.msgAssignTaskEvent(myList, myList.getNumOfItem() - 1);
            } catch (Exception e) {
                Message.msgInvalidInput();
            }
        }
    }

    public static void AddTaskDeadline(TaskList myList, String line) {
        if (line.length() <= 9) {
            Message.msgInvalidInputMissingDescription();
        } else if (!line.contains("/by")) {
            Message.msgInvalidInputMissingDay();
        } else {
            try {
                String taskDetail = line.substring(9, line.indexOf("/"));
                String dayWithBy = line.substring(line.indexOf("/") + 1, line.length());
                String day = dayWithBy.substring(3);

                myList.addItemDeadline(taskDetail, day); // temporary for Duke Level 4
                Message.msgAssignTaskDeadline(myList, myList.getNumOfItem() - 1);

            } catch (Exception e) {
                Message.msgInvalidInput();
            }
        }
    }

    public static void RemoveTask(TaskList myList, String line) {
        try {
            Integer taskNumber = Integer.parseInt(line.substring(7));
            Message.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            Message.msgWrongTaskNumber();
        }
    }

    public static void EndDuke() {
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }
}
