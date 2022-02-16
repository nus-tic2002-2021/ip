package task;

import com.tic2002.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    Todo todo;
    String description = "having lunch";

    @BeforeEach
    void setUp(){
        todo = new Todo(description);
    }
    @Test
    @DisplayName("Should print correct entries")
    void printTask() {
        assertEquals("[T][ ] having lunch", todo.printTask(),
                "should match output");
    }
}