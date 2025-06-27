package DAO;

import java.sql.SQLException;

public interface UtenteDAO {
    boolean passeggeroEsiste(String idDoc) throws SQLException;
    void creaPasseggero(String nome, String cognome, String idDoc) throws SQLException;
    boolean prenotazioneEsistente(String idDoc, int idVolo) throws SQLException;
    String generaPostoLibero(int idVolo) throws SQLException;
    int creaPrenotazione(int numero, int idVolo, String idDoc, int bagagli, String posto) throws SQLException;
    int generaNumeroPrenotazioneUnico() throws SQLException;
}
