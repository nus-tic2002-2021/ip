import java.util.Scanner;

public class Duke {
    public static void startDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Hello command
        System.out.println("Hello, I'm Duke\n" + "What tasks can I serve you?");
    }

    public static void endDuke() {
        System.out.println("\tBye! Thanks for visiting The Duke!");
        System.exit(0);
    }

    public static void newLine(){
        System.out.println("________________________________________________");
    }

    public static void main(String[] args) {
        startDuke();

        //read line
        String line;
        String placeholder;
        String description;
        Scanner in = new Scanner(System.in);
        List tasks = new List();
        newLine();
        //to set what to do with the line in
        while(true){
            line = in.nextLine();
            newLine();
            String command = line.split(" ")[0];

            //use lineCheck for case statements for cleaner code
            switch (command) {
                case "bye":
                    endDuke();

                case "done":
                    System.out.println("\tNice! I've marked this task as done:");
                    int ref = Integer.parseInt(line.split(" ")[1]);
                    tasks.setDone(ref - 1);
                    newLine();
                    break;

                case "deadline":
                    placeholder = line.replaceFirst(command + " ","");
                    description = placeholder.split(" /")[0];
                    String by = placeholder.split(" /by ")[1];
                    tasks.addList(new Deadline(description, by));
                    tasks.printCount();
                    newLine();
                    break;

                case "todo":
                    description = line.replaceFirst(command + " ","");
                    tasks.addList(new Todo(description));
                    tasks.printCount();
                    newLine();
                    break;

                case "event":
                    placeholder = line.replaceFirst(command + " ","");
                    description = placeholder.split(" /")[0];
                    String at = placeholder.split(" /at ")[1];
                    tasks.addList(new Event(description, at));
                    tasks.printCount();
                    newLine();
                    break;

                case "list":
                    tasks.printList();
                    newLine();
                    break;
                }
            }
        }
    }

