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
        if(count == 0){
            System.out.println("\tThere are no items in your list");
            return;
        }
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

    public void deleteTask(int ref){
        if(count == 0 || ref >= count) {
            throw new NullPointerException();
        }
        System.out.println("\tNoted. I've removed this task: ");
        System.out.println("\t\t" + this.List[ref].printTask());
        Task[] Temp = new Task[count];
        for (int i=0; i<count; i++) {
            if(ref == i) {
                Temp[ref] = List[i++];
                count = count - 1;
            }
            if (count > 0) {
                Temp[i] = List[i];
            }
        }
        List = Temp;
    }
}
