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
        String eventTime = "2/12/2019 1800";
        String eventInfo = eventDesc + "/at" + eventTime;

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Event e = (Event) t;
        System.out.println(e.toString());
        assertEquals(e.getDescription(), eventDesc);
        assertEquals(e.getDate(), eventTime);
    }

    /*
        Deadlines:
        tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
     */
    @Test
    public void createDeadlineTest() {
        String eventType = "deadline";
        String eventDesc = "submit report";
        String eventTime = "2/12/2019 1800";
        String eventInfo = eventDesc + "/by" + eventTime;

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Event e = (Event) t;
        System.out.println(e.toString());
        assertEquals(e.getDescription(), eventDesc);
        assertEquals(e.getDate(), eventTime);
    }
}