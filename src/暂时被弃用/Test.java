package 暂时被弃用;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    public class Test extends JFrame{
        public Test(){
            this.setTitle("Buttontest");
            this.setSize(400,400);

            //create button
            JButton yellowbutton=new JButton("Yellow");
            JButton bluebutton=new JButton("Blue");
            JButton redbutton=new JButton("red");

            //add buttons to panel
            buttonPanel=new JPanel();
            buttonPanel.add(yellowbutton);
            buttonPanel.add(bluebutton);
            buttonPanel.add(redbutton);

            //add panel to frame
            this.add(buttonPanel);

            //craete button actions
            yellowbutton.addActionListener(new ColorAction(Color.YELLOW));
            bluebutton.addActionListener(new ColorAction(Color.BLUE));
            redbutton.addActionListener(new ColorAction(Color.RED));
            yellowbutton.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                    // TODO Auto-generated method stub
                    System.out.println("mouseReleased");
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                    System.out.println("mousePressed");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // TODO Auto-generated method stub
                    System.out.println("mouseExited");
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // TODO Auto-generated method stub
                    System.out.println("mouseEntered");
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    // TODO Auto-generated method stub

                }
            });
        }

        public class ColorAction implements ActionListener{
            public ColorAction(Color c){
                backgroundColor=c;
            }

            private Color backgroundColor;

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                buttonPanel.setBackground(backgroundColor);
            }
        }
        private JPanel buttonPanel;
    }
