package com.lockarhythm.responders.addlist;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lockarhythm.responders.Response;

public class TestAddListResponder {
    @Test
    public void testAddsItemsAndLists() throws Exception {
        AddListResponder responder = new AddListResponder();

        Response res = responder.respondTo("read book");
        assertEquals("added: read book", res.getText());

        res = responder.respondTo("return book");
        assertEquals("added: return book", res.getText());

        res = responder.respondTo("list");
        assertTrue(res.getText().contains("1.[ ] read book\n2.[ ] return book\n"));

    }

    @Test
    public void testMarkAsDoneHappyPath() throws Exception {
        AddListResponder responder = new AddListResponder();

        Response res = responder.respondTo("read book");
        assertEquals("added: read book", res.getText());

        res = responder.respondTo("return book");
        assertEquals("added: return book", res.getText());

        res = responder.respondTo("done 1");
        assertTrue(res.getText().contains("Nice! I've marked this task as done"));
        assertTrue(res.getText().contains("read book"));

        res = responder.respondTo("list");
        assertTrue(res.getText().contains("1.[X] read book\n2.[ ] return book\n"));

    }

    @Test
    public void testMarkAsDoneOnNonExistentItem() throws Exception {
        AddListResponder responder = new AddListResponder();

        Response res = responder.respondTo("read book");
        assertEquals("added: read book", res.getText());

        res = responder.respondTo("return book");
        assertEquals("added: return book", res.getText());

        res = responder.respondTo("done 10000");
        assertTrue(res.getText().contains("Item 10000 is not on the list. I cannot mark it as done!"));

        res = responder.respondTo("list");
        assertTrue(res.getText().contains("1.[ ] read book\n2.[ ] return book\n"));

    }
}
