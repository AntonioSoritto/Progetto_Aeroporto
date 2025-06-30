package controller;


import DAO.UtenteDAO;
import DAO.VoloDAO;
import GUI.*;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import implementazionePostgresDAO.VoloImplementazionePostgresDAO;
import model.*;
import Util.ConnessioneDatabase;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Controller {

    public static void apriUtente() {

        UTENTE utente = new UTENTE();
        JFrame frame = new JFrame("Interfaccia Utente");
        frame.setContentPane(utente.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriAmministratore() {
        AMMINISTRATORE amministratore = new AMMINISTRATORE();
        JFrame frame = new JFrame("Interfaccia Aministratore");
        frame.setContentPane(amministratore.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriPrenotazione() {
        PRENOTAZIONE prenotazione = new PRENOTAZIONE();
        JFrame frame = new JFrame("Prenotazione volo");
        frame.setContentPane(prenotazione.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriArrivo() {
        V_ORIGINE v = new V_ORIGINE(); // o V_ORIGINE se è davvero la GUI giusta
        JFrame frame = new JFrame("Inserisci Arrivo");
        frame.setContentPane(v.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void apriPartenza() {
        V_DESTINAZIONE v = new V_DESTINAZIONE(); // o V_ORIGINE se è davvero la GUI giusta
        JFrame frame = new JFrame("Inserisci Partenza");
        frame.setContentPane(v.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public static void apriEffettuaPrenotazione(String destinazione, LocalDate data) {
        List<Volo> voli = cercaMeta(destinazione, data);

        if (voli.isEmpty()) {
            JOptionPane.showMessageDialog(null, "NESSUN VOLO TROVATO");
            apriPrenotazione(); // opzionale: riapri la schermata precedente
            return;
        }

        EFFETTUA_P effettuaP = new EFFETTUA_P(destinazione, data); // passi già i voli trovati
        JFrame frame = new JFrame("Dati Passeggero");
        frame.setContentPane(effettuaP.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void apriHome() {
        HOME home = new HOME();
        JFrame frame = new JFrame("Home");
        frame.setContentPane(home.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


public static List<Volo> cercaPerNumeroVolo(int numero) throws SQLException {
    Connection conn = ConnessioneDatabase.getInstance().connection;

    VoloDAO dao = new VoloImplementazionePostgresDAO();
return dao.cercaPerNumeroVolo(numero);
}

    public static List<Volo> cercaPerNomeIntestatario(String nome, String cognome)
            throws SQLException {
        // 1) Recupera la connessione aperta
        Connection conn = ConnessioneDatabase.getInstance().connection;
        // 2) Costruisci il DAO passando la Connection
        VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
        // 3) Delega la query al DAO
        return dao.cercaPerNomeIntestatario(nome, cognome);
    }

    public static List<Volo> cercaPerIdPrenotazione(int id) throws SQLException {
     Connection conn = ConnessioneDatabase.getInstance().connection;

     VoloDAO dao = new VoloImplementazionePostgresDAO();
return dao.cercaPerIdPrenotazione(id);
}
    public static List<Volo> cercaMeta(String destinazione, LocalDate data) {
        VoloDAO dao = new VoloImplementazionePostgresDAO();
        return dao.cercaMeta(destinazione, data);
    }
    public static boolean effettuaPrenotazione(String nome, String cognome, String idDocumento, Volo volo, int numBagagli) {
        try {
            UtenteDAO dao = new UtenteImplementazionePostgresDAO();

            // 1. Se non esiste il passeggero, lo creo
            if (!dao.passeggeroEsiste(idDocumento)) {
                dao.creaPasseggero(nome, cognome, idDocumento);
            }

            // 🔁 Nessun controllo per prenotazioni già esistenti

            // 2. Genera posto casuale unico
            String posto = dao.generaPostoLibero(volo.getIdVolo());

            // 3. Genera numero prenotazione e inserisce
            int numeroPrenotazione = dao.generaNumeroPrenotazioneUnico();
            int idGenerato = dao.creaPrenotazione(
                    numeroPrenotazione, volo.getIdVolo(), idDocumento, numBagagli, posto);

            // 4. Banner di conferma
            JOptionPane.showMessageDialog(null,
                    "✅ Prenotazione effettuata!\nNumero: " + idGenerato + "\nPosto: " + posto,
                    "Conferma", JOptionPane.INFORMATION_MESSAGE);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante la prenotazione", "❌", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    public static boolean verificaLogin(String email, String password) throws SQLException {
        UtenteDAO dao = new UtenteImplementazionePostgresDAO();
        return dao.loginValido(email, password);
    }
    public static boolean isAmministratore(String email) throws SQLException {
        UtenteDAO dao = new UtenteImplementazionePostgresDAO();
        return dao.isAdmin(email);
    }
    public static void apriModifica(int numeroVolo) {
        try {
            List<Volo> voli = new VoloImplementazionePostgresDAO().cercaPerNumeroVolo(numeroVolo);

            if (voli.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "⚠️ Nessun volo trovato con questo numero",
                        "Volo non trovato", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Volo volo = voli.get(0); // Per ora usiamo il primo (modificabile)

            JFrame frame = new JFrame("MODIFICA VOLO");
            frame.setContentPane(new MODIFICA(volo).getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Errore durante la ricerca del volo",
                    "❌ Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void apriModificaPrenotazione(int idPren) {
        try {
            Connection conn = ConnessioneDatabase.getInstance().connection;
            VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
            Prenotazione pren = dao.cercaPerIdPrenotazionePrenotazione(idPren);

            if (pren == null) {
                JOptionPane.showMessageDialog(null,
                        "Prenotazione con ID " + idPren + " non trovata.",
                        "❌ Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SwingUtilities.invokeLater(() -> {
                MOD_P gui = new MOD_P();
                gui.setPrenotazione(pren);

                JFrame f = new JFrame("Modifica Prenotazione");
                f.setContentPane(gui.getPanel());
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);

            });

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Errore durante l’apertura della modifica prenotazione",
                    "❌ Errore", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void aggiornaStatoPrenotazione(int idPren, String nuovoStato) throws SQLException {
        Connection conn = ConnessioneDatabase.getInstance().connection;
        VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
        dao.aggiornaStatoPrenotazione(idPren, nuovoStato);
    }
    public static void aggiungiVoloDestinazione(VoloDestinazione volo) throws SQLException {
        Connection conn = ConnessioneDatabase.getInstance().connection;
        new VoloImplementazionePostgresDAO().inserisciVoloDestinazione(volo);
    }
    public static void aggiungiVoloOrigine(VoloOrigine volo) throws SQLException {
        Connection conn = ConnessioneDatabase.getInstance().connection;
        new VoloImplementazionePostgresDAO().inserisciVoloOrigine(volo);
    }

}