/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lutzen_jacopo_zoltar.Client;

import java.io.*;
import java.net.*;
/**
 *
 * @author lutzen.jacopo
 */
public class ThreadScrittura implements Runnable{
    
    private Socket socket;
    private PrintWriter out;
    private BufferedReader tastiera;

    // Il costruttore riceve il Socket aperto verso il server
    public ThreadScrittura(Socket socket) {
        this.socket = socket;
        try {
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.tastiera = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.err.println("Errore nella creazione dei flussi I/O: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            String messaggio;
            while ((messaggio = tastiera.readLine()) != null) {
                out.println(messaggio);

                if ("fine".equalsIgnoreCase(messaggio)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Errore durante l'invio: " + e.getMessage());
        } finally {
            out.close();
        }
    }
    
}
