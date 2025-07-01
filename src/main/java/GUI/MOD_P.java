package GUI;

import controller.Controller;
import model.Prenotazione;
import javax.swing.*;
import java.awt.*;

public class MOD_P {

    private JButton confermaButton;
    private Prenotazione prenotazione;
    private JComboBox comboBox1;
    private JButton indietroButton;
    private JPanel panel1;

    public MOD_P() {
        confermaButton.addActionListener(e -> {
            try {
                String nuovoStato = comboBox1.getSelectedItem().toString();
                Controller controller=new Controller();
                controller.aggiornaStatoPrenotazione(prenotazione.getNumero(), nuovoStato);

                JOptionPane.showMessageDialog(panel1, "Stato prenotazione aggiornato ✅", "Salvato", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel1, "Errore durante il salvataggio", "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });


        indietroButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(indietroButton).dispose();
            Controller.apriAmministratore();

        });

    }
    public void setPrenotazione(Prenotazione p) {
        this.prenotazione = p;
    }

    public Container getPanel() {
        return panel1;
    }

}
