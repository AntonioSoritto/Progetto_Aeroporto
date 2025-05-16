package gui;

import controller.Controller;
import javax.swing.*;

public class Home {
    private JPanel panel1;
    private JButton button1;
    private JPasswordField PASSWORD;
    private JTextField textField1;
    private JComboBox CMBlogin;

    private JFrame frameHome;
    private Controller controller;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Home home = new Home();
            home.frameHome = new JFrame("Home");
            home.frameHome.setContentPane(home.panel1);  // uso panel1 invece di mainPanel
            home.frameHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            home.frameHome.pack();
            home.frameHome.setVisible(true);
        });
    }

    public Home() {
        controller = new Controller();
        //
    }
}