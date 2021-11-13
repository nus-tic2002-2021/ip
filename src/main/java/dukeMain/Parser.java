package dukeMain;

import dukeMain.commands.AddCommand;
import dukeMain.commands.Command;
import dukeMain.commands.DoneCommand;
import dukeMain.commands.DeleteCommand;
import dukeMain.commands.ExitCommand;
import dukeMain.commands.HelpCommand;
import dukeMain.commands.IncorrectCommand;
import dukeMain.commands.ListCommand;
import dukeMain.commands.SaveCommand;


// deals with making sense of the user command
public class Parser {
    public static String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \\n%1$s";

    /**
     * Used for initial separation of command word and args.
     */
    public static String[] parseFileLine(String str){
        return str.split("\\s\\|\\s");
    }
    public static String[] parseType(String str, String type){
        return str.split(type);
    }

    public static Command parse(String fullCommand){

        String[] strArr = processCommand(fullCommand);

        final String commandWord = strArr[0];
        final String arguments = strArr[1];
        switch (commandWord.toLowerCase()) {

            case AddCommand.COMMAND_WORD_1: // todo
            case AddCommand.COMMAND_WORD_2: // deadline
            case AddCommand.COMMAND_WORD_3: // event
                return prepareAdd(commandWord,arguments);
            case DeleteCommand.COMMAND_WORD:
            case DoneCommand.COMMAND_WORD:
                return prepareDeleteNDone(commandWord,arguments);
            case SaveCommand.COMMAND_WORD:
                return new SaveCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
//            case FindCommand.COMMAND_WORD:
//                return prepareFind(arguments);
            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

    private static Command prepareDeleteNDone(String commandWord, String arguments) {
        String[] str = parseType(arguments,"\\s");
//        System.out.println("Str : " + str[0]);
        int index;
        if (str.length != 1 ) return new IncorrectCommand("Please only include 1 Number after 'done'" );
        try{
            index = Integer.parseInt(str[0]);
            return (commandWord.equalsIgnoreCase(DoneCommand.COMMAND_WORD)) ? new DoneCommand(index) : new DeleteCommand(index);
        }catch(NumberFormatException e){
            return new IncorrectCommand("Please enter a number");
        }
    }

    private static Command prepareAdd(String command,String arguments) {
        String[] str = parseType(arguments,"/");
        if ((command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) && str.length != 1 ) ||
            (!command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) && str.length != 2))
            return new IncorrectCommand("Please only include 1 Number argument");

        return new AddCommand(command, str);
    }

    private static String[] processCommand(String command){
        String [] split = command.split("\\s");
        String [] returnStr = new String[2];
        String arg = "";
        int count = 0;
        for (String str : split){
            if (count == 0){
                returnStr[0] = str;// add command into First slot
                count ++;
            }else{
                if (count == 1){
                    arg += str;
                    count++ ;
                }else{
                    arg +=  " " + str;
                }
//                System.out.println(str);
            }
        }
        returnStr[1] = arg;
        return returnStr;
    }



}
