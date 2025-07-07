package implementazionePostgresDAO;

import dao.VoloDAO;
import model.StatoVolo;
import util.ConnessioneDatabase;
import model.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementazione dell'interfaccia {@link VoloDAO} per la gestione delle operazioni di accesso persistent data
 * sui voli, utilizzando un database PostgreSQL.
 * Fornisce metodi per ricerca, inserimento e aggiornamento di voli e prenotazioni.
 */

public class VoloImplementazionePostgresDAO implements VoloDAO {
    /** Connessione al database. */

    private Connection connection;
    /**
     * Costruttore. Inizializza la connessione al database tramite {@link ConnessioneDatabase}.
     */

    public VoloImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Ricerca tutti i voli che corrispondono a un certo numero di volo.
     *
     * @param numero numero del volo da cercare
     * @return lista dei voli trovati
     */

    @Override
    public List<Volo> cercaPerNumeroVolo(int numero) {
        List<Volo> voli = new ArrayList<>();

        // Prima query su VoloOrigine
        String queryOrigine = "SELECT * FROM \"VoloOrigine\" WHERE \"IdVolo\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(queryOrigine)) {
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                voli.add(creaVoloDaResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Seconda query su VoloDestinazione
        String queryDestinazione = "SELECT * FROM \"VoloDestinazione\" WHERE \"IdVolo\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(queryDestinazione)) {
            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                voli.add(creaVoloDaResultSet(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voli;
    }


    /**
     * Cerca voli associati all'intestatario tramite nome e cognome.
     *
     * @param nome nome intestatario
     * @param cognome cognome intestatario
     * @return lista di voli collegati all'intestatario
     * @throws SQLException in caso di problemi SQL
     */

    @Override
    public List<Volo> cercaPerNomeIntestatario(String nome, String cognome)
            throws SQLException {
        String sql = """
        SELECT 
          v."IdVolo", v."Compagnia", v."A_Volo_Origine",
          v."A_Volo_Destinazione", v."Data_Volo", 
          v."Ora_Volo_Prevista", v."Ritardo", 
          v."StatoVolo", v."IdGate"
        FROM "Prenotazioni" p
          JOIN "Passeggero" pa ON p."idDocumento" = pa."idDocumento"
          JOIN "VoloOrigine" v ON p."IdVolo" = v."IdVolo"
        WHERE pa."Nome"   ILIKE ?
          AND pa."Cognome" ILIKE ?
        UNION ALL
        SELECT 
          v."IdVolo", v."Compagnia", v."A_Volo_Origine",
          v."A_Volo_Destinazione", v."Data_Volo", 
          v."Ora_Volo_Prevista", v."Ritardo", 
          v."StatoVolo", NULL AS "IdGate"
        FROM "Prenotazioni" p
          JOIN "Passeggero" pa ON p."idDocumento" = pa."idDocumento"
          JOIN "VoloDestinazione" v ON p."IdVolo" = v."IdVolo"
        WHERE pa."Nome"   ILIKE ?
          AND pa."Cognome" ILIKE ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // imposto 4 parametri, 2 per ogni blocco
            ps.setString(1, "%" + nome  + "%");
            ps.setString(2, "%" + cognome + "%");
            ps.setString(3, "%" + nome  + "%");
            ps.setString(4, "%" + cognome + "%");

            List<Volo> risultati = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    risultati.add(creaVoloDaResultSet(rs));
                }
            }
            return risultati;
        }
    }
    /**
     * Ricava una prenotazione tramite ID.
     *
     * @param idPren id della prenotazione
     * @return prenotazione corrispondente all'id
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public Prenotazione cercaPerIdPrenotazionePrenotazione(int idPren) throws SQLException {
        String sql = """
        SELECT "numero", "IdVolo", "idDocumento", "StatoPrenotazione"
        FROM "Prenotazioni"
        WHERE "numero" = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPren);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Prenotazione p = new Prenotazione();
                    p.setNumero(rs.getInt("numero"));
                    p.setIdVolo(rs.getInt("IdVolo"));
                    p.setIdDocumento(rs.getString("idDocumento"));
                    p.setStatoPrenotazione(
                            StatoPrenotazione.valueOf(rs.getString("StatoPrenotazione"))
                    );
                    return p;
                }
            }
        }

