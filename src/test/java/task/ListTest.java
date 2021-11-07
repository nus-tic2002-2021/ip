package task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ListTest {
    ArrayList<Task> testList = new ArrayList<>();
    private static final DateTimeFormatter  DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("H:mm");

    @Test
    /**
     *  Test for adding different types of task.
     */
    public void addTaskTest(){

        testList.add(new Todo("todo"));
        testList.add(new Deadline("deadline", LocalDateTime.parse(
                "2021-12-20 1359",DATE_TIME_FORMATTER)));
        testList.add(new Event("event", LocalDateTime.parse(
                "2021-05-12 2359",DATE_TIME_FORMATTER), LocalTime.parse("01:23",TIME_FORMATTER)));

        assertEquals("todo", testList.get(0).getDescription());
        assertEquals("deadline", testList.get(1).getDescription());
        assertEquals("event",testList.get(2).getDescription());
        testList.clear();
    }

    @Test
    /**
     *  Test if task successfully set to done
     */
    public void setDoneTest(){

        Task t = new Todo("HELLO");
        assertEquals("0",t.getDone());
        t.setDone();
        assertEquals("1",t.getDone());
    }

}
