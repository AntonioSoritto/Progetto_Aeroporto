package GUI;

import Util.ConnessioneDatabase;
import controller.Controller;
import implementazionePostgresDAO.VoloImplementazionePostgresDAO;
import model.Prenotazione;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MOD_P {
    private JPanel panelModifica;
    private JButton confermaButton;
    private JComboBox comboBox5;
    private JButton zuco;
private Prenotazione prenotazione;

    public MOD_P() {
        confermaButton.addActionListener(e -> {
            try {
                String nuovoStato = comboBox5.getSelectedItem().toString();
                Connection conn = ConnessioneDatabase.getInstance().connection;
                VoloImplementazionePostgresDAO dao = new VoloImplementazionePostgresDAO();
                dao.aggiornaStatoPrenotazione(prenotazione.getNumero(), nuovoStato);

                JOptionPane.showMessageDialog(panelModifica, "Stato prenotazione aggiornato ✅", "Salvato", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panelModifica, "Errore durante il salvataggio", "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });


        zuco.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(zuco).dispose();
            Controller.apriAmministratore();
        });


    }
    public void setPrenotazione(Prenotazione p) {
        this.prenotazione = p;
    }

    public Container getPanel() {
        return panelModifica;
    }






}
