package src.java.task;

/**
 * Create an instance of todo class
 *
 * @author  Kang Teng
 * @version 8.0
 * @since   2021-09-01
 */

public class TaskToDos extends Task {

    public TaskToDos(String taskDetail) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.TODOS;
    }
}
