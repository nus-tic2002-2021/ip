package src.java.task;

/**
 * Create 
 *
 * @author  Kang Teng
 * @version 8.0
 * @since   2021-09-01
 */

public enum TaskType {
    TODOS, DEADLINE, EVENT;

    public static String taskTypeToString(TaskType taskType) {
        String s = "";
        switch (taskType) {
            case TODOS:
                s = "T";
                break;
            case DEADLINE:
                s = "D";
                break;
            case EVENT:
                s = "E";
                break;
        }
        return s;
    }
}
