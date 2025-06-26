package DAO;

import model.Volo;

import java.util.List;

public interface VoloDAO {
    List<Volo> cercaPerNumeroVolo(int numero);
    List<Volo> cercaPerNomeIntestatario(String nome);
    List<Volo> cercaPerIdPrenotazione(int id);
}
