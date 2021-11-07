package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    @Test
    /**
     *  Test for todo constructor
     */
    public void todoConstructor() {
        Todo test = new Todo("test description");
        assertEquals("test description", test.getDescription());
    }

}
