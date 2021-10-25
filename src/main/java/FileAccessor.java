import java.io.File;
import java.io.FileNotFoundException;

public class FileAccessor {
    File file;

    String path;

    public FileAccessor(String path) {
        this.path = path;
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
        this.file = new File(this.path);
    }
}
