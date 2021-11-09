package duke.action;

import duke.ui.Message;

public class Parser {

    public Parser (){

    }

    public String processUserCommand(String userInput){
        if (userInput.equals("bye")) {
            return "bye";
        }

        if (userInput.equals("list")) {
            return "list";
        } else if (userInput.startsWith("set")) {
            return "set";
        } else if (userInput.startsWith("done")) {
            return "done";
        } else if (userInput.startsWith("todo")) {
            return "todo";
        } else if (userInput.startsWith("save")) {
            return "save";
        } else if (userInput.startsWith("load")) {
            return "load";
        } else if (userInput.startsWith("find")) {
            return "find";
        } else if (userInput.startsWith("event")) {
            return "event";
        } else if (userInput.startsWith("delete")) {
            return "delete";
        } else if (userInput.startsWith("deadline")) {
            return "deadline";
        } else {
            return "invalid";
        }
    }

}
