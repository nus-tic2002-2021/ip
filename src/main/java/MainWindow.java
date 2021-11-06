import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private String welcome = ("Hi, I'm Duke "
            +"How to interact with me: \n"
            +"\tCommand:            Function:" + System.lineSeparator()
            +"\tlist                 View all existing tasks"+ System.lineSeparator()
            +"\ttodo                 Add a new to-do"+ System.lineSeparator()
            +"\tdeadline            Add a new deadline"+ System.lineSeparator()
            +"\tevent               Add a new event"+ System.lineSeparator()
            +"\tdone                Mark a task as completed [√]"+ System.lineSeparator()
            +"\tdelete             Delete a task"+ System.lineSeparator()
            +"\tsearch              Search tasks by keyword"+ System.lineSeparator()
            +"\tview                Check schedule of a date"+ System.lineSeparator()
            +"\thelp                Show help message"+ System.lineSeparator()
            +"\treset               Delete all tasks"+ System.lineSeparator()
            +"\tbye                 Exit the program");

    /**
     * Controller for MainWindow. Provides the layout for the other controls.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        VBox.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(welcome, dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.dukeReply(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }
}