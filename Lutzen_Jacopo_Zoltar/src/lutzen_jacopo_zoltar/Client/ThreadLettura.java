/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

/**
 *
 * @author lutzen.jacopo
 */
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
            String messaggio;

            while ((messaggio = in.readLine()) != null) {
                System.out.println("Server: " + messaggio);
            }
            System.out.println("Il server ha chiuso la connessione.");
        } catch (IOException e) {
            if (!socket.isClosed()) {
                System.err.println("Errore durante la ricezione: " + e.getMessage());
            }
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Errore nella chiusura del socket: " + e.getMessage());
            }
        }
    }
    
}
