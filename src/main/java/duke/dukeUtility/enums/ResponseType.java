package duke.dukeUtility.enums;

/**
 * @see duke.command.Command
 */
public enum ResponseType {
    ECHO,
    EXIT_LOOP,
    ERROR_COMMAND_EXECUTION,
    ERROR_REQUEST_INVALID_PARAMETERS,
    TASK_CREATE_TODO,
    TASK_LIST_ALL,
    TASK_UPDATE_DONE_STATUS,
    TASK_NOT_FOUND,
}