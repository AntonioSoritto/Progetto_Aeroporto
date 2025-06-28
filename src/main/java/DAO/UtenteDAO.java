package DAO;

import model.Volo;

import java.sql.SQLException;

public interface UtenteDAO {
    boolean passeggeroEsiste(String idDoc) throws SQLException;
    void creaPasseggero(String nome, String cognome, String idDoc) throws SQLException;
    boolean prenotazioneEsistente(String idDoc, int idVolo) throws SQLException;
    String generaPostoLibero(int idVolo) throws SQLException;
    int creaPrenotazione(int numero, int idVolo, String idDoc, int bagagli, String posto) throws SQLException;
    int generaNumeroPrenotazioneUnico() throws SQLException;
    boolean loginValido(String login, String password) throws SQLException;
    boolean isAdmin(String login) throws SQLException;
    public void aggiornaVolo(Volo volo) throws SQLException;
}
