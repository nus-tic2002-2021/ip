package task;

import java.util.ArrayList;

import duke.Duke;
import error.*;
import java.time.LocalDate;



public class List {
    private static ArrayList<Task> taskArrayList;
    private static ArrayList<String> taskSave;
    public static Task recentDelete;
    /**
     *
     *  Create new a new empty List
     *
     */
    public List(){
        taskArrayList = new ArrayList<>();
        taskSave = new ArrayList<>();
    }

    /**
     *
     *  Create new a new loaded List
     *
     * @param loadFile loaded txt file
     */
    public List(ArrayList<String> loadFile) throws FileException{
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
            LocalDate date;
            try {
                date = LocalDate.parse(taskArr[3]);
            } catch (Exception e) {
                throw new FileException();
            }
            switch (taskType){
                case "T":
                    taskArrayList.add(new Todo(taskDescription,isDone));
                    break;
                case "D":
                    taskArrayList.add(new Deadline(taskDescription,date,isDone));
                    break;
                case "E":
                    taskArrayList.add(new Event(taskDescription,date,isDone));
                    break;
            }
        }

    }

    /**
     *
     *  Adds new to do task to taskList as a TODO object
     *
     * @param inputMsg description to be added
     * @throws DukeException if inputMsg is empty
     */
    public void addTodo(String inputMsg)throws DukeException {
        if(inputMsg.isEmpty()){
            throw new DukeException("TODO_DESCRIPTION_ERROR");
        }
        taskArrayList.add(new Todo(inputMsg));
        printAdd();
    }

    /**
     *
     *  Adds new deadline task to taskList as a DEADLINE object
     *
     * @param inputMsg description and /by to be added
     * @throws DukeException if inputMsg is does not contain /by or more than 1 /at OR date is not valid
     */
    public void addDeadline(String inputMsg)throws DukeException {
        String [] input = inputMsg.split(" /by ");
        LocalDate date;
        if(input.length < 2){
            throw new DukeException("DEADLINE_DESCRIPTION_ERROR");
        }
        if(input.length > 2){
            throw new DukeException("DEADLINE_LENGTH_ERROR");
        }
        try {
            date = LocalDate.parse(input[1]);
        } catch (Exception e) {
            throw new DukeException("INVALID_DATE_FORMAT");
        }
        taskArrayList.add(new Deadline(input[0],date));
        printAdd();
    }

    /**
     *
     *  Adds new event task to taskList as a DEADLINE object
     *
     * @param inputMsg description and /at to be added
     * @throws DukeException if inputMsg does not contain /at or more than 1 /at
     */
    public void addEvent(String inputMsg)throws DukeException {
        String [] input = inputMsg.split(" /at ");
        LocalDate date;
        if(input.length < 2){
            throw new DukeException("EVENT_DESCRIPTION_ERROR");
        }
        if(input.length > 2){
            throw new DukeException("EVENT_LENGTH_ERROR");
        }
        try {
            date = LocalDate.parse(input[1]);
        } catch (Exception e) {
            throw new DukeException("INVALID_DATE_FORMAT");
        }
        taskArrayList.add(new Event(input[0],date));
        printAdd();
    }

    /**
     *
     *  Modify the task to be completed.
     *
     * @param taskNumber number to determine which task to modify
     * @throws NotFoundException if the taskNumber is not within array size
     */
    public void taskDone(String taskNumber)throws NotFoundException {
        Integer inputNumber = Integer.parseInt(taskNumber);
        if (taskArrayList.size() < inputNumber){
            throw new NotFoundException();
        }
        taskArrayList.get(inputNumber - 1).setDone();
    }
    /**
     *
     *  Deletes the task.
     *
     * @param taskNumber number to determine which task to delete
     * @throws NotFoundException if the taskNumber is not within array size
     */
    public void taskDelete(String taskNumber)throws NotFoundException {
        Integer inputNumber = Integer.parseInt(taskNumber);
        if (taskArrayList.size() < inputNumber){
            throw new NotFoundException();
        }
        recentDelete = taskArrayList.get(inputNumber - 1);
        taskArrayList.remove(inputNumber - 1);
        printDelete();
    }

    /**
     *
     *  Prints out all the task.
     *
     */
    public void printList() {
        if(taskArrayList.size() == 0){ //0 items in list
            System.out.println("\tList is empty!"); //throw empty list
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

    /**
     *
     *  Print out the most recent task added.
     *
     */
    public void printAdd() {
        System.out.println("\tGot it. Item successfully added to the list: ");
        taskArrayList.get(taskArrayList.size() - 1).print();
        System.out.println("\tNow you have " + (taskArrayList.size()) +" task(s) in the list");
    }
    /**
     *
     *  Print out the most recent task deleted.
     *
     */
    public void printDelete() {
        System.out.println("\tNoted. I have removed the task: ");
        recentDelete.print();
        System.out.println("\tNow you have " + (taskArrayList.size()) +" task(s) in the list");
    }

    /**
     *
     *  task function to determine the type of task to add
     *
     * @param action the type of task to be added
     * @param inputMsg the message be added in taskArrayList
     */
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

