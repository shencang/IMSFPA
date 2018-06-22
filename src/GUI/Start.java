package GUI;

import javax.swing.*;

public class Start extends JFrame {
    private JButton button1;
    private JButton button2;

    public static void init() {
        JFrame frame = new JFrame("Start");
        frame.setContentPane(new Start().panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;


    public Start(){

    }
}
