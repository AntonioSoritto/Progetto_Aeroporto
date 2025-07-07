package gui;

import controller.Controller;
import model.Gate;
import model.StatoVolo;
import model.VoloOrigine;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Finestra grafica per l'inserimento e la gestione di un nuovo volo in partenza dall'aeroporto.
 * Consente la compilazione e la registrazione di tutte le informazioni relative al volo di origine.
 */

public class VoloOrig {
    /** Campo di testo per l'inserimento dell'ID del volo. */

    private JTextField textField1;
    /** Campo di testo per la compagnia aerea del volo. */

    private JTextField compagniaTextField;
    /** Campo di testo per il numero del gate di imbarco. */

    private JTextField gateTextField;
    /** Campo di testo per l'inserimento dell'aeroporto di destinazione. */

    private JTextField aeroportoDOrigineTextField;
    /** ComboBox per la selezione della data del volo. */

    private JComboBox <String> comboBox1;
    /** Bottone che consente di aggiungere il nuovo volo. */

    private JButton aggiungiButton;
    /** ComboBox per la selezione dello stato attuale del volo. */

    private JComboBox <String> comboBox5;
    /** Pannello principale dell'interfaccia grafica. */

    private JPanel panel1;
    /** Bottone che consente di tornare alla schermata amministratore. */

    private JButton indietroButton;
    /** Campo di testo per l'inserimento dell'orario previsto di partenza del volo. */

    private JTextField oraTextField;
    /**
     * Costruttore della finestra di aggiunta di un volo di origine (in partenza).
     * Definisce i listener per la gestione degli eventi sui pulsanti e l'invio dei dati.
     */

    public VoloOrig() {
        indietroButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(indietroButton).dispose();

            Controller.apriAmministratore();
        });

        aggiungiButton.addActionListener(e -> {
            try {
                int idVolo = Integer.parseInt(textField1.getText().trim());
                String compagnia = compagniaTextField.getText().trim();
                String destinazione = aeroportoDOrigineTextField.getText().trim();
                int idGate = Integer.parseInt(gateTextField.getText().trim());
                LocalDate data = LocalDate.parse(comboBox1.getSelectedItem().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalTime ora = LocalTime.parse(oraTextField.getText().trim());
                String statoStr = (String) comboBox5.getSelectedItem();
                StatoVolo stato = StatoVolo.valueOf(statoStr);

                VoloOrigine volo = new VoloOrigine();
                volo.setIdVolo(idVolo);
                volo.setCompagnia(compagnia);
                volo.setaVoloOrigine("Napoli");
                volo.setaVoloDestinazione(destinazione);
                volo.setDataVolo(data);
                volo.setOraVoloPrevista(ora);
                volo.setStato(stato);
                Gate gate = new Gate();
                gate.setidGate(idGate);
                volo.setImbarco(gate);
                Controller.aggiungiVoloOrigine(volo);

                JOptionPane.showMessageDialog(
                        panel1,
                        """
                        ✅ Successo
                        ─────────────────────────
                        Il volo in partenza è stato aggiunto correttamente.
                        """,
                        "Volo aggiunto",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        panel1,
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
     * Restituisce il pannello principale della schermata di inserimento volo.
     *
     * @return il pannello grafico principale
     */

    public JPanel getPanel() {
        return panel1;
    }

}