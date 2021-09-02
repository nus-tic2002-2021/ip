import java.util.*;

public class Duke {
    public static void main(String[] args) {
        boolean exit = false;
//        ArrayList<String> tasks = new ArrayList<String>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("________________________________");
        System.out.println("Hello, I'm Duke!" );
        System.out.println("What can I do for you?");
        System.out.println("________________________________");

        while (!exit){
            String inputStr = sc.nextLine();

            System.out.println("________________________________");
            if (inputStr.equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit = true;
            }
            else if (inputStr.equalsIgnoreCase("list")){
                if(tasks.size() == 0){
                    System.out.println("There's no task now :D");
                }
                else {
                    for (Task task : tasks) {
                        System.out.println(task.toString());
                    }
                }
            }
            else if (inputStr.startsWith("done")){
                String[] words = inputStr.split(" ");
                int doneItemId = Integer.parseInt(words[1]);

                Task doneTask = tasks.get(doneItemId - 1);
                doneTask.markAsDone();

                System.out.println("Amazing! The task is marked as done now:");
                System.out.println(doneTask.toString());
            }
            else{
                Task newTask = new Task(inputStr);
                tasks.add(newTask);
                System.out.println("added: " + inputStr);
            }
            System.out.println("________________________________");
        }
    }
}
