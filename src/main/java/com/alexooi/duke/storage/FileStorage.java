package com.alexooi.duke.storage;

import com.alexooi.duke.interfaces.StorageClient;

import java.io.*;

/**
 * The concrete implementation of the StorageClient for storing in files.
 */
public class FileStorage implements StorageClient<BufferedReader> {
    final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the specific file at the file path and returns a reader for use by the parser
     * @return  A buffered reader that can be used to scan the file.
     */
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
            System.out.println("IOException: Had an issue creating file");
        }
        return null;
    }

    /**
     * Save the input into the file at the file path.
     * @param input     The string to write into the file
     * @param isAppend  Whether to append to the file or overwrite the file
     */
    @Override
    public void save(String input, boolean isAppend) {
        try {
            File file = new File(this.filePath);
            FileWriter writer = new FileWriter(file, isAppend);
            BufferedWriter out = new BufferedWriter(writer);
            out.write(input);
            out.close();
        } catch (IOException ioe) {
            System.out.println("Had trouble saving!");
        }
    }
}
