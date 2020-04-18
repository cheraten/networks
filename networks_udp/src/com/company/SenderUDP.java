package com.company;

import java.io.IOException;
import java.net.*;

public class SenderUDP  extends Thread{
    private static DatagramSocket socket;
    private static InetAddress address;
    private static byte[] buf;

    public SenderUDP() throws UnknownHostException, SocketException {
        socket = new DatagramSocket();
        address = InetAddress.getLocalHost();
    }

    public String unicast(String unimsg) throws IOException {
        System.out.println("Host unicast message: " + unimsg);
        buf = unimsg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4446);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    public static void broadcast(String broadmsg) throws IOException {
        System.out.println("Host broadcast message: " + broadmsg);
        address = InetAddress.getByName("255.255.255.255");
        socket.setBroadcast(true);
        buf = broadmsg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4446);
        socket.send(packet);
        System.out.println("Max size datagram socket: " + socket.getReceiveBufferSize());
    }

    public void close() {
        socket.close();
    }
}
