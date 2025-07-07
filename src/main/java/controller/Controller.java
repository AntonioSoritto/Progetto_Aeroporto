package controller;


import dao.UtenteDAO;
import dao.VoloDAO;
import gui.*;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import implementazionePostgresDAO.VoloImplementazionePostgresDAO;
import model.*;
import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


/**
 * Classe di utilità che funge da controller principale dell'applicazione.
 * Fornisce metodi statici per la gestione delle viste, la ricerca e la manipolazione
 * dei dati relativi a utenti, voli e prenotazioni.
 */
public class Controller {

    /**
     * Costruttore privato per prevenire l'istanziazione della classe controller.
     */
    private Controller() {}
    /**
     * Visualizza la schermata per l’utente.
     */
    public static void apriUtente() {

        UTENTE utente = new UTENTE();
        JFrame frame = new JFrame("Interfaccia Utente");
        frame.setContentPane(utente.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Visualizza la schermata per l’amministratore.
     */
    public static void apriAmministratore() {
        AMMINISTRATORE amministratore = new AMMINISTRATORE();
        JFrame frame = new JFrame("Interfaccia Aministratore");
        frame.setContentPane(amministratore.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Apre la schermata delle prenotazioni.
     */
    public static void apriPrenotazione() {
        PRENOTAZIONE prenotazione = new PRENOTAZIONE();
        JFrame frame = new JFrame("Prenotazione volo");
        frame.setContentPane(prenotazione.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Apre la schermata degli arrivi dei voli.
     */
    public static void apriArrivo() {
        VoloOrig v = new VoloOrig();
        JFrame frame = new JFrame("Inserisci Arrivo");
        frame.setContentPane(v.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Apre la schermata delle partenze dei voli.
     */
    public static void apriPartenza() {
        VoloDest v = new VoloDest();
        JFrame frame = new JFrame("Inserisci Partenza");
        frame.setContentPane(v.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    /**
     * Apre la schermata per effettuare una prenotazione specificando la destinazione e la data.
     *
     * @param destinazione destinazione desiderata
     * @param data         data di partenza
     */
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

        EffettuaPrenotazione effettuaP = new EffettuaPrenotazione(destinazione, data);
        JFrame frame = new JFrame("Dati Passeggero");
        frame.setContentPane(effettuaP.getPanel());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Torna alla schermata principale (Home).
     */
    public static void apriHome() {
        HOME home = new HOME();
        JFrame frame = new JFrame("Home");
        frame.setContentPane(home.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Cerca voli per numero di volo.
     *
     * @param numero numero del volo
     * @return lista dei voli trovati
     */
    public static List<Volo> cercaPerNumeroVolo(int numero) {

        VoloDAO dao = new VoloImplementazionePostgresDAO();
    return dao.cercaPerNumeroVolo(numero);
    }

    /**
     * Cerca voli tramite nome e cognome dell’intestatario.
     *
     * @param nome nome dell’intestatario
     * @param cognome cognome dell’intestatario
     * @return lista dei voli trovati
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static List<Volo> cercaPerNomeIntestatario(String nome, String cognome)
            throws SQLException {

         VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
        return dao.cercaPerNomeIntestatario(nome, cognome);
    }

    /**
     * Cerca prenotazioni tramite ID.
     *
     * @param id identificativo della prenotazione
     * @return lista delle prenotazioni trovate
     */
    public static List<Volo> cercaPerIdPrenotazione(int id) {

        VoloDAO dao = new VoloImplementazionePostgresDAO();
    return dao.cercaPerIdPrenotazione(id);
    }

    /**
     * Cerca voli per destinazione e data.
     *
     * @param destinazione destinazione desiderata
     * @param data data di partenza
     * @return lista dei voli trovati
     */
    public static List<Volo> cercaMeta(String destinazione, LocalDate data) {
        VoloDAO dao = new VoloImplementazionePostgresDAO();
        return dao.cercaMeta(destinazione, data);
    }

    /**
     * Effettua una prenotazione per un volo specificando i dettagli del passeggero e il numero di bagagli.
     *
     * @param nome nome del passeggero
     * @param cognome cognome del passeggero
     * @param idDocumento documento identificativo
     * @param volo volo scelto
     * @param numBagagli numero di bagagli
     * @return true se la prenotazione ha successo, false altrimenti
     */
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
    /**
     * Verifica le credenziali dell’utente durante il login.
     *
     * @param email email dell’utente
     * @param password password
     * @return true se le credenziali sono valide, false altrimenti
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static boolean verificaLogin(String email, String password) throws SQLException {
        UtenteDAO dao = new UtenteImplementazionePostgresDAO();
        return dao.loginValido(email, password);
    }
    /**
     * Verifica se l’utente con la mail fornita è un amministratore.
     *
     * @param email email dell’utente
     * @return true se è amministratore, false altrimenti
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static boolean isAmministratore(String email) throws SQLException {
        UtenteDAO dao = new UtenteImplementazionePostgresDAO();
        return dao.isAdmin(email);
    }

    /**
     * Apre la schermata per la modifica di un volo.
     *
     * @param numeroVolo identificativo del volo
     * @return true se la schermata viene aperta correttamente, false altrimenti
     */
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

    /**
     * Apre la schermata per la modifica di una prenotazione.
     *
     * @param idPren identificativo prenotazione
     * @return true se la schermata viene aperta correttamente, false altrimenti
     */
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
                ModificaPrenotazione gui = new ModificaPrenotazione();
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

    /**
     * Aggiorna lo stato di una prenotazione.
     *
     * @param idPren id della prenotazione
     * @param nuovoStato nuovo stato da assegnare
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static void aggiornaStatoPrenotazione(int idPren, String nuovoStato) throws SQLException {
        VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
        dao.aggiornaStatoPrenotazione(idPren, nuovoStato);
    }
    /**
     * Aggiunge una nuova destinazione ad un volo.
     *
     * @param volo oggetto contenente le informazioni della destinazione
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static void aggiungiVoloDestinazione(VoloDestinazione volo) throws SQLException {
        new VoloImplementazionePostgresDAO().inserisciVoloDestinazione(volo);
    }
    /**
     * Aggiunge una nuova origine ad un volo.
     *
     * @param volo oggetto contenente le informazioni dell’origine
     * @throws SQLException in caso di errori durante l’accesso al database
     */
    public static void aggiungiVoloOrigine(VoloOrigine volo) throws SQLException {
        new VoloImplementazionePostgresDAO().inserisciVoloOrigine(volo);
    }

    /**
     * Visualizza la schermata di registrazione utente.
     */
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