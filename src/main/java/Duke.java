import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello I'm Alice\nWhat can i do for you?");
        System.out.println("=======================================================");

        text = in.nextLine();

        while(text != null){
            if (text.equals("bye")){
                System.out.println("Bye, i will miss you.");
                System.out.println("=========================END===========================");
                return;
            } else {
                System.out.println(text);
                System.out.println("=======================================================");
                text = in.nextLine();
            }
        }
    }

    /*  can i create a method called Echo rather than Echo inside main()?
    public static void Echo(Scanner args){
        while(text != null){
            if (text.equals("bye")){
                System.out.println("Bye, i will miss you.");
                return;
            } else {
                System.out.println(text);
                text = in.nextLine();
            }
        }
    }
    */

}
