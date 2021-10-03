import java.sql.Array;
import java.util.ArrayList;

public class List {
    private static int inputCounter;
    //private static Task[] inputArray;
    private static ArrayList<Task> taskArrayList;

    public List(int size){
        inputCounter = 1; // array 0 is empty to remove + 1 in all codes
        //inputArray =  new Task[size];
        taskArrayList = new ArrayList<>();
    }
    public void checkAction(String inputMsg) throws UnrecognizedException,InvalidFormatException,NotFoundException{

        String inputLow = inputMsg.toLowerCase();
        String action[] = inputLow.split(" ");
        switch(action[0]){
            /*add to array */
            case "todo":
                addTodo(inputMsg.substring(4).trim());
                break;
            case "deadline":
                addDeadline(inputMsg.substring(8).trim());
                break;
            case "event":
                addEvent(inputMsg.substring(5).trim());
                break;
            /* modify array */
            case "done":
                taskDone(inputMsg.substring(4).trim());
                break;
            case "delete":
                taskDelete(inputMsg.substring(6).trim());
                break;

            default:
                throw new UnrecognizedException();

        }
        /*
        if(action[0].equals("todo")){
            addTodo(inputMsg.substring(4).trim());
        }
        else if(action[0].equals("deadline")){
            addDeadline(inputMsg.substring(8).trim());
        }
        else if(action[0].equals("event")){
            addEvent(inputMsg.substring(5).trim());
        }
        else{
            throw new UnrecognizedException();
        }
         */

    }

    public void addTodo(String inputMsg)throws InvalidFormatException{
        if(inputMsg.isEmpty()){
            throw new InvalidFormatException("Todo is missing a description.");
        }
        taskArrayList.add(new Todo(inputMsg));
        //inputArray[inputCounter] = new Todo(inputMsg);
        inputCounter = inputCounter + 1;
        printSuccessAdd();
    }

    public void addDeadline(String inputMsg)throws InvalidFormatException{
        String [] input = inputMsg.split(" /by ");
        if(input.length < 2){
            throw new InvalidFormatException("Deadline command is missing a description and/or deadline.");
        }
        if(input.length > 2){
            throw new InvalidFormatException("Deadline command has too many /by.");
        }
        taskArrayList.add(new Deadline(input[0],input[1]));
        //inputArray[inputCounter] = new Deadline(input[0],input[1]);
        inputCounter = inputCounter + 1;
        printSuccessAdd();
    }
    public void addEvent(String inputMsg)throws InvalidFormatException{
        String [] input = inputMsg.split(" /at ");
        if(input.length < 2){
            throw new InvalidFormatException("Event command is missing a description and/or time.");
        }
        if(input.length > 2){
            throw new InvalidFormatException("Event command has too many /at.");
        }
        taskArrayList.add(new Event(input[0],input[1]));
        //inputArray[inputCounter] = new Event(input[0],input[1]);
        inputCounter = inputCounter + 1;
        printSuccessAdd();
    }

    public void taskDone(String counter)throws NotFoundException{
        Integer inputNumber = Integer.parseInt(counter);
        if (inputCounter <= inputNumber){
            throw new NotFoundException();
        }
        taskArrayList.get(inputNumber - 1).setDone();
        //inputArray[inputNumber].setDone();
    }
    public void taskDelete(String counter)throws NotFoundException{
        Integer inputNumber = Integer.parseInt(counter);
        if (inputCounter <= inputNumber){
            throw new NotFoundException();
        }
        taskArrayList.remove(inputNumber - 1);
        inputCounter = inputCounter - 1;
        //inputArray[inputNumber].setDone();
    }

    public void printList() {
        if(taskArrayList.size() == 0){ //0 items in list
            System.out.println("\tList is empty!");
        }
        else{
            for(int i = 1; i < inputCounter; i++){
                System.out.println("\t" + (i) + "." + taskArrayList.get(i-1) );
            }
        }
    }

    public void printSuccessAdd() {
        System.out.println("\tGot it. Item successfully added to the list: ");
        //inputArray[inputCounter - 1].print();
        taskArrayList.get(taskArrayList.size() - 1).print();
        System.out.println("\tNow you have " + (inputCounter-1) +" task(s) in the list");
    }

}
