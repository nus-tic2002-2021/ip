package Duke.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Duke.TaskList.*;
import java.util.ArrayList;

public class Storage {
    protected static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";
    protected static final String home = System.getProperty("user.home");
    private static String filePath = DEFAULT_STORAGE_FILEPATH;
    protected Path path;

    public Storage(){
        filePath = DEFAULT_STORAGE_FILEPATH;
        path = Paths.get(home, filePath);
    }
    public Storage(String filepath) throws IOException {
        this.filePath = filePath;
        path = Paths.get(home, filePath);
        if (!isValidPath(path)) {
            //throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
        //check if file exist
        isPathExist();
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    private void isPathExist() throws IOException {
        Path path = Paths.get(home, filePath);
        // check if the directory and file exists, else create
        if (!Files.exists(path)) {
            Files.createDirectory(Paths.get(home,"data"));
            Files.createFile(path);
        }
    }
    public void saveTask(TaskList tasks){
        path = Paths.get(home, "data",DEFAULT_STORAGE_FILEPATH);

        // start saving task
        String content = "";
        ArrayList<Task> save = tasks.getTaskList();
        for (Task task : save)
            content += task.saveTask() + System.lineSeparator();
        //System.out.println(content);
        try{
            writeToFile(path+"",content);
        }catch(IOException e){

        }
    }

    private void writeToFile(String filePath, String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public String load() throws IOException {
        String path = home+"\\data\\"+filePath;
        //return (File) Files.readAllLines(Path.of(path));
        return path; // create a File for the given file path
    }
}
