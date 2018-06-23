package GUI.Adopter;


import Res.Values.GetString;

import javax.swing.*;

public class AdopterMain  {
    private JButton button1;
    private JPanel panel1;

    public AdopterMain() {


    }

    public static void StartAM() {
        JFrame jFrame = new JFrame(GetString.adopterTitle);
        jFrame.setContentPane(new AdopterMain().panel1);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }


}
