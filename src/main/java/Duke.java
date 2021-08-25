import java.util.*;

public class Duke {
    public static void main(String[] args) {
        boolean exit = false;
        ArrayList<String> tasks = new ArrayList<String>();
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

            if (inputStr.equalsIgnoreCase("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                exit = true;
            }
            else if (inputStr.equalsIgnoreCase("list")){
                if(tasks.size() == 0){
                    System.out.println("There's no task now :D");
                }
                else {
                    int count = 1;
                    for (String task : tasks) {
                        System.out.println(count + ". " + task);
                        count += 1;
                    }
                }
            }
            else{
                tasks.add(inputStr);
                System.out.println("added: " + inputStr);
            }
        }
    }
}
