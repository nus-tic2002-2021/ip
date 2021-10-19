package duke.storage;

import duke.tasklist.*;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> getTaskList() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String taskStr = s.nextLine();
            try {
                Task task = convertStrToTask(taskStr);
                taskList.add(task);
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("___________________________________________________________________\n" +
                        "The task \""+taskStr+"\" is not added due to IndexOutOfBoundsException.\n" +
                        "The data are likely to be corrupted.");
            }
        }
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
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
                    taskStr = taskType + "," + taskStatus + "," + taskDesc + "," + task.getBy() + "\n";
                    break;
                }
                case "E": {
                    String taskDatetime = task.getAt();
                    taskStr = taskType + "," + taskStatus + "," + taskDesc + "," + task.getAt() + "\n";
                    break;
                }
            }
            fw.write(taskStr);
        }
        fw.close();
    }

    private Task convertStrToTask(String taskStr) throws IndexOutOfBoundsException {
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
                String taskDateTime = taskFullDesc[3];
                taskDateTime = dateTimeFormatter(taskDateTime);
                task = new Deadline(taskDesc, taskDateTime);
                break;
            }
            case "E": {
                String taskDateTime = taskFullDesc[3];
                taskDateTime = dateTimeFormatter(taskDateTime);
                task = new Event(taskDesc, taskDateTime);
                break;
            }
        }
        if (taskStatus.equalsIgnoreCase("X")) task.setDone();
        return task;
    }

    private String dateTimeFormatter(String taskDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        LocalDateTime dt = LocalDateTime.parse(taskDateTime, dtf);
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return taskDateTime = dt.format(dtf);
    }

}
