package com.tic2002.app;

import java.util.Scanner;

import static com.tic2002.app.Storage.loadList;

public class UI {
    private Parser parser;

    /** Main function of UI to run program
     * dukeLogo - Calling the logo
     * loadList - Calling the Storage function to load duke.txt
     * line - Creates a String to store user input
     * Scanner in - to scan and read user input
     * while(true) - while there is input from the user
     * newLine() - Calls a line break
     * command Split user line to the first word
     * commandUpper Convert user input String to uppercase
     * parser.validInput(commandUpper) passes command String to check boolean
     * catch(NullPointerException e) catches null in String
     * println tells user to input accurately
     * instructions() print instructions to use Duke
     * parser.parseCommand(line) passes command String to Parser to do command
     * catch(NullPointerException e) catches null in String
     * println tells user to input accurately
     * instructions() print instructions to use Duke
     * catch(ArrayIndexOutOfBoundsException e) catches array out of bounds in String
     * catch(IndexOutOfBoundsException e) catches index out of bounds in String
     */
    public void run(){
        dukeLogo();
        loadList();
        String line;
        Scanner in = new Scanner(System.in);
        parser = new Parser();

        while(true) {
            line = in.nextLine();
            newLine();
            String command = line.split(" ")[0];
            String commandUpper = command.toUpperCase();

            try {
                assert commandUpper != null : "Please enter valid input\n";
                parser.isValidInput(commandUpper);
            } catch (NullPointerException e){
                System.out.println("Please enter a valid input\n");
                instructions();
            }
            try {
                assert line != null: "Please enter a valid input\n";
                parser.parseCommand(line);
            } catch (DukeException e) {
                System.out.println("Please enter a valid input\n");
                instructions();
            } catch (NullPointerException e){
                System.out.println("Please enter a valid input\n");
                instructions();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a valid input\n");
                instructions();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a valid input\n");
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

    /**
     * to end program
     * Storage.saveList() - call Storage to save list to duke.txt
     * System.exit() - exit system safely
     */
    public static void endDuke () {
        System.out.println("\tBye! Thanks for visiting The Duke!");
        Storage.saveList();
        System.exit(0);
    }

    /**
     * newLine() - Prints a new line break
     */
    public static void newLine () {
                System.out.println("________________________________________________");
            }

    /**
     * Prints out instructions to use Duke
     */
    public static void instructions () {
        System.out.println("DUKE helps you organise your tasks efficiently, please enter as follows:" +
                "\n\tevent [description] /at [time]\n\t" +
                "todo [description]\n\t" +
                "deadline [description] /by yyyy-mm-dd, hh:mm\n\t" +
                "done [number] - to mark task as completed\n\t" +
                "list - to show all tasks\n\t" +
                "delete [number] - to delete a task\n\t" +
                "on yyyy-mm-dd - to check if tasks match date given\n\t" +
                "priority [index] [0(TO REMOVE) / 1(LOW) / 2(MED) / 3(HIGH)] - to set priority\n\t" +
                "find [word] - To search for a word\n\t" +
                "bye - to end DUKE");
    }
}