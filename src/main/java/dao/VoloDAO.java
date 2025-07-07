package dao;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
/**
 * Interfaccia che definisce i metodi per l'accesso e la gestione dei dati
 * relativi ai voli e alle prenotazioni nel sistema.
 */

public interface VoloDAO {
    /**
     * Cerca i voli in base al numero del volo.
     *
     * @param numero numero identificativo del volo
     * @return lista di voli trovati con il numero specificato
     */
    List<Volo> cercaPerNumeroVolo(int numero);
    /**
     * Cerca i voli tramite nome e cognome dell'intestatario della prenotazione.
     *
     * @param nome nome dell'intestatario
     * @param cognome cognome dell'intestatario
     * @return lista di voli associati all'intestatario fornito
     * @throws SQLException in caso di errore durante l'accesso al database
     */
    public List<Volo> cercaPerNomeIntestatario(String nome, String cognome) throws SQLException;
    /**
     * Cerca i voli tramite l'ID di una prenotazione.
     *
     * @param id identificativo della prenotazione
     * @return lista di voli collegati alla prenotazione
     */
    List<Volo> cercaPerIdPrenotazione(int id);
    /**
     * Aggiorna lo stato di una prenotazione dato il suo identificativo.
     *
     * @param idPrenotazione identificativo della prenotazione
     * @param nuovoStato nuovo stato da assegnare alla prenotazione
     * @throws SQLException in caso di errore durante l'aggiornamento sul database
     */
    public void aggiornaStatoPrenotazione(int idPrenotazione, String nuovoStato) throws SQLException;
    /**
     * Cerca voli in base alla destinazione e alla data.
     *
     * @param destinazione destinazione del volo
     * @param data data di partenza del volo
     * @return lista di voli corrispondenti ai criteri specificati
     */
    List<Volo> cercaMeta(String destinazione, LocalDate data);
    /**
     * Cerca una prenotazione tramite il suo identificativo, restituendo l’oggetto prenotazione.
     *
     * @param idPren identificativo della prenotazione
     * @return la prenotazione trovata; null se non esiste
     * @throws SQLException in caso di errore durante la ricerca nel database
     */
    public Prenotazione cercaPerIdPrenotazionePrenotazione(int idPren) throws SQLException;
    /**
     * Inserisce una nuova destinazione per un volo.
     *
     * @param v oggetto VoloDestinazione da inserire
     * @throws SQLException in caso di errore durante l'inserimento nel database
     */
    public void inserisciVoloDestinazione(VoloDestinazione v) throws SQLException;
    /**
     * Inserisce una nuova origine per un volo.
     *
     * @param v oggetto VoloOrigine da inserire
     * @throws SQLException in caso di errore durante l'inserimento nel database
     */
    void inserisciVoloOrigine(VoloOrigine v) throws SQLException;
    /**
     * Crea un oggetto Volo a partire da un ResultSet del database.
     *
     * @param rs il ResultSet contenente i dati del volo
     * @return oggetto Volo ricostruito dal ResultSet
     * @throws SQLException in caso di errore nella lettura dal ResultSet
     */
    Volo creaVoloDaResultSet(ResultSet rs) throws SQLException;
}