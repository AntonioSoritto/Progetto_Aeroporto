package implementazionePostgresDAO;

import DAO.VoloDAO;
import model.StatoVolo;
import Util.ConnessioneDatabase;
import model.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

public class VoloImplementazionePostgresDAO implements VoloDAO {

    private Connection connection;

    public VoloImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



    @Override
    public List<Volo> cercaPerNomeIntestatario(String nomeCompleto) {
        List<Volo> voli = new ArrayList<>();

        String sqlVoloOrigine = """
        SELECT v.*, v."IdGate"
        FROM "Prenotazioni" p
        JOIN "Passeggero" pa ON p."idDocumento" = pa."idDocumento"
        JOIN "VoloOrigine" v ON p."IdVolo" = v."IdVolo"
        WHERE LOWER(TRIM(pa."Nome") || ' ' || TRIM(pa."Cognome")) = LOWER(?)    """;

        String sqlVoloDestinazione = """
        SELECT v.*
        FROM "Prenotazioni" p
        JOIN "Passeggero" pa ON p."idDocumento" = pa."idDocumento"
        JOIN "VoloDestinazione" v ON p."IdVolo" = v."IdVolo"
        WHERE LOWER(TRIM(pa."Nome") || ' ' || TRIM(pa."Cognome")) = LOWER(?)    """;

        try {
            try (PreparedStatement ps = connection.prepareStatement(sqlVoloOrigine)) {
                ps.setString(1, nomeCompleto.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        voli.add(creaVoloDaResultSet(rs));
                    }
                }
            }

            try (PreparedStatement ps = connection.prepareStatement(sqlVoloDestinazione)) {
                ps.setString(1, nomeCompleto.trim());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        voli.add(creaVoloDaResultSet(rs));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voli;
    }

    @Override
    public List<Volo> cercaPerIdPrenotazione(int id) {
        List<Volo> voli = new ArrayList<>();
        String query = "SELECT * FROM \"Prenotazioni\" p " +
                "JOIN \"VoloDestinazione\" v ON p.\"IdVolo\" = v.\"IdVolo\" " +
                "WHERE p.\"numero\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
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

    private Volo creaVoloDaResultSet(ResultSet rs) throws SQLException {
        int idVolo = rs.getInt("IdVolo");
        String compagnia = rs.getString("Compagnia");
        String origine = rs.getString("A_Volo_Origine");
        String destinazione = rs.getString("A_Volo_Destinazione");
        LocalDate data = rs.getDate("Data_Volo").toLocalDate();
        LocalTime ora = rs.getTime("Ora_Volo_Prevista").toLocalTime();
        LocalTime ritardo = rs.getTime("Ritardo").toLocalTime();
        StatoVolo stato = StatoVolo.valueOf(rs.getString("StatoVolo"));

        // Verifica se c'è un campo IdGate presente → se sì, crea un VoloOrigine
        Object gateObj = rs.getObject("IdGate");
        if (gateObj != null) {
            VoloOrigine vo = new VoloOrigine();
            vo.setIdVolo(idVolo);
            vo.setCompagnia(compagnia);
            vo.setA_Volo_Origine(origine);
            vo.setA_Volo_Destinazione(destinazione);
            vo.setData_Volo(data);
            vo.setOra_Volo_Prevista(ora);
            vo.setRitardo(ritardo);
            vo.setStato(stato);

            Gate g = new Gate();
            g.setIdGate(rs.getInt("IdGate"));
            vo.setImbarco(g);

            return vo;
        }

        // Altrimenti, ritorna un Volo "standard" (es. VoloDestinazione o Volo base)
        Volo v = new Volo();
        v.setIdVolo(idVolo);
        v.setCompagnia(compagnia);
        v.setA_Volo_Origine(origine);
        v.setA_Volo_Destinazione(destinazione);
        v.setData_Volo(data);
        v.setOra_Volo_Prevista(ora);
        v.setRitardo(ritardo);
        v.setStato(stato);

        return v;
    }
}