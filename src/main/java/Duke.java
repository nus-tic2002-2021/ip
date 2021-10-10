import java.util.Scanner;
public class Duke {


    private UI ui;
    private Parser Parser;
    private List tasks;
    private Storage storage;
    private static String Path = "data/tasks.txt";;

    public Duke (String filePath) {
        ui = new UI();
        Parser = new Parser();
        storage = new Storage(filePath);
        try{
            tasks = new List(storage.load());
            System.out.println("File loaded successfully");
        }catch (Exception e){
            System.out.println("Error reading file");
            tasks = new List();
        }
    }

    public void run(){
        ui.printIntro();
        boolean isExit = false;
        while (!isExit){
            try{
                ui.printLine();
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks,storage,ui);
                isExit = c.isExit();
            } catch (UnrecognizedException e){
                System.out.println("\tUnrecognized Command");
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("\tInvalid task entry");
            } catch (InvalidFormatException e){
                System.out.println("\t" + e.getMessage());
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
        new Duke(Path).run();
        //printIntro();
        //initialize();

    }
}
