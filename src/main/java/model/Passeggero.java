package model;

public class Passeggero {
    private String nome;
    private String cognome;
    private String idDocumento;

    public Passeggero(String nome, String cognome, String idDocumento) {
        this.nome = nome;
        this.cognome = cognome;
        this.idDocumento = idDocumento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
 }
 public void setCognome(String cognome) {
        this.cognome = cognome;
 }
 public String getCognome() {
        return cognome;
 }
 public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
 }
 public String getIdDocumento() {
        return idDocumento;
 }
}
