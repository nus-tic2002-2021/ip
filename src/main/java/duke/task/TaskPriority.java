package duke.task;

public enum TaskPriority {
    HIGH, MEDIUM, LOW;

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

    private static int convertPriorityToInt(TaskPriority task) {
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

}
