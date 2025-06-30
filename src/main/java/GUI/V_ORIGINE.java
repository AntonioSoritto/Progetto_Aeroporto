package GUI;

import controller.Controller;
import model.Gate;
import model.StatoVolo;
import model.VoloOrigine;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class V_ORIGINE {
    private JTextField textField1;
    private JTextField compagniaTextField;
    private JTextField gateTextField;
    private JTextField aeroportoDOrigineTextField;
    private JComboBox comboBox1;
    private JButton aggiungiButton;
    private JComboBox comboBox5;
    private JPanel panel1;
    private JButton indietroButton;
    private JTextField ORATextField;

    public V_ORIGINE() {
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
                LocalTime ora = LocalTime.parse(ORATextField.getText().trim());
                String statoStr = (String) comboBox5.getSelectedItem();
                StatoVolo stato = StatoVolo.valueOf(statoStr);

                VoloOrigine volo = new VoloOrigine();
                volo.setIdVolo(idVolo);
                volo.setCompagnia(compagnia);
                volo.setA_Volo_Origine("Napoli");
                volo.setA_Volo_Destinazione(destinazione);
                volo.setData_Volo(data);
                volo.setOra_Volo_Prevista(ora);
                volo.setStato(stato);
                Gate gate = new Gate();
                gate.setIdGate(idGate);
                volo.setImbarco(gate);
                Controller.aggiungiVoloOrigine(volo);

                JOptionPane.showMessageDialog(panel1,
                        "Volo in partenza aggiunto con successo! 🛫",
                        "Successo", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel1,
                        "Errore durante l’inserimento del volo",
                        "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public JPanel getPanel() {
        return panel1;
    }

}