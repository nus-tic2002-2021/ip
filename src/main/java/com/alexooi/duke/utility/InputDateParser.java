package com.alexooi.duke.utility;

import com.alexooi.duke.interfaces.DateParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class InputDateParser implements DateParser {
    private final DateTimeFormatter[] ACCEPTED_DATE_FORMATS = {
            DateTimeFormatter.ISO_DATE
    };
    private final DateTimeFormatter[] ACCEPTED_DATE_TIME_FORMATS = {
            DateTimeFormatter.ISO_DATE_TIME
    };

    /**
     * Checks if a string matches some particular date formats, returning true if it does, false if not.
     * @param input             Date in string format
     * @param acceptedFormats   Can be a DateTimeFormatter or a list of accepted formats built using DateTimeFormatterBuilder
     * @return                  True if the string is an accepted date format, false if not
     */
    @Override
    public boolean isDate(String input, DateTimeFormatter acceptedFormats) {
        try {
            LocalDate.parse(input, acceptedFormats);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    /**
     * Checks if a string matches some particular date formats, returning true if it does, false if not
     * @param input             Date and Time in string format
     * @param acceptedFormats   Can be a DateTimeFormatter or a list of accepted formats built using DateTimeFormatterBuilder
     * @return                  True if the string is an accepted Date and Time format, false if not
     */
    @Override
    public boolean isDateTime(String input, DateTimeFormatter acceptedFormats) {
        try {
            LocalDateTime.parse(input, acceptedFormats);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    private DateTimeFormatter formatBuilder(DateTimeFormatter[] formats) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (DateTimeFormatter formatter : formats) {
            builder.appendOptional(formatter);
        }
        return builder.toFormatter();
    }

    /**
     * Parses a LocalDateTime object from an input string
     * @param input Date or Date and Time in string format
     * @return      Returns a LocalDateTime object containing the parsed Date and parsed Time if applicable,
     *                  otherwise time defaults to start of day 00:00. If the string cannot be parsed, then returns null
     */
    @Override
    public LocalDateTime parseInput(String input) {
        DateTimeFormatter dateTimeFormats = formatBuilder(ACCEPTED_DATE_TIME_FORMATS);
        DateTimeFormatter dateFormats = formatBuilder(ACCEPTED_DATE_FORMATS);
        if (isDateTime(input, dateTimeFormats)) {
            return LocalDateTime.parse(input, dateTimeFormats);
        } else if (isDate(input, dateFormats)) {
            return LocalDate.parse(input, dateFormats).atStartOfDay();
        }
        return null;
    }
}
