package GUI;

import controller.Controller;
import javax.swing.*;

public class HOME {
    private JTextField textField1;
    private JLabel Messaggio;
    private JPasswordField passwordField1;
    private JButton VOLAButton;
    private JPanel panel;
    private JButton indietroButton;

    public HOME() {
        VOLAButton.addActionListener(e -> {
            Controller controller = new Controller();
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

                SwingUtilities.getWindowAncestor(VOLAButton).dispose();

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

    public JPanel getPanel() {
        return panel;
    }

}