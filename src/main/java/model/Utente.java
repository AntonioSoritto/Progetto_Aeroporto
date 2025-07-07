package model;
/**
 * Rappresenta un utente del sistema, con credenziali di accesso e ruolo (admin o utente normale).
 */

public class Utente {
    /** Nome utente per il login */

    private String login;
    /** Password per l'autenticazione */

    private String password;
    /** Indica se l'utente ha privilegi di amministratore */

    private boolean isAdmin;
    /**
     * Costruttore che inizializza un utente con login e password.
     *
     * @param login    nome utente per il login
     * @param password password per l'accesso
     */

    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
    }
    /**
     * Imposta il nome utente.
     *
     * @param login nome utente da impostare
     */


    public void setLogin(String login) {
        this.login = login;
    }
    /**
     * Restituisce il nome utente.
     *
     * @return il nome utente
     */

    public String getLogin() {
        return login;
    }
    /**
     * Imposta la password dell'utente.
     *
     * @param password password da impostare
     */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Restituisce la password dell'utente.
     *
     * @return la password dell'utente
     */

    public String getPassword() {
        return password;
    }
    /**
     * Restituisce true se l'utente è un amministratore, false altrimenti.
     *
     * @return true se l'utente è admin, false altrimenti
     */

    public boolean isAdmin() {

        return isAdmin;
    }
    /**
     * Imposta se l'utente è un amministratore.
     *
     * @param admin true se admin, false altrimenti
     */

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }


}
