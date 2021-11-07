package com.alexooi.duke.interfaces;

import java.time.LocalDate;

public interface OutputFormat<T> {
    String start();

    String add(T printable, int length);

    String done(T printable);

    String remove(T printable, int length);

    String error(String header, String errorMessage);

    String error(String errorMessage);

    String list(Iterable<T> inputs);

    String viewSchedule(Iterable<T> inputs, LocalDate date);

    String find(Iterable<T> inputs);

    String archive(T printable);

    String archive(Iterable<T> inputs);

    String help(Iterable<String> inputs);

    String exit();
}
