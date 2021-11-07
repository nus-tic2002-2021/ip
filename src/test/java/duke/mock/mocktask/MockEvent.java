package duke.mock.mocktask;


import java.time.LocalDateTime;

import duke.testhelper.help.codeundertest.ParserUnderTest;

public class MockEvent extends MockTask {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Mock event
     *
     * @param desc desc
     * @param id   id
     * @param done done
     * @param from from
     * @param to   to
     */
    public MockEvent(String desc, Integer id, Boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc, id, done);
        this.from = (from);
        this.to = (to);
    }

    public String getToDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this.to);
    }

    public String getFromDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this.from);
    }
}
