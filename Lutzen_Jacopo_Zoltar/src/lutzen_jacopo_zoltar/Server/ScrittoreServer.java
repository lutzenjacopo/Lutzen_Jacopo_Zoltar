package lutzen_jacopo_zoltar.Server;

import java.io.*;

/**
 * Thread opzionale per messaggi amministrativi del server.
 *
 * <p>Legge la console del server e inoltra ogni riga a tutti i client
 * registrati. Non gestisce le domande personali, che sono responsabilita
 * di {@link LettoreServer}.</p>
 */
public class ScrittoreServer implements Runnable {

    /** Input della console amministrativa. */
    private final BufferedReader tastiera;

    /** Crea il lettore collegato a standard input. */
    public ScrittoreServer() {
        this.tastiera = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            // Questo thread permette al server di inviare comunicazioni globali.
            String messaggio;

            // Legge i messaggi della console finche l'input resta disponibile.
            while ((messaggio = tastiera.readLine()) != null) {
                // Invia il messaggio a tutti i client collegati.
                ServerZoltar.inviaATutti("Server: " + messaggio);

            }
        } catch (IOException e) {
            // Segnala eventuali problemi nella lettura della console.
            System.err.println("Errore durante l'invio: " + e.getMessage());
        }
    }
}
