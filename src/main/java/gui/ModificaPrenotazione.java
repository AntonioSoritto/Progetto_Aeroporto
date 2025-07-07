package gui;

import controller.Controller;
import model.Prenotazione;
import javax.swing.*;
import java.awt.*;
/**
 * Finestra grafica dedicata alla modifica dello stato di una prenotazione.
 * Consente all'amministratore di aggiornare rapidamente lo stato di una prenotazione già esistente.
 */

public class ModificaPrenotazione {
    /** Bottone per confermare e salvare le modifiche effettuate sulla prenotazione. */

    private JButton confermaButton;
    /** Prenotazione da modificare. */

    private Prenotazione prenotazione;
    /** ComboBox per la selezione del nuovo stato della prenotazione. */

    private JComboBox <String> comboBox1;
    /** Bottone per tornare alla schermata amministratore senza salvare le modifiche. */

    private JButton indietroButton;
    /** Pannello principale che contiene tutti i componenti grafici della finestra di modifica. */

    private JPanel panel1;
    /**
     * Costruttore della finestra di modifica della prenotazione.
     * Inizializza i listener dei bottoni e gestisce il salvataggio dello stato della prenotazione.
     */

    public ModificaPrenotazione() {
        confermaButton.addActionListener(e -> {
            try {
                String nuovoStato = comboBox1.getSelectedItem().toString();
                Controller.aggiornaStatoPrenotazione(prenotazione.getNumero(), nuovoStato);

                JOptionPane.showMessageDialog(
                        panel1,
                        """
                        ✅ Salvataggio riuscito
                        ─────────────────────────
                        Lo stato della prenotazione è stato aggiornato correttamente.
                        """,
                        "Salvato",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        panel1,
                        """
                        ❌ Errore
                        ──────────────
                        Si è verificato un errore durante il salvataggio.
                        Riprova oppure verifica i dati inseriti.
                        """,
                        "Errore",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriAmministratore();

        });

    }
    /**
     * Imposta la prenotazione su cui effettuare la modifica.
     *
     * @param p prenotazione da modificare
     */

    public void setPrenotazione(Prenotazione p) {
        this.prenotazione = p;
    }
    /**
     * Restituisce il pannello principale della finestra di modifica.
     *
     * @return il pannello grafico principale
     */

    public Container getPanel() {
        return panel1;
    }

}
