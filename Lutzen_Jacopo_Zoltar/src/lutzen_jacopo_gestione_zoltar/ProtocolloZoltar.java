package lutzen_jacopo_gestione_zoltar;

import lutzen_jacopo_gestione_zoltar.ControlliZoltar;

/**
 * Regole del protocollo di comunicazione tra client e server.
 */
public final class ProtocolloZoltar {

    // Prefisso usato per identificare una domanda inviata dal client.
    public static final String PREFISSO_DOMANDA = "DOM_";
    // Prefisso usato per cambiare la personalita dell'indovino.
    public static final String PREFISSO_PERSONALITA = "PERS_";
    // Comando speciale che chiude la comunicazione.
    public static final String COMANDO_FINE = "fine";

    // La classe contiene solo metodi statici e non deve essere istanziata.
    private ProtocolloZoltar() {
    }

    // Controlla che il messaggio sia una domanda DOM_ con testo non vuoto.
    public static boolean eDomanda(String messaggio) {
        return ControlliZoltar.domandaValida(messaggio);
    }

    // Aggiunge il prefisso DOM_ al testo scritto dall'utente.
    public static String creaDomanda(String domanda) {
        return PREFISSO_DOMANDA + domanda.trim();
    }

    // Restituisce il testo della domanda senza il prefisso DOM_.
    public static String estraiTestoDomanda(String messaggio) {
        return messaggio.substring(PREFISSO_DOMANDA.length()).trim();
    }

    // Verifica che la personalita richiesta sia una delle tre disponibili.
    public static boolean ePersonalita(String messaggio) {
        return ControlliZoltar.personalitaValida(messaggio);
    }

    // Estrae il nome della personalita dal messaggio PERS_.
    public static String nomePersonalita(String messaggio) {
        return messaggio.substring(PREFISSO_PERSONALITA.length()).trim();
    }

    // Costruisce il comando da inviare al server per scegliere una personalita.
    public static String creaPersonalita(String nome) {
        return PREFISSO_PERSONALITA + nome;
    }
}
