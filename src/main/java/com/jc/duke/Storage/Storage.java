package com.jc.duke.Storage;

import com.jc.duke.Task.DateTime;
import com.jc.duke.Task.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class handles storing and retrieving of Duke Task List from a txt file.
 */

public class Storage {

    public Storage() {

    }

    public ArrayList<String> getArchiveFile() {

        ArrayList<String> archiveFiles = new ArrayList<String>();

        String directoryName = "data";
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File f : files) {
            if(f.getName().startsWith("archive")) {
                archiveFiles.add(f.getName());
            }
        }

        return archiveFiles;
    }
    public void writeArchiveFile(String stringToSave) {
        String directoryName = "data";
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "archive_" + timeStamp + ".txt";

        File directory = new File(directoryName);
        if (!directory.exists()){
            System.out.println("folder not found. creating folder");
            directory.mkdir();
        }

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(stringToSave);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void writeSaveFile(String stringToSave) {
        String directoryName = "data";
        String fileName = "save.txt";

        File directory = new File(directoryName);
        if (!directory.exists()){
            System.out.println("folder not found. creating folder");
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }

        File file = new File(directoryName + "/" + fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(stringToSave);
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public List<String> readSaveFile() {

        List<String> content = new ArrayList<String>();

        String directoryName = "data";
        String fileName = "save.txt";

        File directory = new File(directoryName);
        File file = new File(directoryName + "/" + fileName);

        if (!directory.exists() || !file.exists()){
            System.out.println("file not found");
            return null;
        } else {
            try{
                content = Files.readAllLines(Paths.get(directoryName + "/" + fileName));
            } catch (Exception ex) {

            }
        }

        return content;
    }

}
