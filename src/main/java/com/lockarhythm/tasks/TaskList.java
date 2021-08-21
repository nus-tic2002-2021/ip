package com.lockarhythm.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public int size() {
        return list.size();
    }

    public void add(String description) {
        list.add(new Task(description));
    }

    public Task markAsDone(int index) {
        Task t = list.get(index);
        t.toggleDone();
        return t;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        int i = 1;
        for (i = 0; i < list.size(); i++) {
            s.append(i+1);
            s.append(".");
            s.append(list.get(i));
            s.append("\n");
        }
        return s.toString();
    }
}
