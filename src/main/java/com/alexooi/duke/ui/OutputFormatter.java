package com.alexooi.duke.ui;

import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.interfaces.OutputFormat;

import java.time.LocalDate;

public class OutputFormatter implements OutputFormat<Task> {
    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = "_________________________________________";
    private static final String START = "Hello! I'm Duke\n    What can I do for you?";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String DONE = "Nice! I've marked this task as done:";
    private static final String ADD = "added: ";
    private static final String REMOVE = "Noted. I've removed this task:";
    private static final String VIEW_SCHEDULE = "You have the following tasks scheduled on ";
    private static final String FIND = "Here are the matching tasks in your list:";

    @Override
    public String start() {
        return formatOutput(formatLine(START));
    }

    @Override
    public String add(Task printable, int length) {
        String output = formatLine(ADD + printable.toString());
        output += count(length);
        return formatOutput(output);
    }

    @Override
    public String done(Task printable) {
        String output = formatLine(DONE);
        output += formatLine("   " + printable.toStatusString());
        return formatOutput(output);
    }

    @Override
    public String remove(Task printable, int length) {
        String output = formatLine(REMOVE);
        output += formatLine("   " + printable.toStatusString());
        output += count(length);
        return formatOutput(output);
    }

    @Override
    public String error(String header, String errorMessage) {
        String output = formatLine(header) + formatLine(errorMessage);
        return formatOutput(output);
    }

    @Override
    public String error(String errorMessage) {
        return formatOutput(formatLine(errorMessage));
    }

    @Override
    public String list(Iterable<Task> inputs) {
        StringBuilder output = new StringBuilder();
        output.append(formatList(inputs));
        return formatOutput(output.toString());
    }

    @Override
    public String viewSchedule(Iterable<Task> inputs, LocalDate date) {
        StringBuilder output = new StringBuilder();
        output.append(formatLine(VIEW_SCHEDULE + date.toString()));
        output.append(formatList(inputs));
        return formatOutput(output.toString());
    }

    @Override
    public String find(Iterable<Task> inputs) {
        StringBuilder output = new StringBuilder();
        output.append(formatLine(FIND));
        output.append(formatList(inputs));
        return formatOutput(output.toString());
    }

    @Override
    public String exit() {
        return formatOutput(formatLine(END));
    }

    private String count(int length) {
        String multipleStr = (length > 1) ? "tasks" : "task";
        String taskStr = length + " " + multipleStr;
        return formatLine("Now you have " + taskStr + " in the list.");
    }

    private String formatLine(String output) {
        return PADDING + output + System.lineSeparator();
    }

    private String formatOutput(String output) {
        return formatLine(HORIZONTAL_LINE) + output + PADDING + HORIZONTAL_LINE;
    }

    private String formatList(Iterable<Task> inputs) {
        StringBuilder output = new StringBuilder();
        int count = 1;
        for (Task input : inputs) {
            output.append(formatLine(count + ". " + input.toStatusString()));
            count++;
        }
        return output.toString();
    }
}
