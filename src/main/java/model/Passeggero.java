package model;
/**
 * Rappresenta un passeggero con nome, cognome e documento identificativo.
 */

public class Passeggero {
    /** Nome del passeggero */

    private String nome;
    /** Cognome del passeggero */

    private String cognome;
    /** Identificativo del documento del passeggero */

    private String idDocumento;
    /**
     * Costruttore completo.
     *
     * @param nome        nome del passeggero
     * @param cognome     cognome del passeggero
     * @param idDocumento identificativo del documento del passeggero
     */

    public Passeggero(String nome, String cognome, String idDocumento) {
        this.nome = nome;
        this.cognome = cognome;
        this.idDocumento = idDocumento;
    }
    /**
     * Imposta il nome del passeggero.
     *
     * @param nome nuovo nome
     */

    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce il nome del passeggero.
     *
     * @return nome del passeggero
     */

    public String getNome() {
        return nome;
 }
    /**
     * Imposta il cognome del passeggero.
     *
     * @param cognome nuovo cognome
     */

    public void setCognome(String cognome) {
        this.cognome = cognome;
 }
    /**
     * Restituisce il cognome del passeggero.
     *
     * @return cognome del passeggero
     */

    public String getCognome() {
        return cognome;
 }
    /**
     * Imposta l'identificativo del documento del passeggero.
     *
     * @param idDocumento nuovo identificativo del documento
     */

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
 }
    /**
     * Restituisce l'identificativo del documento del passeggero.
     *
     * @return identificativo del documento
     */

 public String getIdDocumento() {
        return idDocumento;
 }
}
