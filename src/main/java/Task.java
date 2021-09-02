
public class Task {

    protected int id = 0;
    protected String name = "";
    protected boolean isToDo = false;
    protected boolean isDeadline = false;
    protected boolean isDone = false;
    protected static int totalCount = 0;

    public Task(String cmd) {
        id = totalCount + 1;
        totalCount++;

        if (cmd.startsWith("todo")) {
            name = cmd.substring(5);
        }

        else if (cmd.startsWith("deadline")) {
            name = cmd.substring(9);
        }

        else name = cmd;

    }

    public int getID() {
        return id;
    }

    public String getType() {
        if (isToDo == true) return "T";
        else if (isDeadline == true) return "D";
        else return " ";
    }

    public String getDone() {
        return (isDone ? "X" : " ");
    }

    public String getTotalCount() {
        if (totalCount == 1) return totalCount + " task";
        else return totalCount + " tasks";
    }

    public String getTask() {
        return "[" + getType() + "]" + "[" + getDone() + "] " + name;
    }

    public String listTask() {
        return id + ". [" + getType() + "]" + "[" + getDone() + "] " + name;
    }

    public void setToDo() {
        isToDo = true;
    }

    public void setDeadline() {
        isDeadline = true;
    }

    public void setDone() {
        isDone = true;
    }

}
