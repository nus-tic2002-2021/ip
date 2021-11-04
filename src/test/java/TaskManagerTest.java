import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    /*
        Todos:
        tasks without any date/time attached to it
     */
    @Test
    public void createTodoTest() {
        String eventType = "todo";
        String eventInfo = "read books";

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        Todo e = (Todo) t;
        System.out.println(e.toString());
        assertEquals(e.getType(), eventType);
        assertEquals(e.getDescription(), eventInfo);
    }

    /*
        Events:
        tasks that start at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
     */
    @Test
    public void createEventTest() {
        String eventType = "event";
        String eventDesc = "project meeting";
        String eventStartTime = "18:00";
        String eventEndTime = "21:00";
        String eventDate = "2019-12-01";
        String eventInfo = eventDesc + "/at" + eventDate + " " + eventStartTime + "-" + eventEndTime;

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Event e = (Event) t;
        System.out.println(e.toString());
        assertEquals(e.getDescription(), eventDesc);
        Assertions.assertEquals(e.getDate().toString(), eventDate);
    }

    /*
        Deadlines:
        tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
     */
    @Test
    public void createDeadlineTest() {
        String eventType = "deadline";
        String eventDesc = "submit report";
        String eventEndTime = "21:00";
        String eventDate = "2019-12-01";
        String eventInfo = eventDesc + "/by" + eventDate + " " + eventEndTime;

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Deadline e = (Deadline) t;
        System.out.println(e.toString());
        assertEquals(e.getDescription(), eventDesc);
        Assertions.assertEquals(e.getDate().toString(), eventDate);
    }
}