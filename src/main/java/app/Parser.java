package app;

import task.TaskCommands;
import static task.TaskCommands.*;

public class Parser {

    public enum validCommands {
        LIST, DONE, DELETE, HELP, BYE, TODO, EVENT, DEADLINE
    }

    public static boolean validInput (String check){
        for (validCommands a : validCommands.values()){
            if(a.name().equals(check)){
                return true;
            }
        }
        return false;
    }

    public static void parseCommand(String userEntry) throws DukeException {
        String [] userInput = userEntry.split("\\ ");
        int size = userEntry.length();
        String commandLower = userInput[0];
        String commandFirst = commandLower.toUpperCase();
        if(size == 1 && !commandFirst.equals("LIST") && !commandFirst.equals("BYE")){
            throw new DukeException();
        }
        {
                switch (commandFirst){
                    case "LIST":
                        TaskCommands.printList();
                        break;

                    case"DONE":
                        int ref = 0;
                        ref = Integer.valueOf(userInput[1]);
                        TaskCommands.setDone(ref-1);
                        break;

                    case"DELETE":
                        ref = 0;
                        ref = Integer.valueOf(userInput[1]);
                        TaskCommands.deleteTask(ref-1);
                        break;

                    case"HELP":
                        UI.instructions();
                        break;

                    case"BYE":
                        UI.endDuke();
                        break;

                    case"TODO":
                        addTodo(userInput[1]);
                        break;

                    case"EVENT":
                        if(!userInput[2].equals("/at")){
                            System.out.println("include /at");
                            break;
                        }
                        else{
                            addEvent(userInput[1],userInput[3]);
                        }
                        break;

                    case"DEADLINE":
                        if(!userInput[2].equals("/by")){
                            System.out.println("include /by");
                            break;
                        }
                        else{
                            addDeadline(userInput[1],userInput[3]);
                        }
                        break;

                    default: throw new DukeException();
                }
            }
        }

    }



