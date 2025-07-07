package gui;

import controller.Controller;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Finestra grafica per la ricerca delle prenotazioni da parte dell'utente.
 * Consente all'utente di selezionare una destinazione e una data,
 * per poi accedere alla schermata di prenotazione dettagliata.
 */

public class PRENOTAZIONE {
    /** Campo di testo per l'inserimento della destinazione desiderata. */

    private JTextField inserisciDestinazioneTextField;
    /** ComboBox per la selezione della data del viaggio. */

    private JComboBox <String> comboBox1;
    /** Bottone per avviare la ricerca delle prenotazioni disponibili. */

    private JButton trovaButton;
    /** Pannello principale che contiene tutti i componenti dell'interfaccia grafica. */

    private JPanel panel1;
    /** Bottone per tornare alla schermata utente. */

    private JButton indietroButton;
    /**
     * Costruttore della finestra di prenotazione.
     * Inizializza i listener per gestire la navigazione e la ricerca delle prenotazioni.
     */

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
    /**
     * Restituisce il pannello principale della finestra di prenotazione.
     *
     * @return il pannello grafico principale
     */

    public JPanel getPanel() {
        return panel1;
    }

}
