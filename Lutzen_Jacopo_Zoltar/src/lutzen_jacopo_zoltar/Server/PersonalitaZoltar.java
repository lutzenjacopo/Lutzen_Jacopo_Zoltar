package lutzen_jacopo_zoltar.Server;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Personalita generica e predefinita del server.
 *
 * <p>Viene usata all'avvio di ogni connessione, prima che il client scelga
 * eventualmente Luck o Tarot.</p>
 */
public class PersonalitaZoltar implements Personalita {

    // Personalita generica attiva come comportamento predefinito.
    // Profezie generiche della personalita predefinita di Zoltar.
    private static final String[] RISPOSTE = {
        "Le stelle sono dalla tua parte.",
        "Il futuro e incerto, ma sento buone notizie in arrivo.",
        "Con un po' di impegno otterrai il risultato che desideri.",
        "Non avere fretta: la risposta arrivera al momento giusto.",
        "Gli astri consigliano di fidarti del tuo istinto.",
        "Vedo una sorpresa positiva all'orizzonte.",
        "Un incontro inatteso potrebbe cambiare il corso dei tuoi prossimi giorni.",
        "La risposta che cerchi e piu vicina di quanto immagini: osserva i piccoli segnali."
    };

    /** @return nome protocollo {@code Zoltar} */
    @Override
    public String getNome() {
        return "Zoltar";
    }

    /** @return una profezia generica scelta casualmente */
    @Override
    public String generaRisposta() {
        return RISPOSTE[ThreadLocalRandom.current().nextInt(RISPOSTE.length)];
    }
}
