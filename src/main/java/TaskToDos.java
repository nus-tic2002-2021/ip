public class TaskToDos extends Task {

    public TaskToDos(String taskDetail) {
        super(taskDetail);
        this.isDone = false;
        this.taskType = TaskType.TODOS;
    }
}
