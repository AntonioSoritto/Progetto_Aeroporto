package model;
/**
 * Rappresenta un utente generico del sistema, estende la classe {@link Utente}
 */

public class UtenteGenerico extends Utente {

    /**
     * Costruttore della classe UtenteGenerico.
     *
     * @param login    login dell'utente
     * @param password password dell'utente
     */

    public UtenteGenerico(String login, String password) {
        super(login, password);
    }
}
