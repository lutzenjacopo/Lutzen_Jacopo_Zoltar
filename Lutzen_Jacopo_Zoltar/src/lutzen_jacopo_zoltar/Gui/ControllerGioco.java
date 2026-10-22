package lutzen_jacopo_zoltar.Gui;

import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import lutzen_jacopo_gestione_zoltar.ControlliZoltar;
import lutzen_jacopo_gestione_zoltar.ProtocolloZoltar;
import lutzen_jacopo_zoltar.Client.ClientGUI;

/**
 * Controller della schermata in cui l'utente invia le domande.
 *
 * <p>Coordina quattro responsabilita: validazione dell'input, comunicazione
 * con il server, animazione della personalita e visualizzazione delle
 * risposte. Gli eventi ricevuti dalla rete vengono riportati sull'Event
 * Dispatch Thread di Swing prima di modificare la GUI.</p>
 */
public class ControllerGioco implements ClientGUI.Ascoltatore {

    /** Parametri della connessione usata dalla GUI locale. */
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    /** Componenti Swing controllati da questa classe. */
    private final JFrame finestra;
    private final JTextPane campoDomanda;
    private final JButton pulsanteInvia;
    private final JLabel immaginePersonalita;
    private final PersonalitaGrafica personalita;

    /** Client TCP che nasconde i dettagli dei socket. */
    private final ClientGUI client = new ClientGUI();
    /** Caricatore condiviso per anteprime e frame. */
    private final GestoreImmagini immagini = new GestoreImmagini();
    /** Animazione eseguita mentre si attende la profezia. */
    private final AnimazioneFrame animazione;

    // La risposta puo arrivare prima o dopo la fine dell'animazione.
    private boolean animazioneFinita = true;
    private String rispostaInAttesa = null;

    /**
     * Collega la logica ai componenti creati dal form Swing.
     *
     * @param finestra finestra proprietaria dei dialoghi
     * @param campoDomanda area in cui l'utente scrive
     * @param pulsanteInvia pulsante per inviare la domanda
     * @param immaginePersonalita label che mostra la personalita
     * @param personalita scelta dal menu precedente
     */
    public ControllerGioco(JFrame finestra, JTextPane campoDomanda, JButton pulsanteInvia,
            JLabel immaginePersonalita, PersonalitaGrafica personalita) {
        this.finestra = finestra;
        this.campoDomanda = campoDomanda;
        this.pulsanteInvia = pulsanteInvia;
        this.immaginePersonalita = immaginePersonalita;
        this.personalita = personalita;
        this.animazione = new AnimazioneFrame(immaginePersonalita, immagini);

        // Mostra il personaggio scelto nel menu.
        immagini.mostraPersonalita(immaginePersonalita, personalita);
    }

    /**
     * Apre la sessione e seleziona la personalita sul server.
     *
     * @return {@code true} se la connessione e stata aperta
     */
    public boolean connetti() {
        try {
            client.connetti(HOST, PORTA, this);
            client.invia(ProtocolloZoltar.creaPersonalita(personalita.getNomeProtocollo()));
            return true;
        } catch (IOException e) {
            alert("Server non raggiungibile",
                    "Impossibile collegarsi a " + HOST + ":" + PORTA
                    + ".\nAvvia prima ServerZoltar e riprova.",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Valida il testo, costruisce {@code DOM_}, invia la domanda e avvia
     * l'animazione. Il pulsante viene disabilitato fino alla risposta.
     */
    public void inviaDomanda() {
        // La domanda viene verificata prima di essere trasformata in DOM_.
        String testo = campoDomanda.getText();

        if (!ControlliZoltar.testoPresente(testo)) {
            alert("Domanda mancante", "Scrivi una domanda prima di inviare.",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!client.eConnesso()) {
            alert("Non connesso", "La connessione con il server e chiusa.",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Il protocollo e a righe: gli "a capo" della domanda diventano spazi.
        String pulita = testo.replaceAll("\\s+", " ").trim();
        client.invia(ProtocolloZoltar.creaDomanda(pulita));

        campoDomanda.setText("");
        pulsanteInvia.setEnabled(false); // riattivato dopo l'alert con la risposta

        // Prima l'animazione, poi (quando c'e anche la risposta) l'alert.
        rispostaInAttesa = null;
        animazioneFinita = false;
        animazione.avvia(personalita, this::animazioneTerminata);
    }

    /** Mostra una conferma modale prima della chiusura dell'applicazione. */
    public boolean confermaUscita() {
        int scelta = JOptionPane.showConfirmDialog(finestra,
                "Vuoi davvero uscire?", "Esci",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return scelta == JOptionPane.YES_OPTION;
    }

    /** Ferma l'animazione e chiude ordinatamente la sessione TCP. */
    public void chiudi() {
        animazione.ferma();
        client.chiudi();
    }

    // ----- Animazione e risposta -----

    private void animazioneTerminata() {
        animazioneFinita = true;
        mostraRispostaSePronta();
    }

    // Mostra l'alert solo se animazione finita e risposta arrivata.
    /**
     * Sincronizza i due eventi asincroni: risposta ricevuta e animazione finita.
     */
    private void mostraRispostaSePronta() {
        if (!animazioneFinita || rispostaInAttesa == null) {
            return;
        }
        String risposta = rispostaInAttesa;
        rispostaInAttesa = null;

        alert(personalita.getNome() + " risponde", risposta, JOptionPane.INFORMATION_MESSAGE);

        riportaAlPrimoFrame();
        pulsanteInvia.setEnabled(true);
    }

    private void riportaAlPrimoFrame() {
        immagini.mostraPersonalita(immaginePersonalita, personalita);
    }

    // ----- Messaggi dal server (arrivano da un thread in background) -----

    @Override
    public void messaggioRicevuto(String messaggio) {
        SwingUtilities.invokeLater(() -> gestisci(messaggio));
    }

    @Override
    public void connessioneChiusa() {
        SwingUtilities.invokeLater(() -> {
            animazione.ferma();
            pulsanteInvia.setEnabled(false);
            alert("Connessione persa", "Il server ha chiuso la connessione.",
                    JOptionPane.ERROR_MESSAGE);
        });
    }

    /** Traduce i prefissi del protocollo in azioni grafiche. */
    private void gestisci(String messaggio) {
        if (messaggio.startsWith("RSP_")) {
            // Risposta alla domanda: l'alert aspetta la fine dell'animazione.
            rispostaInAttesa = messaggio.substring("RSP_".length());
            mostraRispostaSePronta();
        } else if (messaggio.startsWith("ERRORE_PROTOCOLLO")) {
            animazione.ferma();
            animazioneFinita = true;
            riportaAlPrimoFrame();
            alert("Errore", messaggio, JOptionPane.ERROR_MESSAGE);
            pulsanteInvia.setEnabled(true);
        } else if (messaggio.startsWith("Server:")) {
            // Messaggio scritto dall'amministratore sulla console del server.
            alert("Messaggio dal server", messaggio.substring("Server:".length()).trim(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
        // PERS_OK_... e il saluto finale non richiedono nessun avviso.
    }

    // ----- Alert -----

    private void alert(String titolo, String testo, int tipo) {
        // Converte il testo in HTML per visualizzarlo correttamente nel dialogo.
        String html = "<html><body style='width:300px'>"
                + escapeHtml(testo).replace("\n", "<br>") + "</body></html>";
        JOptionPane.showMessageDialog(finestra, html, titolo, tipo);
    }

    /** Protegge il testo prima di inserirlo nel messaggio HTML di Swing. */
    private static String escapeHtml(String testo) {
        return testo.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
