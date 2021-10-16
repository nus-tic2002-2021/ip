package duke.test;
import duke.DateFun;
import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
public class DateFunTest {

    @Test
    public void stringToLocalDateTest(){
        assertEquals(LocalDate.of(2021, 10, 14), DateFun.stringToLocalDate("2021-10-14"));
        assertEquals(LocalDate.of(2021, 11, 15), DateFun.stringToLocalDate("2021-11-15"));
    }

    @Test
    public void LocalDateToStringTest(){
        assertEquals("Oct 11 2000", DateFun.LocalDateToString(LocalDate.of(2000, 10, 11)));
        assertEquals("Jan 31 2021", DateFun.LocalDateToString(LocalDate.of(2021, 1, 31)));
    }
}