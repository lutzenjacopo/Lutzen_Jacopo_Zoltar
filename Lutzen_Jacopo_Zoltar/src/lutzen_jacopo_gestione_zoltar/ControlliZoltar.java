package lutzen_jacopo_gestione_zoltar;

/**
 * Punto unico di validazione degli input del protocollo.
 *
 * <p>Separare i controlli dalla logica client/server evita di duplicare
 * condizioni e fa si che console e GUI applichino le stesse regole.</p>
 */
public final class ControlliZoltar {

    // Classe di utilita: non deve essere istanziata.
    private ControlliZoltar() {
    }

    /**
     * Controlla prefisso e contenuto di una domanda.
     *
     * @param messaggio messaggio da validare
     * @return {@code true} solo se il testo dopo {@code DOM_} non e vuoto
     */
    public static boolean domandaValida(String messaggio) {
        // Una domanda deve iniziare con DOM_ e avere un testo dopo il prefisso.
        if (messaggio == null || !messaggio.startsWith(ProtocolloZoltar.PREFISSO_DOMANDA)) {
            return false;
        }
        return testoPresente(messaggio.substring(
                ProtocolloZoltar.PREFISSO_DOMANDA.length()));
    }

    /**
     * Controlla prefisso e nome di una personalita.
     *
     * @param messaggio messaggio nel formato {@code PERS_<nome>}
     * @return {@code true} se il comando e riconosciuto
     */
    public static boolean personalitaValida(String messaggio) {
        // Controlla prima il prefisso e poi il nome della personalita.
        if (messaggio == null
                || !messaggio.startsWith(ProtocolloZoltar.PREFISSO_PERSONALITA)) {
            return false;
        }

        String nome = messaggio.substring(
                ProtocolloZoltar.PREFISSO_PERSONALITA.length()).trim();
        return personalitaNomeValido(nome);
    }

    /**
     * Valida il solo nome, senza il prefisso del protocollo.
     *
     * @param nome nome da confrontare
     * @return {@code true} se il nome e supportato
     */
    public static boolean personalitaNomeValido(String nome) {
        // Elenco dei nomi accettati dal protocollo.
        return "Zoltar".equals(nome)
                || "Luck".equals(nome)
                || "Tarot".equals(nome);
    }

    /**
     * Controlla che un input non sia nullo ne composto solo da spazi.
     *
     * @param testo testo da controllare
     * @return {@code true} se esiste almeno un carattere utile
     */
    public static boolean testoPresente(String testo) {
        // trim elimina gli spazi usati accidentalmente prima o dopo il testo.
        return testo != null && !testo.trim().isEmpty();
    }

    /**
     * Riconosce il comando di uscita indipendentemente da maiuscole e spazi.
     *
     * @param messaggio riga ricevuta
     * @return {@code true} se la riga equivale a {@code fine}
     */
    public static boolean comandoFine(String messaggio) {
        // Il confronto ignora maiuscole e minuscole.
        return ProtocolloZoltar.COMANDO_FINE.equalsIgnoreCase(
                messaggio == null ? null : messaggio.trim());
    }
}
