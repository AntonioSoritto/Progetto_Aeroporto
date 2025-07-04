package GUI;

import controller.Controller;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
import implementazionePostgresDAO.VoloImplementazionePostgresDAO;
import model.Gate;
import model.Volo;
import model.StatoVolo;
import model.VoloOrigine;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MODIFICA {
    private Volo volo;

    private JTextField IMPOSTANUOVOGATETextField;
    private JComboBox<String> comboBox2;
    private JComboBox<StatoVolo> comboBox5;
    private JButton confermaButton;
    private JPanel panelModifica;
    private JButton indietroButton;
    private JLabel tratta;
    private JTextField Text1;

    public MODIFICA(Volo volo) {
        this.volo = volo;

        if (volo.getStato() != null) {
            comboBox5.setSelectedItem(volo.getStato());
        }

        if (volo.getOra_Volo_Prevista() != null) {
            Text1.getText();
        }

        if (volo.getData_Volo() != null) {
            comboBox2.setSelectedItem(volo.getData_Volo().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        if (volo.getA_Volo_Origine() != null && volo.getA_Volo_Destinazione() != null) {
            tratta.setText(volo.getA_Volo_Origine().toUpperCase() + " ➜ " + volo.getA_Volo_Destinazione().toUpperCase());
        }

        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriAmministratore();
        });

        confermaButton.addActionListener(e -> {
            int numeroGate = Integer.parseInt(IMPOSTANUOVOGATETextField.getText().trim());

            String statoSelezionato = comboBox5.getSelectedItem().toString();
            StatoVolo nuovoStato = StatoVolo.valueOf(statoSelezionato);
            String dataSelezionata = (String) comboBox2.getSelectedItem();
            String orarioInserito = Text1.getText();
            String ritardoInserito = Text1.getText().trim();
            LocalTime nuovoRitardo;

            try {
                nuovoRitardo = LocalTime.parse(ritardoInserito);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        null,
                        """
                        ❌ Errore
                        ──────────────
                        Ritardo non valido.
                        Usa il formato corretto: HH:mm (es. 14:30).
                        """,
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            volo.setRitardo(nuovoRitardo);

                LocalTime nuovoOrario;

                try {
                    nuovoOrario = LocalTime.parse(orarioInserito);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            """
                            ❌ Errore
                            ──────────────
                            Formato orario non valido.
                            Usa il formato corretto: HH:mm (es. 09:45).
                            """,
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
             LocalDate nuovaData = LocalDate.parse(dataSelezionata, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            volo.setStato(nuovoStato);
            volo.setOra_Volo_Prevista(nuovoOrario);
            volo.setData_Volo(nuovaData);
            if (volo instanceof VoloOrigine vo) {
                vo.setImbarco(new Gate(numeroGate));
            }
            try {
                new UtenteImplementazionePostgresDAO().aggiornaVolo(volo);
                JOptionPane.showMessageDialog(
                        null,
                        """
                        ✅ Successo
                        ─────────────────────────
                        Il volo è stato aggiornato correttamente.
                        """,
                        "Volo aggiornato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        """
                        ❌ Errore
                        ──────────────
                        Si è verificato un errore durante l’aggiornamento del volo.
                        Riprova oppure verifica i dati inseriti.
                        """,
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
    }
    private void createUIComponents() {
        comboBox5 = new JComboBox<>(StatoVolo.values());
    }
    public Container getPanel() { return panelModifica;
    }

}