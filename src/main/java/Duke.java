import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The project aims to build a product named Duke, a Personal Assistant Chatbot that helps a person to keep track of various things.
 * The name Duke was chosen as a placeholder name, in honor of Duke, the Java Mascot.
 */

public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;

    private final Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));


    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);


        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());


        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }


    public static void main(String[] args) throws IOException {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Duke" + "\n" + logo + "\n" + "How can I help you today?");

        List<Task> list = new ArrayList<>();
        boolean isTrue = true;

        while (isTrue) {
            Scanner sc = new Scanner(System.in);
            String[] input = sc.nextLine().split(" ", 2);
            String [] s = new String[2];
            try {
                switch (input[0]) {

                    case "bye":
                        System.out.println("\tBye. Hope to see you again soon!");
                        isTrue = false;
                        break;
                    case "list":
                        if (list.isEmpty()) {
                            System.out.println("\tList is empty.");
                            break;
                        }
                        System.out.println("\tHere are the tasks in your list:");
                        int taskNumber = 1;
                        for (Task task : list) {
                            System.out.println("\t" + taskNumber + ". " + task);
                            taskNumber++;
                        }
                        createFile("event","");
                        break;
                    case "done":
                        int doneTaskNumber = Integer.parseInt(input[1]);
                        if (doneTaskNumber > list.size()) {
                            throw new DukeException("☹ OOPS!!! There is no such task.");
                        }
                        Task doneTask = list.get(doneTaskNumber - 1);
                        doneTask.setDone();
                        System.out.println("\tNice! I've marked this task as done: ");
                        System.out.println("\t\t" + list.get(Integer.parseInt(input[1]) - 1));
                        break;
                    case "delete":

                        int deletedTaskNumber = Integer.parseInt(input[1]);
                        if (deletedTaskNumber > list.size()) {

                            throw new DukeException("☹ OOPS!!! There is no such task.");
                        }
                        Task deletedTask = list.get(deletedTaskNumber - 1);
                        list.remove(deletedTaskNumber - 1);
                        System.out.println("\tNoted. I've removed this task: ");
                        System.out.println("\t\t" + deletedTask);
                        Task.taskDeleted();
                        Task.getTotalTasks();
                        break;
                    case "todo":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }

                        list.add(new Todo(input[1]));
                        Task.getTotalTasks();
                        createFile("todo", s[1]);
                        break;
                    case "deadline":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] by = input[1].split("/");
                        if (by.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
                        }
                        list.add(new Deadline(by[0], LocalDate.parse(by[1])));
                        Task.getTotalTasks();
                        createFile("deadline", "");

                        break;
                    case "event":
                        if (input.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        }
                        String[] at = input[1].split("/");
                        if (at.length < 2) {
                            throw new DukeException("☹ OOPS!!! The date of a event cannot be empty.");
                        }
                        list.add(new Event(at[0], LocalDate.parse(at[1])));
                        Task.getTotalTasks();

                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println("\t" + e);

            }

        }
    }
        public static void createFile(String command,String task) throws IOException{

            String filePath = "duke.txt";
            File f = new File(filePath);
            FileWriter fw = new FileWriter(filePath,true);
            BufferedWriter br = new BufferedWriter(fw);
            br.write(command+" || "+task);
            br.newLine();
            br.close();

            fw.close();
            try {
                boolean result = f.createNewFile();
                Scanner s = new Scanner(f);
                System.out.println("Load data from file ++++ ");
                while(s.hasNext()){
                    System.out.println(s.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}