        return null;
    }
    /**
     * Restituisce la lista dei voli associati a una prenotazione tramite l'ID.
     *
     * @param id id della prenotazione
     * @return lista di voli collegati alla prenotazione
     */

    @Override
    public List<Volo> cercaPerIdPrenotazione(int id) {
        List<Volo> voli = new ArrayList<>();

        String queryOrigine = """
        SELECT v.*, v."IdGate"
        FROM "Prenotazioni" p
        JOIN "VoloOrigine" v ON p."IdVolo" = v."IdVolo"
        WHERE p."numero" = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(queryOrigine)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    voli.add(creaVoloDaResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String queryDestinazione = """
        SELECT v.*
        FROM "Prenotazioni" p
        JOIN "VoloDestinazione" v ON p."IdVolo" = v."IdVolo"
        WHERE p."numero" = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(queryDestinazione)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    voli.add(creaVoloDaResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voli;
    }
    /**
     * Cerca voli diretti verso una destinazione e una particolare data.
     *
     * @param destinazione destinazione del volo
     * @param data data del volo
     * @return lista di voli che rispondono ai criteri
     */

    @Override
    public List<Volo> cercaMeta(String destinazione, LocalDate data) {
        List<Volo> voli = new ArrayList<>();

        String sqlOrigine = """
        SELECT v.*, v."IdGate"
        FROM "VoloOrigine" v
        WHERE LOWER(v."A_Volo_Destinazione") = LOWER(?)
          AND v."Data_Volo" = ?
    """;

        String sqlDestinazione = """
        SELECT *
        FROM "VoloDestinazione" v
        WHERE LOWER(v."A_Volo_Destinazione") = LOWER(?)
          AND v."Data_Volo" = ?
    """;

        try (PreparedStatement ps1 = connection.prepareStatement(sqlOrigine)) {
            ps1.setString(1, destinazione.trim());
            ps1.setDate(2, Date.valueOf(data));
            try (ResultSet rs = ps1.executeQuery()) {
                while (rs.next()) {
                    voli.add(creaVoloDaResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps2 = connection.prepareStatement(sqlDestinazione)) {
            ps2.setString(1, destinazione.trim());
            ps2.setDate(2, Date.valueOf(data));
            try (ResultSet rs = ps2.executeQuery()) {
                while (rs.next()) {
                    voli.add(creaVoloDaResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voli;
    }
    /**
     * Aggiorna lo stato di una prenotazione.
     *
     * @param idPrenotazione id della prenotazione
     * @param stato nuovo stato da impostare
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public void aggiornaStatoPrenotazione(int idPrenotazione, String stato) throws SQLException {
        String sql = """
        UPDATE "Prenotazioni"
        SET "StatoPrenotazione" = ?::"StatoPrenotazione"
        WHERE "numero" = ?
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, stato);
            ps.setInt(2, idPrenotazione);

            int righe = ps.executeUpdate();
            if (righe == 0) {
                throw new SQLException("""
                                        ⚠️ Attenzione
                                        ──────────────────────────
                                        Nessuna prenotazione trovata con ID: """ + idPrenotazione + """
                                        Verifica il numero inserito e riprova.
                                        """);
            }
        }
    }
    /**
     * Inserisce nel database un nuovo volo di destinazione.
     *
     * @param v il volo destinazione da inserire
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public void inserisciVoloDestinazione(VoloDestinazione v) throws SQLException {
        String sql = """
        INSERT INTO "VoloDestinazione"
            ("IdVolo", "Compagnia", "A_Volo_Origine", "A_Volo_Destinazione",
             "Data_Volo", "Ora_Volo_Prevista", "Ritardo", "StatoVolo")
        VALUES (?, ?, ?, ?, ?, ?, ?, ?::"StatoVolo")
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, v.getIdVolo());
            ps.setString(2, v.getCompagnia());
            ps.setString(3, v.getaVoloOrigine());
            ps.setString(4, "Napoli");
            ps.setDate(5, Date.valueOf(v.getDataVolo()));
            ps.setTime(6, Time.valueOf(v.getOraVoloPrevista()));
            ps.setTime(7, Time.valueOf("00:00:00"));
            ps.setString(8, v.getStato().name());
            ps.executeUpdate();
        }
    }
    /**
     * Inserisce nel database un nuovo volo d'origine.
     *
     * @param v il volo origine da inserire
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public void inserisciVoloOrigine(VoloOrigine v) throws SQLException {
        String sql = """
        INSERT INTO "VoloOrigine"
            ("IdVolo", "Compagnia", "A_Volo_Origine", "A_Volo_Destinazione",
             "Data_Volo", "Ora_Volo_Prevista", "Ritardo", "StatoVolo", "IdGate")
        VALUES (?, ?, ?, ?, ?, ?, ?, ?::"StatoVolo", ?)
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, v.getIdVolo());
            ps.setString(2, v.getCompagnia());
            ps.setString(3, "Napoli");  // Fisso perché è in partenza da Napoli
            ps.setString(4, v.getaVoloDestinazione());
            ps.setDate(5, Date.valueOf(v.getDataVolo()));
            ps.setTime(6, Time.valueOf(v.getOraVoloPrevista()));
            ps.setTime(7, Time.valueOf("00:00:00")); // Ritardo iniziale
            ps.setString(8, v.getStato().name());
            ps.setInt(9, v.getImbarco().getidGate());
            ps.executeUpdate();
        }
    }
    /**
     * Ricava un oggetto {@link Volo} a partire da un {@link ResultSet}.
     *
     * @param rs il result set da cui estrarre i dati
     * @return il volo generato
     * @throws SQLException in caso di errori SQL
     */

    @Override
    public Volo creaVoloDaResultSet(ResultSet rs) throws SQLException {
        int idVolo = rs.getInt("IdVolo");
        String compagnia = rs.getString("Compagnia");
        String origine = rs.getString("A_Volo_Origine");
        String destinazione = rs.getString("A_Volo_Destinazione");
        LocalDate data = rs.getDate("Data_Volo").toLocalDate();
        LocalTime ora = rs.getTime("Ora_Volo_Prevista").toLocalTime();


        LocalTime ritardo = null;
        Time ritardoSQL = rs.getTime("Ritardo");
        if (ritardoSQL != null) {
            ritardo = ritardoSQL.toLocalTime();
        }

        StatoVolo stato = StatoVolo.valueOf(rs.getString("StatoVolo"));


        Gate gate = null;
        int idGate = rs.getInt("IdGate");
        if (!rs.wasNull()) {
            gate = new Gate(idGate);
        }


        if (gate != null) {
            VoloOrigine vo = new VoloOrigine();
            vo.setIdVolo(idVolo);
            vo.setCompagnia(compagnia);
            vo.setaVoloOrigine(origine);
            vo.setaVoloDestinazione(destinazione);
            vo.setDataVolo(data);
            vo.setOraVoloPrevista(ora);
            vo.setRitardo(ritardo);
            vo.setStato(stato);
            vo.setImbarco(gate);
            return vo;
        } else {
            VoloDestinazione vd = new VoloDestinazione();
            vd.setIdVolo(idVolo);
            vd.setCompagnia(compagnia);
            vd.setaVoloOrigine(origine);
            vd.setaVoloDestinazione(destinazione);
            vd.setDataVolo(data);
            vd.setOraVoloPrevista(ora);
            vd.setRitardo(ritardo);
            vd.setStato(stato);
            return vd;
        }
    }
}