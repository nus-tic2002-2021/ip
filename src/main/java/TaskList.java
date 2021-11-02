import Exception.DukeTaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends Task{

    ArrayList<Task> taskList = new ArrayList<Task>();

    static int counter;
    String delimiter = "|";

    public TaskList() {
        counter = 0;
    }

    public void saveTaskList() {
        String temp = "";

        for(int i = 0; i < taskList.size(); i++) {
            Task tempTask = taskList.get(i);
            temp += tempTask.taskType + delimiter + tempTask.isDone + delimiter + tempTask.description + delimiter + tempTask.dateTime.getCondition() + delimiter + tempTask.dateTime.getTime() + "\r\n";
        }

        new DukeFile().writeSaveFile(temp);
    }

    public void loadTaskList() {
        List<String> stringList = new DukeFile().readSaveFile();

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
                setDone(taskList.size());
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
            System.out.println(getTaskDetails(index - 1));
        } catch (Exception ex) {
            throw new DukeTaskNotFoundException("Invalid task number");
        }

    }

    public void deleteTask(int index) throws DukeTaskNotFoundException {
        try{
            System.out.println("Noted. I've removed this task: ");
            System.out.println(getTaskDetails(index - 1));
            taskList.remove(index - 1);
        } catch (Exception ex) {
            throw new DukeTaskNotFoundException("Invalid task number");
        }
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + getTaskDetails(i));
        }
    }

    public String getTaskDetails(int index) {
        Task temp = taskList.get(index);
        return "[" + temp.getTaskType() + "][" + temp.getStatusIcon() + "] " + temp.getDescription() + " " + temp.getDateTime();
    }
}