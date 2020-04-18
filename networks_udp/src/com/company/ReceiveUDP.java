package com.company;

import java.io.IOException;
import java.net.*;

public class ReceiveUDP extends Thread{
    private DatagramSocket socket;
    private boolean running;
    private byte[]buf = new byte[256];

    public ReceiveUDP() throws SocketException, UnknownHostException {
        socket = new DatagramSocket(4446);
    }

    public void run() {
        running = true;
        while (running) {
            DatagramPacket packet = null;
            packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received message: " + received);
            if (received.equals("end")) {
                running = false;
                continue;
            }
            try {
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
