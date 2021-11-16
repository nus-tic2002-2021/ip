package duke.storage;

import duke.app.Parser;
import duke.exceptions.InvalidStorageInput;
import duke.task.Task;
import duke.task.TaskFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Decode the text from file into a list of existing before running the app.
 */
public class FileDecoder {

    /**decode the list of task from String to task object
     * @param originalTasks, list of Strings read from the txt file
     * @return list of tasks, list of task objects
     */
    public static List<Task> decodeTasks(List<String> originalTasks) {
        List<Task> tasks = new ArrayList<>();

        for (String task: originalTasks) {
            try {
                Map<String, String> parsedInput = decodeIndividualTask(task);
                if (!parsedInput.isEmpty()) {
                    Task newTask = getNewTaskWithStatus(parsedInput);
                    tasks.add(newTask);
                }
            } catch (InvalidStorageInput e) {
                System.out.println("Error occurred when decoding task\n"
                        + "reason:" + e.getMessage());
            }
        }
        return tasks;
    }

    private static Task getNewTaskWithStatus(Map<String, String> parsedInput) {
        Task newTask = TaskFactory.creatTask(parsedInput);
        setTaskStatus(parsedInput.get("TaskStatus"), newTask);
        return newTask;
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

        getDatetime(parsedInputs, taskComponents);
        return parsedInputs;
    }

    private static void getDatetime(Map<String, String> parsedInputs, String[] taskComponents) throws InvalidStorageInput {
        if ("EVENT".equals(parsedInputs.get("TaskType")) || "DEADLINE".equals(parsedInputs.get("TaskType"))) {
            String time = taskComponents[3].trim();
            checkValidTime(time);
            parsedInputs.put("Time", time);
        }
    }

    private static void checkValidTime(String time) throws InvalidStorageInput {
        try {
            Parser.parseDateTime(time);
        } catch (Exception e) {
            throw new InvalidStorageInput("DateTime format incorrect");
        }
    }

    private static void checkValidTaskComponents(String[] taskComponents) throws InvalidStorageInput {
        checkValidLength(taskComponents);
        checkAndAddValidTaskType(taskComponents[0].trim());
        checkValidTaskStatus(taskComponents[1].trim());
        checkValidTaskName(taskComponents[2].trim());
    }

    private static void checkValidLength(String[] taskComponents) throws InvalidStorageInput {
        int compulsoryComponents = 3;
        if (taskComponents.length < compulsoryComponents) {
            throw new InvalidStorageInput("Incomplete task components, must have type, description and status");
        }

        if ("EVENT".equals(taskComponents[0].trim()) || "DEADLINE".equals(taskComponents[0].trim())){
            if (taskComponents.length < compulsoryComponents + 1) {
                throw new InvalidStorageInput("Incomplete task components, must have time for event and deadline");
            }
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
