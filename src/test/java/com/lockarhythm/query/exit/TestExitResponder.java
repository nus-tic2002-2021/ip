package com.lockarhythm.query.exit;

import static org.junit.Assert.*;

import com.lockarhythm.query.Result;
import org.junit.Test;

public class TestExitResponder {
  @Test
  public void testExitsOnKeyword() throws Exception {
    ExitResponder responder = new ExitResponder();
    Result res = responder.interpret("bye");

    assertTrue(res.shouldExit());
  }

  @Test
  public void testNullOnNonkeyword() throws Exception {
    ExitResponder responder = new ExitResponder();
    Result res = responder.interpret("hello");

    assertNull(res);
  }
}
