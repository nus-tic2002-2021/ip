package Duke.TaskList;

import Duke.ExceptionList.*;

import java.io.*;
import java.util.ArrayList;


public class TaskList {
    ArrayList<Task> taskLists = new ArrayList<Task>();
    public TaskList() {
    }
    public TaskList(String f) throws DukeException {
        //parse the String from the text file then use it to inside into the DB
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                    addFileTask(line);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found. Please, make sure you entered the correct path.");
        } catch (toDoNotFoundException | eventNotFoundException | deadlineNotFoundException |IOException e) {
            e.printStackTrace();
        }
    }

    public void addFileTask(String str) throws toDoNotFoundException, eventNotFoundException, deadlineNotFoundException {
        String[] input = str.split("\\|", 2);
        String s1 = input[0];
        String s2 = input[1];
        addTask(s1, s2, true);
    }

    /**
     * Adds task into the ArrayList of Tasks object. The cmd has been passed through validation thus unwanted commands
     * will not be written into the list.
     *
     * @param cmd the given command by the user
     * @param description details on the task listed by user
     * @param file checks if it is from a loaded file or an input string
     * */
    public void addTask(String cmd, String description, boolean file) throws toDoNotFoundException, eventNotFoundException, deadlineNotFoundException {
        if (cmd.equalsIgnoreCase("todo") || cmd.equalsIgnoreCase("T")) {
            if (description.isEmpty()) {
                throw new toDoNotFoundException("Sorry, there cannot be any empty todo task.");
            }
            if (file) {
                String[] input = description.split("\\|", 2);
                String s1 = input[0];
                String s2 = input[1];
                taskLists.add(new ToDo(s2));
                if (s1.equals("1")) {
                    taskLists.get(taskLists.size() - 1).setDone(true);
                }
            } else taskLists.add(new ToDo(description));

        } else if (cmd.equalsIgnoreCase("deadline") || cmd.equalsIgnoreCase("D")) {
            if (description.isEmpty()) {
                throw new deadlineNotFoundException("Sorry, there cannot be any empty deadline task.");
            }
            if (file) {
                String[] input = description.split("\\|", 3);
                String s1 = input[0];
                String s2 = input[1];
                String s3 = input[2];
                taskLists.add(new Deadline(s2, s3));
                if (s1.equals("1")) {
                    taskLists.get(taskLists.size() - 1).setDone(true);
                }
            } else {
                String[] input = description.split("/by ", 2);
                String s1 = input[0];
                String s2 = input[1];
                taskLists.add(new Deadline(s1, s2));
            }

        } else if (cmd.equalsIgnoreCase("event") || cmd.equalsIgnoreCase("E")) {
            if (description.isEmpty()) {
                throw new eventNotFoundException("Sorry, there cannot be any empty event task.");
            }
            if (file) {
                String[] input = description.split("\\|", 3);
                String s1 = input[0];
                String s2 = input[1];
                String s3 = input[2];
                taskLists.add(new Event(s2, s3));
                if (s1.equals("1")) {
                    taskLists.get(taskLists.size() - 1).setDone(true);
                }
            } else {
                String[] input = description.split("/at ", 2);
                String s1 = input[0];
                String s2 = input[1];
                taskLists.add(new Event(s1, s2));
            }
        }
    }

    public String getLastAddedTask() {
        return taskLists.get(taskLists.size() - 1).toString();
    }
    /**
     * User input will perform a deleting of the specific task from the ArrayList of Tasks object.
     *
     * @param x the given position of the list given by the user
     * */
    public void deleteTask(int x) {
        System.out.println(taskLists.get(x - 1).toString());
        taskLists.remove(x - 1);
    }

    public void printTaskList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println(taskLists.get(i).toString());
        }
    }

    public void setDoneStatus(int x) {
        taskLists.get(x - 1).setDone(true);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(taskLists.get(x - 1).toString());
    }

    public int getSize() {
        return taskLists.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskLists;
    }
}
