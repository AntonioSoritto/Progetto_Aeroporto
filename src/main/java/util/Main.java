package util;

import controller.Controller;

import javax.swing.*;

public class Main {
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

