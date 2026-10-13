/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author lutzen.jacopo
 */
public class Zoltar_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    /**
     * @param args the command line arguments
     */
    
        int porta = 12345;
        String host = "localhost";
        try {
            Socket socket = new Socket(host, porta);
            System.out.println("Connesso al server " + host + ":" + porta);

            Thread threadLettura = new Thread(new ThreadLettura(socket));
            Thread threadScrittura = new Thread(new ThreadScrittura(socket));
            threadScrittura.setDaemon(true);

            threadLettura.start();
            threadScrittura.start();
            
        } catch (IOException e) {
            System.out.println("Errore I/O: " + e.getMessage());
        }
    }
}
    
    

