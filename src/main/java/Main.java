import GUI.REGISTRAZIONE;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("REGISTRAZIONE");
            REGISTRAZIONE registrazione = new REGISTRAZIONE();
            frame.setContentPane(registrazione.getPanel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

