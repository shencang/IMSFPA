package GUI.Adopter;

import Datebase.SQLserver;
import GUI.Login.InitSysLogin;
import GUI.Login.MyLineBorder;
import Other.GetTime;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdopterMain extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 1200;
    public int WINDOW_HEIGHT = 600;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;

    JLabel jLabelwelcome,jLabelshowSystem,jLabelClose,jLabelIdentity;
    JButton jButtonpeted,jButtonpet,jButtoninform,jButtonmodinfo,jButtonmodpet,jButtonexit,jButtonloginout,jButtondele;
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public AdopterMain(){
        SQLserver.updatePet();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                AdopterMain.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                AdopterMain.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("用户窗口框体正在移动");

            }
        });
    }

    public void  adopeterStart(String username){
        AdopterMain adopterMain= new AdopterMain();
        adopterMain.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Res\\Img\\logo.png"));
        adopterMain.setTitle(GetString.adopterTitle);
        adopterMain.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        adopterMain.setLocation(LOCATION_X,LOCATION_Y);
        adopterMain.setUndecorated(true);//边框不可见
        adopterMain.setResizable(true);//禁止改变大小
        adopterMain.setLayout(new BorderLayout());



        /**
        * 北部面板
        **/
        JPanel panel_north = new JPanel();
        panel_north.setLayout(null);
        panel_north.setPreferredSize(new Dimension(0,200));
        //首先获得系统时间生成欢迎语句
        jLabelwelcome = new JLabel(GetTime.gethourString());
        jLabelwelcome.setBounds(10,10,320,50);
        jLabelwelcome.setFont(new Font("黑体", 0, 30));

        jLabelIdentity = new JLabel(GetString.welIdentity+username);
        jLabelIdentity.setBounds(15,50,320,40);
        jLabelIdentity.setFont(new Font("微软雅黑",0,13));

        ImageIcon image_north = new ImageIcon("Src\\Res\\Img\\adopter_bg.png");
        JLabel background_north = new JLabel(image_north);
        background_north.setBounds(0, 0, 1200, 200);

        jLabelClose = new JLabel(new ImageIcon("src\\Res\\Img\\close_mini.png"));
        jLabelClose.setBounds(1160, 15, 25, 25);
        jLabelClose.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("注册窗体退出");
                adopterMain.dispose();

            }

            @Override
            public void mousePressed(MouseEvent e) {


                System.out.println("点击");
                System.out.println("在领养人系统，准备关闭");

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon_close = new ImageIcon("src\\Res\\Img\\close.png");
                jLabelClose.setIcon(icon_close);
                jLabelClose.setToolTipText("关闭窗口");
                System.out.println("显示");


            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon_close = new ImageIcon("src\\Res\\Img\\close_mini.png");
                jLabelClose.setIcon(icon_close);
                System.out.println("不显示");

            }
        });


        panel_north.add(background_north);
        panel_north.add(jLabelIdentity,0);
        panel_north.add(jLabelClose, 0);
        panel_north.add(jLabelwelcome,0);
        adopterMain.add(panel_north,BorderLayout.NORTH);

        /**
         * 中部面板
         */

        JPanel panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(400,300));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon imageInfo = new ImageIcon("src\\Res\\Img\\gaiinformbtn.png");
        ImageIcon imageAp = new ImageIcon("src\\Res\\Img\\newapbtn.png");
        /**
         * 修改个人信息按钮
         */


        jButtonmodinfo= new JButton(imageInfo);
        jButtonmodinfo.setBounds(20,20,imageInfo.getIconWidth()-10,imageInfo.getIconHeight()-10);
       // jButtonmodinfo.setBorder(myLineBorder);
        jButtonmodinfo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdopterInfoUpdate adopterInfoUpdate = new AdopterInfoUpdate();
                adopterInfoUpdate.start(username);

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

        /**
         * 领养申请按钮
         */
        jButtonmodpet = new JButton(imageAp);
        jButtonmodpet.setBounds(20,70,imageAp.getIconWidth()-10,imageAp.getIconHeight()-10);
       // jButtonmodpet.setBorder(myLineBorder);
        jButtonmodpet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdoApplication adoApplication = new AdoApplication();
               adoApplication.start(username);

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
        panel_center.add(jButtonmodinfo);
        panel_center.add(jButtonmodpet);

        adopterMain.add(panel_center,BorderLayout.CENTER);



        /**
         * 西部面板
         */

        JPanel panel_west = new JPanel();
        panel_west.setLayout(null);
        panel_west.setPreferredSize( new Dimension(400,300));
        MyLineBorder myLineBorder1 = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon chaimage1 = new ImageIcon("src\\Res\\Img\\chapetbtn1.png");
        ImageIcon chaimage2 = new ImageIcon("src\\Res\\Img\\chapetbtn2.png");
        ImageIcon chaimage3 = new ImageIcon("src\\Res\\Img\\chainformbtn.png");

        /**
         * 查询已领养按钮
         */
        jButtonpeted = new JButton(chaimage1);
        jButtonpeted.setBounds(20,20,chaimage1.getIconWidth()-10,chaimage1.getIconHeight()-10);
      //  jButtonpeted.setBorder(myLineBorder1);
        jButtonpeted.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindPeted findPeted = new FindPeted();
                findPeted.start(username);

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

        /**
         * 查询未领养宠物按钮
         */

        jButtonpet = new JButton(chaimage2);
        jButtonpet.setBounds(20,70,chaimage1.getIconWidth()-10,chaimage1.getIconHeight()-10);
       // jButtonpet.setBorder(myLineBorder1);
        jButtonpet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindPet findPet = new FindPet();
                findPet.start();

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

        /**
         * 查询个人信息按钮
         */
        jButtoninform = new JButton(chaimage3);
        jButtoninform.setBounds(20,120,chaimage1.getIconWidth()-10,chaimage1.getIconHeight()-10);
       // jButtoninform.setBorder(myLineBorder1);
        jButtoninform.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdopterInfo adopterInfo = new AdopterInfo();
                adopterInfo.start(username);

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

        panel_west.add(jButtoninform);
        panel_west.add(jButtonpeted);
        panel_west.add(jButtonpet);
       adopterMain.add(panel_west,BorderLayout.WEST);

        /**
         * 东部面板
         */
        JPanel panel_east = new JPanel();
        panel_east.setLayout(null);
        panel_east.setPreferredSize(new Dimension(400, 300));
        MyLineBorder  myLineBorder3 = new MyLineBorder(new Color(192, 192, 192), 1, true);

        ImageIcon imageDele = new ImageIcon("src\\Res\\Img\\deleapbtn.png");

        /**
         * 删除领养申请按钮
         */
        jButtondele = new JButton(imageDele);
        jButtondele.setBounds(20, 20, imageDele.getIconWidth() - 10, imageDele.getIconHeight() - 10);
      //  jButtondele.setBorder(myLineBorder3);
        jButtondele.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AdoDele adoDele = new AdoDele();
                adoDele.start(username);
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
        panel_east.add(jButtondele);
        adopterMain.add(panel_east,BorderLayout.EAST);




        /**
         * 南部面板
         */

        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(1200,100));
        MyLineBorder myLineBorder2 = new MyLineBorder(new Color(192, 192, 192), 1, true);

        /**
         * 注销按钮
         */
        ImageIcon imageout = new ImageIcon("src\\Res\\Img\\loginoutbtn.png");
        jButtonloginout = new JButton(imageout);
        jButtonloginout.setBounds(440,60,imageout.getIconWidth()-10,imageout.getIconHeight()-10);
      //  jButtonloginout.setBorder(myLineBorder2);
        jButtonloginout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InitSysLogin initSysLogin= new InitSysLogin();
                initSysLogin.initLogin();
                adopterMain.dispose();

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

        /**
         * 退出按钮
         */
        ImageIcon imageexit = new ImageIcon("src\\Res\\Img\\exitbtn.png");
        jButtonexit = new JButton(imageexit);
        jButtonexit.setBounds(820,60,imageout.getIconWidth()-10,imageout.getIconHeight()-10);
      //  jButtonexit.setBorder(myLineBorder2);
        jButtonexit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

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




        panel_south.add(jButtonloginout);
        panel_south.add(jButtonexit);

        adopterMain.add(panel_south,BorderLayout.SOUTH);

        adopterMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adopterMain.setVisible(true);

    }



}
