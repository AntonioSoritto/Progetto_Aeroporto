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
            String selected = (String) comboBox1.getSelectedItem();
            String email = textField1.getText();
            String password = new String(passwordField1.getPassword());

            if ("UTENTE".equals(selected)) {
                if ("a@b.com".equals(email) && "qwerty".equals(password)) {
                    Controller.apriUtente();
                    SwingUtilities.getWindowAncestor(VOLAButton).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email o password errati", "Errore login", JOptionPane.ERROR_MESSAGE);
                }
            }else if("AMMINISTRATORE".equals(selected)){
                if ("a@b.com".equals(email) && "qwerty".equals(password)) {
                    Controller.apriAmministratore();
                    SwingUtilities.getWindowAncestor(VOLAButton).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Email o password errati", "Errore login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

}