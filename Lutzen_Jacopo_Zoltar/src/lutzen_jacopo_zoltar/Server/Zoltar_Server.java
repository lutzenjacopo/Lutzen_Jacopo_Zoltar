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
 *
 * @author lutzen.jacopo
 */
public class Zoltar_Server {

    // Elenco dei flussi di output dei client attualmente collegati.
    private static final List<PrintWriter> CLIENTI = new ArrayList<>();

    // Aggiunge in modo sicuro un nuovo client all'elenco.
    public static synchronized void aggiungiCliente(PrintWriter out) {
        CLIENTI.add(out);
    }

    // Rimuove il client e chiude il relativo flusso di output.
    public static synchronized void rimuoviCliente(PrintWriter out) {
        CLIENTI.remove(out);
        out.close();
    }

    // Invia un messaggio a tutti i client collegati.
    public static synchronized void inviaATutti(String messaggio) {
        for (PrintWriter out : CLIENTI) {
            out.println(messaggio);
        }
    }

    /**
     * @param args the command line arguments
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
                        new ThreadLettura(connessione));
                threadLettura.start();
            }
        } catch (IOException e) {
            // Gestisce errori di apertura o accettazione delle connessioni.
            System.err.println(e);
        }
    }
}
    
