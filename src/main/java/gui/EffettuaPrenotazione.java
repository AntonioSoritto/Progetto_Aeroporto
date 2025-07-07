package gui;

import controller.Controller;
import model.Volo;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
/**
 * Finestra grafica per la procedura di prenotazione di un volo.
 * Consente all’utente di selezionare un volo, inserire i propri dati e prenotare un posto.
 */

public class EffettuaPrenotazione {
    /** ComboBox per la selezione del volo tra quelli trovati in base alla destinazione e data. */

    private JComboBox <String> comboBox1;
    /** Campo di testo per il nome del passeggero. */

    private JTextField nomeTextField;
    /** Campo di testo per il cognome del passeggero. */

    private JTextField cognomeTextField;
    /** Campo di testo per il documento di identità del passeggero. */

    private JTextField iDDocumentoTextField;
    /** ComboBox per selezionare il numero di bagagli. */

    private JComboBox <String> comboBox2;
    /** Bottone per confermare e procedere con la prenotazione del volo. */

    private JButton prenotaButton;
    /** Pannello principale che contiene tutti i componenti grafici della finestra di prenotazione. */

    private JPanel panel1;
    /** Bottone per tornare indietro alla schermata precedente. */

    private JButton indietroButton;
    /** Lista dei voli trovati corrispondenti ai criteri di ricerca indicati dall’utente. */

    List<Volo> voliTrovati;
    /**
     * Costruttore della finestra per effettuare la prenotazione.
     * Inizializza la lista dei voli disponibili, imposta i listener per i bottoni
     * e gestisce il flusso della prenotazione.
     *
     * @param destinazione   destinazione richiesta
     * @param data           data del volo
     */

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
    /**
     * Restituisce il pannello principale della GUI.
     *
     * @return il pannello principale contenente tutti i componenti grafici.
     */

    public JPanel getPanel() {
        return panel1;
    }

}
