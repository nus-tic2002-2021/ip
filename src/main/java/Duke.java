import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);
        //Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();


        System.out.println("        _..._\n" +
                "      .'     '.      _\n" +
                "     /    .-\"\"-\\   _/ \\\n" +
                "   .-|   /:.   |  |   |\n" +
                "   |  \\  |:.   /.-'-./\n" +
                "   | .-'-;:__.'    =/\n" +
                "   .'=  *=|DUKE _.='\n" +
                "  /   _.  |    ;\n" +
                " ;-.-'|    \\   |\n" +
                "/   | \\    _\\  _\\\n" +
                "\\__/'._;.  ==' ==\\\n" +
                "         \\    \\   |\n" +
                "         /    /   /\n" +
                "         /-._/-._/\n" +
                "         \\   `\\  \\" + "\t\tAye there. I'm DUKE.\n" +
                "          `-._/._/" + "\t\tHow can i help you?");
        System.out.println("___^/\\___^--____/\\____O______________/\\/\\---/\\___________---______________ \n" +
                "   /\\^   ^  ^    ^                  ^^ ^  '\\ ^          ^       ---         \n");

        //takes in user input
        text = in.nextLine();

        //use while loop to program running
        while(!text.equalsIgnoreCase("bye")){
            //print the current list by calling printList method, if user inputs "list"
            try {
                Task.tasks(text, tasks);
            } catch (DukeException e){
                System.err.println("\t" + e);
                System.out.println("=======================================================");
            } finally {
                text = in.nextLine();
            }
        }

        System.out.println("                                                   ,:\n" +
                "                                                 ,' |\n" +
                "                                                /   :\n" +
                "                                             --'   /\n" +
                "                                             \\/ />/\n" +
                "                                             / /_\\\n" +
                "                                          __/   /\n" +
                "                                          )'-. /\n" +
                "                                          ./  :\\\n" +
                "                                           /.' '\n" +
                "                                         '/'\n" +
                "                                         +\n" +
                "                                        '\n" +
                "                                      `.\n" +
                "                                  .-\"-\n" +
                "                                 (    |\n" +
                "                              . .-'  '.\n" +
                "                             ( (.   )8:\n" +
                "                         .'    / (_  )\n" +
                "                          _. :(.   )8P  `\n" +
                "                      .  (  `-' (  `.   .\n" +
                "                       .  :  (   .a8a)\n" +
                "                      /_`( \"a `a. )\"'\n" +
                "                  (  (/  .  ' )=='\n" +
                "                 (   (    )  .8\"   +\n" +
                "                   (`'8a.( _(   (\n" +
                "                ..-. `8P    ) `  )  +\n" +
                "              -'   (      -ab:  )\n" +
                "            '    _  `    (8P\"Ya\n" +
                "          _(    (    )b  -`.  ) +\n" +
                "         ( 8)  ( _.aP\" _a   \\( \\   *\n" +
                "       +  )/    (8P   (88    )  )\n" +
                "          (a:f   \"     `\"       `\n" +
                "Bye, i will miss you.\n");
        System.out.println("=========================TERMINATED===========================");
        System.exit(0);
    }
}
