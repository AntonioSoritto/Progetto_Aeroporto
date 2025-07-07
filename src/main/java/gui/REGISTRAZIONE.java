package gui;

import controller.Controller;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import model.Utente;
import javax.swing.*;

public class REGISTRAZIONE {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton registratiButton;
    private JButton loginButton;

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

    public JPanel getPanel() {
        return panel1;
    }

}