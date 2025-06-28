package GUI;

import controller.Controller;

import javax.swing.*;
import java.sql.SQLException;

public class AMMINISTRATORE {
    private JLabel MessaggioA;
    private JButton cercaVoloButton;
    private JButton partenzaButton;
    private JButton arrivoButton;
    private JPanel PANEL1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton cercaButton;
    private JButton logoutButton;


    public AMMINISTRATORE() {
        logoutButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(logoutButton).dispose();

            Controller.apriHome();
        });
        partenzaButton.addActionListener(e -> Controller.apriPartenza());
        arrivoButton.addActionListener(e -> Controller.apriArrivo());
        cercaButton.addActionListener(e -> {
            String criterio = (String) comboBox1.getSelectedItem();
            String testo   = textField1.getText().trim();

            // controllo campo vuoto
            if (testo.isEmpty()) {
                String msg = criterio.equals("Numero volo")
                        ? "Inserisci il numero del volo"
                        : "Inserisci l'ID della prenotazione";
                JOptionPane.showMessageDialog(
                        null, msg, "⚠️ Attenzione", JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // in base al criterio scelto
            switch (criterio) {
                case "Numero volo":
                    try {
                        int numero = Integer.parseInt(testo);
                        // apre la GUI di modifica volo
                        Controller.apriModifica(numero);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Il numero del volo deve essere un numero intero",
                                "❌ Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;

                case "ID prenotazione":
                    try {
                        int idPren = Integer.parseInt(testo);
                        Controller.apriModificaPrenotazione(idPren);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "L'ID della prenotazione deve essere un numero intero",
                                "❌ Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    break;

                default:
                    // in teoria non ci arrivi mai, ma meglio mettere un fallback
                    JOptionPane.showMessageDialog(
                            null,
                            "Criterio di ricerca non riconosciuto",
                            "❌ Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
            }
        });


    }

    public JPanel getPanel() {
        return PANEL1;
    }
}