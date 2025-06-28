package model;

public class Prenotazione {
    private int numero;
    private int idVolo;
    private String posto;
    private StatoPrenotazione stato;
    private int numeroBagagli;
    private String idDocumento;

 public Prenotazione(int numero, String posto, StatoPrenotazione stato, int numeroBagagli) {
     this.numero = numero;
     this.posto = posto;
     this.stato = stato;
     this.numeroBagagli = numeroBagagli;
 }

    public Prenotazione() {

    }

    public void setNumero(int numero) {
     this.numero = numero;
 }
 public int getNumero() {
     return numero;
 }
 public void setPosto(String posto) {
     this.posto = posto;
 }
 public String getPosto() {
     return posto;
 }
 public void setStato(StatoPrenotazione stato) {
     this.stato = stato;
 }
 public StatoPrenotazione getStato() {
     return stato;
 }
 public void setNumeroBagagli(int numeroBagagli) {
     this.numeroBagagli = numeroBagagli;
 }
 public int getNumeroBagagli() {
     return numeroBagagli;
 }
 public void setIdDocumento(String idDocumento) {
     this.idDocumento = idDocumento;
 }
 public String getIdDocumento() {
     return idDocumento;
 }

    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }

    public int getIdVolo() {
        return idVolo;
    }

    public void setStatoPrenotazione(StatoPrenotazione statoPrenotazione) {
     this.stato = statoPrenotazione;
    }
}
