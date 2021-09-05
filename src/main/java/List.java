public class List {
    private static int inputCounter;
    private static Task[] inputArray;

    public List(int size){
        inputCounter = 1; // array 0 is empty to remove + 1 in all codes
        inputArray =  new Task[size];
    }
    public void checkAction(String inputMsg){
        String action[] = inputMsg.split(" ");
        String inputLow = inputMsg.toLowerCase();
        int initialCounter = inputCounter;
        if(inputLow.startsWith("todo ")){
            addTodo(inputMsg.substring(5));
        }
        else if(inputLow.startsWith("deadline ")){
            addDeadline(inputMsg.substring(9));
        }
        else if(inputLow.startsWith("event ")){
            addEvent(inputMsg.substring(6));
        }
        else{
            System.out.println("\tError occurred.");
        }
        if(initialCounter < inputCounter){
            System.out.println("\tGot it. Item successfully added to the list: ");
            inputArray[inputCounter - 1].print();
            System.out.println("\tNow you have " + (inputCounter-1) +" task(s) in the list");
        }
    }

    public void addTodo(String inputMsg){
        inputArray[inputCounter] = new Todo(inputMsg);
        inputCounter = inputCounter + 1;
    }

    public void addDeadline(String inputMsg){
        String [] input = inputMsg.split(" /by ");
        if(input.length == 2){
            inputArray[inputCounter] = new Deadline(input[0],input[1]);
            inputCounter = inputCounter + 1;
        }
        else{
            System.out.println("Invalid deadline entry.");
        }
    }
    public void addEvent(String inputMsg){
        String [] input = inputMsg.split(" /at ");
        if(input.length == 2){
            inputArray[inputCounter] = new Event(input[0],input[1]);
            inputCounter = inputCounter + 1;
        }
        else{
            System.out.println("Invalid event entry.");
        }
    }

    public void taskDone(String counter){
        Integer inputNumber = Integer.parseInt(counter);
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
