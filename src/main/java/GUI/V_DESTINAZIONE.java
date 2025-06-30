package GUI;

import controller.Controller;
import model.StatoVolo;
import model.VoloDestinazione;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class V_DESTINAZIONE {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField aeroportoOrigineTextField;
    private JComboBox comboBox1;
    private JComboBox comboBox5;
    private JButton aggiungiButton;
    private JPanel PANEL;
    private JButton indietroButton;
    private JTextField ora;

    public V_DESTINAZIONE()

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
                volo.setA_Volo_Origine(origine);
                volo.setA_Volo_Destinazione("Napoli");  // fisso
                volo.setData_Volo(data);
                volo.setOra_Volo_Prevista(ora1);
                volo.setStato(stato);
                volo.setRitardo(null);

                Controller.aggiungiVoloDestinazione(volo);

                JOptionPane.showMessageDialog(PANEL,
                        "Volo in arrivo aggiunto correttamente 🛬", "Successo",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(PANEL,
                        "Errore durante l’inserimento del volo",
                        "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return PANEL;
    }
}
