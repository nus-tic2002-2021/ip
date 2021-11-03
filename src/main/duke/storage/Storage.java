package duke.storage;

import duke.tasklist.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static String filePath;

    public Storage (String filePath){
        this.filePath = filePath;
    }

    public void saveTask(TaskList taskList) throws IOException {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if(!file.exists()){
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for(int i = 0; i < taskList.size(); i++){
            fw.write(taskList.get(i).printTask()+ System.getProperty("line.separator"));
        }
        fw.close();
    }


    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String text = sc.nextLine();
            String keyword = Character.toString(text.charAt(1));
            Task task;
            if(keyword.equals("T")){
                task = new Todo(text);
            }
            else if(keyword.equals("D")){
                task = new Deadline(text);
            }
            else{
                task = new Event(text);
            }
            taskList.add(task);
        }
        return taskList;
    }

}
