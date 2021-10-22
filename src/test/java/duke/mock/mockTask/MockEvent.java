package duke.mock.mockTask;


import duke.testhelper.help.CodeUnderTest.ParserUnderTest;

import java.time.LocalDateTime;

public class MockEvent extends MockTask{
    private LocalDateTime _from;
    private LocalDateTime _to;
    public MockEvent(String desc, Integer id, Boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc,id,done);
        this._from = (from);
        this._to = (to);
    }

    public String getToDateString(){
        return ParserUnderTest.prettifyLocalDateTime(this._to);
    }
    public String getFromDateString(){
        return ParserUnderTest.prettifyLocalDateTime(this._from);

    }
}
