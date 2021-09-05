import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class Duke {
    private final static String DETECT_END = "bye";
    private final static String DETECT_LIST = "list";
    private final static String STMT_END = "Bye. Hope to see you again soon!";
    private final static String STMT_START = "Hello! I'm Duke\nWhat can I do for you?";

    public static void list(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
    }

    public static void add(ArrayList<Task> list, Task task) {
        list.add(task);
        System.out.println("added: " + task);
    }

    public static void main(String[] args) {

        System.out.println(STMT_START);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.toLowerCase(Locale.ROOT).equals(DETECT_END)) {
            if (input.toLowerCase(Locale.ROOT).equals(DETECT_LIST)) {
                list(list);
            } else {
                add(list, new Task(input));
            }
            input = in.nextLine();
        }
        System.out.println(STMT_END);
    }
}
