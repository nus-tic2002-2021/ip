package com.lockarhythm.cmdline;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class TestDuke {

    @Test
    public void testNoArgument() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Duke.out = new PrintStream(out);
        Duke.main(null);
        assertEquals(("Hello from\n"+Duke.logo).trim(), new String(out.toByteArray(), StandardCharsets.UTF_8).trim());
    }

}
