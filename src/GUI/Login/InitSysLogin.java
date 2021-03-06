package GUI.Login;

import Datebase.SQLserver;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class InitSysLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    //合并方法，弃用
    //public   CreatePanel createPanel=new CreatePanel();
    public String LOG_TITLE = GetString.loginTitle;
    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 400;
    public int LOCATION_X = 497;
    public int LOCATION_Y = 242;
    static JTextField username = new JTextField();
    static JTextField password = new JPasswordField(JPasswordField.LEFT);
    boolean flag = false;
    JLabel close_label;
    String usernames = "\\w{5,18}";//用户名必须是5-18
    String passwords = "\\w{6}";//"密码必须为6位字母或者数字";

    boolean getnamApas = false;

    //定义面板
    public InitSysLogin() {
        /**
         * 实现拖拽窗口的功能
         */


        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                InitSysLogin.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                InitSysLogin.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("登录框体正在移动");

            }
        });


    }


    public boolean isFlag() {
        return flag;
    }


    public void initLogin() {
        InitSysLogin login = new InitSysLogin();
        login.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Res\\Img\\logo.png"));
        login.setTitle(LOG_TITLE);
        login.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        login.setLocation(LOCATION_X, LOCATION_Y);

        login.setUndecorated(true);   //设置frame边框不可见
        login.setResizable(true);    //禁止改变窗口大小
        BorderLayout border_layout = new BorderLayout();
        login.setLayout(border_layout);


        /**
         * 北部面板
         */

        JPanel panel_north = new JPanel();
        panel_north.setLayout(null);
        panel_north.setPreferredSize(new Dimension(900, 140));

        ImageIcon image_north = new ImageIcon("src\\Res\\Img\\back.png");
        JLabel background_north = new JLabel(image_north);
        background_north.setBounds(0, 0, 900, 140);
        panel_north.add(background_north);


        close_label = new JLabel(new ImageIcon("src\\Res\\Img\\close_mini.png"));
        close_label.setBounds(850, 50, 25, 25);
        panel_north.add(close_label, 0);
        close_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("登录窗体退出");
                login.dispose();

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
        //JPanel panel_north=createPanel.CreateNorthPanel();
        login.add(panel_north, BorderLayout.NORTH);

        /**
         * 中部面板
         */


        //属于西部面板，需要跟换头像，发在这里
        ImageIcon image_west = new ImageIcon("src\\Res\\Img\\Head.png");
        JLabel background_west = new JLabel(image_west);

        background_west.setBounds(10, 10, 120, 120);
        JPanel panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(570, 200));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        /**
         * 用户名框
         */
        //username = new JTextField();
        username.setBounds(70, 25, 375, 30);
        username.setToolTipText(GetString.usernameTip);
        username.setBorder(myLineBorder);
        ActionListener usernametext = new GeText();
        username.addActionListener(usernametext);
        username.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (username.getText().length()==5){
                    ImageIcon image_west1 = new ImageIcon("src\\Res\\Img\\PSSHead.png");
                    background_west.setIcon(image_west1);
                    System.out.println("释放");


                }
                else if (username.getText().length()>=6&&username.getText().length()<=18){
                    ImageIcon image_west2 = new ImageIcon("src\\Res\\Img\\adopterHead.png");
                    background_west.setIcon(image_west2);

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {


            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (username.getText().length()==5){
                    ImageIcon image_west1 = new ImageIcon("src\\Res\\Img\\PSSHead.png");
                    background_west.setIcon(image_west1);


                }
                else if (username.getText().length()>=6&&username.getText().length()<=18){
                    ImageIcon image_west2 = new ImageIcon("src\\Res\\Img\\adopterHead.png");
                    background_west.setIcon(image_west2);

                }



            }
        });

        /**
         * 密码名框
         */
        //password = new JPasswordField(JPasswordField.LEFT);
        password.setBounds(70, 54, 375, 30);
        password.setToolTipText(GetString.passwordTip);
        password.setBorder(myLineBorder);
        ActionListener passwordtext = new GeText();
        password.addActionListener(passwordtext);
        password.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (username.getText().length()==5){
                    ImageIcon image_west1 = new ImageIcon("src\\Res\\Img\\PSSHead.png");
                    background_west.setIcon(image_west1);
                    System.out.println("密码框触发头像跟换");


                }
                else if (username.getText().length()>=6&&username.getText().length()<=18){
                    ImageIcon image_west2 = new ImageIcon("src\\Res\\Img\\adopterHead.png");
                    background_west.setIcon(image_west2);

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (username.getText().length()==5){
                    ImageIcon image_west1 = new ImageIcon("src\\Res\\Img\\PSSHead.png");
                    background_west.setIcon(image_west1);
                    System.out.println("碰触密码框导致更换");


                }
                else if (username.getText().length()>=6&&username.getText().length()<=18){
                    ImageIcon image_west2 = new ImageIcon("src\\Res\\Img\\adopterHead.png");
                    background_west.setIcon(image_west2);

                }

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


        JCheckBox rember_pwd = new JCheckBox("记住密码");
        rember_pwd.setBounds(150, 90, 80, 20);
        getnamApas = rember_pwd.isSelected();

        JCheckBox login_auto = new JCheckBox("自动登录");
        login_auto.setBounds(250, 90, 80, 20);



        panel_center.add(rember_pwd);
        panel_center.add(username);
        panel_center.add(password);
        panel_center.add(login_auto);


        //  JPanel panel_center=createPanel.CrateCenterPanel();

        login.add(panel_center, BorderLayout.CENTER);

        /**
         * 西部面板
         */
        JPanel panel_west = new JPanel();
        panel_west.setLayout(null);
        panel_west.setPreferredSize(new Dimension(200, 200));



        panel_west.add(background_west);
        //JPanel panel_west=createPanel.CreateWestPanel();
        login.add(panel_west, BorderLayout.WEST);

        /**
         * 南部面板
         */
        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(900, 60));
        MyLineBorder myLineBorder1 = new MyLineBorder(new Color(192, 192, 192), 1, true);
        /**
         * 登录按钮
         */
        ImageIcon image = new ImageIcon("src\\Res\\Img\\loginbutton.png");
        JButton btn = new JButton(image);
        btn.setBounds(140, 0, image.getIconWidth() - 10, image.getIconHeight() - 10);
        //btn.setBorder(myLineBorder1);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean userFlag = username.getText().matches(usernames);
                boolean passwordFlag = password.getText().matches(passwords);

                if (rember_pwd.isSelected()) {
                    getnamApas = rember_pwd.isSelected();
                    try {
                        String pathname = "src\\Res\\Values\\user.save";
                        File filename = new File(pathname);
                        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
                        // System.out.println(newoldName);
                        out.write(username.getText() + "\n" + password.getText()); // \r\n即为换行
                        out.flush(); // 把缓存区内容压入文件
                        out.close(); // 最后记得关闭文件
                    } catch (Exception es) {
                        es.printStackTrace();
                    }

                }




                if (userFlag == false) {

                    JOptionPane.showMessageDialog(null, GetString.usernameErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
                    username.setText("");
                } else if (passwordFlag == false) {
                    JOptionPane.showMessageDialog(null, GetString.passwordErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
                    password.setText("");
                }
                else {
                SQLserver sqLserver = new SQLserver();
                sqLserver.connectSQL();
                flag = sqLserver.SQLverify(username.getText(), password.getText());
                username.setText("");
                password.setText("");
                if (flag) {


                    login.dispose();


                    System.out.println("YES");

                } else {
                    System.out.println("NO");
                }

            }
            }
        });
        panel_south.add(btn);

        // JPanel panel_south=createPanel.CreateSouthPanel();
        login.add(panel_south, BorderLayout.SOUTH);


        /**
         * 东部面板
         */


        JPanel pannel_east = new JPanel();
        pannel_east.setLayout(null);
        pannel_east.setPreferredSize(new Dimension(130, 200));

        JLabel regeist = new JLabel("注册账号");
        regeist.setForeground(new Color(100, 149, 238));
        regeist.setBounds(0, 23, 60, 30);
        regeist.setFont(new Font("宋体", 0, 12));
        regeist.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


                Register register = new Register();
                register.registerStart();
                login.dispose();


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                regeist.setForeground(new Color(16, 88, 231));


            }

            @Override
            public void mouseExited(MouseEvent e) {
                regeist.setForeground(new Color(100, 149, 238));


            }
        });


        JLabel regetpwd = new JLabel("找回密码");
        regetpwd.setForeground(new Color(100, 149, 238));
        regetpwd.setBounds(0, 53, 60, 30);
        regetpwd.setFont(new Font("宋体", 0, 12));
        regetpwd.addMouseListener(new MouseListener() {
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
                regetpwd.setForeground(new Color(16, 88, 231));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                regetpwd.setForeground(new Color(100, 149, 238));

            }
        });

        pannel_east.add(regetpwd);
        pannel_east.add(regeist);
        //JPanel pannel_east=createPanel.CrateEastPanel();
        login.add(pannel_east, BorderLayout.EAST);


        login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        login.setVisible(true);


    }



}
