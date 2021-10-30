package task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ListTest {
    ArrayList<Task> testList = new ArrayList<>();
    @Test
    public void addTaskTest(){
        testList.add(new Todo("todo"));
        testList.add(new Deadline("deadline", LocalDate.parse("02-12-2021")));
        testList.add(new Event("event", LocalDate.parse("02-10-2021")));

        assertEquals("todo", testList.get(0).getDescription());
        assertEquals("deadline", testList.get(1).getDescription());
        assertEquals("event",testList.get(2).getDescription());
    }
}
