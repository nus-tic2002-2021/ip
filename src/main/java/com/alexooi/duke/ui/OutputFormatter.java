package com.alexooi.duke.ui;

import com.alexooi.duke.tasks.Task;
import com.alexooi.duke.interfaces.OutputFormat;

import java.time.LocalDate;

/**
 * This class is the concrete implementation of OutputFormat. It is used for formatting the chatbot output to the
 * desired output format.
 */
public class OutputFormatter implements OutputFormat<Task> {
    private static final String PADDING = "    ";
    private static final String HORIZONTAL_LINE = "_________________________________________";
    private static final String START = "Hello! I'm Tassie, your virtual assistant.\n    What can I do for you?\n\n    Note: Enter help to see a list of commands.";
    private static final String COMMAND_LIST = "The following commands are available:";
    private static final String END = "Bye. Hope to see you again soon!";
    private static final String DONE = "Nice! I've marked this task as done:";
    private static final String ADD = "added: ";
    private static final String REMOVE = "Noted. I've removed this task:";
    private static final String VIEW_SCHEDULE = "You have the following tasks scheduled on ";
    private static final String FIND = "Here are the matching tasks in your list:";
    private static final String ARCHIVE_SINGLE = "We successfully archived the following task:";
    private static final String ARCHIVE_ALL = "We successfully archived all of your tasks!";

    /**
     * Generates the output when first starting the application.
     * @return   formatted string containing the output to print.
     */
    @Override
    public String start() {
        return formatOutput(formatLine(START));
    }

    /**
     * Generates the output when adding a new Task
     * @param printable Task or Child Task that has implemented the toString() method
     * @param length    Length of the task list after adding the new Task
     * @return          formatted string containing the output to print
     */
    @Override
    public String add(Task printable, int length) {
        String output = formatLine(ADD + printable.toString());
        output += count(length);
        return formatOutput(output);
    }

    /**
     * Generates the output when marking a Task as done.
     * @param printable Task or Child Task that has implemented the toStatusString method
     * @return          formatted string containing the output to print
     */
    @Override
    public String done(Task printable) {
        String output = formatLine(DONE);
        output += formatLine("   " + printable.toStatusString());
        return formatOutput(output);
    }

    /**
     * Generates the output when removing a Task.
     * @param printable The task that has been removed
     * @param length    Length of the task list after removing the Task
     * @return          formatted string containing the output to print
     */
    @Override
    public String remove(Task printable, int length) {
        String output = formatLine(REMOVE);
        output += formatLine("   " + printable.toStatusString());
        output += count(length);
        return formatOutput(output);
    }

    /**
     * Generates the output when there is an error that has occurred.
     * @param header        The header to print for the error
     * @param errorMessage  The error message of the error that occurred
     * @return              formatted string containing the error header and message
     */
    @Override
    public String error(String header, String errorMessage) {
        String output = formatLine(header) + formatLine(errorMessage);
        return formatOutput(output);
    }

    /**
     * Generates the output when there is an error that has occurred.
     * @param errorMessage  The error message of the error that occurred
     * @return              formatted string containing the error message
     */
    @Override
    public String error(String errorMessage) {
        return formatOutput(formatLine(errorMessage));
    }

    /**
     * Generates the output when listing the current task list
     * @param inputs An iterable list of Tasks
     * @return       formatted string containing the list of Tasks
     */
    @Override
    public String list(Iterable<Task> inputs) {
        return formatOutput(formatList(inputs));
    }

    /**
     * Generates the output when viewing the schedule of a specific date
     * @param inputs An iterable list of tasks on that date
     * @param date   Date that is being viewed
     * @return       formatted string containing the list of Tasks on that specific date
     */
    @Override
    public String viewSchedule(Iterable<Task> inputs, LocalDate date) {
        String output = formatLine(VIEW_SCHEDULE + date.toString()) +
                formatList(inputs);
        return formatOutput(output);
    }

    /**
     * Generates the output of a description find
     * @param inputs An iterable list of tasks that matches the find
     * @return       formatted string containing the list of Tasks that match the keyword
     */
    @Override
    public String find(Iterable<Task> inputs) {
        String output = formatLine(FIND) +
                formatList(inputs);
        return formatOutput(output);
    }

    /**
     * Generates the output of a single Task archival
     * @param printable The task that has been archived
     * @return          Formatted string containing the Task
     */
    @Override
    public String archive(Task printable) {
        String output = formatLine(ARCHIVE_SINGLE) + formatLine(printable.toStatusString());
        return formatOutput(output);
    }

    /**
     * Generates the output of a complete Task List archival
     * @param inputs The list of tasks the have been archived
     * @return       Formatted string containing all the tasks that are archived
     */
    @Override
    public String archive(Iterable<Task> inputs) {
        String output = formatLine(ARCHIVE_ALL) +
                formatList(inputs);
        return formatOutput(output);
    }

    @Override
    public String help(Iterable<String> inputs) {
        StringBuilder builder = new StringBuilder();
        builder.append(formatLine(COMMAND_LIST));
        for (String input : inputs) {
            builder.append(formatLine(input));
        }
        return formatOutput(builder.toString());
    }

    /**
     * Generates the output when the user is exiting
     * @return  Formatted string for the exit message
     */
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
