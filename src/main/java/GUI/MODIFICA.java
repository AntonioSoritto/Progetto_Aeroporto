package GUI;

import controller.Controller;

import javax.swing.*;

public class MODIFICA {
    private JTextField IMPOSTANUOVOGATETextField;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox3;
    private JComboBox comboBox1;
    private JButton confermaButton;
    private JPanel panelModifica;
    private JButton indietroButton;
    private JComboBox comboBox5;

    public MODIFICA()
    {
        indietroButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            Controller.apriAmministratore();
        });

        confermaButton.addActionListener(e -> {
        JOptionPane.showMessageDialog(null, "Volo modificato correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);
    });}


    public JPanel getPanel() {
        return panelModifica;
    }
}


