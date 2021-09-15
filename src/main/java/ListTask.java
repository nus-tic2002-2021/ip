public class ListTask extends Task{

    private Task[] itemList = new Task[100];
    private int indx = 0;
    String horiLine = "\t-----------------------------------------";
    String buff = "\t";

    public ListTask(){}

    public void addTask(String item){
        String[] tmp = item.trim().split(" ");
        switch (tmp[0].trim()){
            case "done":
                itemList[Integer.parseInt(tmp[1]) - 1].setDone(true);
                System.out.println("Nice! I've marked this task as done: " + "\n"
                        + "[X] " + itemList[Integer.parseInt(tmp[1]) - 1].getDescription());
                break;

            case "list":
                System.out.println(showList());
                break;

            default:
                itemList[indx] = new Task(item);
                indx++;
                System.out.println(horiLine + "\n" + buff + "added: " + item + "\n" + horiLine);
                break;
        }
    }

    public String showList(){
        String output = horiLine;

        for(int i = 0; i < indx; i++){
            String tem = itemList[i].getDescription();
            int indx = i + 1;
            output += ("\n" +  buff + Integer.toString(indx) + ".["+ itemList[i].getStatusIcon() +"] "+ tem );
        }
        output += ("\n" + horiLine);
        return output;
    }
}
