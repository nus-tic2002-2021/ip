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
            "View schedule should be in the format: view schedule /for <date>.";

    private String errorMessage;

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
