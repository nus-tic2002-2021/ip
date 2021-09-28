import java.util.ArrayList;

import java.util.*;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public void addItem(String s) {
        list.add(new Task(s));
    }

    public void removeItem(int index) {
        list.remove(index);
    }

    // Getter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public Task getTask(int index) {
        return list.get(index);
    }

    public String getTaskDetail(int index) {
        Task task = list.get(index);
        return task.getTaskDetail();
    }

    public boolean getTaskDoneStatus(int index) {
        Task task = list.get(index);
        return task.getDoneStatus();
    }

    public int getNumOfItem() {
        return list.size();
    }

    // Setter <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public void setTaskDone(int index) {
        Task task = list.get(index);
        task.setTaskCompleted();
    }
}
