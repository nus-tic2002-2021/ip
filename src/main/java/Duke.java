import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Duke {
    // TODO: Change these to enums
    private final static String DETECT_END = "bye";
    private final static String DETECT_LIST = "list";
    private final static String DETECT_DONE = "done";
    private final static String DETECT_ADD_TODO = "todo";
    private final static String DETECT_ADD_EVENT = "event";
    private final static String DETECT_ADD_DEADLINE = "deadline";
    private final static String DETECT_DELETE = "delete";

    private final static String STMT_END = "Bye. Hope to see you again soon!";
    private final static String STMT_START = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String STMT_DONE = "Nice! I've marked this task as done: ";
    private final static String STMT_DELETE = "Noted. I've removed this task: ";

    private final static String ERROR_PREFIX = "Oops I did not quite understand that.";

    public static void list(ArrayList<Task> list) throws Exception {
        list.forEach(l -> System.out.println(l.toString()));
    }

    public static void add(ArrayList<Task> list, Task todo) {
        list.add(todo);
        System.out.println("added: " + todo.toString());
    }

    public static void markDone(ArrayList<Task> list, Integer taskId) {
        list.stream().filter(t -> t.getId().equals(taskId)).forEach(t -> {
            System.out.println(STMT_DONE);
            Todo task = (Todo) t;
            task.setDone(true);
            System.out.println(t.toString());
        });
    }

    public static void markDelete(ArrayList<Task> list, Integer taskId) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() != null && list.get(i).getId().equals(taskId)) {
                System.out.println(STMT_DELETE);
                System.out.println(list.get(i).toString());
                list.remove(list.get(i));
                return;
            }
        }
    }

    public static void printErrorMessage(Message m) {
        switch (m) {
            case ERROR_UNRECOGNISED:
                System.out.println(ERROR_PREFIX + " Unrecognised operation.");
                break;
            case ERROR_INVALID:
                System.out.println(ERROR_PREFIX + " Invalid input.");
                break;
            case EMPTY_LIST:
                System.out.println(ERROR_PREFIX + " Empty list.");
                break;
            case UNKNOWN_OBJECT:
                System.out.println(ERROR_PREFIX + " Could not find resource.");
                break;
        }
    }


    public static void main(String[] args) {
        System.out.println(STMT_START);
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String input = in.nextLine();
        ArrayList<String> tokens = new ArrayList(Arrays.asList(input.split(" ")));
        String instruction = tokens.get(0);
        Integer id = 0;
        String taskInfo = tokens // TODO: Fix this (duh)
                .subList(1, tokens.size())
                .toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "")
                .trim();

        while (!instruction.toLowerCase(Locale.ROOT).equals(DETECT_END)) {
            if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_LIST)) {
                try {
                    list(list);
                } catch (Exception e) {
                    printErrorMessage(Message.EMPTY_LIST);
                }
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_DONE) && tokens.size() >= 2) {
                markDone(list, Integer.parseInt((tokens.get(1))));
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_DELETE) && tokens.size() >= 2) {
                try {
                    markDelete(list, Integer.parseInt((tokens.get(1))));
                } catch (Exception e) {
                    printErrorMessage(Message.UNKNOWN_OBJECT);
                }

            } else {
                Task task = null;
                switch (instruction) {
                    case DETECT_ADD_TODO:
                        task = new Todo(taskInfo, id);
                        break;
                    case DETECT_ADD_EVENT:
                        ArrayList<String> addInfo = new ArrayList(Arrays.asList(taskInfo.split("/at")));
                        task = new Event(addInfo.get(0), addInfo.get(1), id);
                        break;
                    case DETECT_ADD_DEADLINE:
                        ArrayList<String> deadlineInfo = new ArrayList(Arrays.asList(taskInfo.split("/by")));
                        task = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1), id);
                        break;
                }

                try {
                    add(list, task);
                } catch (Exception e) {
                    printErrorMessage(Message.ERROR_UNRECOGNISED);
                }
            }

            input = in.nextLine();
            System.out.println("input: " + input);
            tokens = new ArrayList(Arrays.asList(input.split(" ")));
            instruction = tokens.get(0);
            System.out.println("instruction: " + instruction);
            taskInfo = tokens
                    .subList(1, tokens.size())
                    .toString()
                    .replace(",", "")
                    .replace("[", "")
                    .replace("]", "")
                    .trim();
            System.out.println("task info:" + taskInfo);
            id = list.size();
        }
        System.out.println(STMT_END);
    }
}
