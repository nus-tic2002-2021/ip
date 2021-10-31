package duke.Ui;

import java.util.Arrays;

public class Ui {
    public static void Welcome (){
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
    }

    public static void goodBye (){
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
    }

    public static void errorMessage (DukeException e) {
        System.err.println("\t" + e);
        System.out.println("=======================================================");
    }

    public static void validateEventCommand(String[] command) {
        if (command.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty. Please re-enter:");
        } else if (!Arrays.asList(command).contains("/at")) {
            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty. Please re-enter:");
        }
    }

    public static void validateDeadlineCommand(String[] command) {
        if (command.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty. Please re-enter:");
        } else if (!Arrays.asList(command).contains("/by")) {
            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty. Please re-enter:");
        }
    }

    public static void validateTodoCommand(String[] command) {
        if (command.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty. Please re-enter:");
        }
    }

}
