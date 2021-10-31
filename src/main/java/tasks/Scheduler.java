package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A <code>Scheduler</code> object stores the start and end datetime of events.
 * It will check for clashes before an event is added to the task list.
 * Return true if the new event can be added and false otherwise.
 */
public class Scheduler {

    private final ArrayList<DeadlineTask> deadlines;
    private final ArrayList<EventTask> events;

    public Scheduler(){
        deadlines = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * Returns after adding new deadline.
     * The core scheduling function.
     *
     * @param deadlineTask New deadline.
     */
    public void scheduleDeadline(DeadlineTask deadlineTask) {
        LocalDateTime deadline = deadlineTask.deadline;
        boolean scheduleIsEmpty = deadlines.size() == 0;
        if (scheduleIsEmpty) {
            deadlines.add(deadlineTask);
            return;
        }
        int index = searchForNewDeadlineSlot(deadline);
        deadlines.add(index, deadlineTask);
        return;
    }

    /**
     * Returns true if the event is scheduled, false if the event cannot be added.
     * The core scheduling function.
     *
     * @param event New event.
     */
    public boolean scheduleEvent(EventTask event) {
        LocalDateTime start = event.start;
        LocalDateTime end = event.end;

        // start time is later than end time
        if (start.isAfter(end)) {
            return false;
        }

        // no event in the schedule
        boolean scheduleIsEmpty = events.size() == 0;
        if (scheduleIsEmpty) {
            events.add(event);
            return true;
        }

        boolean startLate = start.isAfter(events.get(events.size()-1).start) ||
                start.isEqual(events.get(events.size()-1).start);
        boolean startAfterLastEvent = start.isAfter(events.get(events.size()-1).end) ||
                start.isEqual(events.get(events.size()-1).end) ;
        boolean startEarly = start.isBefore(events.get(0).start) ||
                start.isEqual(events.get(0).start);
        boolean endBeforeFirstEvent = end.isBefore(events.get(0).start) ||
                end.isEqual(events.get(0).start);

        if (startLate) {
            if (startAfterLastEvent) {
                events.add(event);
                return true;
            }
        } else if (startEarly) {
            if (endBeforeFirstEvent){
                events.add(0,event);
                return true;
            }
        } else { //start somewhere in the middle
            int index = searchForNewEventSlot(start);
            if (index == -1) { //clashed
                return false;
            }
            boolean startNotClash = start.isAfter(events.get(index - 1).end)
                    || start.isEqual(events.get(index - 1).end) ;
            boolean endNotClash = end.isBefore(events.get(index).start) ||
                    end.isEqual(events.get(index).start);
            if (startNotClash && endNotClash) {
                events.add(index, event);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns -1 if there is time clashes, else returns the index of the slot.
     * The helper function to binary search for slot based on event start time.
     *
     * @param start Start DateTime of the new event.
     */
    private int searchForNewEventSlot(LocalDateTime start){
        int left = 0;
        int right = events.size() - 1;

        while (left < right){
            int mid = (left + right) / 2;
            if ((events.get(mid).start).equals(start)) {
                return -1;
            } else if ((events.get(mid).start).isAfter(start)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * Returns index+1 if there is same deadline timing, else returns the index of the desired slot.
     * The helper function to binary search for slot based on event start time.
     *
     * @param deadline Start DateTime of the new event.
     */
    private int searchForNewDeadlineSlot(LocalDateTime deadline){
        int left = 0;
        int right = deadlines.size() - 1;

        while (left < right){
            int mid = (left + right) / 2;
            if ((deadlines.get(mid).deadline).equals(deadline)) {
                return mid+1;
            } else if ((deadlines.get(mid).deadline).isAfter(deadline)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * Loads schedule of existing events.
     *
     * @param tasks List of tasks loaded from existing tasks file.
     */
    public void loadSchedule(ArrayList<Task> tasks){
        for (Task task: tasks) {
            boolean isDeadline = task.getClass().equals(DeadlineTask.class);
            boolean isEvent = task.getClass().equals(EventTask.class);

            if (isEvent) {
                EventTask event = (EventTask) task;
                scheduleEvent(event);
            }
            if (isDeadline) {
                DeadlineTask deadline = (DeadlineTask) task;
                scheduleDeadline(deadline);
            }
        }
    }

    /**
     * Returns -1 if the event is not found, else return the index of the event.
     * Another helper function to binary search for slot based on event start time.
     *
     * @param event Event to look up for.
     */
    private int retrieveEventSlot(EventTask event){
        int left = 0;
        int right = events.size() - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (events.get(mid).equals(event)) {
                return mid;
            } else if ((events.get(mid).start).isAfter(event.start)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Returns -1 if the deadline is not found, else return the index of the deadline.
     * Another helper function to binary search for slot based on event start time.
     *
     * @param deadline Deadline to look up for.
     */
    private int retrieveDeadlineSlot(DeadlineTask deadline){
        int left = 0;
        int right = deadlines.size() - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (deadlines.get(mid).equals(deadline)) {
                return mid;
            } else if ((deadlines.get(mid).deadline).isAfter(deadline.deadline)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * Removes a particular event from the schedule (clear the slot).
     *
     * @param event Event to be removed.
     */
    public void freeUpEventSlot(EventTask event){
        int index = retrieveEventSlot(event);
        assert index != -1: "The event must be in the schedule";
        events.remove(index);
    }

    /**
     * Removes a particular deadline from the schedule (clear the slot).
     *
     * @param deadline Deadline to be removed.
     */
    public void freeUpDeadlineSlot(DeadlineTask deadline){
        int index = retrieveDeadlineSlot(deadline);
        assert index != -1: "The deadline must be in the schedule";
        deadlines.remove(index);
    }

    /**
     * Returns the list of events and deadlines scheduled on the input date.
     *
     * @param date Date of schedule.
     */
    public ArrayList<Task> getSchedule(LocalDate date) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < deadlines.size(); i++) {
            if((deadlines.get(i).deadline).toLocalDate().equals(date)){
                tasks.add(deadlines.get(i));
            }
        }
        for (int i = 0; i < events.size(); i++) {
            if((events.get(i).start).toLocalDate().equals(date)){
                tasks.add(events.get(i));
            }
        }
        return tasks;
    }
}
