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

    private static final List<PrintWriter> CLIENTI = new ArrayList<>();

    public static synchronized void aggiungiCliente(PrintWriter out) {
        CLIENTI.add(out);
    }

    public static synchronized void rimuoviCliente(PrintWriter out) {
        CLIENTI.remove(out);
        out.close();
    }

    public static synchronized void inviaATutti(String messaggio) {
        for (PrintWriter out : CLIENTI) {
            out.println(messaggio);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket sSocket;
        Socket connessione;
        final int PORT = 12345; // (final significa che è 1 costante)
        try {
            sSocket = new ServerSocket(PORT);
            Thread threadScrittura = new Thread(new ThreadScrittura());
            threadScrittura.setDaemon(true);
            threadScrittura.start();

            while (true) {
                System.out.println("In attesa di connessioni...");
                connessione = sSocket.accept();
                System.out.println("Connessione stabilita.");
                System.out.println(connessione.getInetAddress());

                Thread threadLettura = new Thread(
                        new ThreadLettura(connessione));
                threadLettura.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
    

