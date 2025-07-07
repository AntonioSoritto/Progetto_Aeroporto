package gui;

import controller.Controller;
import model.StatoVolo;
import model.VoloDestinazione;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class VoloDest {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField aeroportoOrigineTextField;
    private JComboBox <String> comboBox1;
    private JComboBox <String> comboBox5;
    private JButton aggiungiButton;
    private JPanel panel;
    private JButton indietroButton;
    private JTextField ora;

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

    public JPanel getPanel() {
        return panel;
    }
}
