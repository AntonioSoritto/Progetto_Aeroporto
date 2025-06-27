package implementazionePostgresDAO;

import DAO.UtenteDAO;
import Util.ConnessioneDatabase;

import java.sql.*;
import java.util.Random;

public class UtenteImplementazionePostgresDAO implements UtenteDAO {
    private Connection connection;

    public UtenteImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean passeggeroEsiste(String idDoc) throws SQLException {
        String sql = "SELECT 1 FROM \"Passeggero\" WHERE \"idDocumento\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idDoc);
            return ps.executeQuery().next();
        }
    }

    public void creaPasseggero(String nome, String cognome, String idDoc) throws SQLException {
        String sql = "INSERT INTO \"Passeggero\" (\"Nome\", \"Cognome\", \"idDocumento\") VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, idDoc);
            ps.executeUpdate();
        }
    }

    public boolean prenotazioneEsistente(String idDoc, int idVolo) throws SQLException {
        String sql = "SELECT 1 FROM \"Prenotazioni\" WHERE \"idDocumento\" = ? AND \"IdVolo\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idDoc);
            ps.setInt(2, idVolo);
            return ps.executeQuery().next();
        }
    }

    public String generaPostoLibero(int idVolo) throws SQLException {
        Random random = new Random();
        String posto;
        boolean occupato;

        do {
            char lettera = (char) ('A' + random.nextInt(26));
            int numero = random.nextInt(100); // 0-99
            posto = lettera + String.format("%02d", numero);

            String sql = "SELECT 1 FROM \"Prenotazioni\" WHERE \"Posto\" = ? AND \"IdVolo\" = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, posto);
                ps.setInt(2, idVolo);
                occupato = ps.executeQuery().next();
            }
        } while (occupato);

        return posto;
    }

    @Override
    public int creaPrenotazione(int numero, int idVolo, String idDoc, int bagagli, String posto) throws SQLException {
        String sql = """
        INSERT INTO "Prenotazioni"
        ("numero", "IdVolo", "idDocumento", "NumeroBagagli", "Posto", "StatoPrenotazione")
        VALUES (?, ?, ?, ?, ?, ?::"StatoPrenotazione")
        RETURNING "numero"
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, numero);
            ps.setInt(2, idVolo);
            ps.setString(3, idDoc);
            ps.setInt(4, bagagli);
            ps.setString(5, posto);
            ps.setString(6, "CONFERMATA");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("numero");
                } else {
                    throw new SQLException("⚠️ Errore: nessun ID di prenotazione restituito.");
                }
            }
        }
    }
    @Override
    public int generaNumeroPrenotazioneUnico() throws SQLException {
        Random random = new Random();
        int numero;
        boolean esiste;

        do {
            numero = 10000 + random.nextInt(90000); // genera tra 10000 e 99999

            String sql = "SELECT 1 FROM \"Prenotazioni\" WHERE \"numero\" = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, numero);
                esiste = ps.executeQuery().next();
            }

        } while (esiste);

        return numero;
    }
}
