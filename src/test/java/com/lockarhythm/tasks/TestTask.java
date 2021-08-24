package com.lockarhythm.tasks;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTask {
  @Test
  public void testToggleDone() throws Exception {
    Task task = new Task("learn java well");

    task.toggleDone();
    assertTrue("task should be done", task.isDone());
  }
}
