package lutzen_jacopo_zoltar.Server;

import java.net.*;
import java.io.*;

public class ThreadScrittura implements Runnable {

    private final BufferedReader tastiera;

    public ThreadScrittura() {
        this.tastiera = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        try {
            String messaggio;
            while ((messaggio = tastiera.readLine()) != null) {
                Zoltar_Server.inviaATutti("Server: " + messaggio);

            }
        } catch (IOException e) {
            System.err.println("Errore durante l'invio: " + e.getMessage());
        }
    }
}
