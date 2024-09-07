/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
                System.out.println("FROM");
                System.out.println(pct.getAddress().getHostAddress());
                System.out.println(pct.getAddress().getCanonicalHostName());
                System.out.println("MSG");
                System.out.println(msg + "\n\n");
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
