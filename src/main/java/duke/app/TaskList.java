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

    public void printTask(List<Task> tasks) {
        for (int i=0; i< tasks.size(); i++){
            System.out.println(i + 1 + "." + tasks.get(i));
        }
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

    public List<Task> findMatchTasks() {
        List<Task> matchedTasks = new ArrayList<>();
        String keyword = parsedUserInputs.get("NameOrIndex").toLowerCase();

        for (Task task: taskList) {
            if (task.getTaskName().toLowerCase().contains(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * find out a list of tasks due before a specified date.
     * @return list of tasks
     */
    public List<Task> getTasksReminder() {
        List<Task> criticalTasks = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime remindDate = now.plusDays(Integer.parseInt(parsedUserInputs.get("NameOrIndex")));

        for (Task task: taskList) {
            if (task.getTime() != null) {
                LocalDateTime taskDate = Parser.parseDateTime(task.getTime());
                if (taskDate.isAfter(now) && taskDate.isBefore(remindDate)) {
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
