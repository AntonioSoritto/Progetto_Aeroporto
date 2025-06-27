package implementazionePostgresDAO;

import DAO.VoloDAO;
import model.StatoVolo;
import Util.ConnessioneDatabase;
import model.*;

import java.sql.*;
import java.time.LocalTime;
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
    public List<Volo> cercaPerNomeIntestatario(String nome) {
        List<Volo> voli = new ArrayList<>();
        String query = "SELECT * FROM \"Prenotazioni\" p " +
                "JOIN \"VoloDestinazione\" v ON p.\"numero\" = v.\"IdVolo\" " +
                "WHERE p.\"intestatario\" = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nome);
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
    public List<Volo> cercaPerIdPrenotazione(int id) {
        List<Volo> voli = new ArrayList<>();
        String query = "SELECT * FROM \"Prenotazioni\" p " +
                "JOIN \"VoloDestinazione\" v ON p.\"numero\" = v.\"IdVolo\" " +
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
        boolean contieneIdGate;
        try {
            rs.findColumn("IdGate");
            contieneIdGate = true;
        } catch (SQLException e) {
            contieneIdGate = false;
        }

        Volo volo;
        if (contieneIdGate) {
            // Inizializza l’oggetto e imposta il gate
            int idGate = rs.getInt("IdGate");
            VoloOrigine vo = new VoloOrigine();
            vo.setImbarco(new Gate(idGate));
            volo = vo;
        } else {
            volo = new Volo();
        }

        volo.setIdVolo(rs.getInt("IdVolo"));
        volo.setCompagnia(rs.getString("Compagnia"));
        volo.setA_Volo_Destinazione(rs.getString("A_Volo_Destinazione"));
        volo.setA_Volo_Origine(rs.getString("A_Volo_Origine"));
        volo.setData_Volo(rs.getDate("Data_Volo").toLocalDate());
        volo.setOra_Volo_Prevista(rs.getTime("Ora_Volo_Prevista").toLocalTime());

        Time ritardoTime = rs.getTime("Ritardo");
        volo.setRitardo(ritardoTime != null ? ritardoTime.toLocalTime() : LocalTime.of(0, 0));

        String statoString = rs.getString("StatoVolo");
        volo.setStato(StatoVolo.valueOf(statoString.toUpperCase()));

        return volo;
    }
}