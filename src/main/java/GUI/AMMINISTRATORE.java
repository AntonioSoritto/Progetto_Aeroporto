package GUI;

import controller.Controller;

import javax.swing.*;

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
            try {
                String testo = textField1.getText().trim();
                if (testo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Inserisci il numero del volo", "⚠️ Attenzione", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int numero = Integer.parseInt(testo);
                Controller.apriModifica(numero);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Il numero del volo deve essere un numero intero", "❌ Errore", JOptionPane.ERROR_MESSAGE);
            }
        });



    }

    public JPanel getPanel() {
        return PANEL1;
    }
}