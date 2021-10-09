package duke;

import duke.command.*;
import duke.exception.InvalidException;
import duke.exception.MissReqException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    public static Task getTask(String req) throws MissReqException, InvalidException {
        String[] reqs = req.trim().split(" ");
        String type = reqs[0];
        String description;
        if(type.equals("todo")){
            if(reqs.length == 1){
                throw new MissReqException("todo");
            }
            description = req.replaceFirst("todo", "").trim();
            return new Todo(description);
        } else if(type.equals("deadline")){
            if(reqs.length == 1){
                throw new MissReqException("deadline");
            }
            String[] descriptions = req.replaceFirst("deadline", "").trim().split("/by ");
            description = descriptions[0].trim();
            String by = descriptions[1];
            return new Deadline(description, by);
        } else if(type.equals("event")){
            if(reqs.length == 1){
                throw new MissReqException("event");
            }
            String[] descriptions = req.replaceFirst("event", "").trim().split("/at ");
            description = descriptions[0].trim();
            String at = descriptions[1];
            return new Event(description, at);
        } else{
            throw new InvalidException();
        }
    }

    public static Command parse(String req) throws MissReqException, InvalidException {
        Command command;
        String[] reqs = req.split(" ");
        if(req.equals("bye")){
            return new ExitCommand(req);
        }
        else if (reqs[0].equals("list")) {
            return new ListCommand(req);
        } else if (reqs[0].equals("done")) {
            int idx = Integer.parseInt(req.substring(4).trim()) - 1;
            return new DoneCommand(req, idx);
        } else if (reqs[0].equals("delete")) {
            int idx = Integer.parseInt(reqs[1]) - 1;
            return new DeleteCommand(req, idx);
        } else {
            Task task = getTask(req);
            return new AddCommand(req, task);
        }
    }


}
