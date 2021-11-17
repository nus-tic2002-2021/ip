public class Config {
    String logPath = "duke_info.logs";

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
    }

    String storagePath = "~/go/src/github.com/metildachee/duke/duke.txt";

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
