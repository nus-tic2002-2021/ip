import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String datetime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    //description.Getter
    public String getDescription() {return description;}

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

    public static void tasks (String text, ArrayList<Task> tasks){
        String[] command = Parser.commandToArray(text);
        int taskNumber;
        switch (command[0]) {
            case "event":
                Ui.validateEventCommand(command);
                tasks.add(new Event(text));
                Event.print(tasks);
                break;
            case "todo":
                Ui.validateTodoCommand(command);
                tasks.add(new Todo(text));
                Todo.print(tasks);
                break;
            case "deadline":
                Ui.validateDeadlineCommand(command);
                tasks.add(new Deadline(text));
                Deadline.print(tasks);
                break;
            case "list":
                if (tasks.size() == 0) {
                    System.out.println("\tList is empty. Please add new task.");
                    System.out.println("=======================================================");
                    break;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i=0; i < tasks.size(); i++) {
                    System.out.println("\t"+ (i+1) + "." + tasks.get(i).printList());
                }
                System.out.println("=======================================================");
                break;
            case "done":
                if (command.length < 2) {
                    throw new DukeException("☹ Please state task number.");
                }
                taskNumber  = Integer.parseInt(command[1]) - 1;
                if (taskNumber >= tasks.size()) {
                    throw new DukeException("☹ There is no such task.");
                }
                tasks.get(taskNumber).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("\t[" + tasks.get(taskNumber).getType() + "] "+ "[" +tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                System.out.println("You have total " + (tasks.size()) +" tasks in the list.");
                System.out.println("=======================================================");
                break;
            case "delete":
                if (command.length < 2) {
                    throw new DukeException("☹ Please state task number.");
                }
                taskNumber  = Integer.parseInt(command[1]) -1;
                if (taskNumber >= tasks.size()) {
                    throw new DukeException("☹ There is no such task.");
                }
                System.out.println("Noted. I've removed this task: ");
                System.out.println("\t[" + tasks.get(taskNumber).getType() + "] "+ "[" + tasks.get(taskNumber).getStatusIcon() + "] " + tasks.get(taskNumber).getDescription());
                tasks.remove(taskNumber);
                System.out.println("Now you have " + (tasks.size()) +" tasks in the list.");
                System.out.println("=======================================================");
                break;
            default:
                throw new DukeException("\t☹ Sorry! I don't know what that means :-(\n\tPlease re-enter:");
        }
    }

    public String printList (){return "";}

}