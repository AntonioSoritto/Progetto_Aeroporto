package GUI;

import controller.Controller;

import javax.swing.*;

public class AMMINISTRATORE {
    private JLabel MessaggioA;
    private JButton cercaVoloButton;
    private JButton partenzaButton;
    private JButton arrivoButton;
    private JPanel PANEL1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton cercaButton;



    public AMMINISTRATORE() {
        partenzaButton.addActionListener(e -> Controller.apriPartenza());
        arrivoButton.addActionListener(e -> Controller.apriArrivo());
        cercaButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Volo non trovato", "Attenzione", JOptionPane.WARNING_MESSAGE);
        });


    }

    public JPanel getPanel() {
        return PANEL1; // Usa il nome effettivo del tuo root panel
    }
}