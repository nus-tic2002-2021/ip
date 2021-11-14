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
import dukeMain.common.month.monthEnum;

import java.time.LocalDate;

/** Parser class helps the program to understand
 * the command entered by the users and/or files.
 * */
public class Parser {
    public static String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \\n%1$s";

    /**
     * Used for initial separation of command word and args.
     * split str with regex  : "\\s\\|\\s" translate to " | "
     * Specially used for File processing
     *
     * @param str String
     * @return String[] of splited str
     */
    public static String[] parseFileLine(String str){
        return str.split("\\s\\|\\s");
    }

    /**
     * Used for initial separation of command word and args.
     * split str with regex indicated by type variable
     *
     * @param type
     * @param str String
     * @return String[] of splited str
     */
    public static String[] parseType(String str, String type){
        return str.split(type);
    }

    /**
     * Breakdown the commands given from the users.
     *
     * @param fullCommand String
     * @return Command
     */
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

    /** Helps to prepare and check
     *  the arguments need for Delete and Done command.
     *
     * @param command String
     * @param arguments String
     * @return Command type
     * */
    private static Command prepareDeleteNDone(String command, String arguments) {
        String[] str = parseType(arguments,"\\s");
//        System.out.println("Str : " + str[0]);
        int index;
        if (str.length != 1 ) return new IncorrectCommand("Please only include 1 Number after 'done'" );
        try{
            index = Integer.parseInt(str[0]);
            return (command.equalsIgnoreCase(DoneCommand.COMMAND_WORD)) ? new DoneCommand(index) : new DeleteCommand(index);
        }catch(NumberFormatException e){
            return new IncorrectCommand("Please enter a number");
        }
    }

    /** Helps to prepare and check
     *  the arguments need for Add commands.
     *
     * @param command String
     * @param arguments String
     * @return Command type
     * */
    private static Command prepareAdd(String command,String arguments) {
        String[] str = parseType(arguments,"/");
        if ((command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) && str.length != 1 ) ||
            (!command.equalsIgnoreCase(AddCommand.COMMAND_WORD_1) && str.length != 2))
            return new IncorrectCommand("Please only include 1 Number argument");

        return new AddCommand(command, str);
    }

    /** Helps to break the user commands
     * into commands and argments
     *
     * @param command String
     * @return String Array
     * */
    private static String[] processCommand(String command){
        String [] split = command.split("\\s");
        String [] returnStr = new String[2];
        String arg = "";
        int count = 0;
        for (String str : split){
            if (count == 0){
                returnStr[0] = str; // add command into First slot
                count ++;
            }else{
                if (count == 1){
                    arg += str;
                    count++ ;
                }else{
                    arg +=  " " + str;
                }
            }
        }
        returnStr[1] = arg;
        return returnStr;
    }

    /** parse Local Date from string variable
     *
     * @param dateTime String
     * @return LocalDate
     * */
    public static LocalDate parseLDT(String dateTime){
        String str[] = dateTime.split("\\W");
        int beginIndex = 0,day,month,year;
        LocalDate lDate ;

        // Check for any by or at
        if (str[beginIndex].toLowerCase().startsWith("at") || str[beginIndex].toLowerCase().startsWith("by")){
            beginIndex ++;
        }
        if(str[beginIndex].isEmpty()){
            beginIndex++;
        }
        // datetime only has Date no time
        System.out.println(str[beginIndex]);
        System.out.println(str[beginIndex+1]);
        System.out.println(str[beginIndex+2]);
        day = Integer.parseInt(str[beginIndex]);
        try {
            month = Integer.parseInt(str[beginIndex+1]);
        }catch(NumberFormatException e){
            month = convertMonth(str[beginIndex+1]);
        }
        year = Integer.parseInt(str[beginIndex+2]);

        // Add 2000 to make any value such as 21 into 2021
        if(year < 100) year += 2000;

        lDate = LocalDate.of(year,month,day);

        return lDate;
    }

    /** Convert Month from String to integer
     * With the use of monthEnum enum Class
     *
     * @param month String
     * @return int of month
     * */
    public static int convertMonth(String month){
        monthEnum mon = monthEnum.valueOf(month.toUpperCase());
        return mon.getMonthInt();
    }

    /** Retrieve the time only
     * from the dateTime String
     *
     * @param dateTime String
     * @return String
     * */
    public static String getTime(String dateTime){
        String[] str = dateTime.split("\\W");
        String returnStr = "";
        int index = 3;
        if(str[0].isEmpty()) index ++;
        for (int i = index; i < str.length;i++){
            returnStr += str[i];
        }
        System.out.println(returnStr);
        return returnStr;
    }
}
