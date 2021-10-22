package duke.mock.mockTask;

import duke.testhelper.help.CodeUnderTest.ParserUnderTest;

import java.time.LocalDateTime;

public class MockDeadline extends MockTask{
    private LocalDateTime by;

    public MockDeadline(String desc, Integer id, Boolean done, LocalDateTime by) {
        super(desc,id,done);
        this.by = by;
    }

    public String getbyDateString(){
        return ParserUnderTest.prettifyLocalDateTime(this.by);
    }
}
