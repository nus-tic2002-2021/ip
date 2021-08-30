import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    //define class-level variables using static 
    static int list_count = 0;
    static String[] list = new String[100];

    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);

        System.out.println("Yo, I'm Alice!\nHow can i help you?");
        System.out.println("=======================================================");

        //takes in user input
        text = in.nextLine();
        
        //use while loop to program running
        while(text != null){
            //print the current list by calling printList method, if user inputs "list"
            if (text.equalsIgnoreCase("list")) {
                printList(list);
            } else {
                //every other words will be added to the list by calling addToList method
                addToList(text);
            }
            text = in.nextLine();
        }
    }

    public static void echo(String text){
        //terminate the program if user inputs "bye"
        if (text.equalsIgnoreCase("bye")){
            System.out.println("Bye, i will miss you.");
            System.out.println("=========================END===========================");
            System.exit(0);
        } else {
            System.out.println("added: " + text);
            System.out.println("=======================================================");
            }
    }

    public static String[] addToList(String text){
        //print out added: text by calling Echo method
        echo(text);
        list[list_count] = text;
        list_count++;
        
        //return an array of first n elements
        return Arrays.copyOf(list, list_count);
    }

    public static void printList (String[] s){
        //print out every element in the array
        for (int i =0; i < s.length; i ++){
            if (s[i] != null){
                System.out.println(i+1 + ". " + s[i]);
            }
        }
    }

}
