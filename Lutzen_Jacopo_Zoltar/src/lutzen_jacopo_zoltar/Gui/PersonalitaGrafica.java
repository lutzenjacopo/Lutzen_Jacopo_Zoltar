package lutzen_jacopo_zoltar.Gui;

/**
 * Catalogo delle personalita mostrate dalla GUI.
 *
 * <p>Ogni costante collega tre informazioni: il nome visualizzato, il nome
 * inviato al server e l'elenco delle risorse grafiche da animare.</p>
 */
public enum PersonalitaGrafica {

    NESSUNO("Nessuno", "Nessuno"),
    ZOLTAR("Zoltar", "Zoltar", frames("Zoltar", "Z")),
        // Hakari ha 10 frame: Frame_1_L.png ... Frame_10_L.png nella cartella Hakari.
    LUCK("Hakari", "Luck", frames("Hakari", "L", 10)),
    // Il primo frame e quello gia usato nel designer (percorso sicuro), gli altri
    // sono Frame_2_T.png ... Frame_6_T.png nella cartella Avdoul.
    TAROT("Avdoul", "Tarot", frames("Avdoul", "T", "/Img_Personalità/Avdoul/Frame_1_T_P.png"));

    private static final int NUMERO_FRAME = 6;

    private final String nome;
    private final String nomeProtocollo;
    private final String[] frames;

    /** Costruttore interno usato dalle costanti dell'enum. */
    PersonalitaGrafica(String nome, String nomeProtocollo, String... frames) {
        this.nome = nome;
        this.nomeProtocollo = nomeProtocollo;
        this.frames = frames;
    }
    private static String[] frames(String cartella, String suffisso) {
        return frames(cartella, suffisso, NUMERO_FRAME);
    }

    /**
     * Genera i percorsi standard di un numero qualsiasi di frame.
     */
    private static String[] frames(String cartella, String suffisso, int numero) {
        String[] percorsi = new String[numero];
        for (int i = 0; i < numero; i++) {
            percorsi[i] = "/Img_Personalità/" + cartella
                    + "/Frame_" + (i + 1) + "_" + suffisso + ".png";
        }
        return percorsi;
    }

    /** Genera i percorsi standard sostituendo solo il primo frame. */
    private static String[] frames(String cartella, String suffisso, String primoFrame) {
        String[] percorsi = frames(cartella, suffisso);
        percorsi[0] = primoFrame;
        return percorsi;
    }

    /** @return nome visualizzato nella combo box */
    public String getNome() {
        return nome;
    }

    /** @return nome da usare nel comando {@code PERS_} */
    public String getNomeProtocollo() {
        return nomeProtocollo;
    }

    /** @return copia dei percorsi dei frame */
    public String[] getFrames() {
        // Restituisce una copia per impedire modifiche esterne all'enum.
        return frames.clone();
    }

    /** @return primo percorso oppure {@code null} se non esistono frame */
    public String getPrimoFrame() {
        return frames.length > 0 ? frames[0] : null;
    }

    /** @return {@code true} se l'animazione ha almeno due frame */
    public boolean haAnimazione() {
        return frames.length > 1;
    }

    /** @return {@code true} quando la voce rappresenta una scelta reale */
    public boolean eSelezionata() {
        return this != NESSUNO;
    }

    /**
     * Cerca una voce usando nome grafico o nome protocollo.
     *
     * @param testo valore letto dalla combo box
     * @return voce corrispondente o {@link #NESSUNO}
     */
    public static PersonalitaGrafica daNome(String testo) {
        // Permette di usare sia il nome grafico sia quello del protocollo.
        if (testo == null) {
            return NESSUNO;
        }
        String pulito = testo.trim();
        for (PersonalitaGrafica p : values()) {
            if (p.nome.equalsIgnoreCase(pulito) || p.nomeProtocollo.equalsIgnoreCase(pulito)) {
                return p;
            }
        }
        return NESSUNO;
    }
}
