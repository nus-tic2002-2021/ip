package storage;

import Common.Message;
import data.Task;

import java.io.*;
import java.nio.file.*;
import java.util.List;

import static ui.Ui.*;

public class Storage
{
    // original file
    private static String filePath;
    private static String homeLoc = System.getProperty("user.home");
    private static String AbsoluteFilePath;
    private static Path path;

    //new file for archiving
    private static String newAbsFilePath;
    private static Path newPath;

    public Storage(String fpath)
    {
        this.filePath = fpath;
    }

    final public void getNewFilePathForArchiving(String newFilepath){
         newAbsFilePath = Paths.get(homeLoc,"duke/data/", newFilepath).toString();
         newPath = Paths.get(newAbsFilePath); // for future use if user wanting to load from archived file
    }

    public static void initFilePath()
    {
        AbsoluteFilePath = Paths.get(homeLoc, "duke", filePath).toString();
        path = Paths.get(AbsoluteFilePath);

    }

    public static String getAbsFilePath(boolean initialFIle)
    {
        if(initialFIle){
            return AbsoluteFilePath;
        }else{
            return newAbsFilePath;
        }
    }
    public static void load(List<Task> lt) throws InvalidPathException, IOException
    {
        initFilePath();
        try{
            TaskDecoder.decodeTaskListToLoad(Files.readAllLines(path),lt);
            System.out.println(Message.getDataLoadedFromFile());
        } catch (IOException e){
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER +e.getMessage()
                    + Message.getNoDataFileFound());
        }

    }

    public void appendSingleTaskToExternal(Task taskToSave)
    {
        try {
            TaskEncoder.encodeTask(taskToSave, AbsoluteFilePath);
        } catch (IOException e) {
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER +e.getMessage()
                    + NEWLINE_BUFFER +" Unable to append individual task to external file: "+ AbsoluteFilePath
                    + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }
    }

    public void appendTaskListToExternal(List<Task> taskToSave)
    {
        try {
            TaskEncoder.encodeTaskList(taskToSave,AbsoluteFilePath);
        } catch (IOException e) {
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER +e.getMessage()
                    + NEWLINE_BUFFER +" Unable to save list of task to external file: "+ AbsoluteFilePath
                    + NEWLINE + BUFF_PLUS_HORRIZONTALLINE);
        }
    }

    public void archiveTaskListToNewFIle(List<Task> taskToSave)
    {
        try {
            TaskEncoder.encodeTaskList(taskToSave,newAbsFilePath);
        } catch (IOException e) {
            System.out.println(BUFF_PLUS_HORRIZONTALLINE + NEWLINE_BUFFER +e.getMessage()
                    + Message.getNoArchiveFile());
        }
    }
}
