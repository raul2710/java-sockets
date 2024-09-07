/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp.bidirection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JTextArea;

public class ClientServer extends Thread {

    private final int PORT_SEND;
    private final int PORT_RECEIVE;
    private final String HOST;
    private final JTextArea txt;    
    
    public ClientServer(String HOST, int PORT_SEND, int PORT_RECEIVE, JTextArea txt){
        this.HOST = HOST;
        this.PORT_SEND = PORT_SEND;
        this.PORT_RECEIVE = PORT_RECEIVE;
        this.txt = txt;
        txt.append("Servidor iniciado.\n");
    }
    
    public void send(String msg) {
        try {
            byte[] buffer = msg.getBytes();
            DatagramPacket pct = new DatagramPacket(
                    buffer, buffer.length,
                    InetAddress.getByName(HOST), PORT_SEND
            );
            new DatagramSocket().send(pct);
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
    @Override
    public void run(){
        try {
            
            DatagramSocket srv = new DatagramSocket(PORT_RECEIVE);
            
            while(true){
                byte[] buffer = new byte[256];
                DatagramPacket pct = new DatagramPacket(
                    buffer, buffer.length
                );
                
                srv.receive(pct);
                
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formattedDate = LocalDateTime.now().format(myFormatObj);
                
                String msg = new String(pct.getData()).trim();
                txt.append("FROM: ");
                txt.append(pct.getAddress().getHostAddress());
                txt.append("\nTime: " + formattedDate + "\nMSG: ");
                txt.append(msg + "\n\n");
            }
            
        } catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
