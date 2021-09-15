public class Task {

    private String[] itemList = new String[100];
    private int indx = 0;
    String horiLine = "\t-----------------------------------------";
    String buff = "\t";

    public Task(){}

    public void addTask(String item){
        if(!(item.trim().equalsIgnoreCase("list") || item.trim().equalsIgnoreCase("bye")
                || item.trim().isEmpty())){
            itemList[indx] = item;
            indx++;
            System.out.println(horiLine + "\n" + buff + "added: " + item + "\n" + horiLine);
        }
    }

    public String showList(){
        String output = horiLine;

        for(int i = 0; i < indx; i++){
            String tem = itemList[i];
            int indx = i + 1;
            output += ("\n" +  buff + Integer.toString(indx) + ". "+ tem );
        }
        output += ("\n" + horiLine);
        return output;
    }
}
