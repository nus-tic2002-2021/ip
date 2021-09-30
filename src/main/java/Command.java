
public enum Command {
    BYE("bye"),
    LIST("list");

    private String cmd;

    private Command(String cmd) {
        this.cmd = cmd;
    }

    public String enumValue() {
        return this.cmd;
    }

}
