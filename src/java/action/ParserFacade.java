package src.java.action;

import src.java.storage.FileAccess;

public class ParserFacade {

    private FileAccess fileAccess;
    private Parser parser;

    public ParserFacade(){
        fileAccess = new FileAccess();
        parser = new Parser(fileAccess);
    }

    public void StartDuke() {
        parser.ShowGreetMessage();
    }

    public void RunDuke(){
        parser.OnCreateDuke();
    }

    public void EndDuke() {
        parser.ShowByeMessage();
    }

    public FileAccess getFileAccess (){
        return fileAccess;
    }

    public Parser getDukeObject (){
        return parser;
    }
}
