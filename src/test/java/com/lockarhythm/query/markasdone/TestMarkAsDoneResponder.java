package com.lockarhythm.query.markasdone;

import static org.junit.Assert.*;

import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;
import org.junit.Test;

public class TestMarkAsDoneResponder {
  @Test
  public void testMarkAsDoneHappyPath() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder responder = new MarkAsDoneResponder(list);

    list.addTodoTask("read book");
    list.addTodoTask("return book");

    Result res = responder.interpret("done 1");
    assertTrue(res.getText().contains("Nice! I've marked this task as done"));
    assertTrue(res.getText().contains("read book"));

    assertTrue(list.toString().contains("1.[T][X] read book\n2.[T][ ] return book\n"));
  }

  @Test
  public void testMarkAsDoneOnNonExistentItem() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder responder = new MarkAsDoneResponder(list);

    list.addTodoTask("read book");
    list.addTodoTask("return book");

    Result res = responder.interpret("done 10000");
    assertTrue(res.getText().contains("Item 10000 is not on the list. I cannot mark it as done!"));

    assertTrue(list.toString().contains("1.[T][ ] read book\n2.[T][ ] return book\n"));
  }

  @Test
  public void testMarkAsDoneParseCommand() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder responder = new MarkAsDoneResponder(list);

    list.addTodoTask("read book");

    Result res = responder.interpret("all done 10000");
    assertNull(res);
  }
}
