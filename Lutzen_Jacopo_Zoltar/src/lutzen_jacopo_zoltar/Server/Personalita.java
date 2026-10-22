package lutzen_jacopo_zoltar.Server;

/**
 * Contratto comune delle personalita di Zoltar.
 *
 * <p>L'interfaccia permette a {@link Indovino} di cambiare comportamento
 * senza conoscere i dettagli delle risposte di Luck, Tarot o Zoltar.</p>
 */
public interface Personalita {

    /** @return nome usato nel comando e nella conferma {@code PERS_OK_} */
    String getNome();

    /** @return una profezia predefinita della personalita */
    String generaRisposta();
}
