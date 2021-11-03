package duke.command;

import java.util.List;
import com.google.gson.JsonElement;
import duke.dukeutility.enums.ResponseType;


public abstract class CommandJsonResponse extends Command {
    private JsonElement jsonArg;

    protected CommandJsonResponse(ResponseType rt, List<String> args, JsonElement jsonArg) {
        super(rt, args);
        this.setJsonArg(jsonArg);
    }

    public JsonElement getJsonArg() {
        return this.jsonArg;
    }

    private void setJsonArg(JsonElement arg) {
        this.jsonArg = arg;
    }
}
