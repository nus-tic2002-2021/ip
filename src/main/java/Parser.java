public class Parser {
    public Command parse(String input) throws UnrecognizedException, InvalidFormatException{
        if (input.contains("|")){
            throw new InvalidFormatException("Please do not use '|' as it breaks the program");
        }
        String action;
        String[] inputArray;
        inputArray = input.split(" ", 2);
        action = inputArray[0].toLowerCase();
        switch(action){
            case "bye":
                return new ExitCommand("bye");
            case "todo":
            case "deadline":
            case "event":
                return new AddCommand(action, inputArray[1]);
            case "delete":
                return new DeleteCommand(action, inputArray[1]);
            case "done":
                return new ModifyCommand(action, inputArray[1]);
            case "list":
                return new ListCommand("list");
            default: throw new UnrecognizedException();
        }
    }
}
