import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> storedResponses = new ArrayList<String>();

    public static void getList(){
        for (int i=1; i<storedResponses.size(); i++){
            System.out.format("%d: " + storedResponses.get(i-1) + "\n" ,i);
        }
    }

    public static void response(String sentence){
        System.out.println("____________________________________________________________");
        switch (sentence) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            case "hi":
                System.out.println("Hello");
                break;
            case "list":
                getList();
                break;
            default:
                System.out.println(sentence);
                break;
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String line = "";
        System.out.println("Hello! I'm Duke\nWhat can I do for you today?");
        do {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            storedResponses.add(line);
            response(line);
        } while (!line.equals("bye"));
    }
}
