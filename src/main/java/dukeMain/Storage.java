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

/** Handles loading dukeMain.tasks from the file and
 * saving dukeMain.tasks in the file
 *
 * */
public class Storage {

    private static String filePath;
    public static final String DEFAULT_STORAGE_FILEPATH = "data.txt";
    public final Path path;
    public static final String home = System.getProperty("user.home");


    public Storage(){
        filePath = DEFAULT_STORAGE_FILEPATH;
        path = Paths.get(home, filePath);
    }

    /**Constructor of Storage
     * To initialise the filepath given.
     *
     * @param filePath String
     * @throws InvalidStorageFilePathException if the given file path is invalid
     * @throws DukeException if IOException is detected when creating directories or file
     */
    public Storage(String filePath) throws InvalidStorageFilePathException, DukeException{
        this.filePath = filePath;

        path = Paths.get(home, filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("dukeMain.Storage file should end with '.txt'");
        }
        //check if file exist
        isPathExist();
    }

    /**Create a File object with the filepath given.
     *
     * @return File
     */
    public static File load() {

        return  new File(home+"/"+filePath); // create a File for the given file path

    }

    /** Check if the given path ends with .txt
     *
     * @param filePath Path
     * @return boolean
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /** Initiate to Save the Tasklist
     * into the filepath
     *
     * @param tkL TaskList
     * @throws DukeException
     */
    public static void save(TaskList tkL) throws DukeException{
        System.out.println("Entered Save");
        // start saving task
        String content = "";
        for (Task tk : tkL.getTaskList())
            content += tk.toStringSaveTask("|") + System.lineSeparator();
        try{
            writeToFile(home+"/"+filePath+"",content);
        }catch(IOException e){
            throw new DukeException("Something is wrong when Saving the taskList. Please Try Again later");
        }
    }

    /** Write the contents into filepath
     *
     * @param filePath String
     * @param textToAdd String
     * @throws IOException if there is any issues with writing or accessing the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        System.out.println("Entered Write");
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
        System.out.println("Exit Write");
    }

    /** Check if the file path given exist in the directory,
     * else create new directories and/or file
     *
     * @throws IOException if there is any issues with writing or accessing the file
     */
    private void isPathExist() throws DukeException{

        Path path = Paths.get(home, filePath);

        // check if the directory and file exists
        boolean directoryFullExists = Files.exists(path);
        if (!directoryFullExists) {
            createDirectories(home,path);
            createFile(home,path);
        }
    }
    /** Create Directories if it does not exist
     *
     * @throws IOException if there is any issues with writing or accessing the file
     */

    private static void createDirectories(String home, Path path) throws DukeException{
        boolean directoryExists;
        Path changePath;

        if (path.getNameCount() > 1) {
            for (int i = 1; i < path.getNameCount(); i++){
                changePath = Paths.get(path.subpath(0,i).toString());
                directoryExists = Files.exists(changePath);

                if(!directoryExists){
                    try{
                        Files.createDirectory(Paths.get(changePath.toString()));
                    }catch (IOException e)
                    {
                        // save task
                        throw new DukeException("Something is wrong when creating Directory for path\n" + changePath);
                    }
                }
            }
        }
    }

    /** Create Directories if it does not exist
     *
     * @throws IOException if there is any issues with writing or accessing the file
     */
    // Create File in the path given
    private static void createFile(String home, Path path) throws DukeException{
        // create txt file
        try{
//            ui.printThis("File path exist : " + path,3);
            Files.createFile(Paths.get(home,path.toString()));
        }catch (IOException e){
//            ui.printThis("Caught error",3);
            throw new DukeException("There seems to be error creating the File located : "+ getFilePath());
        }
    }
    /** Getter for FilePath
     *
     * @return Filepath String
     */
    public static String getFilePath(){
        return home + "/"+filePath;
    }
}
