/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp.getdateandtime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JLabel;

public class Client{

    //private final String HOST = "192.168.56.1";
    private final String HOST = "127.0.0.1";
    private final int PORT = 50000;
    private JLabel txt;
    
    public Client(JLabel txt){
        this.txt = txt;
    }
    
    public void send(String msg){
        try {
            
            DatagramSocket socket = new DatagramSocket();
            
            byte[] buffer = msg.getBytes(); // Convert message to bytes
            DatagramPacket pct = new DatagramPacket(
                buffer,buffer.length,
                InetAddress.getByName(HOST), PORT
            );
            socket.send(pct); // Send message
            
            // Preparação para receber a resposta
            byte[] receiveData = new byte[256]; // Buffer para receber a resposta
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            socket.receive(receivePacket);
            String responseMessage = new String(receivePacket.getData(), 0, receivePacket.getLength(), "UTF-8");
            String lblResponseMessage = responseMessage;

            if(msg.contains("time") && msg.contains("serve")){
                lblResponseMessage = "Hours Server: " + responseMessage;
                
            } else if(msg.contains("date") && msg.contains("serve") && msg.contains("write")){
                lblResponseMessage = "Date Server Write: " + responseMessage;
                
            } else if(msg.contains("date") && msg.contains("serve")){
                lblResponseMessage = "Date Server: " + responseMessage;
                
            }
            
            txt.setText(lblResponseMessage);
            
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
}
