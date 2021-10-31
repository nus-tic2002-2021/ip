//import java.util.Arrays;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int task_count =0;
    protected String type;
    protected String datetime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //description.Getter
    public  String getDescription() {return description;}

    //isDone.Setter
    public void setDone() {isDone = true;}

    //isDone.Getter
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getType(){return type;}

    public  String getDatetime(){
        return datetime;
    }

    public static void tasks (String text, Task[] tasks){
        String[] command = text.split(" ", 2);
        switch (command[0]) {
            case "event":
                if (command.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. Please re-enter:");
                } else if (!text.contains("/at")) {
                    throw new DukeException("☹ OOPS!!! The date of a event cannot be empty. Please re-enter:");
                }
                tasks[task_count] = new Event(text);
                Event.print(tasks);
                task_count++;
                break;
            case "todo":
                if (command.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. Please re-enter:");
                }
                tasks[task_count] = new Todo(text);
                Todo.print(tasks);
                task_count++;
                break;
            case "deadline":
                if (command.length < 2) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. Please re-enter:");
                } else if (!text.contains("/by")) {
                    throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty. Please re-enter:");
                }
                tasks[task_count] = new Deadline(text);
                Deadline.print(tasks);
                task_count++;
                break;
            case "list":
                if (task_count ==0) {
                    System.out.println("\tList is empty.");
                    System.out.println("=======================================================");
                    break;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < task_count; i++) {
                    System.out.println("\t"+ (i+1) + "." + tasks[i].printList());
                }
                System.out.println("=======================================================");
                break;
            case "done":
                int whichTask = Integer.parseInt(command[1]);
                if (whichTask > task_count) {
                    throw new DukeException("☹ There is no such task.");
                }
                String[] word = text.split(" ");
                int i = Integer.parseInt(word[1]) - 1;
                tasks[i].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                System.out.println("=======================================================");
                break;
            default:
                throw new DukeException("\t☹ Sorry! I don't know what that means :-(\n\tPlease re-enter:");
        }

        //return an array of first n elements
        //return Arrays.copyOf(tasks, task_count);
    }

    public String printList (){return "";}

}