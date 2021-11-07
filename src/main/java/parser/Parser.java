package parser;

import command.*;
import tasklist.*;
import ui.Ui;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


/**
 * The <code>Parser</code> class contains methods to parse the user command into required actions.
 */
public class Parser {
    /**
     * <code>commandToArray</code> method splits user input to an array of Strings.
     *
     * @param text is the user input
     * @return an array of Strings
     */
    public static String[] commandToArray(String text) {
        return text.split(" ",2);
    }

    /**
     *The parseDate method returns LocalDate object from user input
     * @param command is the user input
     * @return LocalDate object date
     * @throws DateTimeParseException when input is not of pattern specified
     */
    public static LocalDate parseDate(String[] command) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;
        date = LocalDate.parse(command[1], formatter);
        return date;
    }

    /**
     *The parseDateTime method returns LocalDateTime object from user input
     * @param command is the user input
     * @return LocalDateTime object dateTime
     * @throws DateTimeParseException when input is not of pattern specified
     */
    public static LocalDateTime parseDateTime(String[] command) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime;
        dateTime = LocalDateTime.parse(command[1].split(" /by | /at ")[1], formatter);
        return dateTime;
    }

    /**
     *Since the user input and tasks.txt file are of different pattern.
     * The parseDateTime method returns LocalDateTime object from tasks.txt
     * @param command is a line in tasks.txt
     * @return LocalDateTime object dateTime
     * @throws DateTimeParseException when input is not of pattern specified
     */
    public static LocalDateTime parseDateTimeFromFile(String[] command) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        LocalDateTime dateTimeFromFile;
        dateTimeFromFile = LocalDateTime.parse(command[1].split(" /by | /at ")[1], formatter);
        return dateTimeFromFile;
    }

    /**
     * <code>parse</code> method parses user input to corresponding functions of the program
     * @param text is the user input
     * @param taskList is the list of tasks
     * @return a command object for corresponding functions of the program
     * @throws IllegalArgumentException when user command is not included in the CommandCollection Enum Class
     */
    public static Command parse(String text, TaskList taskList) throws IllegalArgumentException{
        assert text != null : "Command cannot be null";

        String[] command = commandToArray(text);
        String instruction = command[0].toUpperCase();
        LocalDateTime dateTime;

        CommandCollections commandCollections;
        try {
            commandCollections = CommandCollections.valueOf(instruction);
        } catch (IllegalArgumentException e) {
            return new InvalidCommand("Sorry. I can't understand ["+ command[0] +"] yet. Please try again or type [help].");
        }


        switch (commandCollections) {
            case EVENT:
                try{
                    Ui.validateEventCommand(command);
                    dateTime = parseDateTime(command);
                    return new AddCommand(new Event(text,dateTime));
                }catch (DateTimeParseException e) {
                    return new InvalidCommand(Ui.validateDateTime());
                }
            case TODO:
                Ui.validateTodoCommand(command);
                return new AddCommand(new Todo(text));
            case DEADLINE:
                try{
                    Ui.validateDeadlineCommand(command);
                    dateTime = parseDateTime(command);
                    return new AddCommand(new Deadline(text,dateTime));
                }catch (DateTimeParseException e) {
                    return new InvalidCommand(Ui.validateDateTime());
                }
            case LIST:
                return new ListCommand();
            case DONE:
                Ui.validateDoneCommand(command, taskList);
                return new DoneCommand(command);
            case DELETE:
                Ui.validateDoneCommand(command, taskList);
                return new DeleteCommand(command);
            case VIEW:
                try{
                    Ui.validateViewCommand(command);
                    LocalDate date = parseDate(command);
                    return new ViewCommand(date);
                }catch (DateTimeParseException e) {
                    return new InvalidCommand("Please enter datetime in the format of 'd/M/yyyy'");
                }
            case SEARCH:
                Ui.validateSearchCommand(command);
                String keyword = command[1];
                return new SearchCommand(keyword);
            case HELP:
                return new HelpCommand();
            case RESET:
                return new ResetCommand();
            case BYE:
                return new ByeCommand();
            default:
                return new InvalidCommand("");
        }

    }
}
