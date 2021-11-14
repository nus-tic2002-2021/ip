package task;

import app.Storage;
import task.List;

import java.util.ArrayList;

public class TaskCommands extends Task {

        private ArrayList<Task> Temp = new ArrayList<>();

        public static void addTodo(String description){
            List.addList(new Todo(description));
        }
        public static void addEvent(String description, String at){
            List.addList(new Event(description, at));
        }

        public static void addDeadline(String description, String by){
            List.addList(new Deadline(description, by));
        }

        public static void printCount(){
            System.out.println("\tNow you have " + List.getSize() + " tasks in the list.");
        }
        public static void printList() {
            ArrayList<Task> Temp = List.getList();
            if(Temp.size() == 0){
                System.out.println("\tThere are no items in your list");
                return;
            }
            System.out.println("\tHere are the tasks in your list:");
            for (int i=0; i<Temp.size(); i++) {
                System.out.println("\t" + (i+1) + ". " + Temp.get(i).printTask());
            }
        }

        public static void printTask(int ref) {
            ArrayList<Task> Temp = List.getList();
            System.out.println("\t\t" + Temp.get(ref));
        }

        public static void setDone(int ref) {
            ArrayList<Task> Temp = List.getList();
            Temp.get(ref).setDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + Temp.get(ref).printTask());
            List.setList(Temp);
        }

        public static void deleteTask(int ref){
            System.out.println("\tNoted. I've removed this task: ");
            ArrayList<Task> Temp = List.getList();
            System.out.println("\t\t" + Temp.get(ref).printTask());
            Temp.remove(ref);
            List.setList(Temp);
        }

    }

