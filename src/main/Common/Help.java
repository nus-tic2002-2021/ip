package Common;

import data.Commands;

import static Common.Message.*;

public class Help {

    /**
     * Display HELP content for user. To aid user to accustom to the command supported in this DUKE Application.
     * @param s is parameter comes after HELP command to understand the individual command usages.
     */
    public static void HelpCommand(String[] s){
        Commands commandKey = null;
        final String command;
        if(s.length > 1){
            command = s[1].trim().toUpperCase();
        }else{
            command = "BREAKTHROUGH";
        }
        try{
            commandKey = Commands.valueOf(command);
        }catch (IllegalArgumentException e){
            commandKey = Commands.BREAKTHROUGH;
        }

        switch (commandKey){
            case DONE:
                System.out.println(HELP_DONE);
                break;
            case TODO:
                System.out.println(HELP_TODO);
                break;
            case LIST:
                System.out.println(HELP_LIST);
                break;
            case DEADLINE:
                System.out.println(HELP_DEADLINE);
                break;
            case EVENT:
                System.out.println(HELP_EVENT);
                break;
            case FIND:
                System.out.println(HELP_FIND);
                break;
            case SAVE:
                System.out.println(HELP_SAVE); // Individual Feature
                break;
            case DELETE:
                System.out.println(HELP_DELETE);
                break;
            case VIEW:
                System.out.println(HELP_VIEW); // Individual Feature
                break;
            case BYE:
                System.out.println(HELP_BYE);
                break;
            case BREAKTHROUGH:
               System.out.println(Message.getListOfCommandForUser());
                break;

        }
    }
}
