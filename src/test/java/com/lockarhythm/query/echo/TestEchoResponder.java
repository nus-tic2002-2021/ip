package com.lockarhythm.query.echo;

import static org.junit.Assert.*;

import com.lockarhythm.query.Result;
import org.junit.Test;

public class TestEchoResponder {
  @Test
  public void testEchosBackText() throws Exception {
    EchoResponder responder = new EchoResponder();
    Result res = responder.interpret("hello");

    assertEquals("hello", res.getText());
    assertFalse(res.shouldExit());
  }
}
