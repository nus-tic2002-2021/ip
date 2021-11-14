package com.jc.duke.Ui;

/**
 * Handler for all UI related function such as printing messages and exceptions
 */

public class Ui {

    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("Type 'help' to view all commands");
    }

    public static void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0) ;
    }

    public static void showHelp() {
        System.out.println("The following commands are available:\n" +
                "    todo <description> /<condition> <date> : <date> must be in yyyy/MM/dd HHmm format\n" +
                "    deadline <description> /<condition> <date> : <date> must be in yyyy/MM/dd HHmm format\n" +
                "    event <description> /<condition> <date> : <date> must be in yyyy/MM/dd HHmm format\n" +
                "    done <index> : Mark <index> task as done\n" +
                "    delete <index> : Delete <index> task\n" +
                "    bye : Terminate Program\n" +
                "    list : List all task\n" +
                "    find <description> : List all task with <description>\n" +
                "    save : Save all current tasks into /data/save.txt \n" +
                "    load : Load from /data/save.txt\n" +
                "    archive : Archive all current tasks\n" +
                "    archive show : Display all archived files\n");
    }

    public static void showException() {
        System.out.println("unexpected command");
    }

    public static void showException(String e) {
        System.out.println("unexpected error: " + e);
    }
}
