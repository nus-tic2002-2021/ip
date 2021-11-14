package task;

import app.Storage;

import java.util.ArrayList;

import static task.TaskCommands.printCount;

public class List extends Task{
    private static ArrayList<Task> List = new ArrayList<>();

    public static void setList(ArrayList<Task> Other) {
        List = Other;
    }

    public static ArrayList<Task> getList(){
        return List;
    }

    public static void addList(Task t){
        List.add(t);
        System.out.println("\tGot it. I've added this task:");
        printCount();
        Storage.saveList();
    }
    public static int getSize(){
        return List.size();
    }
}

