package lutzen_jacopo_zoltar.Server;

/**
 * Personalita generica e predefinita del server.
 */
public class PersonalitaZoltar implements Personalita {

    private final DataMenagerJson dataMenager;

    /** @param dataMenager gestore condiviso delle risposte JSON */
    public PersonalitaZoltar(DataMenagerJson dataMenager) {
        this.dataMenager = dataMenager;
    }

    /** @return nome protocollo {@code Zoltar} */
    @Override
    public String getNome() {
        return "Zoltar";
    }

    /** @return una risposta casuale letta dal file JSON */
    @Override
    public String generaRisposta() {
        return dataMenager.getRisposta(getNome());
    }
}
