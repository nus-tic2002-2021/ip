package com.alexooi.duke.storage;

import com.alexooi.duke.interfaces.StorageClient;

import java.io.*;

public class FileStorage implements StorageClient<BufferedReader> {
    String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    public BufferedReader load() {
        try {
            File file = new File(this.filePath);
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                return new BufferedReader(reader);
            } else if (file.createNewFile()) {
                FileReader reader = new FileReader(file);
                return new BufferedReader(reader);
            } else {
                throw new IOException();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File Not Found Exception: Shouldn't have hit this!");
        } catch (IOException ioe) {
            System.out.println("IOException: Had an issue creating state file");
        }
        return null;
    }

    public void save(String input) {
        try {
            File file = new File(this.filePath);
            FileWriter writer = new FileWriter(file);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(input);
            out.close();
        } catch (IOException ioe) {
            System.out.println("Had trouble saving!");
        }
    }
}
