package DAO;

import model.Prenotazione;
import model.StatoPrenotazione;
import model.Volo;

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
}
