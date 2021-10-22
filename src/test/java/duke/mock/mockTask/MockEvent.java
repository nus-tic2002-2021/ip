package duke.mock.mockTask;


import java.time.LocalDateTime;
import duke.testhelper.help.CodeUnderTest.ParserUnderTest;

public class MockEvent extends MockTask {
    private final LocalDateTime _from;
    private final LocalDateTime _to;

    public MockEvent(String desc, Integer id, Boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc, id, done);
        this._from = (from);
        this._to = (to);
    }

    public String getToDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this._to);
    }

    public String getFromDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this._from);
    }
}
