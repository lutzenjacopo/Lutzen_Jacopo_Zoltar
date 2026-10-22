package lutzen_jacopo_zoltar.Gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Controller del menu principale.
 *
 * <p>Il form contiene i componenti grafici, mentre questa classe mantiene
 * la logica dello stato: moneta inserita, personalita selezionata, anteprima
 * visibile e pulsante di avvio abilitato.</p>
 */
public class ControllerMenu {

    /** Componenti visuali manipolati dal controller. */
    private final JComboBox<String> comboPersonalita;
    private final JLabel anteprima;
    private final JLabel pulsante;
    private final JLabel inserisci1;
    private final JLabel inserisci2;

    private final GestoreImmagini immagini = new GestoreImmagini();

    /** Riceve i componenti gia creati dal form e imposta lo stato iniziale. */
    public ControllerMenu(JComboBox<String> comboPersonalita, JLabel anteprima,
            JLabel pulsante, JLabel inserisci1, JLabel inserisci2) {
        this.comboPersonalita = comboPersonalita;
        this.anteprima = anteprima;
        this.pulsante = pulsante;
        this.inserisci1 = inserisci1;
        this.inserisci2 = inserisci2;

        impostaStatoIniziale();
    }

    /** Configura il menu prima dell'inserimento della moneta. */
    private void impostaStatoIniziale() {
        comboPersonalita.setVisible(false);
        anteprima.setVisible(false);
        pulsante.setEnabled(false);
    }

    /** Sblocca la scelta della personalita dopo il click sulla moneta. */
    public void inserisciMoneta() {
        inserisci1.setVisible(false);
        inserisci2.setVisible(false);
        comboPersonalita.setVisible(true);
    }

    /** Aggiorna anteprima e stato del pulsante dopo una selezione. */
    public void personalitaSelezionata() {
        // Aggiorna l'interfaccia in base alla voce selezionata.
        PersonalitaGrafica scelta = personalitaCorrente();

        pulsante.setEnabled(scelta.eSelezionata());

        switch (scelta) {
            case ZOLTAR:
            case LUCK:
            case TAROT:
                mostraAnteprima(scelta);
                break;
            default: // NESSUNO
                nascondiAnteprima();
                break;
        }
    }

    /**
     * Restituisce la scelta solo se il pulsante puo avviare il gioco.
     *
     * @return personalita selezionata oppure {@code null}
     */
    public PersonalitaGrafica pulsanteCliccato() {
        // Evita di avviare il gioco se non e stata scelta una personalita.
        if (!pulsante.isEnabled()) {
            return null;
        }
        return personalitaCorrente();
    }

    // Personalita attualmente scelta nella combo box.
    public PersonalitaGrafica personalitaCorrente() {
        return PersonalitaGrafica.daNome((String) comboPersonalita.getSelectedItem());
    }

    private void mostraAnteprima(PersonalitaGrafica personalita) {
        immagini.mostraPersonalita(anteprima, personalita);
        anteprima.setVisible(true);
    }

    private void nascondiAnteprima() {
        anteprima.setIcon(null);
        anteprima.setText(null);
        anteprima.setVisible(false);
    }
}
