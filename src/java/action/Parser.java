package src.java.action;

import src.java.ui.Message;
import src.java.storage.FileAccess;
import src.java.task.TaskList;
import src.java.task.TaskType;
import src.java.ui.Ui;
import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private FileAccess fileAccess;
    private Ui ui;

    public Parser(FileAccess fileAccess){
        this.fileAccess = fileAccess;
        ui = new Message();
    }

    // Start Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void ShowGreetMessage(){
        ui.msgGreet();
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
                ui.msgInvalidInput();
            }
            return true;
        } catch (Exception e) {
            ui.msgInvalidInput();
            ui.msgError(e);
            return true;
        }
    }

    // End Duke <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void ShowByeMessage(){
        try {
            ui.msgBye();
        } catch (IOException error) {
            ui.msgError(error);
        }
    }

    // ReadUserCommand Support Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    private void ShowFullList(TaskList myList) {
        ui.msgList(myList);
    }

    private void MarkTaskDone(TaskList myList, String line) {
        int taskNumber = Integer.parseInt(line.substring(5));
        myList.setTaskDone(taskNumber - 1);
        ui.msgMarkDone(myList, taskNumber - 1);
    }

    private void AddTaskToDo(TaskList myList, String line) {
        if (line.length() <= 5) {
            ui.msgInvalidInputMissingDescription();
        } else {
            myList.addItemToDos(line.substring(5));
            ui.msgAssignTask(myList, myList.getNumOfItem() - 1);
        }
    }

    private void SaveTask(TaskList myList) {
        String listOfTaskString = ReadTaskConvertToString(myList);
        fileAccess.SaveProgressIntoFile(myList, listOfTaskString);
    }

    private void AddTaskEvent(TaskList myList, String line) {
        if (line.length() <= 6) {
            ui.msgInvalidInputMissingDescription();
        } else if (!line.contains("/at")) {
            ui.msgInvalidInputMissingDay();
        } else {

            try {
                String taskDetail = line.substring(6, line.indexOf("/"));
                String after_at = line.substring(line.indexOf("/") + 4);
                String day = after_at.substring(0, after_at.indexOf(" "));
                String time = line.substring(line.indexOf("/") + 8);

                myList.addItemEvent(taskDetail, after_at); // temporary for Duke Level 4
                ui.msgAssignTaskEvent(myList, myList.getNumOfItem() - 1);
            } catch (Exception e) {
                ui.msgInvalidInput();
            }
        }
    }

    private void RemoveTask(TaskList myList, String line) {
        try {
            int taskNumber = Integer.parseInt(line.substring(7));
            ui.msgRemoveItem(myList, taskNumber - 1);
            myList.removeItem(taskNumber - 1);
        } catch (Exception e) {
            ui.msgWrongTaskNumber();
        }
    }

    private void AddTaskDeadline(TaskList myList, String line) {
        if (line.length() <= 9) {
            ui.msgInvalidInputMissingDescription();
        } else if (!line.contains("/by")) {
            ui.msgInvalidInputMissingDay();
        } else {
            try {
                String taskDetail = line.substring(9, line.indexOf("/"));
                String dayWithBy = line.substring(line.indexOf("/") + 1, line.length());
                String day = dayWithBy.substring(3);

                myList.addItemDeadline(taskDetail, day); // temporary for Duke Level 4
                ui.msgAssignTaskDeadline(myList, myList.getNumOfItem() - 1);

            } catch (Exception e) {
                ui.msgInvalidInput();
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



