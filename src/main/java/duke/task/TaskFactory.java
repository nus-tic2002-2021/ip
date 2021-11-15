package duke.task;

import java.util.Map;

public class TaskFactory {

    public enum validTaskTypes {
        TODO, DEADLINE, EVENT
    }

    public static Task creatTask(Map<String, String> inputs) {

        validTaskTypes taskType = validTaskTypes.valueOf(inputs.get("TaskType"));
        switch (taskType) {
            case TODO:
                return new Todo(inputs.get("NameOrIndex"));
            case DEADLINE:
                return new Deadline(inputs.get("NameOrIndex"), inputs.get("Time"));
            case EVENT:
                return new Event(inputs.get("NameOrIndex"), inputs.get("Time"));
            default:
                System.out.println("Wrong type of task");
                return null;
        }
    }
}
