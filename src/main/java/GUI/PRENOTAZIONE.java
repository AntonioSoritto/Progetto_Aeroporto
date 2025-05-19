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
        // Chiude la finestra attuale
        SwingUtilities.getWindowAncestor(indietroButton).dispose();

        // Riapre la schermata precedente (es. login o menu principale)
        Controller.apriUtente(); // o apriLogin(), dipende da dove vuoi tornare
    });

    trovaButton.addActionListener(e -> {
        controller.Controller.apriEffettuaPrenotazione();
        SwingUtilities.getWindowAncestor(trovaButton).dispose(); // chiude la finestra attuale (facoltativo)
    });

}


    private JPanel panel1; // ⚠️ assicurati che sia il root panel disegnato nel .form
    private JButton indietroButton;

    public JPanel getPanel() {
        return panel1;
    }

}
