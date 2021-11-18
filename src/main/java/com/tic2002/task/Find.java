package com.tic2002.task;

import java.util.ArrayList;

public class Find {

    public static void findWord(String input){
        String description;
        String checkWord = input.toLowerCase();
        ArrayList<Task> Check = new ArrayList<>();
        ArrayList<Task> Temp = List.getList();
        for(int i=0; i<Temp.size(); i++){
            description = Temp.get(i).getDescription();
            if(description.contains(checkWord)){
                Check.add(Temp.get(i));
            }
        }
        if(Check.size() == 0){
            System.out.println("\tOops! There are no matching tasks in your list");
        }
        else {
            System.out.println("\tHere are the matching tasks in your list:");
            for(int i=0; i<Check.size(); i++){
                System.out.println("\t" + (i+1) + ". " + Check.get(i).printTask());
            }
        }
    }
}
