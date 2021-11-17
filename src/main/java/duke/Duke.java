package duke;

import app.UI;

public class Duke {

    private static UI ui;

    /** @param args to create program to run
     * Creating a new UI in main Duke & running
     */
    public static void main(String[] args) {
        ui = new UI();
        ui.run();
    }

}



