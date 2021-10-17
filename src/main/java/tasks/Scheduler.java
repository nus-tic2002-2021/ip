package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Scheduler {

    private ArrayList<LocalDateTime> starts;
    private ArrayList<LocalDateTime> ends;

    public Scheduler(){
        starts = new ArrayList<>();
        ends = new ArrayList<>();
    }

    public boolean schedule(LocalDateTime newStart, LocalDateTime newEnd) {
        if(starts.size()==0){
            starts.add(newStart);
            ends.add(newEnd);
            return true;
        }
        else if(newStart.isAfter(starts.get(starts.size()-1)) ||
                newStart.isEqual(starts.get(starts.size()-1)))
        {
            if(newStart.isAfter(ends.get(ends.size()-1)) ||
                    newStart.isEqual(ends.get(ends.size()-1)))
            {
                starts.add(newStart);
                ends.add(newEnd);
                return true;
            }else{
                return false;
            }
        }
        else if(newStart.isBefore(starts.get(0)) ||
                newStart.isEqual(starts.get(0)))
        {
            if(newEnd.isBefore(starts.get(0)) ||
                    newEnd.equals(starts.get(0))){
                starts.add(0,newStart);
                ends.add(0, newEnd);
                return true;
            }else{
                return false;
            }
        }else{
            int index = binarySearch(newStart);
            if(index==-1){
                return false;
            }
            else{
                if(newStart.isAfter(ends.get(index-1)) || newStart.isEqual(ends.get(index-1))&&
                        newEnd.isBefore(starts.get(index)) || newEnd.isBefore(starts.get(index))){
                    starts.add(index,newStart);
                    ends.add(index,newEnd);
                    return true;
                }else{
                    return false;
                }
            }
        }
    }

    public int binarySearch(LocalDateTime start){
        int left = 0;
        int right = starts.size()-1;

        while(left<right){
            int mid = (left+right)/2;
            if(starts.get(mid).equals(start)){
                return -1;
            }
            else if(starts.get(mid).isAfter(start)){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return right;
    }

    public void updateSchedule(ArrayList<Task> tasks){
        for (Task task: tasks) {
            if(task.getClass().equals(EventTask.class)){
                schedule(((EventTask) task).start, ((EventTask) task).end);
            }
        }
    }
}
