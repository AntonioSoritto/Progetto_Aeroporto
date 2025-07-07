package gui;

import controller.Controller;
import javax.swing.*;
/**
 * Finestra principale (HOME) per il login degli utenti.
 * Permette l’accesso sia agli amministratori sia agli utenti semplici,
 * gestendo l’autenticazione e il flusso verso le relative schermate.
 */

public class HOME {

    /** Campo di testo per l’inserimento dell’email o username dell’utente. */
    private JTextField textField1;
    /** Label di messaggio di benvenuto per l’utente. */

    private JLabel messaggio; //Questa label è utilizzata esclusivamente per dare il benvenuto all'utente
    /** Campo di testo per l’inserimento della password protetta dell’utente. */

    private JPasswordField passwordField1;
    /** Bottone per avviare la procedura di login. */

    private JButton volaButton;
    /** Pannello principale che racchiude tutti i componenti grafici della schermata. */

    private JPanel panel;
    /** Bottone che permette di tornare alla schermata di registrazione. */

    private JButton indietroButton;
    /**
     * Costruttore della finestra HOME.
     * Inizializza i listener dei bottoni per le operazioni di login e navigazione verso altre schermate.
     */

    public HOME() {
        volaButton.addActionListener(e -> {
            String email = textField1.getText();
            String password = new String(passwordField1.getPassword());

            try {
                if (!Controller.verificaLogin(email, password)) {
                    JOptionPane.showMessageDialog(
                            null,
                            """
                            ❌ Errore
                            ──────────────
                            Email o password non validi.
                            Controlla le credenziali inserite e riprova.
                            """,
                            "Errore login",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                boolean isAdmin = Controller.isAmministratore(email);

                if (isAdmin) {
                    Controller.apriAmministratore();
                } else {
                    Controller.apriUtente();
                }

                SwingUtilities.getWindowAncestor(volaButton).dispose();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        """
                        ❌ Errore
                        ──────────────
                        Si è verificato un errore durante il login.
                        Controlla le credenziali e riprova.
                        """,
                        "Errore login",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriRegistrazione();
        });
    }
    /**
     * Restituisce il pannello principale della GUI.
     *
     * @return il pannello principale con tutti i componenti della schermata HOME.
     */

    public JPanel getPanel() {
        return panel;
    }

}