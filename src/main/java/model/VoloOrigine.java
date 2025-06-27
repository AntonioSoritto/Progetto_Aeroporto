package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoloOrigine extends Volo {
    private Gate imbarco;

    public VoloOrigine() {
        super();
    }

    public VoloOrigine(int idVolo, String compagnia, String aVoloOrigine,
                       String aVoloDestinazione, LocalDate dataVolo,
                       LocalTime oraPrevista, LocalTime ritardo,
                       StatoVolo stato, Gate imbarco) {
        super();
        this.imbarco = imbarco;
    }

    public Gate getImbarco() {
        return imbarco;
    }

    public void setImbarco(Gate imbarco) {
        this.imbarco = imbarco;
    }
}