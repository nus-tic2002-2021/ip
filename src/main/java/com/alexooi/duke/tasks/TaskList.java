package com.alexooi.duke.tasks;

import com.alexooi.duke.exceptions.InvalidFileFormatException;
import com.alexooi.duke.interfaces.StorageClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static TaskList instance;
    private ArrayList<Task> taskList;
    private StorageClient<BufferedReader> client;

    private TaskList(StorageClient<BufferedReader> client) {
        taskList = new ArrayList<>();
        this.client = client;
        BufferedReader input = client.load();
        load(input);
    }

    public static TaskList getInstance(StorageClient<BufferedReader> client) {
        if (instance == null) {
            instance = new TaskList(client);
        }

        return instance;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int idx) {
        return taskList.remove(idx);
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

    public void save() {
        String output = taskList.stream().reduce("",
                (partialOutput, task) -> partialOutput + task.toSaveString() + System.lineSeparator(),
                String::concat
        );
        client.save(output);
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
