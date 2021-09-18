import java.util.ArrayList;

public class List {
    private static int inputCounter;
    private static Task[] inputArray;

    public List(int size){
        inputCounter = 1; // array 0 is empty to remove + 1 in all codes
        inputArray =  new Task[size];
    }
    public void checkAction(String inputMsg) throws UnrecognizedException,InvalidFormatException{

        String inputLow = inputMsg.toLowerCase();
        String action[] = inputLow.split(" ");
        int initialCounter = inputCounter;

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
        if(initialCounter < inputCounter){
            System.out.println("\tGot it. Item successfully added to the list: ");
            inputArray[inputCounter - 1].print();
            System.out.println("\tNow you have " + (inputCounter-1) +" task(s) in the list");
        }
    }

    public void addTodo(String inputMsg)throws InvalidFormatException{
        if(inputMsg.isEmpty()){
            throw new InvalidFormatException("Todo is missing a description.");
        }
        inputArray[inputCounter] = new Todo(inputMsg);
        inputCounter = inputCounter + 1;
    }

    public void addDeadline(String inputMsg)throws InvalidFormatException{
        String [] input = inputMsg.split(" /by ");
        if(input.length < 2){
            throw new InvalidFormatException("Deadline command is missing a description and/or deadline.");
        }
        if(input.length > 2){
            throw new InvalidFormatException("Deadline command has too many /by.");
        }
        inputArray[inputCounter] = new Deadline(input[0],input[1]);
        inputCounter = inputCounter + 1;
    }
    public void addEvent(String inputMsg)throws InvalidFormatException{
        String [] input = inputMsg.split(" /at ");
        if(input.length < 2){
            throw new InvalidFormatException("Event command is missing a description and/or time.");
        }
        if(input.length > 2){
            throw new InvalidFormatException("Event command has too many /at.");
        }
        inputArray[inputCounter] = new Event(input[0],input[1]);
        inputCounter = inputCounter + 1;
    }

    public void taskDone(String counter)throws NotFoundException{
        Integer inputNumber = Integer.parseInt(counter);
        if (inputCounter < inputNumber){
            throw new NotFoundException();
        }
        inputArray[inputNumber].setDone();
    }

    public void printList() {
        if(inputCounter == 1){ //0 items in list
            System.out.println("\tList is empty!");
        }
        else{
            for(int i = 1; i < inputCounter; i++){
                System.out.println("\t" + (i) + "." + inputArray[i]  );
            }
        }
    }

}
