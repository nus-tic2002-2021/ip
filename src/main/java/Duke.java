import java.util.Scanner;
public class Duke {

    private static String lineBreak = "\t_______________________________________________________";
    private static List inputList;
    private UI ui;


    public Duke (String filePath) {
        ui = new UI();

    }

    public void run(){

    }

    public static void initialize (){
        Scanner in = new Scanner(System.in);
        String inputLine = "";
        inputList = new List();

        while (!inputLine.equals("bye")) {
            inputLine = in.nextLine();
            printResponse(inputLine);
        }
    }

    public static void printIntro(){
        System.out.println(lineBreak);
        System.out.println("\tHello! This are the implemented actions.\n\t" +
                            " /at & /by are currently case sensitive"); // cannot implement with what we learnt yet.
        System.out.println("\t1.) Todo (text)--- to add todo task");    //
        System.out.println("\t2.) Deadline (text /by text)--- to add deadline task");
        System.out.println("\t3.) Event (text /at text)--- to add event task");
        System.out.println("\tEnter 'list' without quotes show list. Maximum size set to 100");
        System.out.println("\tEnter 'bye' without quotes to exit program\n");
        System.out.println("\tWelcome. Please enter your instruction.");
        System.out.println(lineBreak);
    }
    public static void printResponse(String inputMsg) {
        System.out.println(lineBreak);
        String inputLow = inputMsg.toLowerCase();
        if (inputLow.equals("bye")){
            System.out.println("\tBye. Hope to see you again soon!");
        }
        else if(inputLow.equals("list")){
            inputList.printList();
        }
        else{
            try{
                inputList.checkAction(inputMsg);
            } catch (InvalidFormatException e){
                System.out.println("\t" + e.getMessage() );
            } catch (UnrecognizedException e){
                System.out.println("\tCommand not recognized. Please try again.");
            } catch(NotFoundException e) {
                System.out.println("\tTask cannot be found.");
            } catch (NumberFormatException e){
                System.out.println("\tInvalid task number entry.");
            }
        }
        System.out.println(lineBreak);
    }


    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
        //printIntro();
        //initialize();

    }
}
