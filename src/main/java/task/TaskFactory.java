package task;

import java.util.Map;

public class TaskFactory {

    public enum validTaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static Task creatTask(Map<String, String> inputs) {

        Task newTask = null;

        validTaskTypes taskType = validTaskTypes.valueOf(inputs.get("TaskType"));
        switch (taskType) {
            case TODO:
                newTask = new Todo(inputs.get("NameOrIndex"));
                break;
            case DEADLINE:
                newTask = new Deadline(inputs.get("NameOrIndex"), inputs.get("Time"));
                break;
            case EVENT:
                newTask = new Event(inputs.get("NameOrIndex"), inputs.get("Time"));
                break;
            default:
                System.out.println("Wrong type of task");
                break;
        }
        return newTask;
    }
}
