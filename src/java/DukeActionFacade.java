package src.java;

import src.java.action.DukeAction;
import src.java.task.TaskList;

import java.util.Scanner;
import java.io.*;

public abstract class DukeActionFacade {

    public static void StartDuke() {
        Message.msgGreet();
    }

    public static void RunDuke(){
        boolean runTask = true;
        String line;
        Scanner in = new Scanner(System.in);
        TaskList myList = new TaskList();
        while (runTask) {
            line = in.nextLine();
            runTask = DukeAction.ReadUserCommand(myList,line);
        }
        in.close();
    }

    public static void EndDuke() {
        try {
            Message.msgBye();
        } catch (IOException error) {
            Message.msgError(error);
        }
    }
}
