package gui;

import controller.Controller;
import javax.swing.*;

/**
 * Finestra grafica principale dedicata all’amministratore.
 * Consente la gestione delle partenze, degli arrivi e la modifica di voli e prenotazioni,
 * oltre al logout dall'applicazione.
 */

public class AMMINISTRATORE {
    /**
     * Label utilizzata esclusivamente per dare il benvenuto all’utente amministratore.
     */
    private JLabel messaggioA; //Questa label è utilizzata esclusivamente per dare il benvenuto all'utente

    /**
     * Bottone per la gestione delle partenze dei voli.
     */

    private JButton partenzaButton;

    /**
     * Bottone per la gestione degli arrivi dei voli.
     */

    private JButton arrivoButton;
    /**
     * Pannello principale della GUI dell'amministratore.
     */

    private JPanel panel1;
    /**
     * ComboBox che consente la selezione del criterio di ricerca ("Numero volo" o "ID prenotazione").
     */

    private JComboBox <String> comboBox1;
    /**
     * Campo di testo in cui inserire il valore di ricerca (numero volo o ID prenotazione).
     */

    private JTextField textField1;

    /**
     * Bottone per avviare la ricerca e la eventuale modifica di volo/prenotazione.
     */

    private JButton cercaButton;
    /**
     * Bottone per effettuare il logout e tornare alla schermata principale.
     */

    private JButton logoutButton;

    /**
     * Messaggio utilizzato per mostrare errori nei dialog.
     */

    private String error= "Errore";

    /**
     * Costruttore della finestra amministratore.
     * Imposta i listener ai bottoni e gestisce il flusso tra le varie schermate e funzionalità.
     */

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
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                Il numero del volo deve essere un numero intero.
                                Inserisci un valore valido e riprova.
                                """,
                                error,
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
                    } catch (NumberFormatException _) {
                        JOptionPane.showMessageDialog(
                                null,
                                """
                                ❌ Errore
                                ──────────────
                                L'ID della prenotazione deve essere un numero intero.
                                Inserisci un valore corretto e riprova.
                                """,
                                error,
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
                            error,
                            JOptionPane.ERROR_MESSAGE
                    );
            }
        });
    }
    /**
     * Restituisce il pannello principale della GUI.
     *
     * @return il pannello principale contenente tutti i componenti grafici.
     */

    public JPanel getPanel() {
        return panel1;
    }
}