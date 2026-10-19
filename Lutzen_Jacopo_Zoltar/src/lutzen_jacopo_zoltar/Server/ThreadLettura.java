package lutzen_jacopo_zoltar.Server;

import java.net.*;
import java.io.*;
import lutzen_jacopo_zoltar.ControlliZoltar;

public class ThreadLettura implements Runnable {

    // Connessione con uno specifico client.
    private final Socket socket;
    // Indovino dedicato al client, con una personalita indipendente dagli altri.
    private final Indovino indovino = new Indovino();

    // Salva il socket della connessione da gestire.
    public ThreadLettura(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Crea i flussi per ricevere dal client e rispondere al client.
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // Registra il client tra quelli collegati al server.
            Zoltar_Server.aggiungiCliente(out);
            String messaggio;

            // Elabora ogni riga ricevuta finche il client resta connesso.
            while ((messaggio = in.readLine()) != null) {
                System.out.println("Client: " + messaggio);

                // Il comando fine chiude educatamente la sessione.
                if (ControlliZoltar.comandoFine(messaggio)) {
                    out.println("Zoltar: A presto, torna quando vorrai conoscere il futuro.");
                    break;
                }

                // L'indovino interpreta PERS_ o DOM_ e produce la risposta.
                out.println(indovino.rispondi(messaggio));
            }
            System.out.println("Il client ha chiuso la connessione.");
            Zoltar_Server.rimuoviCliente(out);
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
