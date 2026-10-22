package lutzen_jacopo_zoltar.Server;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Personalita ispirata alle slot machine.
 *
 * <p>Le otto frasi includono sia previsioni fortunate sia avvertimenti su
 * perdite e gioco impulsivo. Una risposta viene scelta casualmente per ogni
 * domanda.</p>
 */
public class PersonalitaLuck implements Personalita {

    // La risposta viene scelta casualmente tra otto profezie sulle slot.
    // Risposte a tema slot, positive o negative, scelte casualmente.
    private static final String[] RISPOSTE = {
        "Prevedo una combinazione fortunata sui rulli: una piccola vincita potrebbe sorprenderti.",
        "Vedo il jackpot vicino, ma ricorda che la fortuna puo cambiare in un istante.",
        "Sette fortunati compariranno nel tuo cammino: oggi potresti ricevere una buona notizia.",
        "I rulli non saranno dalla tua parte: una perdita ti insegnera a non inseguire la fortuna.",
        "Il bonus si accendera presto, ma fermarti al momento giusto evitera una brutta sorpresa.",
        "Campane, stelle e diamanti annunciano un premio inatteso nelle prossime ore.",
        "La ruota girera a vuoto: non sprecare tutte le tue occasioni per inseguire il jackpot.",
        "Un colpo di fortuna arrivera quando meno te lo aspetti, ma non affidare le tue scelte solo al caso."
    };

    /** @return nome protocollo {@code Luck} */
    @Override
    public String getNome() {
        return "Luck";
    }

    /** @return una previsione casuale a tema slot */
    @Override
    public String generaRisposta() {
        return RISPOSTE[ThreadLocalRandom.current().nextInt(RISPOSTE.length)];
    }
}
