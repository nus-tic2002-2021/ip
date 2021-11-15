package duke.app;

import duke.task.Task;
import duke.task.TaskFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

    private List<Task> taskList;
    private Map<String, String> parsedUserInputs;

    public TaskList(List<Task> taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        } else  {
            this.taskList = new ArrayList<>();
        }
        parsedUserInputs = new HashMap<>();
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setParsedUserInputs(Map<String, String> parsedUserInputs) {
        this.parsedUserInputs.clear();
        this.parsedUserInputs = parsedUserInputs;
    }

    public String changeTaskStatus() {

        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        Task taskDone = taskList.get(idx);
        taskDone.setDone(true);
        return taskDone.toString();
    }

    public boolean addTask() {
        Task newTask = TaskFactory.creatTask(parsedUserInputs);
        return addUniqueTask(newTask);
    }

    private boolean addUniqueTask(Task newTask) {
        if (!taskList.contains(newTask)) {
            taskList.add(newTask);
            return true;
        } else {
            return false;
        }
    }

    public String deleteTask() {
        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        Task removedTask = taskList.remove(idx);
        return removedTask.toString();
    }
}
