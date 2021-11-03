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

    public void store(TaskList taskList) throws IOException {
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
            fw.write(taskList.get(i).printTask());
        }
        fw.close();
    }


    /**
     * load task list from selected file
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String text = sc.nextLine();
            String[] keyword = text.split(" \\| ");
            //boolean isDone = keyword[1].equals("1") ? true : false;
            Task task;
            if(keyword[0].equals("T")){
                task = new Todo(text);
            }
            else if(keyword[0].equals("D")){
                task = new Deadline(text);
            }
            else{
                task = new Event(text);
            }
            tasks.add(task);
        }
        return tasks;
    }

}
