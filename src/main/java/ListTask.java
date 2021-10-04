public class ListTask extends Task{

    private Task[] itemList = new Task[100];
    private int indx = 0;
    String horiLine = "\t-----------------------------------------";
    String buff = "\t";

    public ListTask(){}

    public void addTask(String item){
        String[] tmp = item.trim().split(" "), tmp_string;
        switch (tmp[0].trim().toLowerCase()){
            case "done":
                try {
                    setTaskDone(tmp);
                }catch (NumberFormatException e){
                    System.out.println("This is not a number!");
                    System.out.println(e.getMessage());
                }
                break;

            case "list":
                System.out.println(showList());
                break;
            case "todo":
                tmp_string = item.split("todo");
                itemList[indx] = new Task(tmp_string[1],"T");
                indx++;
                printMessage("T");
                break;
            case "event":
                tmp_string = item.split("(?i)event | (?i)/at"); // (?i) ignore case sensitivity
                String[] tmp_s = tmp_string[2].trim().split(" ");
                itemList[indx] = new Event(tmp_string[1],"E",tmp_s[0],tmp_s[1]);
                indx++;
                printMessage("E");
                break;
            case "deadline":
                tmp_string = item.split("(?i)deadline | (?i)/by"); // (?i) ignore case sensitivity
                itemList[indx] = new DeadLine(tmp_string[1],"D",tmp_string[2]);
                indx++;
                printMessage("D");
                break;
//            default:
//                break;
        }
    }

    public String showList(){
        StringBuilder output = new StringBuilder(horiLine);

        for(int i = 0; i < indx; i++){
            String tem = itemList[i].getDescription().trim();
            String acro = itemList[i].getAcronym();
            String status = itemList[i].getStatusIcon();
            int indx = i + 1;
            output.append("\n").append(buff).append(indx).append(".[").append(acro).append("]").append("[").append(status).append("] ").append(tem);
            if(acro.equals("E")){
                output.append(" (at: ").append(itemList[i].getDate()).append(" ").append(itemList[i].getClock()).append(")");
            }else if(acro.equals("D")){
                output.append(" (by:").append(itemList[i].getDate()).append(")");
            }
        }

        output.append("\n").append(horiLine);
        return output.toString();
    }

    public void setTaskDone(String[] str){
        if ((Integer.parseInt(str[1]) - 1) < indx) {
            itemList[Integer.parseInt(str[1]) - 1].setDone(true);
            System.out.println(buff + "Nice! I've marked this task as done: "
                    + "\n" + buff + "[X] " + itemList[Integer.parseInt(str[1]) - 1].getDescription());
        }else if (indx >= 2 ) {
            System.out.println(buff + "Item No. " + Integer.parseInt(str[1]) + " is not in the list yet."
                    + "\n" + buff + "Try between " + 1 + " and " + indx + " to mark as done." + "\n" + showList());
        }else if(indx == 1){
            System.out.println(buff + "Item No. " + Integer.parseInt(str[1]) + " is not in the list yet."
                    + "\n" + buff + "Select 1 to mark as done." + "\n" + showList());
        }else{
            System.out.println(buff + "Empty! No item in the list.");
        }
    }

    public void printMessage(String acron){

        if(acron.equals("T")) {
            System.out.println(horiLine + "\n" + buff + "Got it. I've added this task: "
                    + "\n" + buff + "  [T][ ] " + itemList[indx - 1].getDescription() + "\n" + buff
                    + "Now you have " + (indx) + " tasks in the list." + "\n" + horiLine);
        }else if(acron.equals("D")){
            System.out.println(horiLine + "\n" + buff + "Got it. I've added this task: "
                    + "\n" + buff + "  [D][ ] "+ itemList[indx - 1].getDescription() + " (by: "+ itemList[indx - 1].getDate() +" )"
                    + "\n"+ buff +"Now you have "+ (indx) +" tasks in the list." + "\n" + horiLine);
        }else if(acron.equals("E")){
            System.out.println(horiLine + "\n" + buff + "Got it. I've added this task: "
                    + "\n" + buff + "  [E][ ] "+ itemList[indx - 1].getDescription() +" (at: "+ itemList[indx - 1].getDate() +" "+ itemList[indx - 1].getClock() + ")"
                    + "\n"+ buff +"Now you have "+ (indx) +" tasks in the list." + "\n" + horiLine);
        }
    }


}
