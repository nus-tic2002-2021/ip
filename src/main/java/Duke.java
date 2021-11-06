import parser.Parser;
import storage.Storage;
import ui.UI;
import task.List;
import command.*;
import error.*;




public class Duke{

    private UI ui;
    private Parser Parser;
    private List tasks;
    private Storage storage;
    private static String Path = "data/tasks.txt";;

    /**
     *
     *  Constructor for new Du ke object using filePath given in main.
     *  Creates a new ui.UI, parser.Parser and storage.Storage object.
     *  Try to load a new List object from path.
     *  If path does not exist, create a new empty List object
     *
     * @param filePath Path of file to be executed
     *
     */

    public Duke (String filePath) {
        ui = new UI();
        Parser = new Parser();
        storage = new Storage(filePath);
        try{
            tasks = new List(storage.load());
            System.out.println("File loaded successfully.");
        } catch (FileException e) {
            System.out.println("\tFile format is corrupted. Creating new file.");
            tasks = new List();
        } catch (Exception e){
            System.out.println("Error reading file or does not exist. Creating new file.");
            tasks = new List();
        }
    }

    /**
     *
     *  Executes the program until isExit is true
     *
     */
    public void run(){
        ui.printIntro();
        boolean isExit = false;
        while (!isExit){
            try{
                String fullCommand = ui.readCommand();
                ui.printLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks,storage,ui);
                isExit = c.isExit();
            } catch (UnrecognizedException e){
                System.out.println("\tUnrecognized Command");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\tPlease enter description after command");
            } catch (DukeException e){
                ui.showError(e.getMessage());
            }
            finally {
                ui.printLine();
            }
        }

    }
    public String getResponse(String input) {
        if(input.equals("hello")){
            return "hello";
        }
        else return "1";
    }
    public static void main(String[] args) {
        new Duke(Path).run();
    }


}
