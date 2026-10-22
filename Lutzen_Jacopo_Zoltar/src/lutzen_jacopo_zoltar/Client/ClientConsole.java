/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

import java.io.IOException;
import java.net.Socket;

/**
 * Punto di ingresso del client testuale.
 *
 * <p>A differenza di {@link ClientGUI}, usa la console: un thread legge le
 * risposte del server e un altro legge i comandi digitati dall'utente.</p>
 */
public class ClientConsole {

    /** Avvia una sessione client sulla macchina locale. */
    /**
     * @param args argomenti da riga di comando, non utilizzati
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
            Thread threadLettura = new Thread(new LettoreClient(socket));
            Thread threadScrittura = new Thread(new ScrittoreClient(socket));

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
    
    
