public class List {
    private static int inputCounter;
    private static Task[] inputArray;

    public List(int size){
        inputCounter = 1; // array 0 is empty to remove + 1 in all codes
        inputArray =  new Task[size];
    }
    public void storeList(String inputMsg) {
        addInput(inputMsg);
        inputCounter = inputCounter + 1;
    }
    public void addInput(String inputMsg){
        inputArray[inputCounter] = new Task(inputMsg);
    }
    public void setDone(String counter){
        Integer setCount;
        String[] counters = counter.split(" ");
        System.out.println("Nice! I've marked this task as done:");
        for(String c : counters){
            setCount = Integer.parseInt(c);
            if(inputArray[setCount].isDone != true){
                inputArray[setCount].isDone = true;
                System.out.println("\t  " + inputArray[setCount]);
            }
        }
    }

    public void printList() {
        if(inputCounter == 0){
            System.out.println("\tList is empty!");
        }
        else{
            for(int i = 1; i < inputCounter; i++){
                System.out.println("\t" + (i) + "." + inputArray[i]  );
            }
        }
    }

}
