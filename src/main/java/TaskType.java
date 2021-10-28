package src.main.java;

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
