package lutzen_jacopo_zoltar.Server;

import java.net.*;
import java.io.*;
import lutzen_jacopo_gestione_zoltar.ControlliZoltar;

/**
 * Gestisce il canale di ingresso di un singolo client.
 *
 * <p>Legge una riga alla volta, riconosce {@code fine} e delega gli altri
 * messaggi a {@link Indovino}. La personalita contenuta nell'indovino e
 * quindi privata della connessione e non viene condivisa con altri client.</p>
 */
public class LettoreServer implements Runnable {

    /** Socket del client assegnato a questo thread. */
    private final Socket socket;
    /** Interprete del protocollo e generatore delle risposte. */
    private final Indovino indovino = new Indovino();

    /** Prepara il lettore per la connessione indicata. */
    public LettoreServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // BufferedReader permette di ricevere messaggi terminati da newline.
            // Ogni client possiede flussi e un indovino separati.
            // Crea i flussi per ricevere dal client e rispondere al client.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // Registra il client tra quelli collegati al server.
            ServerZoltar.aggiungiCliente(out);
            String messaggio;

            // Elabora ogni riga ricevuta finche il client resta connesso.
            while ((messaggio = in.readLine()) != null) {
                System.out.println("Client: " + messaggio);

                // Il comando fine chiude educatamente la sessione.
                if (ControlliZoltar.comandoFine(messaggio)) {
                    out.println("Zoltar: A presto, torna quando vorrai conoscere il futuro.");
                    break;
                }

                // L'indovino cambia personalita o genera una profezia.
                out.println(indovino.rispondi(messaggio));
            }
            System.out.println("Il client ha chiuso la connessione.");
            ServerZoltar.rimuoviCliente(out);
        } catch (IOException e) {
            // Mostra l'errore solo se il socket non era gia stato chiuso.
            if (!socket.isClosed()) {
                System.err.println("Errore durante la ricezione: " + e.getMessage());
            }
        } finally {
            try {
                // Chiude la connessione in ogni caso.
                socket.close();
                System.out.println("Connessione chiusa.");
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }

}
