package lutzen_jacopo_zoltar.Server;

/**
 * Personalita basata sugli Arcani maggiori dei tarocchi.
 */
public class PersonalitaTarot implements Personalita {

    private final DataMenagerJson dataMenager;

    /** @param dataMenager gestore condiviso delle risposte JSON */
    public PersonalitaTarot(DataMenagerJson dataMenager) {
        this.dataMenager = dataMenager;
    }

    /** @return nome protocollo {@code Tarot} */
    @Override
    public String getNome() {
        return "Tarot";
    }

    /** @return una risposta casuale letta dal file JSON */
    @Override
    public String generaRisposta() {
        return dataMenager.getRisposta(getNome());
    }
}
