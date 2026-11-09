package lutzen_jacopo_zoltar.Server;

/**
 * Contratto per serializzare e deserializzare un tipo di dati.
 *
 * @param <T> tipo rappresentato dal formato testuale
 */
public interface SerDes<T> {

    /**
     * Converte un valore nel relativo formato testuale.
     *
     * @param valore valore da serializzare
     * @return rappresentazione testuale del valore
     */
    String ser(T valore);

    /**
     * Ricostruisce un valore a partire dal formato testuale.
     *
     * @param testo testo da deserializzare
     * @return valore ricostruito
     */
    T des(String testo);
}