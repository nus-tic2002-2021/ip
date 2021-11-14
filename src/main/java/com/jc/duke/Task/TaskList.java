package com.jc.duke.Task;

import com.jc.duke.Exception.DukeTaskNotFoundException;
import com.jc.duke.Storage.Storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class handles all the Duke task during runtime. It also supports all operations related to Duke Task (e.g. add new task, remove task, update task, save and retrieve tasklist)
 */

public class TaskList extends Task{

    ArrayList<Task> taskList = new ArrayList<Task>();

    static int counter;
    String delimiter = "|";

    public TaskList() {
        counter = 0;
    }

    public void saveTaskList() {
        String temp = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        for(int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            temp += tempTask.taskType + delimiter + tempTask.isDone + delimiter + tempTask.description + delimiter + tempTask.dateTime.getCondition() + delimiter + simpleDateFormat.format(tempTask.getDate()) + "\r\n";
        }

        new Storage().writeSaveFile(temp);
    }

    public void archiveTaskList() {
        String temp = "";

        for(int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            temp += tempTask.taskType + delimiter + tempTask.isDone + delimiter + tempTask.description + delimiter + tempTask.dateTime.getCondition() + delimiter + tempTask.dateTime.getTime() + "\r\n";
        }

        new Storage().writeArchiveFile(temp);

        taskList.clear();
    }

    public void getArchiveList() {
        System.out.println("Here are all the archived task list:");

        ArrayList<String> archiveFiles = new Storage().getArchiveFile();
        for(int i = 0; i < archiveFiles.size(); i++) {
            System.out.println(archiveFiles.get(i).toString() + "\r\n");
        }
    }
    public void loadTaskList() {
        List<String> stringList = new Storage().readSaveFile();

        for(String task:stringList) {
            String[] taskSplit = task.split("\\|");
            boolean isDone = false;

            if(taskSplit[1].equals("true")) {
                isDone = true;
            }

            Type type = null;
            switch(taskSplit[0]) {
                case "T":
                    type = Type.todo;
                    break;
                case "D":
                    type = Type.deadline;
                    break;
                case "E":
                    type = Type.event;
                    break;
                default:
                    System.out.println("invalid task command");
            }

            DateTime dateTime = new DateTime(taskSplit[3],taskSplit[4]);
            addTask(taskSplit[2], type, dateTime, isDone);

        }
//        String[] stringArray = stringList.split("\r\n");


    }

    public void findTask(String keyword) {

        System.out.println("Here are the matching tasks in your list:");

        for(Task t:taskList) {
            if(t.description.contains(keyword)) {
                System.out.println(getTaskDetails(t));
            }
        }
    }
    public void addTask(String description, Type type, DateTime dateTime) {
        taskList.add(new Task(description, type, dateTime));
        System.out.println("Got it. I've added this task: ");
        System.out.println(getTaskDetails(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() +" tasks in the list.");
    }

    public void addTask(String description, Type type, DateTime dateTime, boolean isDone) {
        taskList.add(new Task(description, type, dateTime));

        if(isDone){
            try {
                taskList.get(taskList.size()-1).setDone();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Task Loaded. I've added this task: ");
        System.out.println(getTaskDetails(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() +" tasks in the list.");
    }

    public void setDone(int index) throws DukeTaskNotFoundException {
        try{
            taskList.get(index-1).setDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(getTaskDetails(index-1));
        } catch (Exception ex) {
            throw new DukeTaskNotFoundException("Invalid task number");
        }

    }

    public void deleteTask(int index) throws DukeTaskNotFoundException {
        try{
            taskList.remove(index - 1);

            System.out.println("Noted. I've removed this task: ");
            System.out.println(getTaskDetails(index - 1));
        } catch (Exception ex) {
            throw new DukeTaskNotFoundException("Invalid task number");
        }
    }

    public void printTaskList() {

        if(taskList.size() == 0) {
            System.out.println("There are nothing in your list yet");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + getTaskDetails(i));
            }
        }

    }

    public String getTaskDetails(int index) {
        Task temp = taskList.get(index);
        return "[" + temp.getTaskType() + "][" + temp.getStatusIcon() + "] " + temp.getDescription() + " " + temp.getDateTime();
    }

    public String getTaskDetails(Task task) {
        return "[" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getDescription() + " " + task.getDateTime();
    }

}