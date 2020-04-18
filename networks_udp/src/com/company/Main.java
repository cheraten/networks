package com.company;

import com.company.multicast.ReceiveMulticastUDP;
import com.company.multicast.SenderMulticastUDP;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static SenderUDP client;

    public static void main(String[] args) throws IOException {
        System.out.println("1 - unicast\n" +
                "2 - broadcast\n" +
                "3 - multicast");
        Scanner scan = new Scanner(System.in);
        int mode = scan.nextInt();
        if (mode == 1)
            testUnicastUDP();
        if (mode == 2)
            testBroadcastUDP();
        if (mode == 3)
            testMulticastUDP();
    }

    public static void testUnicastUDP() throws IOException {
        new ReceiveUDP().start();
        client = new SenderUDP();
        client.unicast("hello server");
        client.close();
    }

    public static void testBroadcastUDP() throws IOException {
        new ReceiveUDP().start();
        client = new SenderUDP();
        client.broadcast("HELLO");
        client.close();
    }

    public static void testMulticastUDP() throws IOException {
        new ReceiveMulticastUDP().start();
        new SenderMulticastUDP().multicast("multi hi!");
    }
}
