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
                } else if (line.contains("done")) {
                    // *** assume user key in done properly, tbc edit next time ***
                    // System.out.println(line.substring(5));

                    if (line.substring(0, 4).equals("done")) {
                        Integer taskNumber = Integer.parseInt(line.substring(5)) - 1;
                        MarkTaskDone(myList, taskNumber);
                        Message.msgMarkDone(myList, taskNumber);
                    } else {
                        Message.msgInvalidInput();
                    }
                } else if (line.contains("todo")) {
                    if (line.substring(0, 4).equals("todo") && line.length() >= 6 && line.contains(" ")) {
                        myList.addItemToDos(line.substring(5));
                        Message.msgAssignTask(myList, myList.getNumOfItem() - 1);
                    } else {
                        Message.msgInvalidInput();
                    }
                } else if (line.contains("deadline")) {
                    if (line.substring(0, 8).equals("deadline") && line.contains("/by")) {

                        String taskDetail = line.substring(9, line.indexOf("/"));
                        String dayWithBy = line.substring(line.indexOf("/") + 1, line.length() - 1);
                        String day = dayWithBy.substring(3);

                        myList.addItemDeadline(taskDetail, day); // temporary for Duke Level 4
                        Message.msgAssignTaskDeadline(myList, myList.getNumOfItem() - 1);
                    } else {
                        Message.msgInvalidInput();
                    }
                } else if (line.contains("event")) {
                    if (line.substring(0, 5).equals("event") && line.contains("/at")) {
                        String test = line.substring(100);
                        String taskDetail = line.substring(6, line.indexOf("/"));
                        String after_at = line.substring(line.indexOf("/") + 4);
                        String day = after_at.substring(0, after_at.indexOf(" "));
                        String time = line.substring(line.indexOf("/") + 8);

                        myList.addItemEvent(taskDetail, after_at); // temporary for Duke Level 4
                        Message.msgAssignTaskEvent(myList, myList.getNumOfItem() - 1);

                    } else {
                        Message.msgInvalidInput();
                    }
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

    public static void MarkTaskDone(TaskList myList, int taskNumber) {
        myList.setTaskDone(taskNumber);
    }

    public static void EndDuke() {
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }
}
