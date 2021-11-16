package app;

import task.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static app.DateTime.toDate;
import static app.DateTime.toTime;

public class Storage {

    private static ArrayList<Task> Load = new ArrayList<>();

    /**
     * Method to load listing from Duke.txt
     * Get path for user hom
     * Get path for duke.txt
     */
    public static void loadList() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = Paths.get(home, "data", "duke.txt");
        /**
         * Create boolean to check if path for Duke.txt exists
         * Create String description to store Task description
         * If directory exists
         * read the file using buffered reader
         * create int i as a reference point
         */
        boolean directoryExists = java.nio.file.Files.exists(path);
        String description;
        if(directoryExists) {
            try (BufferedReader br = new BufferedReader(new FileReader(path + ""))) {
                String sCurrentLine;
                int i = 0;
                /**
                 * while the line read is not null
                 * split the line into a String array with delimiter "|"
                 * create String type to store type of Task in String array index 0
                 * create boolean done for String array index 1
                 */
                while ((sCurrentLine = br.readLine()) != null) {
                    String [] words = sCurrentLine.split("\\|");
                    String type = words[0];
                    boolean isDone = Boolean.parseBoolean(words[1]);
                    /**
                     * create switch method for Task type
                     */
                    switch (type){
                        /**
                         * if type is T
                         * store String word array index 2 as description
                         * add Todo as task
                         * if Task is done, mark as done
                         * increment i
                         * break switch
                         */
                        case "T":
                            description = words[2];
                            Load.add(new Todo(description));
                            if(isDone == true){
                                Load.get(i).setDone();
                            }
                            i++;
                            break;
                        /**
                         * if type is D
                         * store String word array index 2 as description
                         * store String word array index 3 as String date
                         * store String word array index 4 as String time
                         * convert String date as LocalDate date
                         * convert String time as LocalTime time
                         * add Deadline as task
                         * if Task is done, mark as done
                         * increment i
                         * break switch
                         */
                        case "D":
                            description = words[2];
                            String date = words[3];
                            String time = words[4];
                            LocalDate byDate = toDate(date);
                            LocalTime byTime = toTime(time);
                            Load.add(new Deadline(description, byDate, byTime));
                            if(isDone == true){
                                Load.get(i).setDone();
                            }
                            i++;
                            break;
                        /**
                         * if type is E
                         * store String word array index 2 as description
                         * store String word array index 3 as String timing
                         * add Deadline as task with description and timing
                         * if Task is done, mark as done
                         * increment i
                         * break switch
                         */
                        case "E":
                            description = words[2];
                            String timing = words[3];
                            Load.add(new Event(description, timing));
                            if(isDone == true){
                                Load.get(i).setDone();
                            }
                            i++;
                            break;
                    }
                }
                /**
                 * if IOException e error occurs, print error tracing
                 */
            } catch (IOException e) {
                e.printStackTrace();
            }
            /**
             * update ArrayList List as ArrayList Load
             */
            List.setList(Load);
        }
    }

    /**
     * Method to save current listing
     * Copy ArrayList List to ArrayList Load
     * get user home path
     * Get path of Duke.txt
     */
    public static void saveList() {
        Load = List.getList();
        String home = System.getProperty("user.home");
        java.nio.file.Path path = Paths.get(home, "data", "duke.txt");
        /**
         * create boolean to check if directory exists
         */
        boolean directoryExists = java.nio.file.Files.exists(path);
        /**
         * if directory does not exist
         * print out path of file created
         * create directory of file
         * create file Duke.txt
         */
        if (!directoryExists) {
            try {
                System.out.println("File created at : " + path);
                Files.createDirectory(Paths.get(home,"data"));
                Files.createFile(path);
                /**
                 * Catch any IOException errors
                 * Print out error statement
                 * Print out trace of errors
                 */
            } catch (IOException e) {
                System.out.println("Error saving file");
                e.printStackTrace();
            }
        }
        /**
         * Create String save to store data as String
         */
        String save = "";
        /**
         * Create for loop to go through Tasks in ArrayList Load
         * Create String type to store Task type
         */
        for (int i = 0; i < Load.size(); i++) {
            String type = Load.get(i).getType();
            try {
                /**
                 * Create switch method for type of tasks
                 */
                switch (type) {
                    /**
                     * for Todo tasks
                     * Store the following in String save
                     * Get Task type for index i, add delimiter
                     * Get done status for index i, add delimiter
                     * Get description for index i, add delimiter
                     * Add line separator
                     * break to next line
                     */
                    case "T":
                        save += Load.get(i).getType() + "|"
                                + Load.get(i).getDone() + "|"
                                + Load.get(i).getDescription()
                                + System.lineSeparator();
                        break;
                    /**
                     * for Event tasks
                     * Store the following in String save
                     * Get Task type for index i, add delimiter
                     * Get done status for index i, add delimiter
                     * Get description for index i, add delimiter
                     * Get timing for index i, add delimiter
                     * Add line separator
                     * break to next line
                     */
                    case "E" :
                        save += Load.get(i).getType() + "|" +
                                Load.get(i).getDone() + "|" +
                                Load.get(i).getDescription() + "|" +
                                Load.get(i).getTiming() + "|" +
                                System.lineSeparator();
                        /**
                         * for Deadline tasks
                         * Store the following in String save
                         * Get Task type for index i, add delimiter
                         * Get done status for index i, add delimiter
                         * Get description for index i, add delimiter
                         * Get date for index i, add delimiter
                         * Get time for index i, add delimiter
                         * Add line separator
                         * break to next line
                         */
                    case "D":
                        save += Load.get(i).getType() + "|" +
                                Load.get(i).getDone() + "|" +
                                Load.get(i).getDescription() + "|" +
                                Load.get(i).getDate() + "|" +
                                Load.get(i).getTime() +
                                System.lineSeparator();
                        break;

                    default: break;
                }
                /**
                 * write String save to Duke.txt
                 */
                writeToFile(path + "", save);
                /**
                 * Handle IOException errors
                 */
            } catch (IOException e) {}
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
