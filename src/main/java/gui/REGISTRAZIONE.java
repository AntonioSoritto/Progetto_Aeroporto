package gui;

import controller.Controller;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import model.Utente;
import javax.swing.*;
/**
 * Finestra grafica per la registrazione di un nuovo utente.
 * Consente l'inserimento di login e password, la validazione dei dati e la creazione dell'account.
 */

public class REGISTRAZIONE {
    /** Pannello principale che contiene tutti i componenti dell'interfaccia grafica di registrazione. */

    private JPanel panel1;
    /** Campo di testo per l'inserimento del login dell'utente. */

    private JTextField textField1;
    /** Campo di testo per l'inserimento della password dell'utente. */

    private JPasswordField passwordField1;
    /** Bottone per confermare la registrazione del nuovo utente. */

    private JButton registratiButton;
    /** Bottone per tornare alla schermata di login principale. */

    private JButton loginButton;
    /**
     * Costruttore della finestra di registrazione.
     * Inizializza i listener per i bottoni di registrazione e login, e gestisce la validazione dei dati inseriti.
     */

    public REGISTRAZIONE() {
        loginButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(loginButton).dispose();
            Controller.apriHome();
        });
        registratiButton.addActionListener(e -> {
            String login = textField1.getText().trim();
            String password = new String(passwordField1.getPassword()).trim();

            if (login.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, """
                        ⚠️ Attenzione
                        ──────────────
                        Inserisci sia login che password.
                        """, "Campi obbligatori", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (login.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, """
                        ⚠️ Login non valido
                        ─────────────────────
                        Il login non può essere solo numerico.
                        Inserisci almeno una lettera.
                        """, "Formato login errato", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Utente nuovo = new Utente(login, password);
            nuovo.setAdmin(false);
            try {
                UtenteImplementazionePostgresDAO dao = new UtenteImplementazionePostgresDAO();
                dao.inserisciUtente(nuovo);

                JOptionPane.showMessageDialog(null, """
                        ✅ Registrazione completata
                        ─────────────────────────────
                        Benvenuto a bordo, """ + login + "!", "Successo", JOptionPane.INFORMATION_MESSAGE);

                SwingUtilities.getWindowAncestor(registratiButton).dispose();
                Controller.apriHome();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, """
                        ❌ Errore
                        ─────────────
                        Registrazione fallita. Il login potrebbe essere già registrato.
                        """, "Errore registrazione", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
    /**
     * Restituisce il pannello principale della finestra di registrazione.
     *
     * @return il pannello grafico principale
     */

    public JPanel getPanel() {
        return panel1;
    }

}