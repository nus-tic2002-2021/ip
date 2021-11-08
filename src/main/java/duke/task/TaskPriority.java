package duke.task;

public enum TaskPriority {
    HIGH, MEDIUM, LOW, INVALID;

    /**
     * Compare two tasks and return true if taskA has higher priority than taskB
     *
     * @param taskA First Task to compare
     * @param taskB Second Task to compare
     * @return boolean true if taskA > taskB; false if otherwise
     */
    public static boolean isHigherPriority(TaskPriority taskA, TaskPriority taskB) {
        int taskAPriority = convertPriorityToInt(taskA);
        int taskBPriority = convertPriorityToInt(taskB);

        if (taskAPriority > taskBPriority) {
            return true;
        }
        return false;
    }

    /**
     * Convert Int value to Priority
     *
     * 1 -> HIGH
     * 2 -> MEDIUM
     * 3 -> LOW
     * any other number -> INVALID
     *
     * @param priority Int that indicates the priority
     * @return TaskPriority
     */
    public static TaskPriority convertIntToPriority(int priority) {
        switch (priority) {
        case 1:
            return HIGH;
        case 2:
            return MEDIUM;
        case 3:
            return LOW;
        default:
            return INVALID;
        }
    }

    /**
     * Convert String value to Priority
     *
     * "1" -> HIGH
     * "2" -> MEDIUM
     * "3" -> LOW
     * any other number -> INVALID
     *
     * @param priority String that indicates the priority
     * @return TaskPriority
     */
    public static TaskPriority convertStringToPriority(String priority) {
        switch (priority) {
        case "1":
            return HIGH;
        case "2":
            return MEDIUM;
        case "3":
            return LOW;
        default:
            return INVALID;
        }
    }

    /**
     * Convert TaskPriority to Int
     *
     * 1 <- HIGH
     * 2 <- MEDIUM
     * 3 <- LOW
     * 0 <- INVALID
     *
     * @param task TaskPriority that indicates the TaskPriority
     * @return int
     */
    public static int convertPriorityToInt(TaskPriority task) {
        switch (task) {
        case LOW:
            return 3;
        case MEDIUM:
            return 2;
        case HIGH:
            return 1;
        default:
            return 0;
        }
    }

    /**
     * Convert TaskPriority to String
     *
     * @param task TaskPriority that indicates the TaskPriority
     * @return String value of TaskPriority
     */
    public static String convertPriorityToString(TaskPriority task) {
        switch (task) {
        case LOW:
            return "LOW";
        case MEDIUM:
            return "MEDIUM";
        case HIGH:
            return "HIGH";
        default:
            return "INVALID";
        }
    }
}
