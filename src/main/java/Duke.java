
import java.util.Scanner;

public class Duke {

    protected static Scanner input = new Scanner(System.in);
    protected static boolean isNotBye = true;
    protected static String cmd;

    public static void main(String[] args) {
        Printer.printIntro();
        while (isNotBye) {
            try {
                Printer.printHeader();
                cmd = input.nextLine();
                isNotBye = Parser.parse(cmd.toLowerCase());
            }
            catch (DukeException e) {
                Printer.printInvalidCmd();
            }
        }
    }

}
