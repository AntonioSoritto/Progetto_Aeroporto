package model;
/**
 * Rappresenta un utente generico del sistema, estende la classe {@link Utente}
 * aggiungendo il campo 'nome' per identificare l'utente.
 */

public class UtenteGenerico extends Utente {
    /** Nome dell'utente */


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