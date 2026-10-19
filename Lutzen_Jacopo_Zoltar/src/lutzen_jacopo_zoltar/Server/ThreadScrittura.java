package lutzen_jacopo_zoltar.Server;

import java.io.*;

public class ThreadScrittura implements Runnable {

    // Tastiera del server, usata per eventuali messaggi amministrativi.
    private final BufferedReader tastiera;

    // Prepara il flusso di lettura dalla console.
    public ThreadScrittura() {
        this.tastiera = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String messaggio;

            // Legge i messaggi della console finche l'input resta disponibile.
            while ((messaggio = tastiera.readLine()) != null) {
                // Invia il messaggio a tutti i client collegati.
                Zoltar_Server.inviaATutti("Server: " + messaggio);

            }
        } catch (IOException e) {
            // Segnala eventuali problemi nella lettura della console.
            System.err.println("Errore durante l'invio: " + e.getMessage());
        }
    }
}
