package Duke;

import Duke.ExceptionList.*;
import Duke.Storage.Storage;
import Duke.TaskList.*;
import Duke.Ui.Ui;

import java.util.Scanner;
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {

    private Storage storage;
    private TaskList tl;
    private Ui ui;

    public Duke(){
        run();
    }


    public void run() {
        ui = new Ui();
        ui.showStartMessage();
        ui.printBreak();
        storage = new Storage();
        try {
            tl = new TaskList(storage.load());
        }
         catch (DukeException | IOException e) {
            ui.showLoadingError();
            tl = new TaskList();
        }
        String line, desc, desc2, s1, s2;
        while (true) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            try {
                if (!line.isEmpty()) {
                    desc2 = "";
                    if (line.contains(" ")) {

                        String[] input = line.split(" ", 2);
                        desc = input[0];
                        desc2 = input[1];
                    } else {
                        desc = line;
                    }
                    if (line.equalsIgnoreCase("bye")) {
                        storage.saveTask(tl);
                        break;
                    }
                    if (line.equalsIgnoreCase("list")) {
                        ui.printBreak();
                        tl.printTaskList();
                        ui.printBreak();
                    } else if (desc.equalsIgnoreCase("done")) {
                        ui.printBreak();
                        if (desc2.isEmpty()){
                            throw new CommandNotFoundException("Sorry, I am unable to handle your request.");
                        }else if (Integer.parseInt(desc2) > tl.getSize() || Integer.parseInt(desc2) <= 0){
                            throw new CommandNotFoundException("Sorry, I am unable to handle your request.");
                        }
                        int x = Integer.parseInt(desc2);
                        tl.setDoneStatus(x);
                        ui.printBreak();
                    } else if (desc.equalsIgnoreCase("delete")) {
                        if (tl.getSize()==0){
                            throw new NoListFoundException("Sorry, I am unable to delete any task because no task is found.");
                        }
                        if (desc2.isEmpty()){
                            throw new CommandNotFoundException("Sorry, I am unable to handle your request.");
                        }else if (Integer.parseInt(desc2) > tl.getSize() || Integer.parseInt(desc2) <= 0){
                            throw new CommandNotFoundException("Sorry, I am unable to handle your request.");
                        }
                        ui.printBreak();
                        int x = Integer.parseInt(desc2);
                        System.out.println("I've deleted this task entered: ");
                        tl.deleteTask(x);
                        ui.printBreak();
                    } else if (desc.equalsIgnoreCase("deadline")) {
                        if (desc2.isEmpty()){
                            throw new deadlineNotFoundException("Sorry, there cannot be any empty deadline task.");
                        }
                        ui.printBreak();
                        tl.addTask(desc,desc2,false);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tl.getLastAddedTask());
                        ui.printTotalTask(tl.getSize());
                        ui.printBreak();
                    } else if (desc.equalsIgnoreCase("event")) {
                        if (desc2.isEmpty()){
                            throw new eventNotFoundException("Sorry, there cannot be any empty event task.");
                        }
                        ui.printBreak();
                        tl.addTask(desc,desc2,false);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tl.getLastAddedTask());
                        ui.printTotalTask(tl.getSize());
                        ui.printBreak();
                    } else if (desc.equalsIgnoreCase("todo")) {
                        if (desc2.isEmpty()){
                            throw new toDoNotFoundException("Sorry, there cannot be any empty todo task.");
                        }
                        ui.printBreak();
                        tl.addTask(desc,desc2,false);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(tl.getLastAddedTask());
                        ui.printTotalTask(tl.getSize());
                        ui.printBreak();
                    } else {
                        throw new CommandNotFoundException("Sorry, I am unable to handle your request.");
                    }
                }
            } catch (toDoNotFoundException | CommandNotFoundException | NoListFoundException | deadlineNotFoundException | eventNotFoundException e ) {
                continue;
            }
        }
        ui.printBreak();
        ui.printBye();
        ui.printBreak();
    }

    public static void main(String[] args) {
        new Duke();
    }
}
