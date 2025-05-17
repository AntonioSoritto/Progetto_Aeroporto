package GUI;

import javax.swing.*;

public class AMMINISTRATORE {
    private JLabel MessaggioA;
    private JButton cercaVoloButton;
    private JButton partenzaButton;
    private JButton arrivoButton;
    private JPanel PANEL1;

    public AMMINISTRATORE() {
        arrivoButton.addActionListener(e -> {
            V_ORIGINE v = new V_ORIGINE();
            JFrame frame = new JFrame("Inserisci Partenza");
            frame.setContentPane(v.getPanel());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

    }

    public JPanel getPanel() {
        return PANEL1; // Usa il nome effettivo del tuo root panel
    }
}