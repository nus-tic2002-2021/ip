package duke.taskmanagertest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.TaskManager;
import duke.task.model.Event;
import duke.testhelper.TestStream;
import duke.testhelper.help.codeundertest.ParserUnderTest;

public class TaskManagerTest extends TestStream {

    @Test
    public void addToDos() {
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewToDo(desc0);
        tm.addNewToDo(desc1);
        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());
    }


    @Test
    public void addEvents() throws Exception {
        TaskManager tm = new TaskManager();

        String desc0 = "desc0";
        String desc1 = "desc1";
        tm.addNewEvent(desc0, ParserUnderTest.parseStringAsLocalDateTime("20210101"),
            ParserUnderTest.parseStringAsLocalDateTime("20210101"));

        LocalDateTime from1 = ParserUnderTest.parseStringAsLocalDateTime("20210101");
        LocalDateTime to1 = ParserUnderTest.parseStringAsLocalDateTime("20210101");
        tm.addNewEvent(desc1, from1, to1);

        assertEquals(desc0, tm.getTaskById(0).getTaskDescription());
        assertEquals(desc1, tm.getTaskById(1).getTaskDescription());

        assertEquals(from1, ((Event) tm.getTaskById(1)).getFrom());
        assertEquals(to1, ((Event) tm.getTaskById(1)).getTo());
    }

    @Test
    public void findTasksByDescription() throws Exception {
        TaskManager tm = new TaskManager();

        String keyword = "MAGIK";
        LocalDateTime from0 = ParserUnderTest.parseStringAsLocalDateTime("20210101");
        LocalDateTime to0 = ParserUnderTest.parseStringAsLocalDateTime("20210202");
        LocalDateTime from1 = ParserUnderTest.parseStringAsLocalDateTime("20210101");
        LocalDateTime to1 = ParserUnderTest.parseStringAsLocalDateTime("20210202");
        tm.addNewEvent("desc0", from0, to0);
        tm.addNewEvent("desc1 " + keyword + " a", from1, to1); // with keyword

        assertEquals(1, tm.getTasksWithWord(keyword).size());
    }

}
