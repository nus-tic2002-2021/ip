package com.tic2002.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static com.tic2002.app.DateTime.*;

/**
 * TaskCommands inherits from Task
 */
public class TaskCommands extends Task {
    /**
     * Creates a Temporary ArrayList
     */
    private ArrayList<Task> Temp = new ArrayList<>();

    /**
     * Method to add to Todo
     * @param description takes String description and adds to Todo
     */
    public static void addTodo(String description) {
        List.addList(new Todo(description));
        }
    /**
     * Method to add to Event
     * @param description takes String description and adds to Event
     * @param at Takes String at and adds to Event (timing)
     */
    public static void addEvent(String description, String at) {
        List.addList(new Event(description, at));
        }
    /**
     * Method to add to Deadline
     * @param description takes description and adds to Deadline
     * @param byDate takes LocalDate date and adds to Deadline (date of deadline)
     * @param byTime takes LocalDate time and adds to Deadline (time of deadline)
     */
    public static void addDeadline(String description, LocalDate byDate, LocalTime byTime) {
        List.addList(new Deadline(description, byDate, byTime));
    }

    /**
     * prints out number of tasks in list
     */
    public static void printCount() {
        System.out.println("\tNow you have " + List.getSize() + " tasks in the list.");
    }

    /**
     * Method prints out list
     * if list size is empty, print out error
     * return
     *
     * print out tasks in list line
     * print out tasks using a loop, number the list items
     */
    public static void printList() {
        ArrayList<Task> Temp = List.getList();
        if(Temp.size() == 0){
            System.out.println("\tThere are no items in your list");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<Temp.size(); i++) {
            if(Temp.get(i).getStatusIcon().equals(" ") && Temp.get(i).getType().equals("D")) {
                if (isWithinThreeDays(Temp.get(i).date)) {
                    Temp.get(i).priority(3);
                }
                if(isOverDue(Temp.get(i).date)){
                    Temp.get(i).priority(4);
                }
            }
            System.out.println("\t" + (i+1) + ". " + Temp.get(i).printTask());
        }
    }

    /**
     * Method to check if date matches
     * @param checkDate takes in user input of date
     *                  creates Temp ArrayList and Placeholder ArrayList
     *                  creates LocalDate listDate as dates in our listing
     *
     *                  if Temp is empty, print error
     *
     * Using for loop, check each item in our listing
     * if the Task in the list matches "D" (Deadline)
     * Get date from that Task
     * Compare date from listing listDate with user input date checkDate
     * if dates are equal, add to Placeholder ArrayList
     * If no items were copied, no dates match, print error
     *                  Print out Tasks that has dates matched
     */
    public static void printDateList(LocalDate checkDate){
        ArrayList<Task> Temp = List.getList();
        ArrayList<Task> Placeholder = new ArrayList<>();
        LocalDate listDate;
        if(Temp.size() == 0){
            System.out.println("\tThere are no items in your list");
            return;
        }
        for (Task task : Temp) {
            if (task.getType().equals("D")) {
                listDate = task.getDate();
                int result = checkDate.compareTo(listDate);
                if (result == 0) {
                    Placeholder.add(task);
                }
            }
        }
        if(Placeholder.size() == 0) {
            System.out.println("\tThere are no items in your list that matches " +
                    toNewDateFormat(checkDate));
            return;
        }

        System.out.println("\tThere are " + Placeholder.size() +
                " tasks in your list that matches " +
                toNewDateFormat(checkDate) + ": " );

        for (int i=0; i<Placeholder.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + Placeholder.get(i).printTask());
        }
    }

    /**
     * Method to set Task as done
     * @param ref gets index from user
     *            Retrieve Task from List and set as done
     *            Print out line to confirm done
     *            Print out Task that is set as done
     *            Update main ArrayList List in List class
     */
    public static void setDone(int ref) {
        ArrayList<Task> Temp = List.getList();
        Temp.get(ref).setDone();
        Temp.get(ref).priority(0);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + Temp.get(ref).printTask());
        List.setList(Temp);
    }

    /**
     * Method to set Task as done
     * @param ref gets index from user
     *            Retrieve Task from List and set as not done
     *            Print out line to set as not done
     *            Print out Task that is set as not done
     *            Update main ArrayList List in List class
     */
    public static void setUnDone(int ref) {
        ArrayList<Task> Temp = List.getList();
        Temp.get(ref).setUnDone();
        System.out.println("\tOk! I've marked this task as not done:");
        System.out.println("\t\t" + Temp.get(ref).printTask());
        List.setList(Temp);
    }

    /**
     * Method to delete Task
     * @param ref gets index from user
     *            Print delete statement
     *            Copy ArrayList List from List class to ArrayList Temp
     *            Print Task to be removed from Temp ArrayList
     *            Delete Task from Temp
     *            Update main ArrayList List in List class
     */
    public static void deleteTask(int ref) {
        System.out.println("\tNoted. I've removed this task: ");
        ArrayList<Task> Temp = List.getList();
        System.out.println("\t\t" + Temp.get(ref).printTask());
        Temp.remove(ref);
        List.setList(Temp);
    }

    /**
     * To update a priority in a task
     * @param ref index of task location in list
     * @param priority set priority value
     */
    public static void updatePriority(int ref, int priority) {
        ArrayList<Task> Temp = List.getList();
        Temp.get(ref).priority = priority;
        if(priority == 0) {
            System.out.println("\tOk! I've removed the priority");
        }
        else {
            System.out.println("\tOk! I've updated the priority");
        }
        System.out.println("\t\t" + Temp.get(ref).printTask());
        List.setList(Temp);
    }
}

