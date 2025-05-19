package GUI;

import controller.Controller;

import javax.swing.*;

public class V_ORIGINE {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField gateTextField;
    private JTextField aeroportoDOrigineTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton aggiungiButton;
    private JComboBox comboBox5;
    private JPanel panel1;
    private JButton indietroButton;

    public V_ORIGINE() {
        indietroButton.addActionListener(e -> {
            // Chiude la finestra attuale
            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            // Riapre la schermata precedente (es. login o menu principale)
            Controller.apriAmministratore(); // o apriLogin(), dipende da dove vuoi tornare
        });


        aggiungiButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Volo aggiunto correttamente", "✅", JOptionPane.INFORMATION_MESSAGE);

        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getPanel() {
        return panel1;  // sostituisci con il nome vero del root panel nel .form
    }

}