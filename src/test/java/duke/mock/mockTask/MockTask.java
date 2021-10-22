package duke.mock.mockTask;

public abstract class MockTask {
    private String desc;
    private Integer id;
    private Boolean done = false;

    private MockTask() {
    }

    protected MockTask(String desc, Integer id, Boolean done) {
        this.desc = desc;
        this.id = id;
        this.done = done;
    }

    public String getDesc() {
        return this.desc;
    }

    public Boolean getDone() {
        return this.done;
    }

    public Integer getId() {
        return this.id;
    }
}
