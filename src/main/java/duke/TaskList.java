package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task t){
        taskList.add(t);
    }

    public Task deleteTask(int idx){
        Task t = taskList.get(idx);
        taskList.remove(idx);
        return t;
    }

    public Task doneTask(int idx){
        taskList.get(idx).doTask();
        return taskList.get(idx);
    }

    public int size(){
        return taskList.size();
    }

    public TaskList find(String search){
        TaskList tl = new TaskList();
        for(Task task: taskList){
            if(task.containInfo(search)){
                tl.addTask(task);
            }
        }
        return tl;
    }
}
