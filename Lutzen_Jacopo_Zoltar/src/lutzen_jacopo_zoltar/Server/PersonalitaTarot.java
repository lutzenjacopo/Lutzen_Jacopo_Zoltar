package lutzen_jacopo_zoltar.Server;

import java.util.concurrent.ThreadLocalRandom;

public class PersonalitaTarot implements Personalita {

    // Profezie ispirate agli Arcani maggiori e rivolte al futuro dell'utente.
    private static final String[] RISPOSTE = {
        "Le carte mostrano il Matto: un nuovo inizio sta arrivando e dovrai avere il coraggio di seguirlo.",
        "Il simbolo del Sole illumina il tuo cammino: una vittoria o una buona notizia e vicina.",
        "La carta del Mago consiglia di fidarti delle tue capacita: troverai una soluzione quando sembrera impossibile.",
        "La Papessa custodisce un segreto: presto scoprirai qualcosa che cambiera il tuo modo di vedere una persona.",
        "L'Imperatrice annuncia crescita e prosperita: un tuo progetto iniziera finalmente a dare frutti.",
        "Gli Amanti mostrano una scelta importante: il tuo cuore ti aiutera a prendere la decisione giusta.",
        "La Ruota della Fortuna sta girando: un cambiamento inatteso portera nuove opportunita nella tua vita.",
        "La Stella brilla sul tuo futuro: dopo un periodo difficile ritroverai speranza e raggiungerai un obiettivo."
    };

    // Nome della personalita restituito al client.
    @Override
    public String getNome() {
        return "Tarot";
    }

    // Sceglie casualmente una profezia dei tarocchi.
    @Override
    public String generaRisposta() {
        return RISPOSTE[ThreadLocalRandom.current().nextInt(RISPOSTE.length)];
    }
}
