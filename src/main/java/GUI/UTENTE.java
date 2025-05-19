package GUI;

import controller.Controller;

import javax.swing.*;

public class UTENTE {
    private JButton effettuaPrenotazioneButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPanel Panel1;
    private JPanel Panel;
    private JButton cercaButton;
    private JButton logoutButton;


    public UTENTE() {
        logoutButton.addActionListener(e -> {
            // Chiude la finestra attuale
            SwingUtilities.getWindowAncestor(logoutButton).dispose();

            // Riapre la schermata precedente (es. login o menu principale)
            Controller.apriHome(); // o apriLogin(), dipende da dove vuoi tornare
        });


        effettuaPrenotazioneButton.addActionListener(e -> {
            Controller.apriPrenotazione();
            SwingUtilities.getWindowAncestor(effettuaPrenotazioneButton).dispose();
        });

        cercaButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Volo non trovato", "Attenzione", JOptionPane.WARNING_MESSAGE);
        });

    }


    public JPanel getPanel() {
        return Panel1; // Usa il nome effettivo del tuo root panel
    }

}