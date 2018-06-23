package GUI.Adopter;


import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AdopterMain  {
    private JButton button1;
    private JPanel panelMain;
    public int WINDOW_WIDTH = 1500;
    public int WINDOW_HEIGHT = 1000;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JFrame jFrame = new JFrame(GetString.adopterTitle);

    public AdopterMain() {
        jFrame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        jFrame.setLocation(LOCATION_X,LOCATION_Y);

        button1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });



    }

    public  void StartAM() {

        jFrame.setContentPane(new AdopterMain().panelMain);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }




}
