package gui;

import controller.Controller;
import model.StatoVolo;
import model.VoloDestinazione;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Finestra grafica per l'aggiunta di un nuovo volo in arrivo nella destinazione.
 * Permette l'inserimento dei dati del volo da parte dell'amministratore.
 */

public class VoloDest {
    /** Campo di testo per l'inserimento dell'ID del volo. */

    private JTextField textField1;
    /** Campo di testo per l'inserimento del nome della compagnia aerea. */

    private JTextField compagniaTextField;
    /** Campo di testo per l'inserimento dell'aeroporto di origine. */

    private JTextField aeroportoOrigineTextField;
    /** ComboBox per la selezione della data di arrivo. */

    private JComboBox <String> comboBox1;
    /** ComboBox per la selezione dello stato del volo. */

    private JComboBox <String> comboBox5;
    /** Bottone per aggiungere il nuovo volo. */

    private JButton aggiungiButton;
    /** Pannello principale che contiene tutti i componenti dell'interfaccia grafica. */

    private JPanel panel;
    /** Bottone per tornare alla schermata amministratore. */

    private JButton indietroButton;
    /** Campo di testo per l'inserimento dell'orario di arrivo previsto. */

    private JTextField ora;
    /**
     * Costruttore della finestra di inserimento di un volo in arrivo.
     * Inizializza i listener per la gestione dell'aggiunta e della navigazione.
     */

    public VoloDest()

    {
        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriAmministratore();

        });

        aggiungiButton.addActionListener(e -> {
            try {
                int idVolo = Integer.parseInt(textField1.getText().trim());
                String compagnia = compagniaTextField.getText().trim();
                String origine = aeroportoOrigineTextField.getText().trim();
                LocalDate data = LocalDate.parse(comboBox1.getSelectedItem().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime ora1 = LocalTime.parse(ora.getText().trim());

                String statoStr = (String) comboBox5.getSelectedItem();
                StatoVolo stato = StatoVolo.valueOf(statoStr);

                VoloDestinazione volo = new VoloDestinazione();
                volo.setIdVolo(idVolo);
                volo.setCompagnia(compagnia);
                volo.setaVoloOrigine(origine);
                volo.setaVoloDestinazione("Napoli");  // fisso
                volo.setDataVolo(data);
                volo.setOraVoloPrevista(ora1);
                volo.setStato(stato);
                volo.setRitardo(null);

                Controller.aggiungiVoloDestinazione(volo);

                JOptionPane.showMessageDialog(
                        panel,
                        """
                        ✅ Successo
                        ─────────────────────────
                        Il volo in arrivo è stato aggiunto correttamente.
                        """,
                        "Volo aggiunto",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        panel,
                        """
                        ❌ Errore
                        ──────────────
                        Si è verificato un errore durante l’inserimento del volo.
                        Verifica i dati inseriti e riprova.
                        """,
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
    /**
     * Restituisce il pannello principale della finestra di inserimento volo.
     *
     * @return il pannello grafico principale
     */

    public JPanel getPanel() {
        return panel;
    }
}
