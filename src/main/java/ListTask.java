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
                try {
                    if ((Integer.parseInt(tmp[1]) - 1) < indx) {
                        itemList[Integer.parseInt(tmp[1]) - 1].setDone(true);
                        System.out.println(buff + "Nice! I've marked this task as done: " + "\n" +
                                buff + "[X] " + itemList[Integer.parseInt(tmp[1]) - 1].getDescription());
                    }else if (indx > 0) {
                        System.out.println(buff + "Item No. " + Integer.parseInt(tmp[1]) + " is not added in the list yet." + "\n" +
                                buff + "Select between " + 0 + " and " + indx + "to mark as done.");
                    }else{
                        System.out.println(buff + "Empty! No item in the list.");
                     }
                }catch (NumberFormatException e){
                    System.out.println("This is not a number!");
                    System.out.println(e.getMessage());
                }
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
