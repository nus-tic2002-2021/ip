import java.io.IOException;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger {
    java.util.logging.Logger logger;

    public Logger() {

    }

    public void init(String path) {
        if (Objects.equals(path, "")) {
            path = "duke_info.logs";
        }
        logger = java.util.logging.Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.FINEST);
        try {
            FileHandler fileTxt = new FileHandler(path);

            SimpleFormatter formatterTxt = new SimpleFormatter();
            fileTxt.setFormatter(formatterTxt);
            logger.addHandler(fileTxt);
        } catch (IOException e) {
            logger.info("init logger got error");
        }
    }

    public java.util.logging.Logger getLogger() {
        return logger;
    }

    public void info(String msg) {
        if (logger != null) {
            logger.finest(msg);
        }
    }
}
