package duke.command;

import java.util.Scanner;

import duke.action.Parser;
import duke.storage.FileAccess;
import duke.task.TaskList;
import duke.ui.Message;

/**
 * Core Command class
 *
 * @author Kang Teng
 * @version 8.0
 * @since 2021-09-01
 */

public class Command {

    private static Parser parser;

    /**
     * Constructor
     */
    public Command(Parser parser) {
        this.parser = parser;
    }

    // Basic processCommand Method <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * Show info of all the command Duke can accept
     */
    public void showInfo() {
        Message.msgShowDukeCommandList();
    }

    /**
     * Show full list of tasks
     *
     * @param myList TaskList that contains the list of task
     */
    public void showFullList(TaskList myList) {
        CmdList.run(myList);
    }

    /**
     * Ask user which task to set priority and change the priority accordingly
     * -- Ask user which task to be changed
     * -- Ask user what is the new priority
     * -- Update the task with new priority
     *
     * @param myList  TaskList that contains the list of task
     * @param scanner Scanner for user input
     */
    public void setPriorityTask(TaskList myList, Scanner scanner) {
        CmdPriority.run(myList, scanner);
    }

    /**
     * Mark a task as done
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that the user type
     */
    public void markTaskDone(TaskList myList, String userInput) {
        CmdMarkTaskDone.run(myList, userInput);
    }

    /**
     * Mark a task as undone
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that the user type
     */
    public void markTaskUnDone(TaskList myList, String userInput) {
        CmdMarkTaskUnDone.run(myList, userInput);
    }

    /**
     * Add a task of ToDo type into the list of task
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that the user type
     */
    public void addTaskToDo(TaskList myList, String userInput) {
        CmdAddTask.addTaskToDo(myList, userInput);
    }

    /**
     * Save the current task list and output a text file
     *
     * @param myList TaskList that contains the list of task
     */
    public void saveTask(TaskList myList, FileAccess fileAccess) {
        new CmdSave(myList, fileAccess).run();
    }

    /**
     * Find the Task requested
     *
     * @param myList TaskList that contains the list of task
     */
    public void findTask(TaskList myList, String userInput) {
        CmdFind.run(myList, userInput);
    }

    /**
     * Add a task of Event type into TaskList
     * <p>
     * Identify the task date and time that the user input
     * Calls for respective AddTaskEvent_XXX to create a task object in TaskList afterwards
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that the user type
     */
    public void addTaskEvent(TaskList myList, String userInput) {
        CmdAddTask.addTaskEvent(myList, userInput);
    }

    /**
     * Delete a task from the user task
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that represents the user input
     */
    public void deleteTask(TaskList myList, String userInput) {
        CmdDelete.run(myList, userInput);
    }

    /**
     * Add a task of Deadline type into TaskList
     * <p>
     * Identify the task date and time that the user input
     * Calls for respective AddTaskEvent_XXX to create a task object in TaskList afterwards
     *
     * @param myList    TaskList that contains the list of task
     * @param userInput String that represents the user input
     */
    public void addTaskDeadline(TaskList myList, String userInput) {
        CmdAddTask.addTaskDeadline(myList, userInput);
    }

    /**
     * Show invalid command
     */
    public void showInvalidCommand() {
        Message.msgInvalidInput();
    }
}
