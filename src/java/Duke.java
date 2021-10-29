package src.java;

public class Duke {

    public static void main(String[] args) {

        DukeActionFacade dukeActionFacade = new DukeActionFacade();

        dukeActionFacade.StartDuke();
        dukeActionFacade.RunDuke();
        dukeActionFacade.EndDuke();
    }
}
