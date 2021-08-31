package com.lockarhythm.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTodoTask {
  @Test
  public void testToggleDone() throws Exception {
    TodoTask task = new TodoTask("learn java well");

    task.toggleDone();
    assertTrue("task should be done", task.isDone());
  }
}
