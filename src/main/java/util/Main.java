package util;

import controller.Controller;

import javax.swing.*;
/**
 * Classe principale dell'applicazione.
 * Avvia l&apos;interfaccia grafica impostando il Look &amp; Feel "Nimbus" (se disponibile)
 * e apre la finestra di registrazione tramite il controller.
 */


public class Main {

    /**
     * Metodo principale dell'applicazione.
     * Imposta il Look &amp; Feel "Nimbus" se disponibile e avvia la schermata di registrazione.
     * @param args Argomenti da linea di comando (non usati).
     */


    public static void main(String[] args) {
     try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    Controller.apriRegistrazione();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


