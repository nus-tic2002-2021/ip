package com.alexooi.duke.tasks;

import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.StorageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class follows the Singleton design pattern as it is used extensively throughout the application
 */
public class TaskList {
    private static TaskList instance;
    private final ArrayList<Task> taskList;
    private final StorageClient<BufferedReader> stateClient;
    private final StorageClient<BufferedReader> archiveClient;

    private TaskList(StorageClient<BufferedReader> stateClient, StorageClient<BufferedReader> archiveClient) {
        assert stateClient != null && archiveClient != null;
        taskList = new ArrayList<>();
        this.stateClient = stateClient;
        this.archiveClient = archiveClient;
    }

    /**
     * This function lazily creates the TaskList if not already existing, else it returns the current instance of the TaskList
     * @param stateClient   The StorageClient corresponding to where the state is stored
     * @param archiveClient The StorageClient corresponding to where the task archive is stored
     * @return              The instance of the TaskList.
     */
    public static TaskList getInstance(StorageClient<BufferedReader> stateClient, StorageClient<BufferedReader> archiveClient) {
        if (instance == null) {
            instance = new TaskList(stateClient, archiveClient);
        }

        return instance;
    }

    /**
     * Adds a task to the task list
     * @param task  The task to add
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the task list by its index
     * @param idx   The index of the task to remove
     * @return      The task that has been removed.
     */
    public Task remove(int idx) {
        return taskList.remove(idx);
    }

    /**
     * Removes all tasks from the task list.
     */
    public void removeAll() {
        taskList.clear();
    }

    /**
     * Get the list of tasks
     * @return  Returns an ArrayList containing all the tasks currently in the task list
     */
    public ArrayList<Task> get() {
        return taskList;
    }

    /**
     * Get a task by its index
     * @param idx   The index of the task to retrieve
     * @return      The task at index idx
     */
    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    /**
     * Get the length of the task list
     * @return      The length of the task list
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Archives a single task in the task list.
     * @param idx The index of the task to archive.
     */
    public void archive(int idx) {
        String output = taskList.get(idx).toSaveString() + System.lineSeparator();
        assert archiveClient != null;
        archiveClient.save(output, true);
    }

    /**
     * Archive all tasks in the task list.
     */
    public void archive() {
        String output = toSaveListString();
        assert archiveClient != null;
        archiveClient.save(output, true);
    }

    /**
     * Save the state of the task list.
     */
    public void save() {
        String output = toSaveListString();
        assert stateClient != null;
        stateClient.save(output, false);
    }

    private String toSaveListString() {
        return taskList.stream().reduce("",
                (partialOutput, task) -> partialOutput + task.toSaveString() + System.lineSeparator(),
                String::concat
        );
    }

    public void load(BufferedReader in) throws IOException, InvalidFileFormatException {
        boolean isEndOfInput = false;
        while (!isEndOfInput) {
            String taskStr = in.readLine();
            if (taskStr == null) {
                isEndOfInput = true;
            } else {
                taskList.add(TaskFactory.getInstance(taskStr));
            }
        }
    }
}
