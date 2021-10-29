package src.java.action;

import src.java.DukeActionFacade;
import src.java.Message;
import src.java.fileAccess.FileAccess;
import src.java.task.TaskList;
import src.java.task.TaskType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeObject{

    FileAccess fileAccess;

    public DukeObject (FileAccess fileAccess){
        this.fileAccess = fileAccess;
    }

    // Start Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void ShowGreetMessage(){
        Message.msgGreet();
    }

    // Run Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void OnCreateDuke(){
        boolean isDukeRunning = true;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (isDukeRunning) {
            line = in.nextLine();
            isDukeRunning = ReadUserCommand(myList,line);
        }
        in.close();
    }

    public boolean ReadUserCommand(TaskList myList, String line) {
        try {
            // Guard Condition
            if (line.equals("bye")) {
                return false;
            }

            // for testing new function
            if (line.equals("tf")) {
                System.out.println("XXXXX Test Function XXXXX");
                fileAccess.DeleteProgressFile();
                System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXX");
            }

            if (line.equals("list")) {
                ShowFullList(myList);
            } else if (line.substring(0, 4).equals("done")) {
                MarkTaskDone(myList, line);
            } else if (line.substring(0, 4).equals("todo")) {
                AddTaskToDo(myList, line);
            } else if (line.substring(0, 4).equals("save")) {
                SaveTask(myList);
            } else if (line.substring(0, 4).equals("load")) {
                // loadTask(myList);
            } else if (line.substring(0, 5).equals("event")) {
                AddTaskEvent(myList, line);
            } else if (line.substring(0, 6).equals("delete")) {
                RemoveTask(myList, line);
            } else if (line.substring(0, 8).equals("deadline")) {
                AddTaskDeadline(myList, line);
            } else {
                Message.msgInvalidInput();
            }
            return true;
        } catch (Exception e) {
            Message.msgInvalidInput();
            Message.msgError(e);
            return true;
        }
    }

    // End Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void ShowByeMessage(){
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }

    // ReadUserCommand Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private void ShowFullList(TaskList myList) {
        Message.msgList(myList);
    }

    private void MarkTaskDone(TaskList myList, String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        myList.setTaskDone(taskNumber - 1);
        Message.msgMarkDone(myList, taskNumber - 1);
    }

    private void AddTaskToDo(TaskList myList, String line) {
        if (line.length() <= 5) {
            Message.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(line.substring(5));
            Message.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    private void SaveTask(TaskList myList) {
        String listOfTaskString = ReadTaskConvertToString(myList);
        fileAccess.SaveProgressIntoFile(myList, listOfTaskString);
    }

    private void AddTaskEvent(TaskList myList, String line) {
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

    private void RemoveTask(TaskList myList, String line) {
        try {
            int taskNumber = Integer.parseInt(line.substring(7));
            Message.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            Message.msgWrongTaskNumber();
        }
    }

    private void AddTaskDeadline(TaskList myList, String line) {
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

    // Other Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private String ReadTaskConvertToString(TaskList myList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myList.getNumOfItem(); i++) {
            String taskDetail = myList.getTaskDetail(i);
            boolean isDone = myList.getTaskDoneStatus(i);
            String isDoneString = (isDone ? "1" : "0");
            String taskType = TaskType.taskTypeToString(myList.getTaskType(i));

            sb.append(taskType).append("|");
            sb.append(isDoneString).append("|");
            sb.append(taskDetail).append("|\n");
        }
        return sb.toString();
    }
}



