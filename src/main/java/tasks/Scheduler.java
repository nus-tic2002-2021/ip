package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Scheduler {

    private final ArrayList<LocalDateTime> starts;
    private final ArrayList<LocalDateTime> ends;

    public Scheduler(){
        starts = new ArrayList<>();
        ends = new ArrayList<>();
    }

    public boolean schedule(LocalDateTime newStart, LocalDateTime newEnd) {
        // start time is later than end time
        if (newEnd.isBefore(newStart)) {
            return false;
        }

        // no event in the schedule
        boolean scheduleIsEmpty = starts.size() == 0;
        if (scheduleIsEmpty) {
            starts.add(newStart);
            ends.add(newEnd);
            return true;
        }

        boolean startLate = newStart.isAfter(starts.get(starts.size()-1)) || newStart.isEqual(starts.get(starts.size()-1));
        boolean startAfterLastEvent = newStart.isAfter(ends.get(ends.size()-1)) || newStart.isEqual(ends.get(ends.size()-1));
        boolean startEarly = newStart.isBefore(starts.get(0)) || newStart.isEqual(starts.get(0));
        boolean endBeforeFirstEvent = newEnd.isBefore(starts.get(0)) || newEnd.equals(starts.get(0));
        if (startLate) {
            if (startAfterLastEvent) {
                starts.add(newStart);
                ends.add(newEnd);
                return true;
            }
        } else if (startEarly) {
            if (endBeforeFirstEvent){
                starts.add(0,newStart);
                ends.add(0, newEnd);
                return true;
            }
        } else {
            int index = searchForSlot(newStart);
            if (index == -1) { //clashed
                return false;
            }
            boolean startNotClash = newStart.isAfter(ends.get(index - 1)) || newStart.isEqual(ends.get(index - 1));
            boolean endNotClash = newEnd.isBefore(starts.get(index)) || newEnd.isBefore(starts.get(index));
            if (startNotClash && endNotClash) {
                starts.add(index, newStart);
                ends.add(index, newEnd);
                return true;
            }
        }

        return false;
    }

    public int searchForSlot(LocalDateTime start){
        int left = 0;
        int right = starts.size() - 1;

        while (left < right){
            int mid = (left + right) / 2;
            if (starts.get(mid).equals(start)) {
                return -1;
            } else if (starts.get(mid).isAfter(start)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public void loadSchedule(ArrayList<Task> tasks){
        for (Task task: tasks) {
            if (task.getClass().equals(EventTask.class)) {
                schedule(((EventTask) task).start, ((EventTask) task).end);
            }
        }
    }

    public int getSlot(LocalDateTime start){
        int left = 0;
        int right = starts.size() - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (starts.get(mid).equals(start)) {
                return mid;
            } else if (starts.get(mid).isAfter(start)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public void freeUpSlot(LocalDateTime start){
        int index = getSlot(start);
        starts.remove(index);
        ends.remove(index);
    }
}
