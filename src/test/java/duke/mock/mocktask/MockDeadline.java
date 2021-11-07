package duke.mock.mocktask;

import java.time.LocalDateTime;

import duke.testhelper.help.codeundertest.ParserUnderTest;

public class MockDeadline extends MockTask {
    private final LocalDateTime by;

    /**
     * Mock deadline
     *
     * @param desc desc
     * @param id   id
     * @param done done
     * @param by   by
     */
    public MockDeadline(String desc, Integer id, Boolean done, LocalDateTime by) {
        super(desc, id, done);
        this.by = by;
    }

    public String getbyDateString() {
        return ParserUnderTest.prettifyLocalDateTime(this.by);
    }
}
