package 暂时被弃用;
//目前重新合并，暂时弃用，但不注释


import Datebase.SQLserver;
import GUI.Login.GeText;
import GUI.Login.MyLineBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreatePanel {
    public static int WINDOW_WIDTH = 420;
    public static int WINDOW_HEIGHT = 300;
    public static int LOCATION_X = 497;
    public static int LOCATION_Y = 242;
    //public static String username;
    //public static String password;
    ActionListener actionListener;
    static JTextField username;
    static JTextField password;
    static boolean flag = false;                   //标识是否登录
    //相关控件
    JButton denglu;
    JButton zhuce;
    JButton tuichu;


    /**
     * 创建北部面板
     *
     * @return
     */
    public JPanel CreateNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(0, 140));

        ImageIcon image = new ImageIcon("src\\Res\\Img\\back.png");
        JLabel background = new JLabel(image);
        background.setBounds(0, 0, 900, 140);
        panel.add(background);
        return panel;
    }

    /**
     * 创建西部面板
     */
    public JPanel CreateWestPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(130, 0));

        ImageIcon image = new ImageIcon("src\\Res\\Img\\HandImgage.png");
        JLabel background = new JLabel(image);

        background.setBounds(10, 0, 120, 120);

        panel.add(background);
        return panel;
    }

    /**
     * 创建南部面板
     */
    public  JPanel CreateSouthPanel(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(0, 50));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1 , true);

        /**
         * 登录按钮
         */
        ImageIcon image=new ImageIcon("src\\Res\\Img\\loginbutton.png");
        JButton btn=new JButton(image);
        btn.setBounds(140,0,image.getIconWidth()-10,image.getIconHeight()-10);
        btn.setBorder(myLineBorder);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLserver sqLserver = new SQLserver();
                sqLserver.connectSQL();
                flag=sqLserver.SQLverify(username.getText(),password.getText());
                username.setText("");
                password.setText("");




            }
        });
        panel.add(btn);
        return panel;
    }

    /**
     * 创建中部面板
     */
    public  JPanel CrateCenterPanel(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(0, 180));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1 , true);

        /**
         * 用户名框
         */
        username=new JTextField();
        username.setBounds(150, 15, 375, 30);
        username.setBorder(myLineBorder);
        ActionListener usernametext = new GeText();
        username.addActionListener(usernametext);

        /**
         * 密码名框
         */
        password=new JPasswordField(JPasswordField.LEFT);
        password.setBounds(150, 44, 375, 30);
        password.setBorder(myLineBorder);
        ActionListener passwordtext = new GeText();
        password.addActionListener(passwordtext);


        JCheckBox rember_pwd=new JCheckBox("记住密码");
        rember_pwd.setBounds(150, 80, 80, 20);

        JCheckBox login_auto=new JCheckBox("自动登录");
        login_auto.setBounds(250, 80, 80, 20);


        panel.add(rember_pwd);
        panel.add(username);
        panel.add(password);
        panel.add(login_auto);
        return panel;
    }

    /**
     * 创建东部面板
     */
    public   JPanel CrateEastPanel(){
            JPanel panel=new JPanel();
            panel.setLayout(null);
            panel.setPreferredSize(new Dimension(100, 0));

            JLabel regeist=new JLabel("注册账号");
            regeist.setForeground(new Color(100,149,238));
            regeist.setBounds(0, 13, 60, 30);
            regeist.setFont(new Font("宋体",0,12));
            regeist.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //InitSysLogin initSysLogin = new InitSysLogin();
                    //initSysLogin.initLogin();

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


            JLabel regetpwd=new JLabel("找回密码");
            regetpwd.setForeground(new Color(100,149,238));
            regetpwd.setBounds(0, 43, 60, 30);
            regetpwd.setFont(new Font("宋体",0,12));

            panel.add(regetpwd);
            panel.add(regeist);
            return panel;
}
}
