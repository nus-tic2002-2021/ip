package com.lockarhythm.responders.echo;

import static org.junit.Assert.*;

import com.lockarhythm.responders.Response;
import org.junit.Test;

public class TestEchoResponder {
  @Test
  public void testEchosBackText() throws Exception {
    EchoResponder responder = new EchoResponder();
    Response res = responder.respondTo("hello");

    assertEquals("hello", res.getText());
    assertFalse(res.shouldExit());
  }
}
