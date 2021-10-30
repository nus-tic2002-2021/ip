package task;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.*;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListTest {
    ArrayList<Task> testList = new ArrayList<>();
    private static final DateTimeFormatter  DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HHmm");
    @Test
    public void addTaskTest(){

        testList.add(new Todo("todo"));
        testList.add(new Deadline("deadline", LocalDateTime.parse("2021-12-20 1359",DATE_TIME_FORMATTER)));
        testList.add(new Event("event", LocalDateTime.parse("2021-05-12 2359",DATE_TIME_FORMATTER)));

        assertEquals("todo", testList.get(0).getDescription());
        assertEquals("deadline", testList.get(1).getDescription());
        assertEquals("event",testList.get(2).getDescription());
    }
}
