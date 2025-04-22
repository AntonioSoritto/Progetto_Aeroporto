package model;

public class Utente {
    private String login, password;

    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getLogin() {
        return login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void VisyalizzaVolo(Volo v){
        System.out.printf(v.getIdVolo()+"\n"+v.getA_Volo_Destinazione()+"\n"+v.getA_Volo_Origine()+"\n"+v.getCompagnia()+"\n"+v.getData_Volo()+"\n"+v.getOra_Volo_Prevista()+"\n"+v.getRitardo()+"\n"+v.getStato()+"\n");
    }
}
