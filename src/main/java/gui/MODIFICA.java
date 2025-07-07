package gui;

import controller.Controller;
import implementazionePostgresDAO.UtenteImplementazionePostgresDAO;
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
    private JTextField impostaGateTextField;
    private JComboBox<String> comboBox2;
    private JComboBox<StatoVolo> comboBox5;
    private JButton confermaButton;
    private JPanel panelModifica;
    private JButton indietroButton;
    private JLabel tratta;
    private String err="Errore";
    private JTextField text1;

    public MODIFICA(Volo volo) {
        this.volo = volo;

        if (volo.getStato() != null) {
            comboBox5.setSelectedItem(volo.getStato());
        }

        if (volo.getOraVoloPrevista() != null) {
            text1.getText();
        }

        if (volo.getDataVolo() != null) {
            comboBox2.setSelectedItem(volo.getDataVolo().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        if (volo.getaVoloOrigine() != null && volo.getaVoloDestinazione() != null) {
            tratta.setText(volo.getaVoloOrigine().toUpperCase() + " ➜ " + volo.getaVoloDestinazione().toUpperCase());
        }

        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriAmministratore();
        });

        confermaButton.addActionListener(e -> {
            int numeroGate = Integer.parseInt(impostaGateTextField.getText().trim());

            String statoSelezionato = comboBox5.getSelectedItem().toString();
            StatoVolo nuovoStato = StatoVolo.valueOf(statoSelezionato);
            String dataSelezionata = (String) comboBox2.getSelectedItem();
            String orarioInserito = text1.getText();
            String ritardoInserito = text1.getText().trim();
            LocalTime nuovoRitardo;

            try {
                nuovoRitardo = LocalTime.parse(ritardoInserito);
            } catch (Exception _) {
                JOptionPane.showMessageDialog(
                        null,
                        """
                        ❌ Errore
                        ──────────────
                        Ritardo non valido.
                        Usa il formato corretto: HH:mm (es. 14:30).
                        """,
                        err,
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            volo.setRitardo(nuovoRitardo);

                LocalTime nuovoOrario;

                try {
                    nuovoOrario = LocalTime.parse(orarioInserito);
                } catch (Exception _) {
                    JOptionPane.showMessageDialog(
                            null,
                            """
                            ❌ Errore
                            ──────────────
                            Formato orario non valido.
                            Usa il formato corretto: HH:mm (es. 09:45).
                            """,
                            err,
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
             LocalDate nuovaData = LocalDate.parse(dataSelezionata, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            volo.setStato(nuovoStato);
            volo.setOraVoloPrevista(nuovoOrario);
            volo.setDataVolo(nuovaData);
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
                        err,
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