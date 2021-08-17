package com.lockarhythm.cmdline;

import java.io.PrintStream;

public class Duke {
    static PrintStream out = System.out;
    static String logo =   " ____        _        \n"
                         + "|  _ \\ _   _| | _____ \n"
                         + "| | | | | | | |/ / _ \\\n"
                         + "| |_| | |_| |   <  __/\n"
                         + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        out.println("Hello from\n" + logo);
    }
}
