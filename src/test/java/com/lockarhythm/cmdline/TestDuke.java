package com.lockarhythm.cmdline;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDuke {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream(
        "list\nblah\nbye".getBytes()
    );

    @Test
    public void testMeetsLevel1() throws Exception {
        System.setIn(in);
        System.setOut(new PrintStream(out));

        Duke.main(null);
        String output = out.toString().trim();
        assertFalse(output.isEmpty());

        assertTrue("it greets the user", output.contains("Hello"));
        assertTrue("it echos the command back", output.contains("blah"));
        assertTrue("it exits", output.contains("Bye. Hope to see you again soon!"));

        System.setOut(standardOut);
        System.setIn(standardIn);
    }

}
