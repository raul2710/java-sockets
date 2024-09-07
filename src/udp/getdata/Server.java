/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp.getdata;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author 836461
 */
public class Server extends Thread {
    
    private final int PORT = 50000;
    
    public Server(){
        System.out.println("Server start.");
    }
    
    @Override
    public void run(){
        try {
            
            DatagramSocket srv = new DatagramSocket(PORT);
            
            while(true){
                byte[] buffer = new byte[256];
                DatagramPacket pct = new DatagramPacket(
                    buffer, buffer.length
                );
                
                srv.receive(pct);
                
                String msg = new String(pct.getData()).trim();
                System.out.println(msg);
                        
                if(msg.equals("hour")){
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedDate = LocalTime.now().format(myFormatObj);
                    System.out.println(formattedDate);
                    
                    InetAddress address = pct.getAddress();
                    int port = pct.getPort();
                    DatagramPacket pctSend = new DatagramPacket(buffer, buffer.length, address, port);
                    
                    srv.send(pctSend);
                }
            }
            
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        Server srv = new Server();
        srv.start();
    }
}
