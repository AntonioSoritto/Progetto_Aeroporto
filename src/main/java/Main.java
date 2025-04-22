import model.StatoVolo;
import model.Volo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Dati del volo
        Volo volo = new Volo(
                123,
                "Alitalia",
                "Roma Fiumicino",
                "Milano Malpensa",
                LocalDate.of(2025, 5, 10),
                LocalTime.of(14, 30),
                StatoVolo.IN_RITARDO,  // o IN_RITARDO, CANCELLATO
                LocalTime.of(0, 0)     // ritardo (es. 00:00 per nessun ritardo)
        );


        // Stampa dati volo
        System.out.println("Volo creato con successo:");
        System.out.println("ID: " + volo.getIdVolo());
        System.out.println("Compagnia: " + volo.getCompagnia());
        System.out.println("Origine: " + volo.getA_Volo_Origine());
        System.out.println("Destinazione: " + volo.getA_Volo_Destinazione());
        System.out.println("Data: " + volo.getData_Volo());
        System.out.println("Ora prevista: " + volo.getOra_Volo_Prevista());
        System.out.println("Stato: " + volo.getStato());
        System.out.println("Ritardo: " + volo.getRitardo());
    }
}
