package com.lockarhythm.responders.exit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lockarhythm.responders.Response;

public class TestExitResponder {
    @Test
    public void testExitsOnKeyword() throws Exception {
        ExitResponder responder = new ExitResponder();
        Response res = responder.respondTo("bye");

        assertTrue(res.shouldExit());
    }

    @Test
    public void testNullOnNonkeyword() throws Exception {
        ExitResponder responder = new ExitResponder();
        Response res = responder.respondTo("hello");

        assertNull(res);
    }
}
