package GUI;

import controller.Controller;

import javax.swing.*;

public class V_ORIGINE {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField gateTextField;
    private JTextField aeroportoDOrigineTextField;
    private JComboBox comboBox1;
    private JButton aggiungiButton;
    private JComboBox comboBox5;
    private JPanel panel1;
    private JButton indietroButton;
    private JTextField ORATextField;

    public V_ORIGINE() {
        indietroButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            Controller.apriAmministratore();
        });


        aggiungiButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Volo aggiunto correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);

        });
    }

    public JPanel getPanel() {
        return panel1;
    }

}