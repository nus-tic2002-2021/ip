import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nHow can I help you today?");
        String input;
        Scanner in = new Scanner(System.in);

        String[] list = new String[100];
        int listCount = 1;


        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            input = in.nextLine();
            if (input.toLowerCase(Locale.forLanguageTag(input)).contains("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.toLowerCase(Locale.forLanguageTag(input)).contains("list")){
                for (int count = 1; count<listCount; count++) {
                    if (count ==1){
                    System.out.println(count + ". " +"[X]"+ list[count]);
                    }else {
                        System.out.println(count + ". " +"[ ]"+ list[count]);
                    }
                }
            }
            else {
                System.out.println(input);
                list[listCount] = input;
                listCount++;
                System.out.println("added: " + input);
            }
        }
    }

}