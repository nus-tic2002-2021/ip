import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello I'm Alice\nWhat can i do for you?");
        System.out.println("=======================================================");

        text = in.nextLine();

        while(text != null){
            Echo(text);
            text = in.nextLine();
        }
    }

    public static void Echo(String text){
        if (text.equals("bye")){
            System.out.println("Bye, i will miss you.");
            System.out.println("=========================END===========================");
            return;
        } else {
            System.out.println(text);
            System.out.println("=======================================================");
            }
    }




}
