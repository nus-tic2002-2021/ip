package duke.storage;

import duke.exceptions.InvalidStorageInput;
import duke.task.Task;
import duke.task.TaskFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDecoder {

    public static List<Task> decodeTasks(List<String> originalTasks) {
        List<Task> tasks = new ArrayList<>();

        for (String task: originalTasks) {
            try {
                Map<String, String> parsedInput = decodeIndividualTask(task);
                if (!parsedInput.isEmpty()) {
                    Task newTask = TaskFactory.creatTask(parsedInput);
                    setTaskStatus(parsedInput.get("TaskStatus"), newTask);
                    tasks.add(newTask);
                }
            } catch (InvalidStorageInput e) {
                System.out.println("Error occurred when decoding task");
            }
        }
        return tasks;
    }

    private static void setTaskStatus(String status, Task newTask) {
        boolean taskStatus = !"0".equals(status);
        newTask.setDone(taskStatus);
    }

    private static Map<String, String> decodeIndividualTask(String task) throws InvalidStorageInput {
        Map<String, String> parsedInputs = new HashMap<>();
        String[] taskComponents = task.replace("|", ",").split(",");

        checkValidTaskComponents(taskComponents);
        parsedInputs.put("TaskType", taskComponents[0].trim());
        parsedInputs.put("TaskStatus", taskComponents[1].trim());
        parsedInputs.put("NameOrIndex", taskComponents[2].trim());
        int compulsoryComponents = 3;
        if (taskComponents.length > compulsoryComponents) {
            parsedInputs.put("Time", taskComponents[3].trim());
        }
        return parsedInputs;
    }

    private static void checkValidTaskComponents(String[] taskComponents) throws InvalidStorageInput {
        checkValidLength(taskComponents);
        checkAndAddValidTaskType(taskComponents[0].trim());
        checkValidTaskStatus(taskComponents[1].trim());
        checkValidTaskName(taskComponents[2].trim());
    }

    private static void checkValidLength(String[] taskComponents) throws InvalidStorageInput {
        if (taskComponents.length <3) {
            throw new InvalidStorageInput("Incomplete task components, must have type, description and status");
        }
    }


    private static void checkAndAddValidTaskType(String taskType) throws InvalidStorageInput {
        for (TaskFactory.validTaskTypes type : TaskFactory.validTaskTypes.values()) {
            if (type.name().equals(taskType)) {
                return;
            }
        }
        throw new InvalidStorageInput("Wrong type of task in the file");
    }

    private static void checkValidTaskName(String taskName) throws InvalidStorageInput {
        if (taskName == null || "".equals(taskName)) {
            throw new InvalidStorageInput("Missing task description");
        }
    }

    private static void checkValidTaskStatus(String taskStatus) throws InvalidStorageInput {
        if (!"01".contains(taskStatus)) {
            throw new InvalidStorageInput("wrong task status, must be 0 or 1");
        }
    }
}
