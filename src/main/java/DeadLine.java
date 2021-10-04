public class DeadLine extends Task{
    private String date;

    public DeadLine(String des, String acro, String dat){
        super(des,acro);
        date = dat;
    }

    @Override
    public String getDate(){
        return  date;
    }

    public void setClock(String input){
        date = input;
    }



}
