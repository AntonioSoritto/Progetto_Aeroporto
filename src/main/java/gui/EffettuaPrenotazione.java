package gui;

import controller.Controller;
import model.Volo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class EffettuaPrenotazione {
    private JComboBox comboBox1;
    private JTextField nomeTextField;
    private JTextField cognomeTextField;
    private JTextField iDDocumentoTextField;
    private JComboBox comboBox2;
    private JButton prenotaButton;
    private JPanel panel1;
    private JButton indietroButton;

    List<Volo> voliTrovati;

    public EffettuaPrenotazione(String destinazione, LocalDate data) {
        voliTrovati = Controller.cercaMeta(destinazione, data);
        for (Volo v : voliTrovati) {
            comboBox1.addItem(v.getOraVoloPrevista() + " - ID: " + v.getIdVolo());
        }

        prenotaButton.addActionListener(e -> {
            int index = comboBox1.getSelectedIndex();
            String nome = nomeTextField.getText();
            String cognome = cognomeTextField.getText();
            String idDoc = iDDocumentoTextField.getText();
            Volo volo = voliTrovati.get(index);
            int bagagli = Integer.parseInt((String) comboBox2.getSelectedItem());

            boolean successo = Controller.effettuaPrenotazione(nome, cognome, idDoc, volo, bagagli);

            if (successo) {
                SwingUtilities.getWindowAncestor(prenotaButton).dispose();
                Controller.apriPrenotazione();
            }
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
