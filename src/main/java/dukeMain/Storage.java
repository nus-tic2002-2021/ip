package dukeMain;

import dukeMain.tasks.Task;
import dukeMain.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import dukeMain.exceptions.*;
// Handles loading dukeMain.tasks from the file and
// saving dukeMain.tasks in the file
public class Storage {

    private static String filePath;
    public static final String DEFAULT_STORAGE_FILEPATH = "data.txt";
    public final Path path;
    public static final String home = System.getProperty("user.home");
    private static Ui ui;

    private static boolean saveResult,createResult;

    public Storage(){
        filePath = DEFAULT_STORAGE_FILEPATH;
        path = Paths.get(home, filePath);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        this.filePath = filePath;

        path = Paths.get(home, filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("dukeMain.Storage file should end with '.txt'");
        }
        //check if file exist
        isPathExist();
    }

    public static File load() {

        return  new File(home+"/"+filePath); // create a File for the given file path

    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.txt'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public static void save(TaskList tkL){
        System.out.println("Entered Save");
        saveResult = true;
        // start saving task
        String content = "";
        for (Task tk : tkL.getTaskList())
            content += tk.toStringSaveTask("|") + System.lineSeparator();
        try{
            writeToFile(home+"/"+filePath+"",content);
        }catch(IOException e){
            saveResult = false;
        }
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        System.out.println("Entered Write");
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
        System.out.println("Exit Write");
    }

    private void isPathExist(){

        Path path = Paths.get(home, filePath);

        // check if the directory and file exists
        boolean directoryFullExists = Files.exists(path);
        if (!directoryFullExists) {
            createDirectories(home,path);
            createFile(home,path);
        }
    }

    // Create Directories if it does not exist
    private static void createDirectories(String home, Path path){
        boolean directoryExists;
        Path changePath;

        // Check if directory exist
        if (path.getNameCount() > 1) {
            for (int i = 1; i < path.getNameCount(); i++){
                changePath = Paths.get(path.subpath(0,i).toString());
                directoryExists = Files.exists(changePath);
                // if dont exist create directory
                if(!directoryExists){
                    try{
//                        ui.printThis("File path dont exist : " + path,1);
                        Files.createDirectory(Paths.get(changePath.toString()));
//                        ui.printThis("File path is created ",2);
                    }catch (IOException e)
                    {
                        // save task
                        createResult = false;
//                        ui.printThis("Caught error",3);

                        e.printStackTrace();    //prints exception if any
                    }
                }
            }
        }
    }

    // Create File in the path given
    private static void createFile(String home, Path path){
        // create txt file
        try{
//            ui.printThis("File path exist : " + path,3);
            Files.createFile(Paths.get(home,path.toString()));
        }catch (IOException e){
//            ui.printThis("Caught error",3);
            createResult = false;
            e.printStackTrace();    //prints exception if any
        }
    }
    //return saveResult to be printed with UI class.
    public boolean getSaveResult(){
        return saveResult;
    }
    public boolean getCreateResult() {
        return createResult;
    }
}
