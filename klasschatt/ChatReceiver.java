package Demo_iot22.klasschatt;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class ChatReceiver extends Thread {
    private MulticastSocket multicastSocket;
    private JTextArea chatArea;
    private boolean receiving;

    public ChatReceiver(JTextArea chatArea) {
        this.chatArea = chatArea;
        receiving = false;

        try {
            multicastSocket = new MulticastSocket(12540);
            multicastSocket.joinGroup(InetAddress.getByName("234.235.236.237"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        receiving = true;
        while (receiving) {
            byte[] buffer = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);

            try {
                multicastSocket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Sender skriver..");

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        System.out.println("Received:" + message);
                        chatArea.append(message + "\n");
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void stopReceiving() {
        receiving = false;
    }

}

     
