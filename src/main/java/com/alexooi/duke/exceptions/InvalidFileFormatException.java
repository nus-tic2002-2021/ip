package com.alexooi.duke.exceptions;

public class InvalidFileFormatException extends Throwable {
    private static final String ERROR_PREFIX = "Invalid File Format:";
    public static final String ERROR_DATE = "Unable to parse date from file, file may have been modified manually or corrupted.";

    public InvalidFileFormatException() {}

    public InvalidFileFormatException(String errorMessage) {
        super(errorMessage);
    }

    public String getErrorHeader() {
        return ERROR_PREFIX;
    }

    public String toString() {
        return getMessage();
    }
}
