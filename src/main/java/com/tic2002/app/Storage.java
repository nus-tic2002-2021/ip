package com.tic2002.app;

import com.tic2002.task.Event;
import com.tic2002.task.Task;
import com.tic2002.task.Todo;
import com.tic2002.task.Deadline;
import com.tic2002.task.List;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static com.tic2002.app.DateTime.*;
import static com.tic2002.app.Parser.isNumber;

public class Storage {

    private static ArrayList<Task> Load = new ArrayList<>();

    /**
     * Method to load listing from Duke.txt
     * Get path for user hom
     * Get path for duke.txt
     * run duke.txt over switch to store as tasks in List
     */
    public static void loadList() {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "duke.txt");

        boolean directoryExists = Files.exists(path);
        String description;
        if(directoryExists) {
            try (BufferedReader br = new BufferedReader(new FileReader(path + ""))) {
                String sCurrentLine;
                int i = 0;

                while ((sCurrentLine = br.readLine()) != null) {
                    String line = sCurrentLine.split(",")[0];
                    String priorityString = sCurrentLine.split(",")[1];
                    assert !isNumber(priorityString) : "Error in file: " + sCurrentLine;
                    int priority = Integer.parseInt(priorityString);
                    String [] words = line.split("\\|");
                    String type = words[0];
                    boolean isDone = Boolean.parseBoolean(words[1]);

                    switch (type) {
                        case "T":
                            description = words[2];
                            Load.add(new Todo(description));
                            Load.get(i).priority(priority);
                            if (isDone) {
                                Load.get(i).setDone();
                            }
                            i++;
                            break;

                    case "D":
                        description = words[2];
                        String date = words[3];
                        String time = words[4];
                        try {
                            LocalDate byDate = toDate(date);
                            LocalTime byTime = toTime(time);
                            Load.add(new Deadline(description, byDate, byTime));

                            if(isDone){
                                Load.get(i).setDone();
                                Load.get(i).priority(0);
                            }

                            if(!isDone) {
                                if (isWithinThreeDays(byDate)) {
                                    Load.get(i).priority(3);
                                } else if (isOverDue(byDate)) {
                                    Load.get(i).priority(4);
                                } else {
                                    Load.get(i).priority(priority);
                                }
                            }

                        } catch (DateTimeParseException e) {
                            System.out.println("Error in file: " + sCurrentLine );
                            break;
                        }
                        if(isDone){
                            Load.get(i).setDone();
                        }
                        i++;
                        break;

                    case "E":
                        description = words[2];
                        Load.add(new Event(description, words[3]));
                        Load.get(i).priority(priority);
                        if(isDone){
                            Load.get(i).setDone();
                        }
                        i++;
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            List.setList(Load);
        }
    }

    /**
     * Method to save current listing
     * Copy ArrayList List to ArrayList Load
     * get user home path
     * Get path of Duke.txt
     * print tasks to Duke.txt based on Task variable
     */
    public static void saveList() {
        Load = List.getList();
        String home = System.getProperty("user.home");
        java.nio.file.Path path = Paths.get(home, "data", "duke.txt");

        boolean directoryExists = java.nio.file.Files.exists(path);

        if (!directoryExists) {
            try {
                System.out.println("File created at : " + path);
                Files.createDirectory(Paths.get(home,"data"));
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Error saving file");
                e.printStackTrace();
            }
        }
        String save = "";
        for (Task task : Load) {
            String type = task.getType();
            try {
                switch (type) {
                case "T":
                    save += task.getType() + "|"
                            + task.getDone() + "|"
                            + task.getDescription() + ","
                            + task.getPriority()
                            + System.lineSeparator();
                    break;

                case "E":
                    save += task.getType() + "|" +
                            task.getDone() + "|" +
                            task.getDescription() + "|" +
                            task.getTiming() + "," +
                            task.getPriority() +
                            System.lineSeparator();
                    break;

                case "D":
                    save += task.getType() + "|" +
                            task.getDone() + "|" +
                            task.getDescription() + "|" + task.getDate() + "|" +
                            task.getTime() + "," +
                            task.getPriority() +
                            System.lineSeparator();
                    break;

                default:
                    break;
                }
                writeToFile(path + "", save);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
