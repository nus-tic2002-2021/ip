package com.lockarhythm.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTaskList {
  @Test
  public void testAddTask() throws Exception {
    TaskList list = new TaskList();

    list.add("learn useful libraries in java");
    list.add("learn bazel");
    assertEquals("has correct number of items", 2, list.size());

    Task task = list.markAsDone(1);
    assertEquals("can mark tasks as done", "learn bazel", task.getDescription());
    assertTrue("task is set to done", task.isDone());

    task = list.markAsDone(1);
    assertTrue("it's an idempotent operation", task.isDone());
  }
}
