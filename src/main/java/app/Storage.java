package app;

import task.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private static ArrayList<Task> Load = new ArrayList<>();
    public static void loadList() {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = Paths.get(home, "data", "duke.txt");
        // if exist
        boolean directoryExists = java.nio.file.Files.exists(path);
        String description;
        if(directoryExists) {
            try (BufferedReader br = new BufferedReader(new FileReader(path + ""))) {
                String sCurrentLine;
                int i = 0;
                while ((sCurrentLine = br.readLine()) != null) {
                    String [] words = sCurrentLine.split("\\|");
                    String type = words[0];
                    boolean done = Boolean.parseBoolean(words[1]);
                    switch (type){
                        case "T":
                            description = words[2];
                            Load.add(new Todo(description));
                            if(done == true){
                                Load.get(i).setDone();
                            }
                            i++;
                            break;
                        case "D":
                            description = words[2];
                            Load.add(new Deadline(description, words[3]));
                            if(done == true){
                                Load.get(i).setDone();
                            }
                            i++;
                            break;
                        case "E":
                            description = words[2];
                            Load.add(new Event(description, words[3]));
                            if(done == true){
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

    public static void saveList() {
        Load = List.getList();
        String home = System.getProperty("user.home");
        java.nio.file.Path path = Paths.get(home, "data", "duke.txt");
        //if exist
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
        for (int i = 0; i < Load.size(); i++) {
            String type = Load.get(i).getType();
            try {
                switch (type) {
                    case "T":
                        save += Load.get(i).getType() + "|"
                                + Load.get(i).getDone() + "|"
                                + Load.get(i).getDescription()
                                + System.lineSeparator();
                        break;

                    case "D" :

                    case "E":
                        save += Load.get(i).getType() + "|"
                                + Load.get(i).getDone() + "|"
                                + Load.get(i).getDescription() + "|"
                                + Load.get(i).getTiming()
                                + System.lineSeparator();
                        break;

                    default: break;
                }
                writeToFile(path + "", save);
            } catch (IOException e) {}
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
