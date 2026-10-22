package lutzen_jacopo_zoltar.Server;

import lutzen_jacopo_gestione_zoltar.ProtocolloZoltar;

/**
 * Coordina la personalita attiva e la risposta alle richieste del client.
 *
 * <p>All'avvio usa {@link PersonalitaZoltar}. Un comando {@code PERS_...}
 * sostituisce l'oggetto corrente; una domanda {@code DOM_...} viene invece
 * inoltrata alla personalita selezionata.</p>
 */
public class Indovino {

    /** Strategia di risposta attiva per questa specifica connessione. */
    private Personalita personalita = new PersonalitaZoltar();

    /**
     * Interpreta un comando del protocollo.
     *
     * @param messaggio comando ricevuto dal client
     * @return conferma, profezia o errore di protocollo
     */
    public String rispondi(String messaggio) {
        // Prima vengono controllati i comandi di cambio personalita.
        // Se arriva PERS_, cambia la personalita usata nelle risposte successive.
        if (ProtocolloZoltar.ePersonalita(messaggio)) {
            String nome = ProtocolloZoltar.nomePersonalita(messaggio);

            // Crea l'oggetto corrispondente alla personalita richiesta.
            if (nome.equals("Tarot")) {
                personalita = new PersonalitaTarot();
            } else if (nome.equals("Luck")) {
                personalita = new PersonalitaLuck();
            } else {
                personalita = new PersonalitaZoltar();
            }

            // Conferma al client il cambio di personalita.
            return "PERS_OK_" + personalita.getNome();
        }

        // Qualsiasi messaggio diverso da una domanda valida e rifiutato.
        if (!ProtocolloZoltar.eDomanda(messaggio)) {
            return "ERRORE_PROTOCOLLO: usa DOM_<domanda> per una domanda "
                    + "oppure PERS_Zoltar, PERS_Luck o PERS_Tarot per cambiare personalita.";
        }

        // Genera una profezia con la personalita attualmente selezionata.
        return "RSP_" + personalita.generaRisposta();
    }
}
