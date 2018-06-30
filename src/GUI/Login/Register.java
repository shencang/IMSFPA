package GUI.Login;

import Datebase.SQLserver;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Register extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 400;
    public int LOCATION_X = 497;
    public int LOCATION_Y = 242;
    public String identL = "领养人";
    public String identS = "店员";
    public static String identitiy = null;


    JLabel jLabelUsername, jLabelIdentity, jLabelPassword, jLabelName, jLabelTel, jLabelEmail;
    JTextField usernameTip, passwordTip, nameTip, telTip, emailTip;
    JButton backButton, trueButton;
    JComboBox<String> identityTip;
    JLabel close_label;

    public Register() {


        /**
         * 实现拖拽窗口的功能
         */


        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                Register.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                Register.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("注册框体正在移动");

            }
        });


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void registerStart() {
        Register regis = new Register();
        regis.setTitle(GetString.registerTitle);
        regis.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        regis.setLocation(LOCATION_X, LOCATION_Y);
        regis.setUndecorated(true);//边框不可见
        regis.setResizable(true);//禁止改变大小
        regis.setLayout(new BorderLayout());

        /**
         * 北部面板
         */
        JPanel panel_north = new JPanel();
        panel_north.setLayout(null);
        panel_north.setPreferredSize(new Dimension(0, 140));
        ImageIcon image_north = new ImageIcon("Src\\Res\\Img\\back2.png");
        JLabel background_north = new JLabel(image_north);
        background_north.setBounds(0, 0, 900, 140);
        panel_north.add(background_north);
        close_label = new JLabel(new ImageIcon("src\\Res\\Img\\close_mini.png"));
        close_label.setBounds(860, 15, 25, 25);
        panel_north.add(close_label, 0);
        close_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("注册窗体退出");
                regis.dispose();

            }

            @Override
            public void mousePressed(MouseEvent e) {


                System.out.println("点击");

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon_close = new ImageIcon("src\\Res\\Img\\close.png");
                close_label.setIcon(icon_close);
                close_label.setToolTipText("关闭窗口");
                System.out.println("显示");


            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon_close = new ImageIcon("src\\Res\\Img\\close_mini.png");
                close_label.setIcon(icon_close);
                System.out.println("不显示");

            }
        });
        regis.add(panel_north, BorderLayout.NORTH);

        /**
         * 中部面板
         */
        JPanel panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(770, 200));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        /**
         * 身份选择框
         */
        identityTip = new JComboBox<String>();
        String[] identitiyText = new String[]{GetString.identity_M, GetString.identity_A, GetString.identity_A};
        //identityTip = new JComboBox();
        identityTip.addItem(GetString.identity_M);
        identityTip.addItem(GetString.identity_A);
        identityTip.addItem(GetString.identity_S);
        identityTip.setToolTipText(GetString.identityTip);
        identityTip.setBounds(150, 15, 375, 30);
        identityTip.setBorder(myLineBorder);

        /**
         * 用户名输入框
         */
        usernameTip = new JTextField();
        //默认为等待
        usernameTip.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (identityTip.getSelectedIndex() == 1) {
                    usernameTip.setToolTipText(GetString.usernameTip_A);
                    identitiy = identL;
                } else if (identityTip.getSelectedIndex() == 2) {
                    usernameTip.setToolTipText(GetString.usernameTip_S);
                    identitiy = identS;

                } else {
                    usernameTip.setToolTipText(GetString.trueIdentity);

                }
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


        usernameTip.setBounds(150, 44, 375, 30);
        usernameTip.setBorder(myLineBorder);
        /**
         * 密码输入框
         */
        passwordTip = new JTextField();
        passwordTip.setToolTipText(GetString.passwordTip);
        passwordTip.setBounds(150, 73, 375, 30);
        passwordTip.setBorder(myLineBorder);

        /**
         *姓名输入框
         */
        nameTip = new JTextField();
        nameTip.setToolTipText(GetString.nameTip);
        nameTip.setBounds(150, 102, 375, 30);
        nameTip.setBorder(myLineBorder);


        /**
         *电话输入框
         */
        telTip = new JTextField();
        telTip.setToolTipText(GetString.telTip);
        telTip.setBounds(150, 131, 375, 30);
        telTip.setBorder(myLineBorder);


        /**
         *电子邮件输入框
         */
        emailTip = new JTextField();
        emailTip.setToolTipText(GetString.emailTip);
        emailTip.setBounds(150, 160, 375, 30);
        emailTip.setBorder(myLineBorder);

        panel_center.add(identityTip);
        panel_center.add(usernameTip);
        panel_center.add(passwordTip);
        panel_center.add(nameTip);
        panel_center.add(telTip);
        panel_center.add(emailTip);
        regis.add(panel_center, BorderLayout.CENTER);


        /**
         * 西部面板
         */
        JPanel panel_west = new JPanel();
        panel_west.setLayout(null);
        panel_west.setPreferredSize(new Dimension(130, 200));
        //用户身份
        jLabelIdentity = new JLabel(GetString.pleaseSetIdentity);
        jLabelIdentity.setBounds(10, 10, 120, 30);
        //用户名
        jLabelUsername = new JLabel(GetString.pleaseSetUsername);
        jLabelUsername.setBounds(10, 40, 120, 30);
        //密码
        jLabelPassword = new JLabel(GetString.pleaseSetPassword);
        jLabelPassword.setBounds(10, 70, 120, 30);
        //姓名
        jLabelName = new JLabel(GetString.pleaseSetname);
        jLabelName.setBounds(10, 100, 120, 30);
        //电话
        jLabelTel = new JLabel(GetString.pleaseSetTel);
        jLabelTel.setBounds(10, 130, 120, 30);
        //电子邮件
        jLabelEmail = new JLabel(GetString.getPleaseSetEmail);
        jLabelEmail.setBounds(10, 160, 120, 30);

        panel_west.add(jLabelIdentity);
        panel_west.add(jLabelUsername);
        panel_west.add(jLabelPassword);
        panel_west.add(jLabelName);
        panel_west.add(jLabelTel);
        panel_west.add(jLabelEmail);
        regis.add(panel_west, BorderLayout.WEST);


        /**
         * 南部面板
         */
        JPanel panel_south = new JPanel();
        panel_south.setLayout(new FlowLayout());
        panel_south.setPreferredSize(new Dimension(900, 60));
        MyLineBorder myLineBorder1 = new MyLineBorder(new Color(192, 192, 192), 1, true);

        /**
         * 注册按钮
         */
        ImageIcon image = new ImageIcon("src\\Res\\Img\\trueBtn.png");
        trueButton = new JButton(image);
        trueButton.setToolTipText(GetString.trueButtonTip);
        trueButton.setBounds(120, 0, image.getIconWidth() - 10, image.getIconHeight() - 10);
        trueButton.setBorder(myLineBorder1);
        trueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (identityTip.getSelectedIndex() == 0){
                   JOptionPane.showMessageDialog(null, GetString.tipRe, GetString.TIP, JOptionPane.ERROR_MESSAGE);

               }else {
                   registerNew();
               }



            }
        });
        panel_south.add(trueButton);


        /**
         * 返回按钮
         */
        //返回按钮
        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\backBtn.png");
        backButton = new JButton(image2);
        backButton.setToolTipText(GetString.backButtonTip);
        backButton.setBounds(220, 0, image2.getIconWidth() - 10, image2.getIconHeight() - 10);
        backButton.setBorder(myLineBorder1);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InitSysLogin initSysLogin = new InitSysLogin();
                initSysLogin.initLogin();
                regis.dispose();

            }
        });
        panel_south.add(backButton);
        regis.add(panel_south, BorderLayout.SOUTH);


        regis.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regis.setVisible(true);

    }

    public void registerNew() {


        String usernameget = identitiy;
        String username_s = "\\w{5}";//店员用户名必须是5位
        String usernamr_a = "\\w{6,18}";////领养人用户名必须是6-18位
        boolean userFlag = false;
        if (identitiy.equals(identL)) {
            userFlag = usernameTip.getText().matches(usernamr_a);
        } else if (identitiy.equals(identS)) {
            userFlag = usernameTip.getText().matches(username_s);

        }

        String password = "\\w{6}";//"密码必须为6位字母或者数字";
        boolean passwordFlag = passwordTip.getText().matches(password);

        String name = "[\\u4e00-\\u9fa5]{2,5}";// "姓名必须2-5位汉字";
        boolean nameFlag = nameTip.getText().matches(name);

        String tel = "\\w{0,15}";// "请输入15位以下字符";
        boolean telFlag = telTip.getText().matches(tel);

        String email = "\\w{0,15}";//"请输入15位以下字符";
        boolean emailFlag = emailTip.getText().matches(email);

        if (userFlag == false) {
            if (identitiy.equals(identL)) {
                usernameget = GetString.usernameErr_A;
            } else if (identitiy.equals(identS)) {
                usernameget = GetString.usernameErr_S;

            }
            JOptionPane.showMessageDialog(null, usernameget, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            usernameTip.setText("");
        } else if (passwordFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.passwordErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            passwordTip.setText("");
        } else if (nameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.nameErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            nameTip.setText("");
        } else if (telFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.telErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            telTip.setText("");
        } else if (emailFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.emailErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            emailTip.setText("");
        } else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();

            sqLserver.getUserRegis(usernameTip.getText(), passwordTip.getText(), nameTip.getText(), telTip.getText(), emailTip.getText());
            sqLserver.RegisterVerify(usernameTip.getText());

            this.usernameTip.setText("");
            this.passwordTip.setText("");
            this.nameTip.setText("");
            this.telTip.setText("");
            this.emailTip.setText("");



        }


    }
}
