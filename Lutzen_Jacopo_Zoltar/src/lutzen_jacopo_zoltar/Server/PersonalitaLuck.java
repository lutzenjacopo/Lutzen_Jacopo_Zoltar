package lutzen_jacopo_zoltar.Server;

/**
 * Personalita ispirata alle slot machine.
 */
public class PersonalitaLuck implements Personalita {

    private final DataMenagerJson dataMenager;

    /** @param dataMenager gestore condiviso delle risposte JSON */
    public PersonalitaLuck(DataMenagerJson dataMenager) {
        this.dataMenager = dataMenager;
    }

    /** @return nome protocollo {@code Luck} */
    @Override
    public String getNome() {
        return "Luck";
    }

    /** @return una risposta casuale letta dal file JSON */
    @Override
    public String generaRisposta() {
        return dataMenager.getRisposta(getNome());
    }
}
