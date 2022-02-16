package functions;

import Common.Message;
import data.Task;

import static ui.Ui.BUFF_PLUS_HORRIZONTALLINE;

import java.util.List;


public class DeleteCommand extends ListTask{

    /**
     * Instead of a class constructor, I have created a static method since there aren't any variable usages.
     * From this method, taskremovedinMessage method from Message class is called
     * @param item takes in as param from ListTask class Task holder but can still be called setter/getter to change value
     * @param idx is a specific index from the task list to be removed
     */
    public static void deleteTask(List<Task> item, int idx)
    {
        int idxTodelete = idx -1;
        if(idxTodelete < item.size() && idxTodelete >= 0)
        {
            Message.taskremovedinMessage(item,idxTodelete);
            item.remove(idxTodelete);

        }else {
            throw new NullPointerException(Message.incorrectSelection());
        }
        System.out.println(BUFF_PLUS_HORRIZONTALLINE);
    }
}
