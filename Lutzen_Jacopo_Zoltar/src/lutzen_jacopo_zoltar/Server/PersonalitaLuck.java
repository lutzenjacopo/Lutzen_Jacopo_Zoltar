package lutzen_jacopo_zoltar.Server;

import java.util.concurrent.ThreadLocalRandom;

public class PersonalitaLuck implements Personalita {

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

    // Nome della personalita restituito al client.
    @Override
    public String getNome() {
        return "Luck";
    }

    // Sceglie casualmente una previsione tra quelle disponibili.
    @Override
    public String generaRisposta() {
        return RISPOSTE[ThreadLocalRandom.current().nextInt(RISPOSTE.length)];
    }
}
