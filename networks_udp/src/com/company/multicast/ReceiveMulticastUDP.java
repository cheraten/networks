package com.company.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

public class ReceiveMulticastUDP extends Thread {
    private Map<String, Long> hosts = new HashMap<String, Long>();
    private static String group = "225.4.5.6";
    private static int port = 4445;
    private static byte[] buf = new byte[256];

    public void run() {
        try {
            MulticastSocket socket = new MulticastSocket(port);
            socket.joinGroup(InetAddress.getByName(group));

            while (!isInterrupted()) {
                DatagramPacket pack = new DatagramPacket(buf, buf.length);
                socket.receive(pack);

                String message = new String(pack.getData(), 0, pack.getLength());
                System.out.println("Received multicast message: " + message);
                if (message.equals("multi hi!")) {
                    hosts.put(pack.getAddress().getHostAddress(), System.currentTimeMillis());
                }
            }
            socket.leaveGroup(InetAddress.getByName(group));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
