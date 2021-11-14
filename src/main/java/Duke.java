import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import app.DukeException;
import app.Storage;
import task.TaskCommands;
import app.UI;

public class Duke {

    private Storage storage;
    private TaskCommands tasks;
    private UI ui;

    public static void main(String[] args) {
        UI ui = new UI();
        ui.run();
    }

}



