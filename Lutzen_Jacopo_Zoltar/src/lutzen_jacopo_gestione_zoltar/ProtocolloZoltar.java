package lutzen_jacopo_gestione_zoltar;

/**
 * Definisce il formato dei messaggi scambiati tra client e server.
 *
 * <p>Il protocollo e testuale e basato su righe. Il client invia una riga
 * alla volta e il server interpreta il prefisso:</p>
 *
 * <ul>
 * <li>{@code DOM_} identifica una domanda;</li>
 * <li>{@code PERS_} cambia la personalita attiva;</li>
 * <li>{@code fine} chiude la connessione.</li>
 * </ul>
 *
 * <p>La classe non conserva stato: contiene solo costanti e operazioni
 * statiche per costruire o leggere i messaggi.</p>
 */
public final class ProtocolloZoltar {

    /** Prefisso obbligatorio per una domanda. */
    public static final String PREFISSO_DOMANDA = "DOM_";
    /** Prefisso obbligatorio per selezionare una personalita. */
    public static final String PREFISSO_PERSONALITA = "PERS_";
    /** Comando che richiede la chiusura ordinata della sessione. */
    public static final String COMANDO_FINE = "fine";

    // La classe contiene solo metodi statici e non deve essere istanziata.
    private ProtocolloZoltar() {
    }

    /**
     * Verifica se un messaggio e una domanda valida.
     *
     * @param messaggio riga ricevuta o costruita dal client
     * @return {@code true} se contiene {@code DOM_} seguito da testo
     */
    public static boolean eDomanda(String messaggio) {
        // Delega la validazione alla classe specializzata nei controlli.
        return ControlliZoltar.domandaValida(messaggio);
    }

    /**
     * Costruisce una domanda nel formato previsto dal protocollo.
     *
     * @param domanda testo scritto dall'utente
     * @return messaggio con prefisso {@code DOM_}
     */
    public static String creaDomanda(String domanda) {
        return PREFISSO_DOMANDA + domanda.trim();
    }

    /**
     * Rimuove il prefisso da una domanda gia formattata.
     *
     * @param messaggio messaggio che deve iniziare con {@code DOM_}
     * @return solo il testo della domanda
     */
    public static String estraiTestoDomanda(String messaggio) {
        return messaggio.substring(PREFISSO_DOMANDA.length()).trim();
    }

    /**
     * Verifica se il messaggio seleziona una personalita supportata.
     *
     * @param messaggio comando ricevuto
     * @return {@code true} per {@code PERS_Zoltar}, {@code PERS_Luck}
     *         o {@code PERS_Tarot}
     */
    public static boolean ePersonalita(String messaggio) {
        // Delega il controllo della sintassi e del nome della personalita.
        return ControlliZoltar.personalitaValida(messaggio);
    }

    /**
     * Estrae il nome dalla forma {@code PERS_<nome>}.
     *
     * @param messaggio comando con prefisso {@code PERS_}
     * @return nome della personalita senza prefisso
     */
    public static String nomePersonalita(String messaggio) {
        return messaggio.substring(PREFISSO_PERSONALITA.length()).trim();
    }

    /**
     * Costruisce un comando di selezione personalita.
     *
     * @param nome nome protocollo della personalita
     * @return messaggio nel formato {@code PERS_<nome>}
     */
    public static String creaPersonalita(String nome) {
        return PREFISSO_PERSONALITA + nome;
    }
}
