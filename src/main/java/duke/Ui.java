package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    public void showLine(){
        System.out.println("    -----------------------------------------------");
    }

    public String readReq(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void showReq(String req){
        System.out.println(req + "\n");
        showLine();
    }

    public void showAdd(TaskList tasks, Task task){
        System.out.println("     Got it. I've added this task:");
        System.out.print("       ");
        task.showTask();
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println();
        showLine();
    }

    public void showDelete(TaskList tasks, Task task){
        System.out.println("     Noted! I've removed this task:");
        System.out.print("       ");
        task.showTask();
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println();
        showLine();
    }

    public void showDone(Task task){
        System.out.println("     Nice! I've marked this task as done:");
        System.out.print("       ");
        task.showTask();
        System.out.println();
        showLine();
    }

    public void showList(TaskList tasks){
        System.out.println("     Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.print("     " + (i + 1) + ".");
            tasks.taskList.get(i).showTask();
        }
        System.out.println();
        showLine();
    }

    public void showExit(){
        System.out.println("     Bye.Hope to see you again soon!\n");
        showLine();
    }

    public void showFind(TaskList tasks){
        System.out.println("     Here are the matching tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.print("     " + (i + 1) + ".");
            tasks.taskList.get(i).showTask();
        }
        System.out.println();
        showLine();
    }

    public void showLoadException(){
        System.out.println("No data can be loaded! ");
        System.out.println();
    }

    public void showStoreException(){
        System.out.println("There may be a problem with the storage! ");
        System.out.println();
    }

    public void showException(Exception e, String req){
        showReq(req);
        System.out.println("     " + e.getMessage());
        System.out.println();
        showLine();
    }
}
