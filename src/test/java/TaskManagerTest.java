import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

    @Test
    public void findTest() {
        // Event 1
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

        // Event 2
        eventType = "event";
        eventDesc = "project meeting";
        String eventStartTime = "18:00";
        eventEndTime = "21:00";
        eventDate = "2019-12-01";
        eventInfo = eventDesc + "/at" + eventDate + " " + eventStartTime + "-" + eventEndTime;

        t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Event event = (Event) t;
        System.out.println(event.toString());
        assertEquals(event.getDescription(), eventDesc);
        Assertions.assertEquals(event.getDate().toString(), eventDate);

        ArrayList<Task> tasks = tm.find("project");
        assertEquals(tasks.toArray().length, 1, "too many returns");
        assertEquals(tasks.get(0).getId(), 1, "did not get expected id");
    }
}