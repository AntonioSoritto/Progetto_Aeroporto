package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Volo {
    private int idVolo;
    private String Compagnia;
    private String A_Volo_Destinazione;
    private String A_Volo_Origine;
    private LocalDate Data_Volo;
    private LocalTime Ora_Volo_Prevista;
    private LocalTime Ritardo;
    private StatoVolo stato;

    public Volo() {
        this.idVolo = idVolo;
        this.Compagnia = Compagnia;
        this.A_Volo_Destinazione = A_Volo_Destinazione;
        this.A_Volo_Origine = A_Volo_Origine;
        this.Data_Volo = Data_Volo;
        this.Ora_Volo_Prevista = Ora_Volo_Prevista;
        this.Ritardo = Ritardo;
        this.stato = stato;
    }

    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }

    public int getIdVolo() {
        return idVolo;
    }

    public void setA_Volo_Origine(String A_Volo_Origine) {
        this.A_Volo_Origine = A_Volo_Origine;
    }

    public String getA_Volo_Origine() {
        return A_Volo_Origine;
    }

    public void setA_Volo_Destinazione(String A_Volo_Destinazione) {
        this.A_Volo_Destinazione = A_Volo_Destinazione;
    }

    public String getA_Volo_Destinazione() {
        return A_Volo_Destinazione;
    }

    public void setCompagnia(String Compagnia) {
        this.Compagnia = Compagnia;
    }

    public String getCompagnia() {
        return Compagnia;
    }

    public void setData_Volo(LocalDate Data_Volo) {
        this.Data_Volo = Data_Volo;
    }

    public LocalDate getData_Volo() {
        return Data_Volo;
    }

    public void setOra_Volo_Prevista(LocalTime Ora_Volo_Prevista) {
        this.Ora_Volo_Prevista = Ora_Volo_Prevista;
    }

    public LocalTime getOra_Volo_Prevista() {
        return Ora_Volo_Prevista;
    }

    public void setRitardo(LocalTime Ritardo) {
        this.Ritardo = Ritardo;
    }

    public LocalTime getRitardo() {
        return Ritardo;
    }

    public void setStato(StatoVolo stato) {
        this.stato = stato;
    }

    public StatoVolo getStato() {
        return stato;
    }
}
