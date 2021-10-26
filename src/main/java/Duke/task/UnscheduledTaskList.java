package Duke.task;

import java.util.ArrayList;

public class UnscheduledTaskList {

    protected int listSize;
    private ArrayList<UnscheduledTask> unscheduleTaskList;


    /**
     * Constructor for Unscheduled Task Duration List
     *
     */
    public UnscheduledTaskList() {
        unscheduleTaskList = new ArrayList<>();
    }

    /**
     * Task list constructor with tasks within
     * @param load - the task list that already contained task.
     */
    public UnscheduledTaskList(UnscheduledTaskList load) {
        this.listSize = load.listSize;
        this.unscheduleTaskList = load.unscheduleTaskList;
    }

    /**
     * Gets the list size.
     * @return - list size in integer
     */
    public int getListSize(){
        return this.listSize;
    }


    /**
     * Adds a unscheduled record to unscheduled list
     * @param task - the full task information
     */
    public void addTask(UnscheduledTask task){
        this.unscheduleTaskList.add(task);
        this.listSize++;
    }

    public int findRecord(String description){
        for(UnscheduledTask ut : unscheduleTaskList){
            System.out.println("input Desc: "+description);
            System.out.println("saved Desc: "+ut.getDesc());
            if(description.equals(ut.getDesc())){
                System.out.println("matched");
                return ut.getDuration();
            }
        }
        return 0;
    }
}
