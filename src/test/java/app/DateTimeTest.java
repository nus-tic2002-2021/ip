package app;

import com.tic2002.task.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeTest {
    DateTime dateTime;
    String date = "2021-11-11";
    String time = "05:00";

    @BeforeEach
    void setUp() {
        dateTime = new DateTime();
    }
    @org.junit.jupiter.api.Test
    @DisplayName("Dates should return true")
    void testIsValidDate() {
        assertEquals(true, dateTime.isValidDate(date),
                "Correct date should work");
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Time should return true")
    void isValidTime() {
        assertEquals(true, dateTime.isValidTime(time),
                "Correct time should work");
    }

    @org.junit.jupiter.api.Test
    void toDate() {
    }

    @org.junit.jupiter.api.Test
    void toTime() {
    }

    @org.junit.jupiter.api.Test
    void toNewDateFormat() {
    }
}