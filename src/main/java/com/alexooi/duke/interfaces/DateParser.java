package com.alexooi.duke.interfaces;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DateParser {
    boolean isDate(String input, DateTimeFormatter formatter);
    boolean isDateTime(String input, DateTimeFormatter formatter);
    LocalDateTime parseInput(String input);
}