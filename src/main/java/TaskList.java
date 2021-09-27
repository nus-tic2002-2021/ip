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

    public String getItem(int index) {
        Task task = list.get(index);
        return task.getTask();
    }

    public int getNumOfItem() {
        return list.size();
    }
}
