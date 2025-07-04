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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void apriAmministratore() {
        AMMINISTRATORE amministratore = new AMMINISTRATORE();
        JFrame frame = new JFrame("Interfaccia Aministratore");
        frame.setContentPane(amministratore.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriPrenotazione() {
        PRENOTAZIONE prenotazione = new PRENOTAZIONE();
        JFrame frame = new JFrame("Prenotazione volo");
        frame.setContentPane(prenotazione.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriArrivo() {
        V_ORIGINE v = new V_ORIGINE();
        JFrame frame = new JFrame("Inserisci Arrivo");
        frame.setContentPane(v.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void apriPartenza() {
        V_DESTINAZIONE v = new V_DESTINAZIONE();
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
            JOptionPane.showMessageDialog(
                    null,
                    """
                    ℹ️ Informazione
                    ─────────────────────
                    Nessun volo corrispondente è stato trovato.
                    Verifica il numero inserito e riprova.
                    """,
                    "Ricerca voli",
                    JOptionPane.INFORMATION_MESSAGE
            );            apriPrenotazione();
            return;
        }

        EFFETTUA_P effettuaP = new EFFETTUA_P(destinazione, data);
        JFrame frame = new JFrame("Dati Passeggero");
        frame.setContentPane(effettuaP.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        VoloDAO dao = new VoloImplementazionePostgresDAO();
    return dao.cercaPerNumeroVolo(numero);
    }

    public static List<Volo> cercaPerNomeIntestatario(String nome, String cognome)
            throws SQLException {

         VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
        return dao.cercaPerNomeIntestatario(nome, cognome);
    }

    public static List<Volo> cercaPerIdPrenotazione(int id) throws SQLException {

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

            if (!dao.passeggeroEsiste(idDocumento)) {
             dao.creaPasseggero(nome, cognome, idDocumento);
            }

         String posto = dao.generaPostoLibero(volo.getIdVolo());

            int numeroPrenotazione = dao.generaNumeroPrenotazioneUnico();
            int idGenerato = dao.creaPrenotazione(
                numeroPrenotazione, volo.getIdVolo(), idDocumento, numBagagli, posto);

         JOptionPane.showMessageDialog(
                null,
                """
                ✅ Prenotazione effettuata
                ───────────────────────────
                Numero prenotazione: """ + idGenerato + """

                Posto assegnato: """ + posto + """
                """,
                "Conferma",
                JOptionPane.INFORMATION_MESSAGE
          );

            return true;

        } catch (org.postgresql.util.PSQLException e) {
            String msg = e.getMessage();

          if (msg.contains("Prenotazione bloccata")) {
                JOptionPane.showMessageDialog(
                    null,
                    """
                    ❌ Prenotazione NON effettuata
                    ──────────────────────────────
                    Il volo selezionato non è disponibile:
                    """ + msg + """
                    """,
                    "Volo non prenotabile",
                    JOptionPane.WARNING_MESSAGE
              );
            } else {
             JOptionPane.showMessageDialog(
                    null,
                    """
                    ❌ Errore
                    ──────────────
                    Si è verificato un problema durante la prenotazione.
                    Riprova o contatta l’assistenza.
                    """,
                    "Errore prenotazione",
                    JOptionPane.ERROR_MESSAGE
             );
            }

            return false;

      } catch (SQLException e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(
                null,
                """
                ❌ Errore
                ──────────────
                Si è verificato un problema durante la prenotazione.
                Riprova o contatta l’assistenza.
                """,
                "Errore prenotazione",
                JOptionPane.ERROR_MESSAGE
          );
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

    public static boolean apriModifica(int numeroVolo) {
        try {
            VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
            List<Volo> voli = dao.cercaPerNumeroVolo(numeroVolo);

            if (voli == null || voli.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                   "❗ Attenzione\n" +
                           "──────────────\n" +
                           "Non è stato trovato alcun volo con il numero inserito.\n" +
                           "Verifica e riprova.",
                   "Volo non trovato", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            Volo volo = voli.get(0);

            SwingUtilities.invokeLater(() -> {
                MODIFICA gui = new MODIFICA(volo);

                JFrame frame = new JFrame("Modifica Volo");
                frame.setContentPane(gui.getPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    """
                    ❌ Errore
                    ──────────────
                    Si è verificato un problema durante l'apertura della schermata di modifica del volo.
                    Riprova più tardi o verifica i dati inseriti.
                    """,
                    "Errore apertura modifica",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return false;
    }

    public static boolean apriModificaPrenotazione(int idPren) {
        try {
            VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
            Prenotazione pren = dao.cercaPerIdPrenotazionePrenotazione(idPren);

            if (pren == null) {
                JOptionPane.showMessageDialog(null,
                        "❗ Attenzione\n" +
                                "──────────────\n" +
                                "Non è stato trovato alcun volo con il numero inserito.\n" +
                                "Verifica e riprova.",
                        "Volo non trovato", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            SwingUtilities.invokeLater(() -> {
                MOD_P gui = new MOD_P();
                gui.setPrenotazione(pren);

                JFrame f = new JFrame("Modifica Prenotazione");
                f.setContentPane(gui.getPanel());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            });

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    """
                    ❌ Errore
                    ──────────────
                    Si è verificato un problema durante l'apertura della schermata di modifica.
                    Riprova più tardi o verifica i dati inseriti.
                    """,
                    "Errore apertura modifica",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
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

    public static void apriRegistrazione() {
        JFrame frame = new JFrame("REGISTRAZIONE");
        REGISTRAZIONE registrazione = new REGISTRAZIONE();
        frame.setContentPane(registrazione.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}