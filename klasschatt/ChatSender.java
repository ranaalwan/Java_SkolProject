package Demo_iot22.klasschatt;

import java.io.IOException;
import java.net.*;


public class ChatSender {
    private DatagramSocket datagramSocket;
    private InetAddress multicastGroup;
    private int port;
    private String Name;

    public ChatSender(String Name) {
        try {
            datagramSocket = new DatagramSocket();
            multicastGroup = InetAddress.getByName("234.235.236.237");
            port = 12540;
            this.Name = Name;
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            String formattedMessage = Name + " : " + message;
            byte[] sendData = formattedMessage.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, multicastGroup, port);

            datagramSocket.send(sendPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}