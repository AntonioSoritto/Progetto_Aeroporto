package model;

public class UtenteGenerico extends Utente {
    private String nome;

    public UtenteGenerico(String nome, String login, String password){
        super(login, password);
        this.nome = nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void Prenotazione(){

    }

    public void CercaPrenotazione_codice(int idVolo){

    }
    public void CercaPrenotazione_nome(String nome){

    }




}
