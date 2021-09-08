package classes;

public class UserCommand {
    private CommandType type;
    private String keyword;
    private String args;

    public UserCommand(CommandType type, String keyword) {
        this.keyword = keyword;
        this.type = type;
    }

    public UserCommand(CommandType type, String keyword, String args) {
        this(type, keyword);
        this.args = args;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
