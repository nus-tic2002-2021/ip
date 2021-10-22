package task;

import java.util.ArrayList;
import error.*;


public class List {
    private static ArrayList<Task> taskArrayList;
    private static ArrayList<String> taskSave;
    public static Task recentDelete;

    public List(){
        taskArrayList = new ArrayList<>();
        taskSave = new ArrayList<>();
    }

    public List(ArrayList<String> loadFile){
        taskArrayList = new ArrayList<>();
        taskSave = new ArrayList<>();
        String[] taskArr;
        loadFile.listIterator();
        for(String task : loadFile){
            taskArr = task.split(" \\| ");
            String taskType, taskDone, taskDescription;
            Boolean isDone = false;
            taskType = taskArr[0];
            taskDone = taskArr[1];
            taskDescription = taskArr[2];
            if(taskDone.equals("1")){
                isDone = true;
            }

            switch (taskType){
                case "T":
                    taskArrayList.add(new Todo(taskDescription,isDone));
                    break;
                case "D":
                    taskArrayList.add(new Deadline(taskDescription,taskArr[3],isDone));
                    break;
                case "E":
                    taskArrayList.add(new Event(taskDescription,taskArr[3],isDone));
                    break;
            }
        }

    }

    public void addTodo(String inputMsg)throws DukeException {
        if(inputMsg.isEmpty()){
            throw new DukeException("TODO_DESCRIPTION_ERROR");
        }
        taskArrayList.add(new Todo(inputMsg));
        printAdd();
    }

    public void addDeadline(String inputMsg)throws DukeException {
        String [] input = inputMsg.split(" /by ");
        if(input.length < 2){
            throw new DukeException("DEADLINE_DESCRIPTION_ERROR");
        }
        if(input.length > 2){
            throw new DukeException("DEADLINE_LENGTH_ERROR");
        }
        taskArrayList.add(new Deadline(input[0],input[1]));
        printAdd();
    }
    public void addEvent(String inputMsg)throws DukeException {
        String [] input = inputMsg.split(" /at ");
        if(input.length < 2){
            throw new DukeException("EVENT_DESCRIPTION_ERROR");
        }
        if(input.length > 2){
            throw new DukeException("EVENT_LENGTH_ERROR");
        }
        taskArrayList.add(new Event(input[0],input[1]));
        printAdd();
    }

    public void taskDone(String counter)throws NotFoundException {
        Integer inputNumber = Integer.parseInt(counter);
        if (taskArrayList.size() < inputNumber){
            throw new NotFoundException();
        }// new exception already done
        taskArrayList.get(inputNumber - 1).setDone();
    }

    public void taskDelete(String counter)throws NotFoundException {
        Integer inputNumber = Integer.parseInt(counter);
        if (taskArrayList.size() < inputNumber){
            throw new NotFoundException();
        }
        recentDelete = taskArrayList.get(inputNumber - 1);
        taskArrayList.remove(inputNumber - 1);
        printDelete();
    }

    public void printList() {
        if(taskArrayList.size() == 0){ //0 items in list
            System.out.println("\ttask.List is empty!"); //throw empty list
        }
        else{
            int count = 1;
            for(Task task : taskArrayList){
                System.out.println("\t" + (count) + "." + task );
                count++;
            }
        }
    }

    public void saveList() {
        for(Task task : taskArrayList){
            taskSave.add(task.getSave());
        }
    }
    public ArrayList<String> getSave(){
        return taskSave;
    }

    public void printAdd() {
        System.out.println("\tGot it. Item successfully added to the list: ");
        taskArrayList.get(taskArrayList.size() - 1).print();
        System.out.println("\tNow you have " + (taskArrayList.size()) +" task(s) in the list");
    }
    public void printDelete() {
        System.out.println("\tNoted. I have removed the task: ");
        recentDelete.print();
        System.out.println("\tNow you have " + (taskArrayList.size()) +" task(s) in the list");
    }
    public void addTask(String action,String inputMsg) throws DukeException {
        switch (action) {
            /*add to array */
            case "todo":
                addTodo(inputMsg);
                break;
            case "deadline":
                addDeadline(inputMsg);
                break;
            case "event":
                addEvent(inputMsg);
                break;
        }
    }
}
