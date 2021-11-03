import java.io.File;
import java.io.FileNotFoundException;

public class FileAccessor {
    File file;
    String path;

    public FileAccessor(String path) {
        this.path = "duke.txt";
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isInit() {
        if (file == null) {
            return false;
        }
        return true;
    }

    public void init() throws Exception {
        System.out.println("init file path: " + path);
        try {
            this.file = new File(this.path);
            System.out.println("path " + path);
            path = this.file.getPath();
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("no file detected, a new file has been created: " + this.path);
            }
        } catch (Exception e) {
            System.out.println("got err: " + e);
        }
    }
}
