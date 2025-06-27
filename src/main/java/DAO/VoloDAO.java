package DAO;

import model.Volo;

import java.time.LocalDate;
import java.util.List;

public interface VoloDAO {
    List<Volo> cercaPerNumeroVolo(int numero);
    List<Volo> cercaPerNomeIntestatario(String nome);
    List<Volo> cercaPerIdPrenotazione(int id);
    List<Volo> cercaMeta(String destinazione, LocalDate data);
}
