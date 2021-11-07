package tasklist;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An TaskList object holds an arraylist of tasks
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask (Task t) {
        taskList.add(t);
    }

    public void removeTask (int i)  {
        taskList.remove(i);
    }

    public void reset() {
        taskList.clear();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public boolean contains(Task t){return taskList.contains(t);}

    /**
     * The method searches task in the tasklist of the date given
     * @param date user specified date parameter
     * @param taskList is the task list
     * @return another list of task of the date given
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public TaskList getTaskByDate(LocalDate date, TaskList taskList) throws IndexOutOfBoundsException{
        TaskList taskByDate = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getType().equals("E") | taskList.get(i).getType().equals("D")) {
                if (taskList.get(i).getDateTime().toLocalDate().equals(date)) {
                    taskByDate.addTask(taskList.get(i));
                }
            }
        }
        return taskByDate;
    }

    /**
     * The method searches task in the tasklist of the keyword given
     * @param keyword user specified keyword parameter
     * @param taskList is the task list
     * @return another list of task of the keyword given
     * @throws IndexOutOfBoundsException when the index is out of bounds
     */
    public TaskList getTaskByKeyword (String keyword, TaskList taskList) throws IndexOutOfBoundsException{
        TaskList taskByKeyword = new TaskList();
        for (int i = 0; i < taskList.size(); i++){
            String[] keywordInTask = taskList.get(i).getKeyword()[0].split(" ");
            for (String s : keywordInTask){
                if (s.contains(keyword) || keyword.contains(s)) {
                    if (!taskByKeyword.contains(taskList.get(i))){
                        taskByKeyword.addTask(taskList.get(i));
                    }
                }
            }
        }
        return taskByKeyword;
    }
}
