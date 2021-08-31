import java.util.Scanner;

public class Duke {

    static Scanner in = new Scanner(System.in);
    static String line = "____________________________________________________________\n";
    static String[] list = new String[100];
    static int listCount = 0;

    public static void StartDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo + " chatbot! \n");
    }

    public static void Greet() {
        String start = "Hello, I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(line + start + line);
    }

    public static void Echo() {
        Boolean stop = false;
        while (stop == false) {
            String word = in.next();
            if (word.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again!\n" + line);
                stop = true;
            } else {
                System.out.println(line + word + "\n" + line);
            }
        }
    }

    public static void Add() {
        Boolean stop = false;
        while (stop == false) {
            String input = in.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                listCount = 0;
                stop = true;
            } else if (input.equals("list")){
                PrintList();
            } else {
                list[listCount] = input;
                listCount++;
                System.out.println(line + "added: " + input + "\n" + line);
            }
        }
    }

    public static void PrintList() {
        System.out.println(line);
        for (int i = 0; i < listCount; i++) {
            System.out.println(i+1 + ". " + list[i]);
        }
        System.out.println(line);
    }

    public static void Level1() {
        Greet();
        Echo();
    }

    public static void main(String[] args) {
        StartDuke();
        Level1();
    }
}
