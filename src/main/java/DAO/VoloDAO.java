package DAO;

import model.Volo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface VoloDAO {
    List<Volo> cercaPerNumeroVolo(int numero);
    public List<Volo> cercaPerNomeIntestatario(String nome, String cognome) throws SQLException;
    List<Volo> cercaPerIdPrenotazione(int id);
    List<Volo> cercaMeta(String destinazione, LocalDate data);
}
