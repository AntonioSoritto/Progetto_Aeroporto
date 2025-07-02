package DAO;

import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VoloDAO {
    List<Volo> cercaPerNumeroVolo(int numero);
    public List<Volo> cercaPerNomeIntestatario(String nome, String cognome) throws SQLException;
    List<Volo> cercaPerIdPrenotazione(int id);
    public void aggiornaStatoPrenotazione(int idPrenotazione, String nuovoStato) throws SQLException;
    List<Volo> cercaMeta(String destinazione, LocalDate data);
    public Prenotazione cercaPerIdPrenotazionePrenotazione(int idPren) throws SQLException;
    public void inserisciVoloDestinazione(VoloDestinazione v) throws SQLException;
    void inserisciVoloOrigine(VoloOrigine v) throws SQLException;
    Volo creaVoloDaResultSet(ResultSet rs) throws SQLException;
}
