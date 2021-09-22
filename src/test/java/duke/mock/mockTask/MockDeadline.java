package duke.mock.mockTask;

public class MockDeadline extends MockTask{
    private String by;

    public MockDeadline(String desc, Integer id, Boolean done, String byDateString) {
        super(desc,id,done);
        this.by = byDateString;
    }

    public String getbyDateString(){
        return this.by;
    }
}
