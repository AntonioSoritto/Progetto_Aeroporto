package GUI;

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

    public V_DESTINAZIONE()

    {aggiungiButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Volo aggiunto correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);
    });
    }

    public JPanel getPanel() {
        return PANEL;
    }
}
