package lutzen_jacopo_zoltar.Server;

import lutzen_jacopo_zoltar.ProtocolloZoltar;

/**
 * Produce le risposte predefinite di Zoltar.
 */
public class Indovino {

    // Ogni client parte sempre con la personalita generale Zoltar.
    private Personalita personalita = new PersonalitaZoltar();

    // Interpreta il comando ricevuto e restituisce la risposta corretta.
    public String rispondi(String messaggio) {
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
