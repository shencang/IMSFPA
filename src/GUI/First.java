package GUI;

import GUI.Login.Login;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class First extends JFrame {

    private JPanel buttonPanel;
    private JFrame setLogo;
    public First(){

        //创建窗口
        setLogo = new JFrame(GetString.name);
        setLogo.setSize((1920), (1080));
        setLogo.setLocation(0, 0);
        setLogo.setLayout(null);
        JLabel l = new JLabel(GetString.name);
        ImageIcon i = new ImageIcon("src\\Res\\Img\\firstpage.jpg");
        i.setImage(i.getImage().getScaledInstance((1920),(1080),Image.SCALE_AREA_AVERAGING));
        //设置ImageIcon
        l.setIcon(i);
        //label的大小设置为ImageIcon,否则显示不完整
        l.setBounds(0,0, i.getIconWidth(), i.getIconHeight());
        setLogo.add(l);
        setLogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建封面按钮
        JButton b = new JButton("启动");
        b.setBounds(120, 850, 350, 80);

        buttonPanel=new JPanel();
        buttonPanel.add(b);
        this.add(buttonPanel);
        b.addActionListener(new Login());
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLogo.dispose();
            }
        });

        setLogo.add(b);
        setLogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        //启动,显示
        setLogo.setVisible(true);


    }

    public static void logo(){
        //创建窗口
        JFrame f = new JFrame(GetString.name);
        f.setSize((1920), (1080));
        f.setLocation(0, 0);
        f.setLayout(null);
        JLabel l = new JLabel(GetString.name);
        ImageIcon i = new ImageIcon("src\\Res\\Img\\firstpage.jpg");
        i.setImage(i.getImage().getScaledInstance((1920),(1080),Image.SCALE_AREA_AVERAGING));
        //设置ImageIcon
        l.setIcon(i);
        //label的大小设置为ImageIcon,否则显示不完整
        l.setBounds(0,0, i.getIconWidth(), i.getIconHeight());
        f.add(l);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建封面按钮
        JButton b = new JButton("启动");
        b.setBounds(120, 850, 350, 80);
        f.add(b);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //启动
        f.setVisible(true);

    }


    public static void welcome(){

    }
}
