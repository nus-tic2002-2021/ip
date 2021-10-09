package duke;

import duke.command.Command;
import duke.exception.InvalidException;
import duke.exception.MissReqException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private boolean isExit;

    public Duke(){isExit = false;}

    public Duke(String path){
        ui = new Ui();
        storage = new Storage(path);
        isExit = false;
        try{
            tasks = storage.load();
        }catch (FileNotFoundException e){
            tasks = new TaskList();
            ui.showLoadException();
        }

    }



    public void dukeGreet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.showLine();
        System.out.println("     Hello! I'm duke.Duke");
        System.out.println("     What can I do for you?\n");
        ui.showLine();
    }


    public void dukeEcho(){
        while(!isExit){
            String req = ui.readReq();
            try{
                Command command = Parser.parse(req);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            }catch (IOException e){
                ui.showStoreException();
            }catch (Exception e){
                ui.showException(e, req);
            }
        }

    }

    public void run(){
        dukeGreet();
        dukeEcho();
    }
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
