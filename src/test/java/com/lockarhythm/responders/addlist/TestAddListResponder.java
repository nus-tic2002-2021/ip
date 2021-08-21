package com.lockarhythm.responders.addlist;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lockarhythm.responders.Response;
import com.lockarhythm.tasks.TaskList;

public class TestAddListResponder {
    @Test
    public void testAddsItemsAndLists() throws Exception {
        AddListResponder responder = new AddListResponder(new TaskList());

        Response res = responder.respondTo("read book");
        assertEquals("added: read book", res.getText());

        res = responder.respondTo("return book");
        assertEquals("added: return book", res.getText());

        res = responder.respondTo("list");
        assertTrue(res.getText().contains("1.[ ] read book\n2.[ ] return book\n"));

    }
}
