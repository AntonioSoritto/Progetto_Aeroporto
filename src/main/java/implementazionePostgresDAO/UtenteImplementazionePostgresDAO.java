package implementazionePostgresDAO;

import DAO.UtenteDAO;
import Util.ConnessioneDatabase;
import model.Gate;
import model.Volo;
import model.VoloDestinazione;
import model.VoloOrigine;

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
    @Override
    public boolean loginValido(String login, String password) throws SQLException {
        String sql = "SELECT 1 FROM \"Utente\" WHERE \"Login\" = ? AND \"Password\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            return ps.executeQuery().next();
        }
    }

    @Override
    public boolean isAdmin(String login) throws SQLException {
        String sql = "SELECT \"isAdmin\" FROM \"Utente\" WHERE \"Login\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isAdmin");
            } else {
                throw new SQLException("Utente non trovato: " + login);
            }
        }
    }

    @Override
    public void aggiornaVolo(Volo volo) throws SQLException {

        if (volo instanceof VoloOrigine voloOrigine) {
            String sqlOrigine = """
            UPDATE "VoloOrigine"
            SET "IdGate" = ?, "Data_Volo" = ?, "Ora_Volo_Prevista" = ?, "StatoVolo" = ?::"StatoVolo"
            WHERE "IdVolo" = ?
        """;

            try (PreparedStatement ps1 = connection.prepareStatement(sqlOrigine)) {
                Gate gate = voloOrigine.getImbarco();
                if (gate == null) {
                    throw new SQLException("⚠️ Impossibile salvare: il Gate è nullo per VoloOrigine");
                }

                ps1.setInt(1, gate.getIdGate());
                ps1.setDate(2, Date.valueOf(volo.getData_Volo()));
                ps1.setTime(3, Time.valueOf(volo.getOra_Volo_Prevista()));
                ps1.setString(4, volo.getStato().name());
                ps1.setInt(5, volo.getIdVolo());
                ps1.executeUpdate();
            }
        } else if (volo instanceof VoloDestinazione) {
            String sqlDestinazione = """
            UPDATE "VoloDestinazione"
            SET "Data_Volo" = ?, "Ora_Volo_Prevista" = ?, "StatoVolo" = ?::"StatoVolo"
            WHERE "IdVolo" = ?
        """;

            try (PreparedStatement ps2 = connection.prepareStatement(sqlDestinazione)) {
                ps2.setDate(1, Date.valueOf(volo.getData_Volo()));
                ps2.setTime(2, Time.valueOf(volo.getOra_Volo_Prevista()));
                ps2.setString(3, volo.getStato().name());
                ps2.setInt(4, volo.getIdVolo());
                ps2.executeUpdate();
            }
        } else {
            throw new IllegalArgumentException("Tipo di volo sconosciuto: " + volo.getClass().getSimpleName());
        }
    }
}
