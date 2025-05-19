package GUI;

import controller.Controller;

import javax.swing.*;

public class EFFETTUA_P {
    private JComboBox comboBox1;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JTextField IDDocumentoTextField;
    private JComboBox comboBox2;
    private JButton prenotaButton;
    private JPanel panel1;
    private JButton indietroButton;

    public EFFETTUA_P() {
        prenotaButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Volo prenotato correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);
        });
        indietroButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            Controller.apriPrenotazione();
        });

    }

    public JPanel getPanel() {
        return panel1;
    }

}
