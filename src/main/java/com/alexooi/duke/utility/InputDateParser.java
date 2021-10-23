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

    boolean isDate(String input, DateTimeFormatter acceptedFormats) {
        try {
            LocalDate.parse(input, acceptedFormats);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    boolean isDateTime(String input, DateTimeFormatter acceptedFormats) {
        try {
            LocalDateTime.parse(input, acceptedFormats);
        } catch (DateTimeParseException dtpe) {
            return false;
        }
        return true;
    }

    DateTimeFormatter formatBuilder(DateTimeFormatter[] formats) {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        for (DateTimeFormatter formatter : formats) {
            builder.appendOptional(formatter);
        }
        return builder.toFormatter();
    }

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
