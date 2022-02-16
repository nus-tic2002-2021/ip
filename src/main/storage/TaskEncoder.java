package storage;

import data.Acronym;
import data.DeadLine;
import data.Event;
import data.Task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskEncoder {

    public static void encodeTaskList(List<Task> toSave, String pathOfFileToSave) throws IOException {
        final List<String> encodedTaskList = new ArrayList<>();
        File newfile = new File(pathOfFileToSave);
        if(!newfile.exists()){
            newfile.createNewFile();
        }
        FileWriter fw = new FileWriter(newfile, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Task individualTask : toSave){
            encodedTaskList.add(encodeAcroToString(individualTask));
        }
        for(String singleTask : encodedTaskList){
            bw.write(singleTask);
            bw.newLine();
        }
        bw.close();
    }

    public static void encodeTask(Task toSave, String pathOfFileToSave) throws IOException {
        try {
            String encodedTask = encodeAcroToString(toSave);
            FileWriter fw = new FileWriter(pathOfFileToSave, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write( encodedTask);
            bw.newLine();
            bw.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static String encodeAcroToString(Task task){
        StringBuilder encodedNewString = new StringBuilder();
        if (task.getAcronym().equals(Acronym.T)){
            encodedNewString.append(Acronym.T);
            encodedNewString = appendEncodedTask(encodedNewString, task);
        }
        else if (task.getAcronym().equals(Acronym.D)){
            encodedNewString.append(Acronym.D);
            encodedNewString = appendEncodedTask(encodedNewString, task);
            DeadLine d = (DeadLine)task;
            encodedNewString.append(" | ");
            encodedNewString.append(d.getDateTimeForStorage());
        }
        else if (task.getAcronym().equals(Acronym.E)){
            encodedNewString.append(Acronym.E);
            encodedNewString = appendEncodedTask(encodedNewString, task);
            Event e = (Event)task;
            encodedNewString.append(" | ");
            encodedNewString.append(e.getDateTimeForStorage());
        }
        return encodedNewString.toString();
    }

    private static StringBuilder appendEncodedTask(StringBuilder sb, Task task){
        sb.append(" | ").append(task.getDone() ? "1" : "0");
        sb.append(" | ").append(task.getDescription());
        return sb;
    }
}
