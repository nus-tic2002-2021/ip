package duke.storage;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Encode the tasklist object to a list of strings in order to be saved in a txt file
 */
public class FileEncoder {

    public static List<String> encodeTasks(List<Task> tasksToSave) {
        List<String> toSave = new ArrayList<>();
        for (Task task: tasksToSave) {
            toSave.add(encodeIndividual(task));
        }
        return toSave;
    }

    private static String encodeIndividual(Task task) {
        StringBuilder taskString = new StringBuilder();
        taskString.append(task.getTaskType()).append(" | ");
        taskString.append(task.isDone() ? 1 : 0).append(" | ");
        taskString.append(task.getTaskName());
        if (task.getTime() != null) {
            taskString.append(" | ").append(task.getTime());
        }
        if (task.getTag() != null) {
            taskString.append(" | ").append(task.getTag());
        }
        return taskString.toString();
    }
}
