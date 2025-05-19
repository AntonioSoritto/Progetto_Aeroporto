package GUI;

import controller.Controller;

import javax.swing.*;

public class PRENOTAZIONE {
    private JTextField inserisciDestinazioneTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox4;
    private JComboBox comboBox3;
    private JButton trovaButton;

public PRENOTAZIONE() {
    indietroButton.addActionListener(e -> {

        SwingUtilities.getWindowAncestor(indietroButton).dispose();

        Controller.apriUtente();
    });

    trovaButton.addActionListener(e -> {
        controller.Controller.apriEffettuaPrenotazione();
        SwingUtilities.getWindowAncestor(trovaButton).dispose();
    });

}


    private JPanel panel1;
    private JButton indietroButton;

    public JPanel getPanel() {
        return panel1;
    }

}
