package lutzen_jacopo_zoltar.Client;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import lutzen_jacopo_gestione_zoltar.ProtocolloZoltar;

/**
 * Adattatore TCP usato dalla GUI per comunicare con il server.
 *
 * <p>Questa classe non modifica direttamente Swing. Invia messaggi tramite
 * {@link #invia(String)} e consegna le righe ricevute a un oggetto
 * {@link Ascoltatore}. La separazione impedisce al codice di rete di
 * dipendere dai componenti grafici.</p>
 */
public class ClientGUI {
    // Client TCP usato dall'interfaccia grafica.

    /** Callback con cui il client notifica la GUI degli eventi di rete. */
    public interface Ascoltatore {

        /** Riceve una riga completa arrivata dal server. */
        void messaggioRicevuto(String messaggio);

        /** Riceve una notifica quando la connessione cade inaspettatamente. */
        void connessioneChiusa();
    }

    /** Tempo massimo concesso alla fase di connessione. */
    private static final int TIMEOUT_CONNESSIONE_MS = 3000;

    /** Socket TCP della sessione corrente. */
    private Socket socket;
    /** Flusso testuale usato per inviare righe al server. */
    private PrintWriter out;
    /** Evita di segnalare come errore una chiusura richiesta dall'utente. */
    private volatile boolean chiusuraVolontaria = false;

    /**
     * Apre la connessione e avvia l'ascolto delle risposte in background.
     *
     * @param host indirizzo del server
     * @param porta porta TCP del server
     * @param ascoltatore destinatario delle risposte
     * @throws IOException se socket o flussi non possono essere creati
     */
    public void connetti(String host, int porta, Ascoltatore ascoltatore) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, porta), TIMEOUT_CONNESSIONE_MS);
        out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        Thread lettore = new Thread(() -> {
            try {
                String riga;
                while ((riga = in.readLine()) != null) {
                    ascoltatore.messaggioRicevuto(riga);
                }
            } catch (IOException e) {
                // La chiusura del socket interrompe normalmente readLine().
            } finally {
                if (!chiusuraVolontaria) {
                    ascoltatore.connessioneChiusa();
                }
            }
        }, "lettore-zoltar");
        lettore.setDaemon(true);
        lettore.start();
    }

    /** Indica se la chiusura e stata richiesta dal programma. */
    boolean isChiusuraVolontaria() {
        return chiusuraVolontaria;
    }

    /**
     * Controlla lo stato locale della connessione.
     *
     * @return {@code true} se il socket esiste e non risulta chiuso
     */
    public boolean eConnesso() {
        // Verifica che il socket sia stato aperto e non sia stato chiuso.
        return socket != null && socket.isConnected() && !socket.isClosed();
    }

    /** Invia una riga gia formattata secondo il protocollo. */
    public void invia(String messaggio) {
        // PrintWriter con auto-flush invia subito ogni riga.
        if (out != null) {
            out.println(messaggio);
        }
    }

    /** Invia {@code fine} e rilascia il socket della sessione. */
    public void chiudi() {
        // Segna la chiusura come volontaria per non mostrare un falso errore.
        chiusuraVolontaria = true;
        if (out != null) {
            out.println(ProtocolloZoltar.COMANDO_FINE);
        }
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Errore nella chiusura del socket: " + e.getMessage());
        }
    }
}
