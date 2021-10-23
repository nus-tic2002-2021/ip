package Duke.parser;

public class Parser {

    /**
     * This is a factory that generates different CMD objects according to the input parameters
     * @see CMD
     */
    public static Command parse(String fullCommand) {
        Command command = new Command();
        if (fullCommand.contains(CMD_Enum.DONE.getName())) {
            command.setCmd(new CMD_Done(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.LIST.getName())) {
            command.setCmd(new CMD_List(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.DELETE.getName())) {
            command.setCmd(new CMD_Delete(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.BYE.getName())) {
            command.setCmd(new CMD_Bye(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.SAVE.getName())) {
            command.setCmd(new CMD_Save(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.TODO.getName())) {
            command.setCmd(new CMD_TODO(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.EVENT.getName())) {
            command.setCmd(new CMD_Event(fullCommand));
        } else if (fullCommand.contains(CMD_Enum.DEADLINE.getName())) {
            command.setCmd(new CMD_Deadline(fullCommand));
        } else {
            command.setCmd(new CMD_Error(fullCommand));
        }

        return command;
    }
}
