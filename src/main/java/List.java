import java.util.Arrays;

public class List extends Task {
    public Task[] List = new Task[100];
    int count = 0;

    public void addList(String description){
        this.List[count] = new Task(description);
        this.count++;
        System.out.println("added: " + description +"\n________________________________________________");
    }

    public void printList () {
        System.out.println("Here are the tasks in your list:");
        for (int i=0; i<count; i++) {
            System.out.println((i+1) + ". " + List[i].printTask());
        }
    }

    public void setDone(int ref) {
        this.List[ref].setDone();
        System.out.println("\t" + this.List[ref].printTask());
    }
}
