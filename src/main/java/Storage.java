import java.io.File;
import java.io.IOException;

public class Storage {

    public static File CreateStorageFile() throws DukeException {
        File newFile = new File("data\\Storage.txt");
        try {
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
            System.out.println("New Storage.txt file created.");
            return newFile;
        } catch (IOException err) {
            throw new DukeException("Failed to create new Storage.txt file.");
        }
    }

    public static File OpenStorageFile() throws DukeException {
        File newFile = new File("data\\storage.txt");
        if (!newFile.exists()) {
            try {
                newFile = CreateStorageFile();
            } catch (DukeException e) {
                throw new DukeException("Duke will run without any prior stored task information");
            }
        }
        return newFile;
    }
}
