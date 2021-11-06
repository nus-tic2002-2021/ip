package duke.task;

import duke.DateFun;

import java.time.LocalDate;

public class Deadline extends Task{
    String by;
    LocalDate deadLineDate;

    /**
     * Deadline constructor
     * @param description
     * @param by deadline date.
     */

    public Deadline(String description, String by){
        super(description);
        this.by = by;
        deadLineDate = DateFun.stringToLocalDate(by);
        type = Types.DEADLINE;
    }

    /**
     *
     * @param isDone status
     * @param description
     * @param by
     */

    public Deadline(boolean isDone, String description, String by){
        super(description);
        this.by = by;
        this.isDone = isDone;
        deadLineDate = DateFun.stringToLocalDate(by);
        type = Types.DEADLINE;
    }

    /**
     * print deardline task
     */
    public void showTask(){
        String mark = isDone ? "X" : " ";
        System.out.println("[D][" + mark + "] " + description + " (by: " + DateFun.LocalDateToString(deadLineDate) + ")");
    }

    /**
     * convert deadline to string
     * @return a string
     */
    public String toStoreString(){
        int state = isDone ? 1 : 0;
        return 'D' + " | " + state + " | " + description + " | " + by + System.lineSeparator();
    }

}