public class App {
    private TaskManager manager;
    private Config config;
    private StorageDTO storage;
    private UserInterface ui;

    public void init() {
        config = new Config();
        storage = new StorageDTO(config.getStoragePath());
        manager = new TaskManager();
        storage.loadInto(manager);
        ui = new UserInterface(manager);
    }

    public void run() {
        if (manager == null || config == null || storage == null || ui == null) {
            System.out.println("nil init for manager or config or storage or UI");
            return;
        }
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
