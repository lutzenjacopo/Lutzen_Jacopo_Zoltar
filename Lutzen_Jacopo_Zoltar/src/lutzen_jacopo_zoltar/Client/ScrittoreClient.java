/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

import java.io.*;
import lutzen_jacopo_gestione_zoltar.ControlliZoltar;
import java.net.*;
/**
 *
 * @author lutzen.jacopo
 */
/**
 * Thread di invio del client da console.
 *
 * <p>Non modifica il testo inserito: l'utente deve scrivere esplicitamente
 * {@code DOM_}, {@code PERS_} oppure {@code fine}, come richiesto dal
 * protocollo.</p>
 */
public class ScrittoreClient implements Runnable{
    
    /** Flusso testuale collegato al socket del server. */
    private PrintWriter out;
    /** Flusso di input della console. */
    private BufferedReader tastiera;

    /** Inizializza i flussi di rete e tastiera. */
    public ScrittoreClient(Socket socket) {
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.tastiera = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.err.println("Errore nella creazione dei flussi I/O: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // Il thread legge comandi completi, quindi non aggiunge prefissi automaticamente.
            String messaggio;

            // Il client non sceglie automaticamente una personalita.
            // Ogni comando deve contenere il proprio prefisso.
            System.out.println("Scrivi DOM_<domanda> per fare una domanda.");
            System.out.println("Scrivi PERS_Zoltar, PERS_Luck o PERS_Tarot "
                    + "per cambiare personalita.");
            System.out.println("Scrivi 'fine' per uscire.");

            // Invia al server il comando esattamente come e stato scritto.
            while ((messaggio = tastiera.readLine()) != null) {
                // Il comando fine chiude il dialogo con il server.
                if (ControlliZoltar.comandoFine(messaggio)) {
                    out.println("fine");
                    break;
                }

                // Le domande vuote non vengono inviate.
                if (!ControlliZoltar.testoPresente(messaggio)) {
                    System.out.println("La domanda non puo essere vuota.");
                    continue;
                }

                // Il server controlla che il comando inizi con DOM_ o PERS_.
                out.println(messaggio.trim());
            }
        } catch (IOException e) {
            // Segnala problemi durante la lettura o l'invio.
            System.err.println("Errore durante l'invio: " + e.getMessage());
        } finally {
            // Chiude il flusso di output quando il thread termina.
            out.close();
        }
    }
    
}
