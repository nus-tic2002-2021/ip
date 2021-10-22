package duke.task.model;


/**
 * Root class for tasks
 */
public abstract class Task implements Comparable<Task> {
    private String _taskDescription;
    private Integer _taskId;
    private Boolean _done;


    protected Task(String taskDescription, Integer taskId, Boolean done) {
        this.setTaskDescription(taskDescription);
        this.setTaskId(taskId);
        this.setDoneStatus(done);
    }

    public void setTaskId(Integer taskId) {
        this._taskId = taskId;
    }

    public void setTaskDescription(String taskDescription) {
        this._taskDescription = taskDescription;
    }

    protected Task() {
    }

    public String getTaskDescription() {
        return this._taskDescription;
    }


    public Integer getTaskId() {
        return this._taskId;
    }


    public Boolean isDone() {
        return this._done;
    }

    public Boolean setDoneStatus(Boolean next) {
        this._done = next;
        return this._done;
    }

    public Boolean descContainsKeyword(String keyword){
        String desc = this._taskDescription;
        String[] words = desc.split("\\s+");
        for(String word : words){
            if(word.equals(keyword)){
                return true;
            }
        }
        return false;
    }

    public abstract String getChronologyString();

    public int compareTo(Task u) {
        return Integer.compare(this.getTaskId(), u.getTaskId());
    }


}