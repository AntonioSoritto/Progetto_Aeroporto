package model;
/**
 * Rappresenta un amministratore del sistema.
 * Un oggetto {@code Amministratore} è un particolare {@link Utente} con privilegi elevati.
 */

public class Amministratore extends Utente {
    /**
     * Costruttore.
     *
     * @param login    nome utente per l'autenticazione
     * @param password password dell'amministratore
     */

    public Amministratore(String login,String password){
        super(login,password);
    }

}
