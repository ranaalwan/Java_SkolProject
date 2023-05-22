package Demo_iot22.klasschatt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatIntro extends JFrame {
    public ChatIntro() {
        //JFrame fr=new JFrame("Chat Intro: ")
        super("Chat Intro");

        setSize(300, 200);
        setLocation(500,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Ange ditt namn:");
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Monospaced", Font.BOLD, 30));

        JButton startButton = new JButton("Starta Chatten");

        panel.add(label, BorderLayout.NORTH);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        add(panel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                ChatFon chatFon = new ChatFon(name);
                chatFon.setVisible(true);
                dispose();
            }
        });
    }
}
