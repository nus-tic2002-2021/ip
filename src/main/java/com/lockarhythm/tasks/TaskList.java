package com.lockarhythm.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class TaskList {
  private ArrayList<Task> list;

  public TaskList() {
    this.list = new ArrayList<Task>();
  }

  public TaskList(ArrayList<Task> list) {
    this.list = list;
  }

  public int size() {
    return list.size();
  }

  public TodoTask addTodoTask(String description) {
    TodoTask task = new TodoTask(description);
    list.add(task);
    return task;
  }

  public DeadlineTask addDeadlineTask(String description, String by) {
    DeadlineTask task = new DeadlineTask(description, by);
    list.add(task);
    return task;
  }

  public EventTask addEventTask(String description, String at) {
    EventTask task = new EventTask(description, at);
    list.add(task);
    return task;
  }

  public Task markAsDone(int index) {
    Task t = list.get(index);
    t.setDone(true);
    return t;
  }

  public Task deleteTask(int index) {
    return list.remove(index);
  }

  /**
   * Returns a copy of TaskList sorted by the task date.
   */
  public TaskList sortByTaskDate(boolean isAscending) {
    ArrayList<Task> copy = new ArrayList<Task>(list);
    Collections.sort(copy, (a, b) -> {
      if (isAscending) {
        return TaskDate.compare(a.getTaskDate(), b.getTaskDate());
      }
      return TaskDate.compare(b.getTaskDate(), a.getTaskDate());
    });
    return new TaskList(copy);
  }

  /**
   * Returns a copy of TaskList sorted by the "done" field.
   */
  public TaskList sortByDone(boolean isAscending) {
    ArrayList<Task> copy = new ArrayList<Task>(list);
    Collections.sort(copy, (a, b) -> {
      if (isAscending) {
        return Boolean.compare(b.isDone(), a.isDone());
      }
      return Boolean.compare(a.isDone(), b.isDone());
    });
    return new TaskList(copy);
  }

  /**
   * Returns a new TaskList that matches the given "query" string.
   */
  public TaskList find(String query) {
    ArrayList<Task> filtered = new ArrayList<Task>();
    for (Task task : list) {
      if (task.getDescription().contains(query)) {
        filtered.add(task);
      }
    }
    return new TaskList(filtered);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    int i = 1;
    for (i = 0; i < list.size(); i++) {
      s.append(i + 1);
      s.append(".");
      s.append(list.get(i));
      s.append("\n");
    }
    return s.toString();
  }
}
