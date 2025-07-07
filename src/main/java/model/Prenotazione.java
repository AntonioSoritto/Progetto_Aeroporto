package model;
/**
 * Rappresenta una prenotazione effettuata da un passeggero per un volo.
 * La prenotazione contiene informazioni sul volo, sul posto assegnato, sullo stato,
 * sul numero di bagagli e sull'identificativo del documento del passeggero.
 */


public class Prenotazione {
    /** Numero identificativo della prenotazione */

    private int numero;
    /** Identificativo del volo associato */

    private int idVolo;
    /** Posto assegnato al passeggero */

    private String posto;
    /** Stato della prenotazione */

    private StatoPrenotazione stato;
    /** Numero di bagagli inclusi nella prenotazione */

    private int numeroBagagli;
    /** Identificativo del documento del passeggero */

    private String idDocumento;
    /**
     * Costruttore completo senza idVolo e idDocumento.
     *
     * @param numero         numero identificativo della prenotazione
     * @param posto          posto assegnato
     * @param stato          stato della prenotazione
     * @param numeroBagagli  numero di bagagli
     */

 public Prenotazione(int numero, String posto, StatoPrenotazione stato, int numeroBagagli) {
     this.numero = numero;
     this.posto = posto;
     this.stato = stato;
     this.numeroBagagli = numeroBagagli;
 }
    /**
     * Costruttore vuoto.
     */

    public Prenotazione() {

    }
    /**
     * Imposta il numero identificativo della prenotazione.
     * @param numero il nuovo numero identificativo della prenotazione
     */

    public void setNumero(int numero) {
     this.numero = numero;
 }
    /**
     * Restituisce il numero identificativo della prenotazione.
     *
     * @return numero identificativo della prenotazione
     */

 public int getNumero() {
     return numero;
 }
    /**
     * Imposta il posto assegnato al passeggero.
     *
     * @param posto posto assegnato
     */

    public void setPosto(String posto) {
     this.posto = posto;
 }
    /**
     * Restituisce il posto assegnato al passeggero.
     *
     * @return posto assegnato
     */

    public String getPosto() {
     return posto;
 }
    /**
     * Imposta lo stato della prenotazione.
     *
     * @param stato stato della prenotazione
     */

    public void setStato(StatoPrenotazione stato) {
     this.stato = stato;
 }
    /**
     * Restituisce lo stato della prenotazione.
     *
     * @return stato della prenotazione
     */

    public StatoPrenotazione getStato() {
     return stato;
 }

    /**
     * Imposta il numero di bagagli inclusi nella prenotazione.
     *
     * @param numeroBagagli numero di bagagli
     */

    public void setNumeroBagagli(int numeroBagagli) {
     this.numeroBagagli = numeroBagagli;
 }
    /**
     * Restituisce il numero di bagagli inclusi nella prenotazione.
     *
     * @return numero di bagagli
     */

    public int getNumeroBagagli() {
     return numeroBagagli;
 }
    /**
     * Imposta l'identificativo del documento associato alla prenotazione.
     *
     * @param idDocumento identificativo del documento
     */

    public void setIdDocumento(String idDocumento) {
     this.idDocumento = idDocumento;
 }
    /**
     * Restituisce l'identificativo del documento associato alla prenotazione.
     *
     * @return identificativo del documento
     */

    public String getIdDocumento() {
     return idDocumento;
 }
    /**
     * Imposta l'identificativo del volo associato alla prenotazione.
     *
     * @param idVolo identificativo del volo
     */

    public void setIdVolo(int idVolo) {
        this.idVolo = idVolo;
    }
    /**
     * Restituisce l'identificativo del volo associato alla prenotazione.
     *
     * @return identificativo del volo
     */

    public int getIdVolo() {
        return idVolo;
    }
    /**
     * Imposta lo stato della prenotazione (metodo alternativo).
     *
     * @param statoPrenotazione stato della prenotazione
     */

    public void setStatoPrenotazione(StatoPrenotazione statoPrenotazione) {
     this.stato = statoPrenotazione;
    }
}
