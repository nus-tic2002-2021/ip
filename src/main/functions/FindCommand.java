package functions;

import Common.Message;
import data.Task;

import java.util.*;

public class FindCommand extends ListTask {

    /**
     * Instead of a class constructor, I have created a static method since there aren't any variable usages.
     * From this method, displayTaskAfterFiltered method from Message class is called
     * @param key set of string is passed to this method From ListTask class when FIND command is called
     * @param itemList takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     */
    public static void FindKeywordCommand(Set<String> key, final List<Task> itemList){
        List<Task> temporaryList = new ArrayList<>();
        for(Task tem : itemList){
            Set<String> splitWordsInEachTask = new HashSet<>(Arrays.asList(tem.getDescription().split(" ")));
            if(!Collections.disjoint(key,splitWordsInEachTask)){
                temporaryList.add(tem);
            }
        }
        Message.displayTaskAfterFiltered(temporaryList,"find","");
    }


}
