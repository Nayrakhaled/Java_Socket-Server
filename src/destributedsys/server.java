/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package destributedsys;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niro
 */
public class server {
    public static void main(String[] args) throws IOException {
        try{
        ServerSocket server= new ServerSocket(55544);
        Socket socket=server.accept();
        PrintWriter pw=new PrintWriter(socket.getOutputStream(),true);
        pw.println("Hello from server");
        
        new Thread(){
            public void run(){
                Scanner scann=new Scanner(System.in);
                while(true)
                {
                    String line=scann.nextLine();
                    pw.println(line);
                }
            }
        }.start();
        new Thread(){
            public void run()
            {
                try {
                    Scanner scan1=new Scanner(socket.getInputStream());
                    while(scan1.hasNextLine())
                    {
                        System.out.println(scan1.nextLine());
                    }
                } catch (IOException ex) {
                    Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        //pw.close();
        //socket.close();
        //server.close();
        
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        
    }
     
}
