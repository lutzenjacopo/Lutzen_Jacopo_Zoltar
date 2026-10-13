package lutzen_jacopo_zoltar.Server;

import java.net.*;
import java.io.*;

public class ThreadLettura implements Runnable {

    private final Socket socket;

    public ThreadLettura(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Zoltar_Server.aggiungiCliente(out);
            String messaggio;

            while ((messaggio = in.readLine()) != null) {
                System.out.println("Client: " + messaggio);
                Zoltar_Server.inviaATutti("Client: " + messaggio);
            }
            System.out.println("Il client ha chiuso la connessione.");
            Zoltar_Server.rimuoviCliente(out);
        } catch (IOException e) {
            if (!socket.isClosed()) {
                System.err.println("Errore durante la ricezione: " + e.getMessage());
            }
        } finally {
            try {
                socket.close();
                System.out.println("Connessione chiusa.");
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }
}
