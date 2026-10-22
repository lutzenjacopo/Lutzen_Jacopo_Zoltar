/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lutzen_jacopo_zoltar.Server;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Avvia il server TCP e accetta le connessioni dei client.
 *
 * <p>Il server resta in ascolto sulla porta {@code 12345}. Per ogni socket
 * accettato crea un {@link LettoreServer}, cosi piu client possono essere
 * gestiti contemporaneamente da thread distinti.</p>
 */
public class ServerZoltar {

    // Punto di avvio del server TCP.
    /** Flussi di risposta dei client collegati, protetti dai metodi synchronized. */
    private static final List<PrintWriter> CLIENTI = new ArrayList<>();

    /**
     * Registra il flusso di un nuovo client.
     *
     * @param out flusso verso il client appena collegato
     */
    public static synchronized void aggiungiCliente(PrintWriter out) {
        CLIENTI.add(out);
    }

    /** Rimuove e chiude il flusso di un client disconnesso. */
    public static synchronized void rimuoviCliente(PrintWriter out) {
        CLIENTI.remove(out);
        out.close();
    }

    /** Inoltra un messaggio amministrativo a tutti i client collegati. */
    public static synchronized void inviaATutti(String messaggio) {
        for (PrintWriter out : CLIENTI) {
            out.println(messaggio);
        }
    }

    /**
     * Crea il socket di ascolto e avvia un lettore per ogni connessione.
     *
     * @param args argomenti da riga di comando, non utilizzati
     */
    public static void main(String[] args) {
        // Riferimenti al server TCP e alla connessione appena accettata.
        ServerSocket sSocket;
        Socket connessione;
        // Porta sulla quale il server resta in ascolto.
        final int PORT = 12345;
        try {
            // Crea il server socket associato alla porta.
            sSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("In attesa di connessioni...");

                // Accetta una nuova connessione e blocca qui fino a un collegamento.
                connessione = sSocket.accept();
                System.out.println("Connessione stabilita.");
                System.out.println(connessione.getInetAddress());

                // Ogni client viene gestito da un thread indipendente.
                Thread threadLettura = new Thread(
                        new LettoreServer(connessione));
                threadLettura.start();
            }
        } catch (IOException e) {
            // Gestisce errori di apertura o accettazione delle connessioni.
            System.err.println(e);
        }
    }
}
    
