public class Duke {
    public static void main(String[] args) {
        // Init config
        Config config = new Config();

        // Init logger
        Logger logger = new Logger();
        logger.init(config.getLogPath());

        // Init storage
        StorageDTO storage = new StorageDTO(config.getStoragePath());

        // Init task manager
        TaskManager manager = new TaskManager();
        storage.loadInto(manager);

        // Init UI
        UI ui = new UI(manager);

        // Program starts
        ui.start();
        while (!ui.isExit()) {
            ui.getInput();
            ui.parseInput();
            ui.processInput();
        }

        storage.save(manager.getTasks());
        ui.end();
    }
}
