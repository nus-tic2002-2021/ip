import java.util.Scanner;

public class Duke {
    private static String[] task_list = new String[100];
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
                print_list();
            }
            else{
                add_task(line);
            }
        }
    }
    public static void printThis(String st, Boolean withLB){
        if(withLB) System.out.println(lineBreak+"\n" +st+"\n"+lineBreak);
        else System.out.println(st);
    }
    public static void add_task(String task){
        task_list[count] = task;
        count++;
        printThis("added: " + task , true);
    }
    public static void print_list(){
        System.out.println(lineBreak);
        for(int i = 0; i < count; i++){
           printThis(Integer.toString(i+1) + ". " + task_list[i],false);
        }
        System.out.println(lineBreak);
    }
}
