import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskManagerTest {

    @Test
    public void createTodo() {
        String eventType = "todo";
        String eventInfo = "read books";

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        Todo e = (Todo) t;
        assertEquals(e.getType(), eventType);
        assertEquals(e.getDescription(), eventInfo);
    }

    @Test
    public void createEventTest() {
        String eventType = "event";
        String eventDesc = "project meeting";
        String eventTime = "Mon 2-4pm";
        String eventInfo = eventDesc + "/at" + eventTime;

        TaskManager tm = new TaskManager();
        Task t = tm.createTask(eventInfo, eventType);
        assertEquals(t.getType(), eventType);
        Event e = (Event) t;
        assertEquals(e.getDescription(), eventDesc);
        assertEquals(e.getAt(), eventTime);
    }
}