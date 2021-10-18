import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskListing = new ArrayList<Task>();
    private static int count = 0;
    private static String lineBreak = "-------------------";
    public static void main(String[] args) {
        String logo = "  _       _ _      _ _______________________________________ \n"
                    + " | |  _  | | |    | |  __  |        |  __  |  __  |___   ___|\n"
                    + " | | | | | | |----| | |__| |  ------| |__| | |__|/    | |    \n"
                    + " | |_| |_| | |----| | |  | |------  | |  | | |__|\\ ___| |___\n"
                    + " |_________|_|    |_|_|  |_|________|_|  |_|______|_________|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can do for you?");

        Scanner in;
        String line;
        while(true){
            in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye")) {
                printThis("Bye. Hope to see you again soon!",true);
                break;
            }else if(line.equalsIgnoreCase("list")){
                printList();
                in = new Scanner(System.in);
                markTask(in.nextLine());
            }
            else{
                addTask(line);
            }
        }
    }
    public static void printThis(String st, Boolean withLB){
        if(withLB) System.out.println(lineBreak+"\n" +st+"\n"+lineBreak);
        else System.out.println(st);
    }

    public static void printList() {
        int c = 1;
        printThis(lineBreak,false);
        for (Task tk : taskListing) {
            System.out.println(c+": ["+tk.getStatusIcon()+"] " + tk.getItem());
            c ++;
        }
        printThis(lineBreak,false);
    }

    public static void markTask(String str){
        String[] splited = str.split("\\s+");
        if(splited.length == 2){
            System.out.println("here " + splited[0] + " " + splited[1]);

            if (splited[0].equalsIgnoreCase("done")) {
                int num;
                try {
                    num = Integer.parseInt(splited[1]) - 1;
                } catch (NumberFormatException nfe) {
                    printThis("Invalid number, Kindly enter 'List' to re-access",true);
                    return;
                }

                if(num >= 0 && num < taskListing.size() ){
                    printThis(lineBreak,false);
                    printThis("Nice! I've marked this task as done:",false);
                    taskListing.get(num).setDone();
                    printThis("[X] " + taskListing.get(num).getItem(),false);
                    printThis(lineBreak,false);
                }else{
                    printThis("Invalid Number",true);
                }
            }else{
                printThis("Invalid command please enter 'List' again",true);
            }
        }
    }

    public static void addTask(String item) {
        taskListing.add(new Task(item));
        printThis("added: " + item , true);
    }

    public static void removeTask(int num){
        taskListing.remove(num-1);
    }

}
