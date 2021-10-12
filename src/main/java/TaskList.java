import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTasks(Task newTask) {
        tasks.add(newTask);
    }

    public Task deleteTasks(int num) {
        int index = num - 1;
        Task removeTask = tasks.remove(index);
        return removeTask;
    }


    public List<Task> getList() {
        return tasks;
    }
    public String getAllTasks() {
        String result = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            result += "\n";
            Task currentTask = tasks.get(i);
            int taskNumber = i + 1;
            result += taskNumber + ". " + currentTask;
        }
        return result;
    }

}
