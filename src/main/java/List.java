import java.util.Arrays;

public class List extends Task {
    public Task[] List = new Task[100];
    public int count = 0;

    public void addList(Task t){
        this.List[count] = t;
        printTask(count);
        this.count++;
    }

    public void printCount(){
        System.out.println("\tNow you have " + this.count + " tasks in the list.");
    }
    public void printList () {
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<count; i++) {
            System.out.println("\t" + (i+1) + ". [" + List[i].printTask());
        }
    }

    public void printTask(int ref) {
            System.out.println("\t\t" + List[ref].printTask());
        }

    public void setDone(int ref) {
        this.List[ref].setDone();
        System.out.println("\t" + this.List[ref].printTask());
    }
}
