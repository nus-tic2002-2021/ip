
import java.util.Scanner;

public class Duke {

    protected static Scanner input = new Scanner(System.in);
    protected static Printer printer = new Printer();
    protected static boolean isNotBye = true;
    protected static String cmd;

    public static void main(String[] args) {

        while (isNotBye) {
            try {
                printer.printNewCmdHeader();
                cmd = input.nextLine();
                isNotBye = Parser.parse(cmd.toLowerCase());
            }
            catch (DukeException e) {
                printer.printInvalidCmd();
            }
        }

    }

}
