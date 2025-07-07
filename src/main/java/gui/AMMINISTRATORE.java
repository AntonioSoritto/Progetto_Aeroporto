package gui;

import controller.Controller;
import javax.swing.*;


public class AMMINISTRATORE {
    private JLabel messaggioA; //Questa label è utilizzata esclusivamente per dare il benvenuto all'utente
    private JButton partenzaButton;
    private JButton arrivoButton;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JButton cercaButton;
    private JButton logoutButton;


    public AMMINISTRATORE() {
        logoutButton.addActionListener(e -> {

            SwingUtilities.getWindowAncestor(logoutButton).dispose();
            Controller.apriHome();
        });
        partenzaButton.addActionListener(e ->
        {
            SwingUtilities.getWindowAncestor(partenzaButton).dispose();
            Controller.apriPartenza();
        });
        arrivoButton.addActionListener(e -> {
            SwingUtilities.getWindowAncestor(arrivoButton).dispose();
            Controller.apriArrivo();
        });
        cercaButton.addActionListener(e -> {
            String criterio = (String) comboBox1.getSelectedItem();
            String testo   = textField1.getText().trim();

            if (testo.isEmpty()) {
                String msg = criterio.equals("Numero volo")
                        ? "Inserisci il numero del volo"
                        : "Inserisci l'ID della prenotazione";
                JOptionPane.showMessageDialog(
                        null, msg, "⚠️ Attenzione", JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            switch (criterio) {
                case "Numero volo":
                    try {
                        int numero = Integer.parseInt(testo);
                        boolean successo = Controller.apriModifica(numero);

                        if (successo) {
                            SwingUtilities.getWindowAncestor(cercaButton).dispose();
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                Il numero del volo deve essere un numero intero.
                                Inserisci un valore valido e riprova.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    return;
                case "ID prenotazione":
                    try {
                        int idPren = Integer.parseInt(testo);
                        boolean successo = Controller.apriModificaPrenotazione(idPren);

                        if (successo) {
                            SwingUtilities.getWindowAncestor(cercaButton).dispose();
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                L'ID della prenotazione deve essere un numero intero.
                                Inserisci un valore corretto e riprova.
                                """,
                                "Errore",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    return;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            """
                            ❌ Errore
                            ──────────────
                            Criterio di ricerca non riconosciuto.
                            Verifica la selezione e riprova.
                            """,
                            "Errore",
                            JOptionPane.ERROR_MESSAGE
                    );
            }
        });


    }

    public JPanel getPanel() {
        return panel1;
    }
}