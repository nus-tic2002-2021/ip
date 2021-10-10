import java.util.Scanner;
public class Duke {


    private UI ui;
    private Parser Parser;
    private List tasks;
    private Storage storage;

    public Duke (String filePath) {
        ui = new UI();
        Parser = new Parser();
        tasks = new List();
        storage = new Storage(filePath);
        storage.newFile();
    }

    public void run(){
        ui.printIntro();
        boolean isExit = false;
        while (!isExit){
            try{
                ui.printLine();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks,storage);
                isExit = c.isExit();
            } catch (UnrecognizedException e){
                System.out.println("Unrecognized Command");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Invalid task entry");
            }
            finally {
                ui.printLine();
            }
        }

    }


    /*
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
*/

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
        //printIntro();
        //initialize();

    }
}
