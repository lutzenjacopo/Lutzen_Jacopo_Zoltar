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

/**
 * Thread di ricezione del client da console.
 *
 * <p>Resta bloccato su {@code readLine()} finche il server invia righe.
 * Non interpreta il protocollo: stampa i messaggi ricevuti e lascia al
 * server il compito di validare e generare le risposte.</p>
 */
public class LettoreClient implements Runnable {

    /** Socket dal quale arrivano le risposte. */
    private final Socket socket;

    /** Costruisce il lettore associato al socket aperto dal main. */
    public LettoreClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Il thread resta in ascolto senza bloccare la scrittura dell'utente.
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
