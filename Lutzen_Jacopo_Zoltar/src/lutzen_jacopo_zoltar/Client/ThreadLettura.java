/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

/**
 *
 * @author lutzen.jacopo
 */
import java.net.*;
import java.io.*;

public class ThreadLettura implements Runnable {

    // Socket condiviso con il thread che invia i messaggi.
    private final Socket socket;

    // Salva il socket ricevuto dal client principale.
    public ThreadLettura(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Trasforma i dati ricevuti dal socket in righe di testo.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String messaggio;

            // Continua a leggere finche il server non chiude la connessione.
            while ((messaggio = in.readLine()) != null) {
                System.out.println("Server: " + messaggio);
            }
            System.out.println("Il server ha chiuso la connessione.");
        } catch (IOException e) {
            // Un errore viene mostrato solo se la chiusura non era gia prevista.
            if (!socket.isClosed()) {
                System.err.println("Errore durante la ricezione: " + e.getMessage());
            }
        } finally {
            try {
                // Chiude il socket anche in caso di errore.
                socket.close();
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }
    
}
