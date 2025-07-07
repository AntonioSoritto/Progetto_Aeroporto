package model;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Rappresenta un volo, includendo informazioni su compagnia, origine, destinazione,
 * data, orario previsto, eventuale ritardo e stato attuale.
 */

public class Volo {
    /** Identificativo univoco del volo */

    private int idVolo;
    /** Nome della compagnia aerea */

    private String compagnia;
    /** Aeroporto di destinazione del volo */

    private String aVoloDestinazione;
    /** Aeroporto di origine del volo */

    private String aVoloOrigine;
    /** Data prevista per il volo */

    private LocalDate dataVolo;
    /** Ora prevista di partenza del volo */

    private LocalTime oraVoloPrevista;
    /** Ritardo stimato del volo, se presente */

    private LocalTime ritardo;
    /** Stato attuale del volo */

    private StatoVolo stato;
    /**
     * Costruttore di default della classe Volo.
     */

    public Volo() {
        //costruttore
    }
    /**
     * Imposta l'ID del volo.
     *
     * @param idVolo l'ID da assegnare al volo
     */

    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }
    /**
     * Restituisce l'ID del volo.
     *
     * @return l'ID del volo
     */

    public int getIdVolo() {
        return idVolo;
    }
    /**
     * Imposta l'aeroporto di origine.
     *
     * @param aVoloOrigine l'aeroporto di origine
     */

    public void setaVoloOrigine(String aVoloOrigine) {
        this.aVoloOrigine = aVoloOrigine;
    }
    /**
     * Restituisce l'aeroporto di origine.
     *
     * @return l'aeroporto di origine
     */

    public String getaVoloOrigine() {
        return aVoloOrigine;
    }
    /**
     * Imposta l'aeroporto di destinazione.
     *
     * @param aVoloDestinazione l'aeroporto di destinazione
     */

    public void setaVoloDestinazione(String aVoloDestinazione) {
        this.aVoloDestinazione = aVoloDestinazione;
    }
    /**
     * Restituisce l'aeroporto di destinazione.
     *
     * @return l'aeroporto di destinazione
     */

    public String getaVoloDestinazione() {
        return aVoloDestinazione;
    }
    /**
     * Imposta la compagnia aerea del volo.
     *
     * @param compagnia la compagnia aerea
     */

    public void setCompagnia(String compagnia) {
        this.compagnia = compagnia;
    }
    /**
     * Restituisce la compagnia aerea del volo.
     *
     * @return la compagnia aerea
     */

    public String getCompagnia() {
        return compagnia;
    }
    /**
     * Imposta la data del volo.
     *
     * @param dataVolo la data del volo
     */

    public void setDataVolo(LocalDate dataVolo) {
        this.dataVolo = dataVolo;
    }
    /**
     * Restituisce la data del volo.
     *
     * @return la data del volo
     */

    public LocalDate getDataVolo() {
        return dataVolo;
    }
    /**
     * Imposta l'orario previsto di partenza del volo.
     *
     * @param oraVoloPrevista l'orario previsto di partenza
     */

    public void setOraVoloPrevista(LocalTime oraVoloPrevista) {
        this.oraVoloPrevista = oraVoloPrevista;
    }
    /**
     * Restituisce l'orario previsto di partenza del volo.
     *
     * @return l'orario previsto di partenza
     */

    public LocalTime getOraVoloPrevista() {
        return oraVoloPrevista;
    }
    /**
     * Imposta il ritardo previsto del volo.
     *
     * @param ritardo il ritardo previsto
     */

    public void setRitardo(LocalTime ritardo) {
        this.ritardo = ritardo;
    }
    /**
     * Restituisce il ritardo previsto del volo.
     *
     * @return il ritardo previsto
     */

    public LocalTime getRitardo() {
        return ritardo;
    }
    /**
     * Imposta lo stato attuale del volo.
     *
     * @param stato lo stato del volo
     */

    public void setStato(StatoVolo stato) {
        this.stato = stato;
    }
    /**
     * Restituisce lo stato attuale del volo.
     *
     * @return lo stato attuale del volo
     */

    public StatoVolo getStato() {
        return stato;
    }
}
