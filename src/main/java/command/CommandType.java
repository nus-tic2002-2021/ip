package command;

import error.DukeException;
import error.UnrecognizedException;

public enum CommandType {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    BYE("bye"),
    DELETE("delete"),
    DONE("done"),
    LIST("list"),
    FIND("find"),
    VIEW("view"),
    HELP("help");

    private String commandType;

    CommandType(String commandType){
        this.commandType = commandType;
    }

    public String getCommandType(){
        return commandType;
    }

    public static CommandType getCommandType(String inputAction) throws DukeException{
        for (CommandType command : CommandType.values()) {
            if (command.getCommandType().equals(inputAction)){
                return command;
            }
        }
        assert false;
        throw new DukeException("INVALID_ACTION");
    }
}
