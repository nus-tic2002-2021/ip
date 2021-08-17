package com.lockarhythm.cmdline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestDuke {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void testNoArgument() throws Exception {
        System.setOut(new PrintStream(out));
        Duke.main(null);
        assertEquals(("Hello from\n"+Duke.logo).trim(), out.toString().trim());
        System.setOut(standardOut);
    }

}
