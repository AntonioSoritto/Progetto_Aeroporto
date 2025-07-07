package implementazionePostgresDAO;

import dao.UtenteDAO;
import util.ConnessioneDatabase;
import model.*;
import java.sql.*;
import java.util.Random;
/**
 * Implementazione dell'interfaccia {@link UtenteDAO} per la gestione degli utenti e delle operazioni correlate
 * su database PostgreSQL.
 * Si occupa della registrazione e autenticazione utenti, gestione di passeggeri e prenotazioni, aggiornamento voli.
 */

public class UtenteImplementazionePostgresDAO implements UtenteDAO {
    /** Gestore della connessione al database. */

    private Connection connection;
    /** Generatore casuale per numeri di prenotazione e posti. */

    Random random = new Random();

    /**
     * Costruttore. Inizializza la connessione al database usando la singleton {@link ConnessioneDatabase}.
     */

    public UtenteImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Verifica l'esistenza di un passeggero tramite ID documento.
     *
     * @param idDoc identificativo univoco del documento
     * @return true se il passeggero esiste
     * @throws SQLException in caso di errori SQL
     */

    public boolean passeggeroEsiste(String idDoc) throws SQLException {
        String sql = "SELECT 1 FROM \"Passeggero\" WHERE \"idDocumento\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idDoc);
            return ps.executeQuery().next();
        }
    }
    /**
     * Registra un nuovo passeggero nel database.
     *
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param idDoc identificativo documento
     * @throws SQLException in caso di errori SQL
     */

    public void creaPasseggero(String nome, String cognome, String idDoc) throws SQLException {
        String sql = "INSERT INTO \"Passeggero\" (\"Nome\", \"Cognome\", \"idDocumento\") VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, cognome);
            ps.setString(3, idDoc);
            ps.executeUpdate();
        }
    }
    /**
     * Controlla se esiste una prenotazione per un certo documento e volo.
     *
     * @param idDoc documento passeggero
     * @param idVolo id del volo
     * @return true se la prenotazione esiste
     * @throws SQLException in caso di errori SQL
     */

    public boolean prenotazioneEsistente(String idDoc, int idVolo) throws SQLException {
        String sql = "SELECT 1 FROM \"Prenotazioni\" WHERE \"idDocumento\" = ? AND \"IdVolo\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, idDoc);
            ps.setInt(2, idVolo);
            return ps.executeQuery().next();
        }
    }
    /**
     * Genera un posto libero e non ancora assegnato per un dato volo.
     *
     * @param idVolo identificativo del volo
     * @return stringa che rappresenta il posto (lettera + numero)
     * @throws SQLException in caso di errori SQL
     */

    public String generaPostoLibero(int idVolo) throws SQLException {
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
    /**
     * Crea una nuova prenotazione per un passeggero.
     *
     * @param numero numero prenotazione
     * @param idVolo id del volo
     * @param idDoc documento del passeggero
     * @param bagagli numero bagagli associati
     * @param posto posto assegnato
     * @return numero di prenotazione generato
     * @throws SQLException in caso di errori SQL
     */

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
                    throw new SQLException("""
                                        ⚠️ Errore
                                        ──────────────
                                        Nessun ID di prenotazione è stato restituito dalla procedura.
                                        Controlla che l'inserimento sia andato a buon fine.
                                        """);
                }
            }
        }
    }
    /**
     * Genera un numero di prenotazione univoco (assente nel database).
     *
     * @return numero di prenotazione non già assegnato
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public int generaNumeroPrenotazioneUnico() throws SQLException {
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
    /**
     * Verifica la correttezza delle credenziali di login di un utente.
     *
     * @param login nome utente
     * @param password password
     * @return true se le credenziali sono corrette
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public boolean loginValido(String login, String password) throws SQLException {
        String sql = "SELECT 1 FROM \"Utente\" WHERE \"Login\" = ? AND \"Password\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            return ps.executeQuery().next();
        }
    }
    /**
     * Restituisce true se l'utente associato al login ha privilegi da amministratore.
     *
     * @param login nome utente
     * @return true se amministratore
     * @throws SQLException in caso di errori SQL o login errato
     */

    @Override
    public boolean isAdmin(String login) throws SQLException {
        String sql = "SELECT \"isAdmin\" FROM \"Utente\" WHERE \"Login\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("isAdmin");
            } else {
                throw new SQLException("""
                                        ❌ Errore
                                        ──────────────
                                        Utente non trovato nel sistema: """ + login + """
                                        Verifica le credenziali o registra un nuovo account.
                                        """);            }
        }
    }

    /**
     * Aggiorna i dati di un volo nel database.
     *
     * @param volo il volo da aggiornare (può essere {@link VoloOrigine} o {@link VoloDestinazione})
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public void aggiornaVolo(Volo volo) throws SQLException {

        if (volo instanceof VoloOrigine voloOrigine) {
            String sqlOrigine = """
            UPDATE "VoloOrigine"
            SET "IdGate" = ?, "Data_Volo" = ?, "Ritardo" = ?, "StatoVolo" = ?::"StatoVolo"
            WHERE "IdVolo" = ?
        """;

            try (PreparedStatement ps1 = connection.prepareStatement(sqlOrigine)) {
                Gate gate = voloOrigine.getImbarco();
                if (gate == null) {
                    throw new SQLException("""
                                            ⚠️ Errore salvataggio
                                            ──────────────────────────
                                            Impossibile completare l’operazione: il Gate associato al VoloOrigine è nullo.

                                            Verifica che un gate valido sia stato assegnato prima del salvataggio.
                                            """);                }

                ps1.setInt(1, gate.getidGate());
                ps1.setDate(2, Date.valueOf(volo.getDataVolo()));
                ps1.setTime(3, Time.valueOf(volo.getRitardo()));
                ps1.setString(4, volo.getStato().name());
                ps1.setInt(5, volo.getIdVolo());
                ps1.executeUpdate();
            }
        } else if (volo instanceof VoloDestinazione) {
            String sqlDestinazione = """
            UPDATE "VoloDestinazione"
            SET "Data_Volo" = ?, "Ritardo" = ?, "StatoVolo" = ?::"StatoVolo"
            WHERE "IdVolo" = ?
        """;

            try (PreparedStatement ps2 = connection.prepareStatement(sqlDestinazione)) {
                ps2.setDate(1, Date.valueOf(volo.getDataVolo()));
                ps2.setTime(2, Time.valueOf(volo.getRitardo()));
                ps2.setString(3, volo.getStato().name());
                ps2.setInt(4, volo.getIdVolo());
                ps2.executeUpdate();
            }
        } else {
            throw new IllegalArgumentException("Tipo di volo sconosciuto: " + volo.getClass().getSimpleName());
        }
    }
    /**
     * Inserisce un nuovo utente nel database.
     *
     * @param u utente da inserire
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public void inserisciUtente(Utente u) throws SQLException {
        String sql = """
        INSERT INTO "Utente" ("Login", "Password", "isAdmin")
        VALUES (?, ?, ?)
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getLogin());
            ps.setString(2, u.getPassword());
            ps.setBoolean(3, u.isAdmin());
            ps.executeUpdate();
        }
    }
    }

