import java.util.Arrays;

public class List {
    public String[] List = new String[100];
    int count = 0;

    public void addList(String list){
        this.List[count] = list;
        this.count++;
        System.out.println("added: " + list +"\n________________________________________________");
    }

    public void printList () {
        for (int i=0; i<count; i++) {
            System.out.println((i+1) + ". " + List[i]);
        }
    }
}
