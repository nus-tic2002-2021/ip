package com.lockarhythm.responders.markasdone;

import static org.junit.Assert.*;

import com.lockarhythm.responders.Response;
import com.lockarhythm.responders.addlist.AddListResponder;
import com.lockarhythm.tasks.TaskList;
import org.junit.Test;

public class TestMarkAsDoneResponder {
  @Test
  public void testMarkAsDoneHappyPath() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder mdresponder = new MarkAsDoneResponder(list);
    AddListResponder alresponder = new AddListResponder(list);

    Response res = alresponder.respondTo("read book");
    assertEquals("added: read book", res.getText());

    res = alresponder.respondTo("return book");
    assertEquals("added: return book", res.getText());

    res = mdresponder.respondTo("done 1");
    assertTrue(res.getText().contains("Nice! I've marked this task as done"));
    assertTrue(res.getText().contains("read book"));

    res = alresponder.respondTo("list");
    assertTrue(res.getText().contains("1.[X] read book\n2.[ ] return book\n"));
  }

  @Test
  public void testMarkAsDoneOnNonExistentItem() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder mdresponder = new MarkAsDoneResponder(list);
    AddListResponder alresponder = new AddListResponder(list);

    Response res = alresponder.respondTo("read book");
    assertEquals("added: read book", res.getText());

    res = alresponder.respondTo("return book");
    assertEquals("added: return book", res.getText());

    res = mdresponder.respondTo("done 10000");
    assertTrue(res.getText().contains("Item 10000 is not on the list. I cannot mark it as done!"));

    res = alresponder.respondTo("list");
    assertTrue(res.getText().contains("1.[ ] read book\n2.[ ] return book\n"));
  }

  @Test
  public void testMarkAsDoneParseCommand() throws Exception {
    TaskList list = new TaskList();
    MarkAsDoneResponder mdresponder = new MarkAsDoneResponder(list);
    AddListResponder alresponder = new AddListResponder(list);

    Response res = alresponder.respondTo("read book");
    assertEquals("added: read book", res.getText());

    res = mdresponder.respondTo("all done 10000");
    assertNull(res);
  }
}
