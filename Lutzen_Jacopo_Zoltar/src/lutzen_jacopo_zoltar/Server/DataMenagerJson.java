package lutzen_jacopo_zoltar.Server;

import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

/**
 * Legge le risposte delle personalita dal file JSON di Zoltar.
 *
 * <p>Implementa {@link SerDes}: {@code ser} converte {@link Risposte} in JSON,
 * {@code des} ricostruisce {@link Risposte} dal testo JSON.</p>
 */
public class DataMenagerJson implements SerDes<Risposte> {

    private final Gson gson = new Gson();
    private final Random random = new Random();
    private Risposte risposte;

    /** Carica le risposte dal file risposte.json. */
    public DataMenagerJson() {
        try {
            String contenuto = new String(Files.readAllBytes(Paths.get("risposte.json")));
            risposte = des(contenuto);
        } catch (Exception e) {
            throw new IllegalStateException("Impossibile leggere risposte.json", e);
        }
    }

    @Override
    public String ser(Risposte valore) {
        return gson.toJson(valore);
    }

    @Override
    public Risposte des(String testo) {
        return gson.fromJson(testo, Risposte.class);
    }

    /**
     * Restituisce una risposta casuale per la personalita indicata.
     *
     * @param personalita nome della personalita
     * @return risposta presente nel file JSON
     */
    public String getRisposta(String personalita) {
        List<String> elenco;
        if (personalita.equals("Luck")) {
            elenco = risposte.Luck;
        } else if (personalita.equals("Tarot")) {
            elenco = risposte.Tarot;
        } else {
            elenco = risposte.Zoltar;
        }
        return elenco.get(random.nextInt(elenco.size()));
    }
}
