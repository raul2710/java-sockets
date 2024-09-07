/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp.getdata;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client{

    //private final String HOST = "192.168.56.1";
    private final String HOST = "127.0.0.1";
    private final int PORTA = 50000;
    
    public void send(String msg){
        try {
            byte[] buffer = msg.getBytes(); // Convert message to bytes
            DatagramPacket pct = new DatagramPacket(
                buffer,buffer.length,
                InetAddress.getByName(HOST), PORTA
            );
            new DatagramSocket().send(pct); // Send message
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}
