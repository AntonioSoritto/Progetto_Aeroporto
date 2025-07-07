package model;
/**
 * Rappresenta un utente generico del sistema, estende la classe {@link Utente}
 * aggiungendo il campo 'nome' per identificare l'utente.
 */

public class UtenteGenerico extends Utente {
    /** Nome dell'utente */

    private String nome;
    /**
     * Costruttore della classe UtenteGenerico.
     *
     * @param nome     nome dell'utente
     * @param login    login dell'utente
     * @param password password dell'utente
     */

    public UtenteGenerico(String nome, String login, String password){
        super(login, password);
        this.nome = nome;
    }
    /**
     * Imposta il nome dell'utente.
     *
     * @param nome il nome da assegnare all'utente
     */

    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Restituisce il nome dell'utente.
     *
     * @return il nome dell'utente
     */

    public String getNome() {
        return nome;
    }

    }
