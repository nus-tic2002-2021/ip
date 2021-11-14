import java.io.File;
import java.io.FileNotFoundException;

public class StorageDAO {
    protected File file;
    protected String path;

    private final Logger logger = new Logger();

    public StorageDAO(String path) {
        this.path = "duke.txt";
        logger.init("");
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
        return file != null;
    }

    public void init() throws Exception {
        logger.info("init file path: " + path);
        try {
            this.file = new File(this.path);
            logger.info("path " + path);
            path = this.file.getPath();
            boolean created = file.createNewFile();
            if (created) {
                logger.info("no file detected, a new file has been created: " + this.path);
            }
        } catch (Exception e) {
            logger.info("got err: " + e);
        }
    }
}
