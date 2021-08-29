package com.lockarhythm.application;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.Test;

public class TestTerminalDuke {
  private final PrintStream standardOut = System.out;
  private final InputStream standardIn = System.in;
  private final ByteArrayOutputStream out = new ByteArrayOutputStream();
  private final ByteArrayInputStream in =
      new ByteArrayInputStream("read book\nreturn book\nlist\nbye".getBytes());

  @Test
  public void testMeetsLevel2() throws Exception {
    System.setIn(in);
    System.setOut(new PrintStream(out));

    TerminalDuke.main(null);
    String output = out.toString().trim();
    assertFalse(output.isEmpty());

    assertTrue("it adds the 1st item", output.contains("added: read book"));
    assertTrue("it adds the 2nd item", output.contains("added: return book"));
    assertTrue("it lists the items", output.contains("1.[ ] read book"));
    assertTrue("it lists the items", output.contains("2.[ ] return book"));
    assertTrue("it exits", output.contains("Bye. Hope to see you again soon!"));

    System.setOut(standardOut);
    System.setIn(standardIn);
  }
}
