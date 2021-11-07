package com.alexooi.duke.exceptions;

public class InvalidCommandFormatException extends Throwable {
    private static final String ERROR_PREFIX = "Invalid Command Format:";
    public static final String ERROR_DEADLINE =
            "Deadline should be in the format: deadline <description> /by <date>.";
    public static final String ERROR_EVENT =
            "Event should be in the format: event <description> /at <date>.";
    public static final String ERROR_TODO =
            "Todo should be in the format: todo <description>.";
    public static final String ERROR_VIEW_SCHEDULE =
            "View schedule should be in the format: view <date>.";
    public static final String ERROR_ARCHIVE_NO_SUCH_TASK =
            "The task number to archive is not valid. Archive should be in the format: archive <all|num> where num is a valid index";
    public static final String ERROR_NO_SUCH_INDEX =
            "The task number is not valid. Please use the corresponding task number when listing the tasks";

    public InvalidCommandFormatException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorHeader() {
        return ERROR_PREFIX;
    }

    public String toString() {
        return getMessage();
    }
}
