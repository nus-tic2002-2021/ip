package duke;

import duke.command.*;
import duke.exception.InvalidException;
import duke.exception.MissReqException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    /**
     *
     * @param req Extract task from command
     * @return a task object
     * @throws MissReqException
     * @throws InvalidException
     */
    public static Task getTask(String req) throws MissReqException, InvalidException {
        String[] reqs = req.trim().split(" ");
        String type = reqs[0];
        String description;
        assert reqs.length > 1 : "invalid input";
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
            assert descriptions.length == 2: "invalid input";
            description = descriptions[0].trim();
            String by = descriptions[1];
            return new Deadline(description, by);
        } else if(type.equals("event")){
            if(reqs.length == 1){
                throw new MissReqException("event");
            }
            String[] descriptions = req.replaceFirst("event", "").trim().split("/at ");
            assert descriptions.length == 2: "invalid input";
            description = descriptions[0].trim();
            String at = descriptions[1];
            return new Event(description, at);
        } else{
            throw new InvalidException();
        }
    }

    /**
     * Extract command
     * @param req the command input of user
     * @return Variable of Command
     * @throws MissReqException
     * @throws InvalidException
     */
    public static Command parse(String req) throws MissReqException, InvalidException {
        String[] reqs = req.split(" ");
        if(req.equals("bye")){
            return new ExitCommand(req);
        }
        if(reqs[0].equals("list")) {
            assert reqs.length == 1: "invalid input";
            return new ListCommand(req);
        }
        if(reqs[0].equals("done")) {
            assert reqs.length == 2: "invalid input";
            int idx = Integer.parseInt(req.substring(4).trim()) - 1;
            return new DoneCommand(req, idx);
        }
        if(reqs[0].equals("delete")) {
            assert reqs.length == 2: "invalid input";
            int idx = Integer.parseInt(reqs[1]) - 1;
            return new DeleteCommand(req, idx);
        }
        if(reqs[0].equals("find")){
            assert reqs.length > 1: "invalid input";
            String search = reqs[1];
            return new FindCommand(req, search);
        }
        Task task = getTask(req);
        return new AddCommand(req, task);
    }


}
