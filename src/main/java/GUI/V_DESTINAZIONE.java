package GUI;

import controller.Controller;

import javax.swing.*;

public class V_DESTINAZIONE {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField gateTextField;
    private JTextField aeroportoDArrivoTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox5;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox3;
    private JButton aggiungiButton;
    private JPanel PANEL;
    private JButton indietroButton;

    public V_DESTINAZIONE()

    {
        indietroButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            Controller.apriAmministratore();
        });

        aggiungiButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Volo aggiunto correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);
    });
    }

    public JPanel getPanel() {
        return PANEL;
    }
}
