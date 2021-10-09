package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public String filepath;

    public Storage(String filepath){
        this.filepath = filepath;

    }


    public void store(TaskList tasks) throws IOException {
        File file = new File(filepath);
        File dir = file.getParentFile();
        if(!file.exists()){
            if(!dir.exists()){
                dir.mkdirs();
            }
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file);
        for(int i = 0; i < tasks.size(); i++){
            fw.write(tasks.taskList.get(i).toStoreString());
        }
        fw.close();
    }


    public TaskList load() throws FileNotFoundException {
        TaskList tasks = new TaskList();
        File file = new File(filepath);
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String line = sc.nextLine();
            String[] data = line.split(" \\| ");
            boolean isDone = data[1].equals("1") ? true : false;
            Task task;
            if(data[0].equals("T")){
                task = new Todo(isDone, data[2]);
            }
            else if(data[0].equals("D")){
                task = new Deadline(isDone, data[2], data[3]);
            }
            else{
                task = new Event(isDone, data[2], data[3]);
            }
            tasks.addTask(task);
        }
        return tasks;
    }

}
