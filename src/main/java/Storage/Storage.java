package Storage;

import Exception_Handler.*;
import TaskList.TaskList;
import TaskList.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected boolean isExist;
    protected String fileName;
    protected File file = new File(System.getProperty("user.dir") + "/src/main/java/Storage/"+fileName); // create a File for the given file path
    Scanner scanner;
    public Storage(String fileName){
        this.fileName = fileName;
    }

    public void load(TaskList task_from_file) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line
                System.out.println("Loading");
            }
        }
        catch(IOException e){

        }
    }
    public void createFile() throws IOException{
        try {
            file.createNewFile();
        }
        catch(IOException ioe) {
            this.isExist = true;
            System.out.println("FIle exists");
        }
        System.out.println("File created: " + file.getName());
    }

    public void save(ArrayList<Task> list) throws IOException{

        FileWriter myWriter = new FileWriter(file.getAbsoluteFile());
        list.forEach((task) -> {
            try {
                myWriter.write(task.toString());
            } catch (IOException e) {
                //
            }
        });
        myWriter.close();
        System.out.println("Successfully wrote to the file.");
    }
}
