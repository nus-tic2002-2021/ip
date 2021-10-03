package storage;

import exceptions.DukeException;
import tasks.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Storage {
    private String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        File taskFile = new File(filePath);
        boolean fileExists = taskFile.exists();

        if(fileExists) {
            Scanner s = new Scanner(taskFile);
            ArrayList<Task> tasks = new ArrayList<>();

            while (s.hasNext()) {
                String taskStr = s.nextLine();

                try {
                    Task newTask = convertToTask(taskStr);
                    tasks.add(newTask);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
            return tasks;
        }
        else {
            throw new FileNotFoundException();
        }
    }

    public void init() throws IOException {
        File tasks = new File(filePath);

        String directory = tasks.getParent();
        File folders = new File(directory);
        if(!folders.exists()) {
            boolean dirCreated = folders.mkdirs();
        }

        boolean fileCreated = tasks.createNewFile();
    }

    private Task convertToTask(String taskStr){
        String[] args = taskStr.split(" \\| ");

        String keyword = args[0];
        String status = args[1];
        String description = args[2];

        Task task = null;
        switch(keyword){
            case "T": {
                task = new ToDoTask(description);
                break;
            }
            case "D": {
                String deadline = args[3];
                task = new DeadlineTask(description, deadline);
                break;
            }
            case "E": {
                String eventTime = args[3];
                task = new EventTask(description, eventTime);
                break;
            }
        }
        if (status.equals("1")){ task.markAsDone(); }
        return task;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        try {
            for (Task task : tasks) {
                String taskType = task.getClass().getSimpleName();

                String taskStr = null;
                String description = task.getDescription();
                String status = task.isDone() ? "1" : "0";

                switch (taskType) {
                    case "ToDoTask": {
                        taskStr = "T | " + status + " | " + description + "\n";
                        break;
                    }
                    case "DeadlineTask": {
                        DeadlineTask deadlineTask = (DeadlineTask)task;
                        taskStr = "D | " + status + " | " + description + " | " + deadlineTask.getBy() + "\n";
                        break;
                    }
                    case "EventTask": {
                        EventTask eventTask = (EventTask)task;
                        taskStr = "E | " + status + " | " + description + " | " + eventTask.getAt() + "\n";
                        break;
                    }
                }
                fw.write(taskStr);
            }
            fw.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
