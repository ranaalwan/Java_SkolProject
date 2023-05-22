package Demo_iot22.klasschatt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatFon extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private ChatSender chatSender;
    private ChatReceiver chatReceiver;
    private JButton connectButton;
    private boolean connected;

    public ChatFon(String name) {
        super("Chat Fönstret - " + name);

        setSize(400, 300);
        setLocation(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        //chatArea.setEditable(false);

        messageField = new JTextField();
        messageField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                chatSender.sendMessage(message);
                messageField.setText("");
            }
        });

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(messageField, BorderLayout.SOUTH);

        connectButton = new JButton("Koppla upp");
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!connected) {

                    connectButton.setText("Koppla ner");
                    connected = true;
                    chatReceiver.start();
                    chatSender.sendMessage(": UPPKOPPLAD");
                } else {
                    connectButton.setText("Koppla upp");
                    connected = false;
                    chatSender.sendMessage(": NEDKOPPLAD");
                    chatReceiver.stopReceiving();
                }
            }
        });
        panel.add(connectButton, BorderLayout.NORTH);

        add(panel);

        chatSender = new ChatSender(name);
        chatReceiver = new ChatReceiver(chatArea);
        connected = false;
    }
}
