import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

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

        ArrayList<Task> tasks = tm.findTask("project");
        assertEquals(tasks.toArray().length, 1, "too many returns");
        assertEquals(tasks.get(0).getId(), 1, "did not get expected id");
    }

    @Test
    public void viewTest() {
        TaskManager tm = new TaskManager();
        Parser p = new Parser();
        ArrayList<Task> expected = new ArrayList<>();
        // Event 1
        String eventType = "deadline";
        String eventDesc = "submit report";
        String eventEndTime = "21:00";
        String eventDate = "2019-12-01";
        String eventInfo = eventDesc + "/by" + eventDate + " " + eventEndTime;

        Task t = tm.createTask(eventInfo, eventType);
        expected.add(t);
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
        expected.add(t);
        assertEquals(t.getType(), eventType);
        Event event = (Event) t;
        System.out.println(event.toString());
        assertEquals(event.getDescription(), eventDesc);
        Assertions.assertEquals(event.getDate().toString(), eventDate);

        // View
        ArrayList<Task> tasks = tm.viewTaskOn(p.stringToDate(eventDate));
        assertEquals(2, tasks.toArray().length, "too many returns");
        expected.forEach(expect -> {
            Task actual = tasks.stream().filter(task -> Objects.equals(task.getId(), expect.getId())).findFirst().orElse(null);
            assertNotNull(actual);

            Deadline ex = (Deadline) expect;
            Deadline a = (Deadline) actual;
            assertEquals(ex.getId(), a.getId(), "id mismatches");
            assertEquals(ex.getDescription(), a.getDescription(), "description mismatches");
            assertEquals(ex.getDate(), a.getDate(), "date mismatches");
            assertEquals(ex.getEndTime(), a.getEndTime(), "end_time mismatches");
        });
    }
}