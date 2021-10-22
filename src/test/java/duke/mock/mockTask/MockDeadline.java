package duke.mock.mockTask;

import java.time.LocalDateTime;
import duke.testhelper.help.CodeUnderTest.ParserUnderTest;

public class MockDeadline extends MockTask {
    private final LocalDateTime by;

    public MockDeadline(String desc, Integer id, Boolean done, LocalDateTime by) {
        super(desc, id, done);
        this.by = by;
    }

    public String getbyDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this.by);
    }
}
