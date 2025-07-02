package GUI;

import controller.Controller;
import model.Gate;
import model.Volo;
import model.VoloOrigine;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UTENTE {
    private JButton effettuaPrenotazioneButton;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JPanel Panel1;
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


        cercaButton.addActionListener(e -> {
            String criterio = (String) comboBox1.getSelectedItem();
            String valore = textField1.getText().trim();


            List<Volo> risultati = new ArrayList<>();

            switch (criterio) {
                case "Numero volo":
                    try {
                        int numero = Integer.parseInt(valore);
                        risultati = Controller.cercaPerNumeroVolo(numero);
                    } catch (NumberFormatException | SQLException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                Il numero del volo inserito non è valido.
                                Inserisci un valore corretto e riprova.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    break;

                case "Nome prenotazione":
                    String[] parts = valore.split("\\s+", 2);
                    if (parts.length < 2) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                Inserisci nome e cognome separati da uno spazio.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    String nome  = parts[0];
                    String cogn = parts[1];

                    try {
                        risultati = Controller.cercaPerNomeIntestatario(nome, cogn);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                Si è verificato un errore durante l’accesso al database.
                                Riprova più tardi o verifica la connessione.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    break;

                case "ID prenotazione":
                    try {
                        int id = Integer.parseInt(valore);
                        risultati = Controller.cercaPerIdPrenotazione(id);
                    } catch (NumberFormatException | SQLException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                L'ID inserito non è valido.
                                Inserisci un valore corretto e riprova.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
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


                    if (v instanceof VoloOrigine) {
                        Gate g = ((VoloOrigine) v).getImbarco();
                        int gate = g.getIdGate();
                        sb.append(" — Gate di partenza: ").append(gate);
                    }

                    sb.append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString(), "Voli trovati", JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }


    public JPanel getPanel() {
        return Panel1;
    }

}
