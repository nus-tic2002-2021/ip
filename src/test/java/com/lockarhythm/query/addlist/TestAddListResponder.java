package com.lockarhythm.query.addlist;

import static org.junit.Assert.*;

import com.lockarhythm.query.Result;
import com.lockarhythm.tasks.TaskList;
import org.junit.Test;

public class TestAddListResponder {
  @Test
  public void testAddsItemsAndLists() throws Exception {
    AddListResponder responder = new AddListResponder(new TaskList());

    Result res = responder.interpret("read book");
    assertEquals("added: read book", res.getText());

    res = responder.interpret("return book");
    assertEquals("added: return book", res.getText());

    res = responder.interpret("list");
    assertTrue(res.getText().contains("1.[ ] read book\n2.[ ] return book\n"));
  }
}
