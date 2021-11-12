package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    @DisplayName("Sample JUnit Test")
    @Disabled("Sample Test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Disabled("Sample Test")
    public void dummyFailTest() {
        assertEquals(2, 1);
    }

    @Test
    @Disabled("Sample Test")
    public void shouldShowSimpleAssertion() {
        assertEquals(1, 1);
        assertEquals(1, 2);
    }

    @Test
    @DisplayName("Should check all items in the list")
    @Disabled("Sample Test")
    public void shouldCheckAllItemsInTheList() {
        List<Integer> numbers = List.of(2, 3, 5, 7);

        Assertions.assertAll(() ->
                assertEquals(2, numbers.get(0)), () ->
                assertEquals(300, numbers.get(1)), () ->
                assertEquals(5, numbers.get(2)), () ->
                assertEquals(700, numbers.get(3)));
    }

    @Test
    @DisplayName("Should only run the test if some criteria are met 1")
    @Disabled("Sample Test")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet1() {
        Assumptions.assumeTrue(13 > 10);
        assertEquals(2, 1);
    }

    @Test
    @DisplayName("Should only run the test if some criteria are met 2")
    @Disabled("Sample Test")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet2() {
        Assumptions.assumeTrue(13 < 10);
        assertEquals(2, 1);
    }


}
