package 暂时被弃用;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Example extends JFrame {
    JTextField text;
    ActionListener actionListener;


    public Example(){
        setLayout(new FlowLayout());
        text = new JTextField(10);

        add(text);
        actionListener = new ReaderListen();
        text.addActionListener(actionListener);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    public static void example1(){
        JFrame windows1 = new JFrame("1");
        JFrame windows2 = new JFrame("2");
        Container con  =windows1.getContentPane();


        windows1.setBounds(60,100,188,108);
        windows2.setBounds(260,100,188,108);

        windows1.setVisible(true);
        windows1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        windows2.setVisible(true);
        windows2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class ReaderListen implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        System.out.println(str+":"+str.length());
    }
}
