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
/**
 * Finestra grafica per la modifica dei dati di un volo esistente.
 * Permette all'amministratore di aggiornare orario, data, stato, gate e ritardo del volo selezionato.
 */

public class MODIFICA {
    /** Volo da modificare. */

    private Volo volo;
    /** Campo di testo per l'inserimento o la modifica del numero del gate. */

    private JTextField impostaGateTextField;
    /** ComboBox per la selezione della data del volo. */

    private JComboBox<String> comboBox2;
    /** ComboBox per la selezione dello stato del volo. */

    private JComboBox<StatoVolo> comboBox5;
    /** Bottone per confermare e salvare le modifiche sul volo. */

    private JButton confermaButton;
    /** Pannello principale che racchiude tutti i componenti della finestra di modifica. */

    private JPanel panelModifica;
    /** Bottone per tornare alla schermata amministratore senza salvare le modifiche. */

    private JButton indietroButton;
    /** Label che visualizza la tratta del volo (origine ➜ destinazione). */

    private JLabel tratta;
    /** Testo mostrato come titolo in finestra di errore. */

    private String err="Errore";
    /** Campo di testo per modificare orario o ritardo del volo. */

    private JTextField text1;
    /**
     * Costruttore della finestra di modifica volo.
     * Inizializza il form con i dati del volo da modificare e imposta i listener dei bottoni.
     *
     * @param volo Volo da modificare.
     */

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
    /**
     * Metodo usato per creare e inizializzare i componenti custom dell'interfaccia grafica.
     */

    private void createUIComponents() {
        comboBox5 = new JComboBox<>(StatoVolo.values());
    }
    /**
     * Restituisce il pannello principale della finestra di modifica.
     *
     * @return il pannello grafico che contiene tutti i componenti della modifica volo.
     */

    public Container getPanel() { return panelModifica;
    }

}