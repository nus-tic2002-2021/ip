package duke.storage;

import duke.tasklist.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.parser.Parser.commandToArray;

public class Storage {

    protected String filePath;

    public Storage (String filePath){
        this.filePath = filePath;
    }

    public void saveTask(ArrayList <Task> taskList) throws IOException {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if(!file.exists()){
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for(Task task : taskList){
            String type = task.getType();
            String toText = null;
            String isDone = task.isDone() ? "√" : " ";
            String description = task.getDescription();

            switch (type){
                case "T":
                    toText = "todo [" + isDone + "] " + description + System.getProperty("line.separator");
                    break;
                case "D":
                    toText = "deadline [" + isDone + "] " + description +"/by" + task.getDatetime() + System.getProperty("line.separator");
                    break;
                case "E":
                    toText = "event [" + isDone + "] " + description +"/at" + task.getDatetime()+ System.getProperty("line.separator");
                    break;
            }
            assert toText != null;
            fw.write(toText);
        }
        fw.close();
    }


    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String text = sc.nextLine();
            String parseText = text.replaceAll(" \\[.*?\\] ", " ");
            boolean isDone = text.contains("√");
            String[] command = commandToArray(text);
            String keyword = command[0];
            Task task;
            if(keyword.equals("todo")){
                task = new Todo(parseText);
                task.setDone(isDone);
            }
            else if(keyword.equals("deadline")){
                task = new Deadline(parseText);
                task.setDone(isDone);
            }
            else{
                task = new Event(parseText);
                task.setDone(isDone);
            }
            taskList.add(task);
        }
        return taskList;
    }
}
