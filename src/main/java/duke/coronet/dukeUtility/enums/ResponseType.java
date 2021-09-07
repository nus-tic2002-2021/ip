package duke.coronet.dukeUtility.enums;

/**
 * @see duke.coronet.command.Command
 */
public enum ResponseType {
    ECHO,
    EXIT_LOOP,
    ERROR_COMMAND_EXECUTION,
    ERROR_REQUEST_INVALID,
    ERROR_REQUEST_UNKNOWN,
    TASK_CREATE_TODO,
    TASK_CREATE_DEADLINE,
    TASK_CREATE_EVENT,
    TASK_LIST_ALL,
    TASK_UPDATE_DONE_STATUS,
    TASK_NOT_FOUND,
}