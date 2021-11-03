package duke.parser;

import duke.tasklist.*;
import duke.ui.Ui;
import duke.command.*;

public class Parser {



    public static String[] commandToArray(String text){
        return text.split(" ");
    }

    public static Command parse(String text, TaskList taskList) throws IllegalArgumentException{
        assert text != null : "Command be null";

        String[] command = commandToArray(text);
        String keyword = command[0].toUpperCase();

        CommandCollections commandCollections;

        try {
            commandCollections = CommandCollections.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("☹ The command in not valid! Please re-enter:");
        }

        switch (commandCollections) {
            case EVENT:
                Ui.validateEventCommand(command);
                return new AddCommand(new Event(text));
            case TODO:
                Ui.validateTodoCommand(command);
                return new AddCommand(new Todo(text));
            case DEADLINE:
                Ui.validateDeadlineCommand(command);
                return new AddCommand(new Deadline(text));
            case LIST:
                return new ListCommand();
            case DONE:
                Ui.validateDoneCommand(command, taskList);
                Ui.printDone(command, taskList);
                return new DoneCommand(command);
            case DELETE:
                Ui.validateDoneCommand(command, taskList);
                Command delete = new DeleteCommand(command);
                Ui.printDelete(command, taskList);
                return delete;
            case BYE:
                return new ByeCommand();
            default:
                return new InvalidCommand("☹ The command in not valid! Please re-enter:");
        }

    }
}
