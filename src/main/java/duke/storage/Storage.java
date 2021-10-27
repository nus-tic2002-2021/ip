package duke.storage;

import duke.exception.DukeException;
import duke.tasklist.*;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Storage</code> object for task list access and storage.
 * Extends the <code>Task</code> class.
 */
public class Storage {

    private String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Constructs Storage with this task list file path.
     *
     * @param filePath The task list file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the stored task list.
     *
     * @return An arraylist of tasks.
     * @throws FileNotFoundException If there is no file found.
     */
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

    /**
     * Writes task list to file.
     *
     * @param taskList The arraylist of tasks to write to file.
     * @throws FileNotFoundException If there is no file found.
     */
    public void setTaskList(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList) {
            String taskType = task.getTaskType();
            String taskStatus = task.getDoneStatus();
            String taskDesc = task.getDescription();
            String taskTagDesc = task.getTagDescription();
            String taskStr = null;
            switch (taskType) {
                case "T": {
                    taskStr = taskType + "," + taskStatus + "," + taskDesc + "," + taskTagDesc + "\n";
                    break;
                }
                case "D": {
                    String taskDatetime = task.getBy();
                    taskStr = taskType + "," + taskStatus + "," + taskDesc + "," + task.getBy() + "," + taskTagDesc + "\n";
                    break;
                }
                case "E": {
                    String taskDatetime = task.getAt();
                    taskStr = taskType + "," + taskStatus + "," + taskDesc + "," + task.getAt() + "," + taskTagDesc + "\n";
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
        String taskTagDesc = "";
        Task task = null;
        switch (taskType) {
            case "T": {
                task = new Todo(taskDesc);
                taskTagDesc = taskFullDesc[3];
                break;
            }
            case "D": {
                String taskDateTime = taskFullDesc[3];
                taskDateTime = dateTimeFormatter(taskDateTime);
                task = new Deadline(taskDesc, taskDateTime);
                taskTagDesc = taskFullDesc[4];
                break;
            }
            case "E": {
                String taskDateTime = taskFullDesc[3];
                taskDateTime = dateTimeFormatter(taskDateTime);
                task = new Event(taskDesc, taskDateTime);
                taskTagDesc = taskFullDesc[4];
                break;
            }
        }
        if (taskStatus.equalsIgnoreCase("X")) {
            task.setDone();
        }
        task.setTag(taskTagDesc);
        return task;
    }

    private String dateTimeFormatter(String taskDateTime) {
        DateTimeFormatter oldFormat = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dt = LocalDateTime.parse(taskDateTime, oldFormat);
        return taskDateTime = dt.format(newFormat);
    }

}
