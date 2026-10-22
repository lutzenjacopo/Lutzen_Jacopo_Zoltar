package lutzen_jacopo_zoltar.Gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Esegue una riproduzione lineare dei frame di una personalita.
 *
 * <p>Usa {@link Timer}, quindi gli aggiornamenti avvengono nel thread grafico
 * di Swing. L'animazione non e ciclica: raggiunto l'ultimo frame si ferma e
 * invoca la callback fornita dal controller.</p>
 */
public class AnimazioneFrame {

    /** Durata di visualizzazione di ciascun frame. */
    private static final int INTERVALLO_MS = 300;

    private static final int LABEL_W_DEFAULT = 300;
    private static final int LABEL_H_DEFAULT = 235;

    private final JLabel label;
    private final GestoreImmagini immagini;
    private Timer timer;

    /** Collega la label di destinazione al caricatore delle immagini. */
    public AnimazioneFrame(JLabel label, GestoreImmagini immagini) {
        this.label = label;
        this.immagini = immagini;
    }

    /**
     * Carica i frame disponibili e avvia il timer.
     *
     * @param personalita personalita di cui visualizzare i frame
     * @param alTermine callback chiamata quando l'ultimo frame e concluso
     */
    public void avvia(PersonalitaGrafica personalita, Runnable alTermine) {
        // Ferma un'eventuale animazione precedente prima di iniziarne una nuova.
        ferma();

        int w = label.getWidth() > 0 ? label.getWidth() : LABEL_W_DEFAULT;
        int h = label.getHeight() > 0 ? label.getHeight() : LABEL_H_DEFAULT;

        // Carica i frame; quelli mancanti vengono saltati (con avviso nel log).
        List<ImageIcon> icone = new ArrayList<>();
        for (String percorso : personalita.getFrames()) {
            ImageIcon icona = immagini.carica(percorso, w, h);
            if (icona != null) {
                icone.add(icona);
            }
        }

        if (icone.size() < 2) {
            alTermine.run();
            return;
        }

        label.setText(null);
        label.setIcon(icone.get(0));

        final int[] indice = {0};
        timer = new Timer(INTERVALLO_MS, evt -> {
            indice[0]++;
            if (indice[0] < icone.size()) {
                label.setIcon(icone.get(indice[0]));
            } else {
                ferma();
                alTermine.run();
            }
        });
        timer.start();
    }

    /** Interrompe il timer corrente, se esiste. */
    public void ferma() {
        // Timer Swing aggiorna l'interfaccia sul thread grafico.
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }
}
