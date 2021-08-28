public class List {
    private static int inputCounter;
    private static String[] inputArray;

    public List(){
        inputCounter = 0;
        inputArray =  new String[100];
    }
    public void storeList(String inputMsg) {
        inputArray[inputCounter]= inputMsg;
        inputCounter = inputCounter + 1;
    }

    public void printList() {
        if(inputCounter == 0){
            System.out.println("\tList is empty!");
        }
        else{
            for(int i = 0; i < inputCounter; i++){
                System.out.println("\t" + (i+1) + "." + inputArray[i]);
            }
        }

    }

}
