package com.company.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class SenderMulticastUDP extends Thread {
    private static String group = "225.4.5.6";
    private static int port = 4445;

    public void multicast(String multimsg) {
        while (!isInterrupted())
            try {
                System.out.println("Multicast message: " + multimsg);
                MulticastSocket socket = new MulticastSocket();
                socket.setTimeToLive(1);
                socket.setLoopbackMode(false);
                byte[] buf = multimsg.getBytes();
                DatagramPacket pack = new DatagramPacket(buf, buf.length, InetAddress.getByName(group), port);
                socket.send(pack);
                socket.close();
                sleep(500);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
            }
    }
}
