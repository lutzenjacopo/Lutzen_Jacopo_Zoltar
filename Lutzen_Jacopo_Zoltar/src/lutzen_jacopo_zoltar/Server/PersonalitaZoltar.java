package lutzen_jacopo_zoltar.Server;

import java.util.concurrent.ThreadLocalRandom;

public class PersonalitaZoltar implements Personalita {

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

    // Nome della personalita restituito al client.
    @Override
    public String getNome() {
        return "Zoltar";
    }

    // Sceglie casualmente una profezia generica.
    @Override
    public String generaRisposta() {
        return RISPOSTE[ThreadLocalRandom.current().nextInt(RISPOSTE.length)];
    }
}
