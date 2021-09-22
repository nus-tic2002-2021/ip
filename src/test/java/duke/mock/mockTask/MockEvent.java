package duke.mock.mockTask;


public class MockEvent extends MockTask{
    private String _from;
    private String _to;
    public MockEvent(String desc, Integer id, Boolean done, String from, String to) {
        super(desc,id,done);
        this._from = from;
        this._to = to;
    }

    public String getToDateString(){
        return this._to;
    }
    public String getFromDateString(){
        return this._from;
    }
}
