package command;

import exception.DukeException;

/**
 * Enum detailing all the valid commands Duke understands.
 * Uses by Parser to generate Command Object.
 * Checks if user types invalid command.
 */
public enum FullCommand {
    LIST("list"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    DONE("done"),
    DELETE("delete"),
    BYE("bye"),
    FIND("find"),
    VIEW("view"),
    INSTRUCTION("instruction");

    private String activityName;

    FullCommand(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityName() {
        return activityName;
    }

    /**
     * Generates FullCommand based on String user input.
     * @param keyword Specifies the type of FullCommand.
     * @return Returns FullCommand to be parsed by parser.
     * @throws DukeException Throws when user enters invalid command.
     */
    public static FullCommand getByAction(String keyword) throws DukeException {
        for (FullCommand activity : values()) {
            if (activity.getActivityName().equals(keyword)) {
                return activity;
            }
        }
        throw new DukeException("\nCommand does not exist.");
    }
}
