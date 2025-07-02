package GUI;

import controller.Controller;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PRENOTAZIONE {
    private JTextField inserisciDestinazioneTextField;
    private JComboBox comboBox1;
    private JButton trovaButton;
    private JPanel panel1;
    private JButton indietroButton;

public PRENOTAZIONE() {
    indietroButton.addActionListener(e -> {

        SwingUtilities.getWindowAncestor(indietroButton).dispose();

        Controller.apriUtente();
    });

    trovaButton.addActionListener(e -> {
        String destinazione = inserisciDestinazioneTextField.getText();
        String dataString = (String) comboBox1.getSelectedItem();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formatter);
        controller.Controller.apriEffettuaPrenotazione(destinazione, data);
        SwingUtilities.getWindowAncestor(trovaButton).dispose();
    });

}

    public JPanel getPanel() {
        return panel1;
    }

}
