package lutzen_jacopo_zoltar;

/**
 * Contiene tutti i controlli sui messaggi del protocollo Zoltar.
 */
public final class ControlliZoltar {

    private ControlliZoltar() {
    }

    /**
     * Controlla se il messaggio contiene una domanda DOM_ non vuota.
     */
    public static boolean domandaValida(String messaggio) {
        if (messaggio == null || !messaggio.startsWith(ProtocolloZoltar.PREFISSO_DOMANDA)) {
            return false;
        }
        return testoPresente(messaggio.substring(
                ProtocolloZoltar.PREFISSO_DOMANDA.length()));
    }

    /**
     * Controlla se il messaggio contiene una personalita valida.
     */
    public static boolean personalitaValida(String messaggio) {
        if (messaggio == null
                || !messaggio.startsWith(ProtocolloZoltar.PREFISSO_PERSONALITA)) {
            return false;
        }

        String nome = messaggio.substring(
                ProtocolloZoltar.PREFISSO_PERSONALITA.length()).trim();
        return personalitaNomeValido(nome);
    }

    /**
     * Controlla se il nome corrisponde a una personalita esistente.
     */
    public static boolean personalitaNomeValido(String nome) {
        return "Zoltar".equals(nome)
                || "Luck".equals(nome)
                || "Tarot".equals(nome);
    }

    /**
     * Controlla se un testo non e nullo e contiene caratteri effettivi.
     */
    public static boolean testoPresente(String testo) {
        return testo != null && !testo.trim().isEmpty();
    }

    /**
     * Controlla se il messaggio e il comando di chiusura della connessione.
     */
    public static boolean comandoFine(String messaggio) {
        return ProtocolloZoltar.COMANDO_FINE.equalsIgnoreCase(
                messaggio == null ? null : messaggio.trim());
    }
}
