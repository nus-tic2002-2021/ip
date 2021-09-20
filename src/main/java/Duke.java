import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    protected static String[] validTypesTask;
    public static Task[] tasks;

    public static void main(String[] args) {
        printGreeting();
        //take user input
        Scanner in = new Scanner(System.in);
        validTypesTask = new String[]{"todo", "deadline", "event"};
        //initiate an Array to store tasks, assume numTask <= 100
        tasks = new Task[100];
        Task.totalTasks = 0;
        while (true) {
            String command = in.nextLine();
            if (processInput(command)) break;
        }
        System.out.println("Bye. Have a nice day!");
    }

    private static String[] checkUserInput(String command) throws InvalidUserInputException {
        /*TODO: improve this method
        currently the list of errors handled by this method:
        Type 1. task description (for all the types of task):
            a. empty description
            b. invalid description, i.e. contains only numbers
        Type 2. task time (for deadline and event only)
            a. wrong preposition, missing "by"/"at"
            b. missing "/" for time indication
        To add more...
         */
        String taskType = command.split(" ")[0].toLowerCase();
        String taskName;
        String time = null;
        //handle task type
        if (!Arrays.asList(validTypesTask).contains(taskType)) {
            throw new InvalidUserInputException("Please enter a valid type of task");
        }
        if (taskType.equals("deadline") || taskType.equals("event")){
            try {
                taskName = command.split(" ", 2)[1].split("/")[0].trim();
                //handle task time
                String timeFormat = command.split("/")[1].substring(0, 2);
                time = command.split("/")[1].substring(2);
                //TODO: decide whether empty time is allowed
                if (!timeFormat.equalsIgnoreCase("by") && taskType.equals("deadline")) {
                    throw new InvalidUserInputException("please specify a deadline using keyword \"by\"");
                } else if (!timeFormat.equalsIgnoreCase("at") && taskType.equals("event")) {
                    throw new InvalidUserInputException("please specify a time for the event using keyword \"at\"");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidUserInputException("Please use \"/by\" or \"/at\" to specify the time");
            }
        } else {
            try {
                taskName = command.split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidUserInputException("empty task, please include the task description");
            }

        }
        //handle task description
        if (taskName.matches("[0-9]+")) {
            throw new InvalidUserInputException("please enter a valid task description that contains alphabets");
        } else if (taskName.equals("")){
            throw new InvalidUserInputException("empty task, please include the task description");
        }

        //TODO: add more invalid cases
        return new String[]{taskType, taskName, time};
    }

    private static boolean processInput(String command) {

        if (command.equals("bye")) {
            return true;
        }
        //print out the list
        else if (command.equals("list")) {
            printTasks(Task.totalTasks);
        }
        //set status of a task to done
        else if (command.startsWith("done")) {
            changeTaskStatus(command);
        }
        //add task to the list
        else {
            //check whether the user input is valid
            try {
                //check if the type of task is valid
                String[] inputs = checkUserInput(command);
                addTask(inputs);
            } catch (InvalidUserInputException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static void printTasks(int numTask) {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i< numTask; i++){
            Task task = tasks[i];
            System.out.println(i+1+"."+task);
        }
    }

    private static void printGreeting() {
        //print greeting messages
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "Hey this is Blanc, welcome to your universe\n"
                + "I will provide everything you need:))\n";
        String instruction = "Please follow the instructions below\n"
                + "1. \"list\": print a list of tasks\n"
                + "2. \"done +number\" (e.g. done 2): set the task status to done\n"
                + "3. add task using \"todo\" \"deadline\" \"event\" keyword\n"
                + "4. \"bye\": end the program\n"
                +"Please type any command to start: \n";
        System.out.println(greeting);
        System.out.println(instruction);
    }

    private static void addTask(String[] inputs) {
        //check if it's a new task, if yes, add it into the list
        boolean redundant = false;
        Task newTask;
        if (inputs[0].equals("todo")){
            newTask = new Todo(inputs[1]);
        } else if (inputs[0].equals("deadline")){
            newTask = new Deadline(inputs[1], inputs[2]);
        } else {
            newTask = new Event(inputs[1], inputs[2]);
        }
        for (Task task : tasks){
            if (task != null && task.equals(newTask)){
                redundant = true;
                break;
            }
        }
        if (!redundant){
            tasks[Task.totalTasks] = newTask;
            Task.totalTasks++;
            System.out.println("Got it. I've added this task: \n" +
                    " " + newTask + "\nNow you have " + Task.totalTasks + " tasks in the list.");
        }
        else {
            System.out.println("The same task has been added previously, please enter another task");
        }
    }

    private static void changeTaskStatus(String command) {
        /*
        The list of errors handled by this method:
        a. list of task is empty
        b. index exceeds the number of tasks
         */
        int indexOfTask = Integer.parseInt(command.split(" ")[1]);
        //make sure the index is not out of the list
        try {
            Task taskDone = tasks[indexOfTask - 1];
            taskDone.setDone(true);
            System.out.println("Nice! I've marked this task as done: \n" + taskDone);
        } catch (NullPointerException e) {
            System.out.println("Oops you don't have that many tasks, please enter a number <= " + Task.totalTasks);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Oops there is no task 0, please start with task 1");
        }
    }
}

