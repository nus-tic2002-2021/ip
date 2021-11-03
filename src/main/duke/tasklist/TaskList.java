package duke.tasklist;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask (Task t) {
        taskList.add(t);
    }

    public void removeTask (int i)  {
        taskList.remove(i);
    }

    public void reset() {
        taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }



}
