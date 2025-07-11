package dao;

import model.Utente;
import model.Volo;

import java.sql.SQLException;
/**
 * Interfaccia Data Access Object (DAO) per la gestione degli utenti e delle operazioni correlate,
 * come passeggeri, prenotazioni e amministratori. Definisce i metodi di accesso
 * al database per la manipolazione degli oggetti Utente e Volo.
 */

public interface UtenteDAO {
    /**
     * Verifica l'esistenza di un passeggero dato il documento identificativo.
     *
     * @param idDoc documento identificativo del passeggero
     * @return true se il passeggero esiste, false altrimenti
     * @throws SQLException in caso di errore nell'accesso al database
     */
    boolean passeggeroEsiste(String idDoc) throws SQLException;
    /**
     * Crea un nuovo passeggero con i dati specificati.
     *
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param idDoc documento identificativo del passeggero
     * @throws SQLException in caso di errore nell'accesso al database
     */
    void creaPasseggero(String nome, String cognome, String idDoc) throws SQLException;
    /**
     * Verifica se esiste già una prenotazione per il passeggero e il volo specificato.
     *
     * @param idDoc documento identificativo del passeggero
     * @param idVolo identificativo del volo
     * @return true se la prenotazione esiste, false altrimenti
     * @throws SQLException in caso di errore nell'accesso al database
     */
    boolean prenotazioneEsistente(String idDoc, int idVolo) throws SQLException;
    /**
     * Genera un posto libero per un determinato volo.
     *
     * @param idVolo identificativo del volo
     * @return codice del posto disponibile
     * @throws SQLException in caso di errore nell'accesso al database
     */
    String generaPostoLibero(int idVolo) throws SQLException;
    /**
     * Crea una prenotazione associata a un volo per un passeggero.
     *
     * @param numero numero della prenotazione
     * @param idVolo identificativo del volo
     * @param idDoc documento identificativo del passeggero
     * @param bagagli numero di bagagli
     * @param posto posto assegnato
     * @return l'identificativo della prenotazione creata
     * @throws SQLException in caso di errore nell'accesso al database
     */
    int creaPrenotazione(int numero, int idVolo, String idDoc, int bagagli, String posto) throws SQLException;
    /**
     * Genera e restituisce un numero di prenotazione univoco.
     *
     * @return numero di prenotazione unico
     * @throws SQLException in caso di errore nell'accesso al database
     */
    int generaNumeroPrenotazioneUnico() throws SQLException;
    /**
     * Verifica la validità delle credenziali di login fornite.
     *
     * @param login username o email dell'utente
     * @param password password dell'utente
     * @return true se le credenziali sono valide, false altrimenti
     * @throws SQLException in caso di errore nell'accesso al database
     */
    boolean loginValido(String login, String password) throws SQLException;
    /**
     * Verifica se l'utente associato al login è un amministratore.
     *
     * @param login username o email dell'utente
     * @return true se l'utente è amministratore, false altrimenti
     * @throws SQLException in caso di errore nell'accesso al database
     */
    boolean isAdmin(String login) throws SQLException;
    /**
     * Aggiorna le informazioni relative a un volo nel database.
     *
     * @param volo oggetto Volo con i dati aggiornati
     * @throws SQLException in caso di errore nell'accesso al database
     */
    public void aggiornaVolo(Volo volo) throws SQLException;
    /**
     * Inserisce un nuovo utente nel database.
     *
     * @param nuovo oggetto Utente da inserire
     * @throws SQLException in caso di errore nell'accesso al database
     */
    void inserisciUtente(Utente nuovo) throws SQLException;
}

