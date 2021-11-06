package com.alexooi.duke.tasks;

import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.StorageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static TaskList instance;
    private ArrayList<Task> taskList;
    private StorageClient<BufferedReader> stateClient;
    private StorageClient<BufferedReader> archiveClient;

    private TaskList(StorageClient<BufferedReader> stateClient, StorageClient<BufferedReader> archiveClient) {
        taskList = new ArrayList<>();
        this.stateClient = stateClient;
        this.archiveClient = archiveClient;
        BufferedReader input = stateClient.load();
        load(input);
    }

    public static TaskList getInstance(StorageClient<BufferedReader> stateClient, StorageClient<BufferedReader> archiveClient) {
        if (instance == null) {
            instance = new TaskList(stateClient, archiveClient);
        }

        return instance;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int idx) {
        return taskList.remove(idx);
    }

    public void removeAll() {
        taskList.clear();
    }

    public ArrayList<Task> get() {
        return taskList;
    }

    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    public int size() {
        return taskList.size();
    }

    public void archive(int idx) {
        String output = taskList.get(idx).toSaveString() + System.lineSeparator();
        archiveClient.save(output, true);
    }

    public void archive() {
        String output = toSaveListString();
        archiveClient.save(output, true);
    }

    public void save() {
        String output = toSaveListString();
        stateClient.save(output, false);
    }

    private String toSaveListString() {
        String output = taskList.stream().reduce("",
                (partialOutput, task) -> partialOutput + task.toSaveString() + System.lineSeparator(),
                String::concat
        );
        return output;
    }

    private void load(BufferedReader in) {
        boolean isEndOfInput = false;
        while (!isEndOfInput) {
            try {
                String taskStr = in.readLine();
                if (taskStr == null) {
                    isEndOfInput = true;
                } else {
                    taskList.add(TaskFactory.getInstance(taskStr));
                }
            } catch (IOException e) {
                System.out.println("Unable to read file!");
            } catch (InvalidFileFormatException iffe) {
                System.out.println("Invalid File Format detected!");
            }
        }
    }
}
