package src.java;

import src.java.action.DukeObject;
import src.java.fileAccess.FileAccess;
import src.java.task.TaskList;

import java.util.Scanner;
import java.io.*;

public class DukeActionFacade {

    private FileAccess fileAccess;
    private DukeObject dukeObject;

    public DukeActionFacade(){
        fileAccess = new FileAccess();
        dukeObject = new DukeObject(fileAccess);
    }

    public void StartDuke() {
        dukeObject.ShowGreetMessage();
    }

    public void RunDuke(){
        dukeObject.OnCreateDuke();
    }

    public void EndDuke() {
        dukeObject.ShowByeMessage();
    }

    public FileAccess getFileAccess (){
        return fileAccess;
    }

    public DukeObject getDukeObject (){
        return dukeObject;
    }
}
