package com.lockarhythm.query.list;

import static org.junit.Assert.*;

import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;
import org.junit.Test;

public class TestListResponder {
  @Test
  public void testAddsItemsAndLists() throws Exception {
    TaskList list = new TaskList();
    ListResponder responder = new ListResponder(list);

    list.addTodoTask("read book");
    list.addTodoTask("return book");

    Result res = responder.interpret("list");
    assertTrue(res.getText().contains("1.[T][ ] read book\n2.[T][ ] return book\n"));
  }
}
