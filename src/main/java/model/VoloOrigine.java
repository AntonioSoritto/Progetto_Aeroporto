package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoloOrigine extends Volo {
    private Gate imbarco;

    public VoloOrigine(Gate imbarco, Volo v, int idVolo, String Compagnia,  String A_Volo_Destinazione, String A_Volo_Origine, LocalDate Data_Volo, LocalTime Ora_Volo_Prevista, LocalTime Ritardo, StatoVolo stato ) {
        super(idVolo, Compagnia, A_Volo_Destinazione, A_Volo_Origine, Data_Volo, Ora_Volo_Prevista, stato, Ritardo);
        v.setA_Volo_Origine("NAPOLI");
        this.imbarco = imbarco;
    }
    public void setImbarco(Gate imbarco) {
        this.imbarco = imbarco;
    }
    public Gate getImbarco() {
        return imbarco;
    }





}
