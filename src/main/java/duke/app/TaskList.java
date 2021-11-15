package duke.app;

import duke.task.Task;
import duke.task.TaskFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represent a list of tasks including todo, event and deadline.
 */
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

    public String deleteTask() {
        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        Task removedTask = taskList.remove(idx);
        return removedTask.toString();
    }

    /**
     * mark the task as done.
     * @return string representation of the task marked as done
     */
    public String changeTaskStatus() {

        int idx = Integer.parseInt(parsedUserInputs.get("NameOrIndex")) - 1;
        Task taskDone = taskList.get(idx);
        taskDone.setDone(true);
        return taskDone.toString();
    }

    /**
     * find out a list of tasks due before a specified date.
     * @return list of tasks
     */
    public List<Task> getTasksBeforeDate() {
        List<Task> criticalTasks = new ArrayList<>();
        LocalDateTime comparedDate = Parser.parseDateTime(parsedUserInputs.get("Time"));

        for (Task task: taskList) {
            if (task.getTime() != null) {
                LocalDateTime taskDate = Parser.parseDateTime(task.getTime());
                if (taskDate.isBefore(comparedDate)) {
                    criticalTasks.add(task);
                }
            }
        }
        return criticalTasks;
    }

    /** Add a new task to the list of tasks, only unique task will be added
     * @return task, Task
     */
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
}
