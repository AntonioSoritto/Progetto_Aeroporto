package GUI;

import javax.swing.*;

public class MODIFICA {
    private JTextField IMPOSTANUOVOGATETextField;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox3;
    private JComboBox comboBox1;
    private JButton confermaButton;
    private JPanel panelModifica;

    public MODIFICA()
    {confermaButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Volo modificato correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);
    });}


    public JPanel getPanel() {
        return panelModifica;
    }
}


