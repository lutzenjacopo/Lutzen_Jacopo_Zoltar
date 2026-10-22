package lutzen_jacopo_zoltar.Gui;

import java.awt.Image;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Servizio di caricamento e ridimensionamento delle immagini della GUI.
 *
 * <p>Le immagini vengono cercate nel classpath, ridimensionate mantenendo
 * le proporzioni e memorizzate in una cache indicizzata da percorso e
 * dimensioni. In caso di risorsa mancante viene registrato un warning e la
 * GUI puo mostrare il messaggio di lavoro in corso.</p>
 */
public class GestoreImmagini {

    private static final Logger LOGGER = Logger.getLogger(GestoreImmagini.class.getName());

    /** Dimensioni di fallback durante la costruzione iniziale della GUI. */
    private static final int LABEL_W_DEFAULT = 300;
    private static final int LABEL_H_DEFAULT = 235;

    /** Cache locale delle icone gia ridimensionate. */
    private final Map<String, ImageIcon> cache = new HashMap<>();

    // Carica, ridimensiona e memorizza un'immagine per riutilizzarla.
    /**
     * Carica o recupera dalla cache un'immagine ridimensionata.
     *
     * @param percorso percorso assoluto della risorsa nel classpath
     * @param larghezzaMax larghezza massima richiesta
     * @param altezzaMax altezza massima richiesta
     * @return icona pronta per Swing oppure {@code null} se non trovata
     */
    public ImageIcon carica(String percorso, int larghezzaMax, int altezzaMax) {
        if (percorso == null) {
            return null;
        }

        String chiave = percorso + "@" + larghezzaMax + "x" + altezzaMax;
        ImageIcon inCache = cache.get(chiave);
        if (inCache != null) {
            return inCache;
        }

        URL url = getClass().getResource(percorso);
        if (url == null) {
            LOGGER.log(Level.WARNING, "Immagine non trovata: {0}", percorso);
            return null;
        }

        ImageIcon originale = new ImageIcon(url);
        int w = originale.getIconWidth();
        int h = originale.getIconHeight();
        if (w <= 0 || h <= 0) {
            return originale;
        }

        double scala = Math.min((double) larghezzaMax / w, (double) altezzaMax / h);
        Image scalata = originale.getImage().getScaledInstance(
                (int) (w * scala), (int) (h * scala), Image.SCALE_SMOOTH);

        ImageIcon icona = new ImageIcon(scalata);
        cache.put(chiave, icona);
        return icona;
    }

    /**
     * Mostra il primo frame o un segnaposto se la risorsa non e disponibile.
     *
     * @param label componente su cui visualizzare l'immagine
     * @param personalita personalita da rappresentare
     */
    public void mostraPersonalita(JLabel label, PersonalitaGrafica personalita) {
        int w = label.getWidth() > 0 ? label.getWidth() : LABEL_W_DEFAULT;
        int h = label.getHeight() > 0 ? label.getHeight() : LABEL_H_DEFAULT;

        ImageIcon icona = carica(personalita.getPrimoFrame(), w, h);
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        if (icona != null) {
            label.setText(null);
            label.setIcon(icona);
        } else {
            label.setIcon(null);
            label.setFont(new java.awt.Font("Arial Narrow", java.awt.Font.BOLD, 48));
            label.setForeground(new java.awt.Color(153, 153, 0));
            label.setText("<html><div style='text-align:center'>WORK IN<br>PROGRESS</div></html>");
        }
    }
}
