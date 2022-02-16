package functions;

import Common.Message;
import data.Acronym;
import data.DeadLine;
import data.Event;
import data.Task;
import parser.Parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SearchFilteredTaskByDateTime extends ListTask{
    /**
     * One of the feature to be implemented in DUKE by me. when user used view command, DUKE will return schedule of a specific date
     * @param date user wants to see what kind of schedule he/she has for the specific date
     * @param list takes in as readonly param from ListTask class Task holder but can still be called setter/getter to change value
     */
    public static void ViewScheduleByDate(String date, final List<Task> list){
        List<Task> temporaryList = new ArrayList<>();
        LocalDate newDateToView = Parser.parseStringDatetoLocaLDate(date);
        for(Task singleItem : list){
              if(singleItem.getAcronym().equals(Acronym.D)){
                  DeadLine task = (DeadLine)singleItem;
                  if(task.getbywhen().toLocalDate().equals(newDateToView)){
                      temporaryList.add(singleItem);
                  }
              }else if(singleItem.getAcronym().equals(Acronym.E)){
                  Event task = (Event) singleItem;
                  if(task.getAtwhen().toLocalDate().equals(newDateToView)){
                      temporaryList.add(singleItem);
                  }
              }
        }
        Message.displayTaskAfterFiltered(temporaryList,"view", date);
    }
}
