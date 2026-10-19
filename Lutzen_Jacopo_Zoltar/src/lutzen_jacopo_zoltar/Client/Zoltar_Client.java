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
        // Indirizzo e porta del server a cui collegarsi.
        int porta = 12345;
        String host = "localhost";

        try {
            // Apre la connessione TCP con il server.
            Socket socket = new Socket(host, porta);
            System.out.println("Connesso al server " + host + ":" + porta);

            // Un thread riceve le risposte mentre l'altro legge la tastiera.
            Thread threadLettura = new Thread(new ThreadLettura(socket));
            Thread threadScrittura = new Thread(new ThreadScrittura(socket));

            // Il thread di scrittura non impedisce la chiusura del programma.
            threadScrittura.setDaemon(true);

            // Avvia i due flussi indipendenti del client.
            threadLettura.start();
            threadScrittura.start();
            
        } catch (IOException e) {
            // Informa l'utente se il server non e raggiungibile.
            System.out.println("Errore I/O: " + e.getMessage());
        }
    }
}
    
    
