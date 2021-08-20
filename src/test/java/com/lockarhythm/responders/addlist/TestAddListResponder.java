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
        assertEquals("1. read book\n2. return book\n", res.getText());

    }

    @Test
    public void testEmptyList() throws Exception {
        AddListResponder responder = new AddListResponder();

        Response res = responder.respondTo("list");
        assertEquals("", res.getText());

    }
}
