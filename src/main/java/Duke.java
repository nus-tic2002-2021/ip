import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class Duke {
    private final static String DETECT_END = "bye";
    private final static String DETECT_LIST = "list";
    private final static String DETECT_DONE = "done";
    private final static String STMT_END = "Bye. Hope to see you again soon!";
    private final static String STMT_START = "Hello! I'm Duke\nWhat can I do for you?";
    private final static String STMT_DONE = "Nice! I've marked this task as done: ";

    public static void list(ArrayList<Todo> list) {
        list.forEach(l -> System.out.println(l.toString()));
    }

    public static void add(ArrayList<Todo> list, Todo todo) {
        list.add(todo);
        System.out.println("added: " + todo);
    }

    public static void markDone(ArrayList<Todo> list, Integer taskId) {
        list.stream().filter(t -> t.getId().equals(taskId)).forEach(t -> {
            System.out.println(STMT_DONE);
            t.setDone(true);
            System.out.println(t.toString());
        });
    }

    public static void main(String[] args) {
        System.out.println(STMT_START);
        Scanner in = new Scanner(System.in);
        ArrayList<Todo> list = new ArrayList<>();
        String input = in.nextLine();
        ArrayList<String> tokens = new ArrayList(Arrays.asList(input.split(" ")));
        String instruction = tokens.get(1);

        while (!instruction.toLowerCase(Locale.ROOT).equals(DETECT_END)) {
            if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_LIST)) {
                list(list);
            } else if (instruction.toLowerCase(Locale.ROOT).equals(DETECT_DONE) && tokens.size() >= 2) {
                markDone(list, Integer.parseInt((tokens.get(1))));
            } else {
                add(list, new Todo(input, list.size()));
            }

            input = in.nextLine();
            tokens = new ArrayList(Arrays.asList(input.split(" ")));
            instruction = tokens.get(0);
        }
        System.out.println(STMT_END);
    }
}
