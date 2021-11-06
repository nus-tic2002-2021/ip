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
        //noinspection InfiniteLoopStatement
        while(true){
            line = in.nextLine();
            newLine();
            String command = line.split(" ")[0];

            //use lineCheck for case statements for cleaner code
                switch (command) {
                    case "bye":
                        endDuke();

                    case "done":
                        if(line.equals ("done")) {
                            System.out.println("Please enter choice for list");
                            break;
                        }
                        try{
                            int ref = Integer.parseInt(line.split(" ")[1]);
                            tasks.setDone(ref - 1);
                            System.out.println("\tNice! I've marked this task as done:");
                        } catch (NullPointerException e) {
                                System.out.println("Please choose from list only");
                                break;
                        }
                        newLine();
                        break;

                    case "deadline":
                        try{
                        placeholder = line.replaceFirst(command + " ", "");
                        description = placeholder.split(" /")[0];
                        String by = placeholder.split(" /by ")[1];
                        tasks.addList(new Deadline(description, by));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Have to include /by");
                            break;
                        }
                        tasks.printCount();
                        newLine();
                        break;

                    case "todo":
                        description = line.replaceFirst(command + " ", "");
                        if(description.equals(command) && line.length() == 4){
                            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                            break;
                        }
                        tasks.addList(new Todo(description));
                        tasks.printCount();
                        newLine();
                        break;

                    case "event":
                        placeholder = line.replaceFirst(command + " ", "");
                        if(placeholder.equals(command) && line.length() == 5){
                            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                            break;
                        }
                        description = placeholder.split(" /")[0];
                        try {
                            String at = placeholder.split(" /at ")[1];
                            tasks.addList(new Event(description, at));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Have to include /at");
                            break;
                        }
                        tasks.printCount();
                        newLine();
                        break;

                    case "list":
                        tasks.printList();
                        newLine();
                        break;

                    case "delete":
                        if(line.equals ("delete")) {
                            System.out.println("Please enter choice for list");
                            break;
                        }
                        try{
                            int ref = Integer.parseInt(line.split(" ")[1]);
                            tasks.deleteTask(ref - 1);
                        } catch (NullPointerException e) {
                            System.out.println("\tPlease choose from list only");
                            break;
                        }
                        tasks.printCount();
                        newLine();
                        break;

                    case "help":
                        System.out.println("DUKE helps you organise your tasks efficiently, please enter as follows:" +
                                "\n\tevent [description] /at [time]\n\t" +
                                "todo [description]\n\t" +
                                "deadline [description] /by [time]\n\t" +
                                "done [number] - to mark task as completed\n\t" +
                                "list - to show all tasks\n\t" +
                                "delete [number] - to delete a task\n\t" +
                                "bye - to end DUKE");
                        newLine();
                        break;

                    default:
                        System.out.println("Please enter a valid input");
                }
            }
        }
    }


