package duke.storage;

import duke.exception.*;
import duke.tasklist.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getTaskList() throws FileNotFoundException {

        File f = new File(filePath);

        if ( f.exists() ) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String taskStr = s.nextLine();
                try {
                    Task task = convertToTask(taskStr);
                    taskList.add(task);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            return taskList;
        }
        else {
            throw new FileNotFoundException();
        }

    }

    private Task convertToTask(String taskStr) {

        String[] taskFullDesc = taskStr.split(",");
        String taskType = taskFullDesc[0];
        String taskStatus = taskFullDesc[1];
        String taskDesc = taskFullDesc[2];
        Task task = null;

        switch (taskType) {
            case "T": {
                task = new Todo(taskDesc);
                break;
            }
            case "D": {
                String taskDatetime = taskFullDesc[3];
                task = new Deadline(taskDesc, taskDatetime);
                break;
            }
            case "E": {
                String taskDatetime = taskFullDesc[3];
                task = new Event(taskDesc, taskDatetime);
                break;
            }
        }

        //if (status.equals("1")){ task.markAsDone(); }
        return task;

    }

    public void setTaskList(ArrayList<Task> taskList) throws IOException {

        FileWriter fw = new FileWriter(filePath);

        try {

            for (Task task : taskList) {

                String taskType = task.getTaskType();
                String taskStatus = task.getDoneStatus();
                String taskDesc = task.getDescription();
                String taskStr = null;

                switch (taskType) {
                    case "T": {
                        taskStr = taskType + "," + taskStatus + "," + taskDesc + "\n";
                        break;
                    }
                    case "D": {
                        String taskDatetime = task.getBy();
                        taskStr = taskType + "," + taskStatus + "," + taskDesc + task.getBy() + "\n";
                        break;
                    }
                    case "E": {
                        String taskDatetime = task.getAt();
                        taskStr = taskType + "," + taskStatus + "," + taskDesc + task.getAt() + "\n";
                        break;
                    }
                }

                fw.write(taskStr);

            }

            fw.close();

        }

        catch (IOException e){
            System.out.println(e.getMessage());
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

}
