package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Volo {
    private int idVolo;
    private String compagnia;
    private String aVoloDestinazione;
    private String aVoloOrigine;
    private LocalDate dataVolo;
    private LocalTime oraVoloPrevista;
    private LocalTime ritardo;
    private StatoVolo stato;

    public Volo() {
        //costruttore
    }

    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }

    public int getIdVolo() {
        return idVolo;
    }

    public void setaVoloOrigine(String aVoloOrigine) {
        this.aVoloOrigine = aVoloOrigine;
    }

    public String getaVoloOrigine() {
        return aVoloOrigine;
    }

    public void setaVoloDestinazione(String aVoloDestinazione) {
        this.aVoloDestinazione = aVoloDestinazione;
    }

    public String getaVoloDestinazione() {
        return aVoloDestinazione;
    }

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }

    public String getCompagnia() {
        return compagnia;
    }

    public void setDataVolo(LocalDate dataVolo) {
        this.dataVolo = dataVolo;
    }

    public LocalDate getDataVolo() {
        return dataVolo;
    }

    public void setOraVoloPrevista(LocalTime oraVoloPrevista) {
        this.oraVoloPrevista = oraVoloPrevista;
    }

    public LocalTime getOraVoloPrevista() {
        return oraVoloPrevista;
    }

    public void setRitardo(LocalTime ritardo) {
        this.ritardo = ritardo;
    }

    public LocalTime getRitardo() {
        return ritardo;
    }

    public void setStato(StatoVolo stato) {
        this.stato = stato;
    }

    public StatoVolo getStato() {
        return stato;
    }
}
