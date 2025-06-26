package GUI;

import controller.Controller;
import model.Volo;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

            SwingUtilities.getWindowAncestor(logoutButton).dispose();

            Controller.apriHome();
        });


        effettuaPrenotazioneButton.addActionListener(e -> {
            Controller.apriPrenotazione();
            SwingUtilities.getWindowAncestor(effettuaPrenotazioneButton).dispose();
        });

<<<<<<< Updated upstream
       cercaButton.addActionListener(e -> {
    String criterio = (String) comboBox1.getSelectedItem();
    String valore = textField1.getText();

    List<Volo> risultati = new ArrayList<>();

    switch (criterio) {
        case "Numero volo":
            try {
                int numero = Integer.parseInt(valore);
                risultati = Controller.cercaPerNumeroVolo(numero);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Numero volo non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            break;

        case "Nome intestatario":
            risultati = Controller.cercaPerNomeIntestatario(valore);
            break;

        case "ID prenotazione":
            try {
                int id = Integer.parseInt(valore);
                risultati = Controller.cercaPerIdPrenotazione(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            break;
    }

    if (risultati.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Nessun volo trovato", "Attenzione", JOptionPane.WARNING_MESSAGE);
    } else {
        StringBuilder sb = new StringBuilder("Risultati:\n");
        for (Volo v : risultati) {
            sb.append("Volo ").append(v.getIdVolo())
              .append(" da ").append(v.getA_Volo_Origine())
              .append(" a ").append(v.getA_Volo_Destinazione())
              .append(" il ").append(v.getData_Volo())
              .append(" alle ").append(v.getOra_Volo_Prevista());

            // Se è un VoloOrigine, mostra anche il gate
            if (v instanceof VoloOrigine) {
                Gate gate = ((VoloOrigine) v).getImbarco();
                sb.append(" - Gate: ").append(gate != null ? gate.toString() : "N/D");
            }

            sb.append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Voli trovati", JOptionPane.INFORMATION_MESSAGE);
    }
});

=======
        cercaButton.addActionListener(e -> {
            String criterio = (String) comboBox1.getSelectedItem();
            String valore = textField1.getText();

            List<Volo> risultati = new ArrayList<>();

            switch (criterio) {
                case "Numero volo":
                    try {
                        int numero = Integer.parseInt(valore);
                        risultati = Controller.cercaPerNumeroVolo(numero);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Numero volo non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;

                case "Nome intestatario":
                    risultati = Controller.cercaPerNomeIntestatario(valore);
                    break;

                case "ID prenotazione":
                    try {
                        int id = Integer.parseInt(valore);
                        risultati = Controller.cercaPerIdPrenotazione(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID non valido", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    break;
            }

            if (risultati.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nessun volo trovato", "Attenzione", JOptionPane.WARNING_MESSAGE);
            } else {
                StringBuilder sb = new StringBuilder("Risultati:\n");
                for (Volo v : risultati) {
                    sb.append("Volo ").append(v.getNumeroVolo())
                            .append(" da ").append(v.getOrigine())
                            .append(" a ").append(v.getDestinazione())
                            .append(" il ").append(v.getData())
                            .append(" alle ").append(v.getOra()).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Voli trovati", JOptionPane.INFORMATION_MESSAGE);
            }
        });
>>>>>>> Stashed changes


    }


    public JPanel getPanel() {
        return Panel1;
    }

}
