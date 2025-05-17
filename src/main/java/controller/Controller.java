package controller;

import GUI.AMMINISTRATORE;
import GUI.UTENTE;

import javax.swing.*;

public class Controller {
    public static void apriUtente() {
        UTENTE utente = new UTENTE();
        JFrame frame = new JFrame("Interfaccia Utente");
        frame.setContentPane(utente.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void apriAmministratore() {
        AMMINISTRATORE amministratore = new AMMINISTRATORE();
        JFrame frame = new JFrame("Interfaccia Aministratore");
        frame.setContentPane(amministratore.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}