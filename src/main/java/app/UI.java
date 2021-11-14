package app;

import exceptions.InvalidUserInputException;
import java.util.Scanner;

public class UI {

    private final DukeController duke;
    private final Parser parser;

    public UI() {
        duke = new DukeController();
        parser = new Parser();
    }

    public void execute() {
        printGreeting();
        Scanner in = new Scanner(System.in);
        while (true) {
            parser.setUserInput(in.nextLine());
            if (!processInput()) {break;}
        }
        System.out.println("Bye. Have a nice day!");
    }

    private boolean processInput() {
        if (parseAndCheckInput()) return true;
        Parser.validActions validAction = parser.getAction();
        switch (validAction) {
            case LIST:
                duke.printTasks();
                break;
            case DONE:
                duke.changeTaskStatus();
                break;
            case DELETE:
                duke.deleteTask();
                break;
            case ADD:
                duke.addTask();
                break;
            case BYE:
                return false;
            default:
                break;
        }
        return true;
    }

    private boolean parseAndCheckInput() {
        try {
            parser.parseValidAction();
            parser.parseUserInput();
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Oops the task description is empty");
            return true;
        }
        duke.setParsedUserInputs(parser.getParsedInput());
        return false;
    }

    private void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n";
        String instruction = "Please follow the instructions below\n"
                + "1. \"list\": print a list of tasks\n"
                + "2. \"done +number\" (e.g. done 2): set the task status to done\n"
                + "3. add task using \"todo\" \"deadline\" \"event\" keyword\n"
                + "4. \"bye\": end the program\n"
                +"Please type any command to start: \n";
        System.out.println(greeting);
        System.out.println(instruction);
    }
}
