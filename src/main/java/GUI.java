import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.WindowEvent;



public class GUI extends JFrame implements ActionListener {

    protected JLabel userLabel = new JLabel("User");;
    protected JTextField userText = new JTextField(20);;
    protected JLabel passwordLabel = new JLabel("Password");;
    protected JPasswordField passwordText  = new JPasswordField();;
    protected JButton button = new JButton("Submit");;
    protected JLabel success = new JLabel("");;
    protected JFrame frame = new JFrame();
    protected JPanel panel = new JPanel();
    protected JTextArea Chatarea = new JTextArea();
    protected JTextField chatbox = new JTextField();
    JScrollPane sp;


    public GUI(){
        this.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.frame.setLayout(null);
        this.frame.setSize(600,600);
        this.frame.setTitle("Duke");
        this.frame.add(Chatarea);
        this.frame.add(chatbox);
        this.frame.add(button);
        sp=new JScrollPane(Chatarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.frame.add(sp);
        //Text area
        Chatarea.setSize(600,400);
        Chatarea.setLocation(2,2);
        Chatarea.setEditable(false);
        Chatarea.setHighlighter(null);
        //Text field
        chatbox.setSize(450,30);
        chatbox.setLocation(2,500);
        //Button
        button.setBounds(460,500,100,28);
        button.addActionListener(this);

        //scrollbar
        sp.setBounds(2,2,540,410);

        chat();

    }

    public void chat(){
        chatbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = chatbox.getText();
                String command = "";
                Chatarea.append("You: " + text + "\n");
                chatbox.setText("");
                command = Duke.getCommand(text);
                Duke.performCommand(text, command);

                if (text.contains("bye")) {
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
                if (text.toLowerCase().contains("clear")) {
                    clearChat();
                }

            }
        });
    }

    public void botResponse(String line){
        Chatarea.append("Bot: " + line);
        Chatarea.append("\n");
    }

    public void clearChat(){
        Chatarea.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = chatbox.getText();
        String command = "";
        Chatarea.append("You: " + text + "\n");
        chatbox.setText("");
        command = Duke.getCommand(text);
        Duke.performCommand(text, command);

        if (text.contains("bye")) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
        if (text.toLowerCase().contains("clear")) {
            clearChat();
        }
    }
}
