package gui;

import controller.Controller;
import model.Gate;
import model.StatoVolo;
import model.VoloOrigine;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VoloOrig {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField gateTextField;
    private JTextField aeroportoDOrigineTextField;
    private JComboBox <String> comboBox1;
    private JButton aggiungiButton;
    private JComboBox <String> comboBox5;
    private JPanel panel1;
    private JButton indietroButton;
    private JTextField oraTextField;

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

    public JPanel getPanel() {
        return panel1;
    }

}