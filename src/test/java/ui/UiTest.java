package ui;

import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStub;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void readTaskTest() {
        Task test = new TaskStub("Task Message");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Ui().readTask(test, 1);
        String expectedMessage = "Got it. I've added this task:" + System.lineSeparator()
                + "Task Message" + System.lineSeparator()
                + "Now you have 1 tasks in the list." + System.lineSeparator();
        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    public void readDeleteTest() {
        Task test = new TaskStub("Task Message");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Ui().readDelete(test, 0);
        String expectedMessage = "Noted. I've removed this task:" + System.lineSeparator()
                + "Task Message" + System.lineSeparator()
                + "Now you have 0 tasks in the list." + System.lineSeparator();
        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    public void readDone() {
        Task test = new TaskStub("Completed Task Message");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Ui().readDone(test);
        String expectedMessage = "Nice! I've marked this task as done:" + System.lineSeparator()
                + "Completed Task Message" + System.lineSeparator();
        assertEquals(expectedMessage, outContent.toString());
    }
}