/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udp.getdateandtime;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author 836461
 */
public class Server extends Thread {
    
    private final int PORT = 50000;
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
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
                
                
                InetAddress clientAddress = pct.getAddress();
                int clientPort = pct.getPort();
                       
                
                if(msg.contains("time") && msg.contains("serve")){
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String formattedDate = LocalTime.now().format(myFormatObj);
                    System.out.println(formattedDate);

                    byte[] responseData = formattedDate.getBytes();
                    DatagramPacket pctSend = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                    srv.send(pctSend); 
                    
                } else if(msg.contains("date") && msg.contains("serve") && msg.contains("write")){
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = LocalDate.now().format(myFormatObj);
                    System.out.println(formattedDate);

                    String[] formattedDateSplit = formattedDate.split("/");
                    String dateWrite = formattedDateSplit[0] + " " + MONTHS[Integer.valueOf(formattedDateSplit[1])] + " " + formattedDateSplit[2];
                    byte[] responseData = dateWrite.getBytes();
                    DatagramPacket pctSend = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                    
                    srv.send(pctSend); 
                    
                } else if(msg.contains("date") && msg.contains("serve")){
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    String formattedDate = LocalDate.now().format(myFormatObj);
                    System.out.println(formattedDate);

                    byte[] responseData = formattedDate.getBytes();
                    DatagramPacket pctSend = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                    srv.send(pctSend); 
                }
                byte[] responseData = new byte[256];
                DatagramPacket pctSend = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                srv.send(pctSend);
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
