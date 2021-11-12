package duke;

import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleTest {
    @Test
    @DisplayName("Sample JUnit Test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void dummyFailTest() {
        assertEquals(2, 1);
    }

    @Test
    @Disabled("Not implemented yet")
    public void shouldShowSimpleAssertion() {
        Assertions.assertEquals(1, 1);
        Assertions.assertEquals(1, 2);
    }

    @Test
    @DisplayName("Should check all items in the list")
    public void shouldCheckAllItemsInTheList() {
        List<Integer> numbers = List.of(2, 3, 5, 7);

        Assertions.assertAll(() -> assertEquals(2, numbers.get(0)),
                () -> assertEquals(300, numbers.get(1)),
                () -> assertEquals(5, numbers.get(2)),
                () -> assertEquals(700, numbers.get(3)));
    }

    @Test
    @DisplayName("Should only run the test if some criteria are met 1")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet1() {
        Assumptions.assumeTrue(13 > 10);
        assertEquals(2,1);
    }

    @Test
    @DisplayName("Should only run the test if some criteria are met 2")
    void shouldOnlyRunTheTestIfSomeCriteriaAreMet2() {
        Assumptions.assumeTrue(13 < 10);
        assertEquals(2,1);
    }


}
