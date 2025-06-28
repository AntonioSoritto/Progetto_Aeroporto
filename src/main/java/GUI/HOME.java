package GUI;

import controller.Controller;

import javax.swing.*;
import java.lang.invoke.VolatileCallSite;

public class HOME {
    private JLabel Messaggio;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton VOLAButton;
    private JPanel panel;

    public HOME() {
        VOLAButton.addActionListener(e -> {
            String email = textField1.getText();
            String password = new String(passwordField1.getPassword());

            try {
                if (!Controller.verificaLogin(email, password)) {
                    JOptionPane.showMessageDialog(null,
                            "Email o password errati",
                            "Errore login", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null,
                        "Errore durante il login",
                        "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

}