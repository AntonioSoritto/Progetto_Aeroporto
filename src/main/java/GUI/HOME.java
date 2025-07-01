package GUI;

import controller.Controller;

import javax.swing.*;
import java.lang.invoke.VolatileCallSite;

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
        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriRegistrazione();
        });
    }


    public JPanel getPanel() {
        return panel;
    }

}