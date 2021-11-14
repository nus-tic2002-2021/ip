package app;

import task.Task;
import task.TaskCommands;

import java.util.ArrayList;
import java.util.Scanner;

import static app.Storage.loadList;

public class UI {
    private Parser parser;
    private ArrayList<Task> List = new ArrayList<>();

    public void run(){
        dukeLogo();
        loadList();
        String line;
        Scanner in = new Scanner(System.in);

        while(true) {
            line = in.nextLine();
            newLine();
            String command = line.split(" ")[0];
            String commandUpper = command.toUpperCase();
            try {
                Parser.validInput(commandUpper);
            } catch (NullPointerException e){
                System.out.println("Please enter a valid input");
            }
            try {
                Parser.parseCommand(line);
            } catch (DukeException e) {
                System.out.println("Please enter a valid input");
                instructions();
            } catch (NullPointerException e){
                System.out.println("Please enter a valid input");
                instructions();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a valid input");
                instructions();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid input");
                instructions();
            }
            newLine();
        }
    }

        private static void dukeLogo () {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            //Hello command
                System.out.println("Hello, I'm Duke\n" + "What tasks can I serve you?");
                newLine();
            }

            public static void endDuke () {
                System.out.println("\tBye! Thanks for visiting The Duke!");
                Storage.saveList();
                System.exit(0);
            }
            public static void newLine () {
                System.out.println("________________________________________________");
            }

            public static void instructions () {
                System.out.println("DUKE helps you organise your tasks efficiently, please enter as follows:" +
                        "\n\tevent [description] /at [time]\n\t" +
                        "todo [description]\n\t" +
                        "deadline [description] /by [time]\n\t" +
                        "done [number] - to mark task as completed\n\t" +
                        "list - to show all tasks\n\t" +
                        "delete [number] - to delete a task\n\t" +
                        "save - to save task list\n\t" +
                        "bye - to end DUKE");
                newLine();
            }
        }