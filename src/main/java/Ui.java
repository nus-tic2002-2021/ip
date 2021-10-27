public class Ui {
    public static void StartDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo + " chatbot! \n");
    }

    public static void Greet() {
        String start = "Hello, I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(Duke.line + start + Duke.line);
    }
}
