package model;

public class Prenotazione {
    private int numero;
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


 public void checkIn() {

    }
}
