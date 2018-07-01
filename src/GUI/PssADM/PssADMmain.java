package GUI.PssADM;


import Datebase.SQLserver;
import GUI.Login.InitSysLogin;
import GUI.Login.MyLineBorder;
import GUI.Pss.*;
import Other.GetTime;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PssADMmain extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 1200;
    public int WINDOW_HEIGHT = 600;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;

    JLabel jLabelwelcome, jLabelshowSystem, jLabelClose, jLabelIdentity;
    JButton jButtonfingAllPet, jButtonfingAdoPet, jButtonfingPssPs, jButtonfingPsPet, jButtonPssUpdate, jButtonsetRSPet, jButtonexit, jButtonloginout, jButtondelRSPet, jButtonsetApp, jButtonPetGet;
    JButton jButtonaddps, jButtondelps, jButtonupdteps, addemp, deleemp;

    public PssADMmain() {
        SQLserver.updatePet();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                PssADMmain.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                PssADMmain.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("用户窗口框体正在移动");

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public void pssStart(String username) {
        PssADMmain adopterMain = new PssADMmain();
        adopterMain.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Res\\Img\\logo.png"));

        adopterMain.setTitle(GetString.adopterTitle);
        adopterMain.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        adopterMain.setLocation(LOCATION_X, LOCATION_Y);
        adopterMain.setUndecorated(true);//边框不可见
        adopterMain.setResizable(true);//禁止改变大小
        adopterMain.setLayout(new BorderLayout());


        /**
         * 北部面板
         **/
        JPanel panel_north = new JPanel();
        panel_north.setLayout(null);
        panel_north.setPreferredSize(new Dimension(0, 200));
        //首先获得系统时间生成欢迎语句
        jLabelwelcome = new JLabel(GetTime.gethourString());
        jLabelwelcome.setBounds(10, 10, 320, 50);
        jLabelwelcome.setFont(new Font("黑体", 0, 30));

        jLabelIdentity = new JLabel(GetString.welIdentity_da + username);
        jLabelIdentity.setBounds(15, 50, 320, 40);
        jLabelIdentity.setFont(new Font("微软雅黑", 0, 13));

        ImageIcon image_north = new ImageIcon("Src\\Res\\Img\\adm_bg.png");
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
                System.out.println("在管理员系统，准备关闭");

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
        panel_north.add(jLabelIdentity, 0);
        panel_north.add(jLabelClose, 0);
        panel_north.add(jLabelwelcome, 0);
        adopterMain.add(panel_north, BorderLayout.NORTH);

        /**
         * 中部面板
         */

        JPanel panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(400, 300));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon imageInfo = new ImageIcon("src\\Res\\Img\\PssUpdateBtn.png");
        ImageIcon imageAp = new ImageIcon("src\\Res\\Img\\setAppBtn.png");
        ImageIcon image2a = new ImageIcon("src\\Res\\Img\\addps.png");
        ImageIcon image3a = new ImageIcon("src\\Res\\Img\\delps.png");
        ImageIcon image4a = new ImageIcon("src\\Res\\Img\\updateps.png");
        /**
         * 查看与修改个人信息按钮
         */


        jButtonPssUpdate = new JButton(imageInfo);
        jButtonPssUpdate.setBounds(20, 20, imageInfo.getIconWidth() - 10, imageInfo.getIconHeight() - 10);
        // jButtonmodinfo.setBorder(myLineBorder);
        jButtonPssUpdate.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PssInfoUpdate pssInfoUpdate = new PssInfoUpdate();
                pssInfoUpdate.start(username);

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
         * 审批申请按钮
         */
        jButtonsetApp = new JButton(imageAp);
        jButtonsetApp.setBounds(20, 70, imageAp.getIconWidth() - 10, imageAp.getIconHeight() - 10);
        // jButtonmodpet.setBorder(myLineBorder);
        jButtonsetApp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PetAdoApproval petAdoApproval = new PetAdoApproval();
                petAdoApproval.start(username);
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
         * 店铺添加
         */
        jButtonaddps = new JButton(image2a);
        jButtonaddps.setBounds(20, 120, image2a.getIconWidth() - 10, image2a.getIconHeight() - 10);
        // jButtonmodpet.setBorder(myLineBorder);
        jButtonaddps.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InsertPS insertPS = new InsertPS();
                insertPS.start(username);
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
         * 店铺删除
         */
        jButtondelps = new JButton(image3a);
        jButtondelps.setBounds(20, 170, image3a.getIconWidth() - 10, image3a.getIconHeight() - 10);
        // jButtonmodpet.setBorder(myLineBorder);
        jButtondelps.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DelePS delePS = new DelePS();
                delePS.start(username);
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
         * 店铺信息修改
         */
        jButtonupdteps = new JButton(image4a);
        jButtonupdteps.setBounds(20, 220, image4a.getIconWidth() - 10, image4a.getIconHeight() - 10);
        // jButtonmodpet.setBorder(myLineBorder);
        jButtonupdteps.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UpdatePS updatePS = new UpdatePS();
                updatePS.start(username);
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


        panel_center.add(jButtonupdteps);
        panel_center.add(jButtonsetApp);
        panel_center.add(jButtonaddps);
        panel_center.add(jButtondelps);
        panel_center.add(jButtonPssUpdate);

        adopterMain.add(panel_center, BorderLayout.CENTER);


        /**
         * 西部面板
         */

        JPanel panel_west = new JPanel();
        panel_west.setLayout(null);
        panel_west.setPreferredSize(new Dimension(400, 300));
        MyLineBorder myLineBorder1 = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon chaimage1 = new ImageIcon("src\\Res\\Img\\fingAllPetBtn.png");
        ImageIcon chaimage2 = new ImageIcon("src\\Res\\Img\\fingAdoPetBtn.png");
        ImageIcon chaimage3 = new ImageIcon("src\\Res\\Img\\allpss.png");
        ImageIcon chaimage4 = new ImageIcon("src\\Res\\Img\\allpet.png");

        /**
         * 查询宠物按钮
         */
        jButtonfingAllPet = new JButton(chaimage1);
        jButtonfingAllPet.setBounds(20, 20, chaimage1.getIconWidth() - 10, chaimage1.getIconHeight() - 10);
        //  jButtonpeted.setBorder(myLineBorder1);
        jButtonfingAllPet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FindPetAll findPetAll = new FindPetAll();
                findPetAll.start();

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
         * 查询领养人按钮
         */

        jButtonfingAdoPet = new JButton(chaimage2);
        jButtonfingAdoPet.setBounds(20, 70, chaimage2.getIconWidth() - 10, chaimage2.getIconHeight() - 10);
        // jButtonpet.setBorder(myLineBorder1);
        jButtonfingAdoPet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                FindApopterAll findApopterAll = new FindApopterAll();
                findApopterAll.start();

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
         * 查询其他员工信息按钮
         */
        jButtonfingPssPs = new JButton(chaimage3);
        jButtonfingPssPs.setBounds(20, 120, chaimage3.getIconWidth() - 10, chaimage3.getIconHeight() - 10);
        // jButtoninform.setBorder(myLineBorder1);
        jButtonfingPssPs.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PsInfoAll psInfo = new PsInfoAll();
                psInfo.start(username);

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
         * 宠物按钮
         */
        jButtonfingPsPet = new JButton(chaimage4);
        jButtonfingPsPet.setBounds(20, 170, chaimage4.getIconWidth() - 10, chaimage4.getIconHeight() - 10);
        // jButtoninform.setBorder(myLineBorder1);
        jButtonfingPsPet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PsPet psPet = new PsPet();
                psPet.start(username);

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

        panel_west.add(jButtonfingAllPet);
        panel_west.add(jButtonfingAdoPet);
        panel_west.add(jButtonfingPssPs);
        // panel_west.add(jButtonfingPsPet);

        adopterMain.add(panel_west, BorderLayout.WEST);

        /**
         * 东部面板
         */
        JPanel panel_east = new JPanel();
        panel_east.setLayout(null);
        panel_east.setPreferredSize(new Dimension(400, 300));
        MyLineBorder myLineBorder3 = new MyLineBorder(new Color(192, 192, 192), 1, true);

        ImageIcon image1 = new ImageIcon("src\\Res\\Img\\PetGetBtn.png");
        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\setRSPetBtn.png");
        ImageIcon image3 = new ImageIcon("src\\Res\\Img\\delRSPetBtn.png");
        ImageIcon image3b = new ImageIcon("src\\Res\\Img\\addemp.png");
        ImageIcon image4b = new ImageIcon("src\\Res\\Img\\delemp.png");

        /**
         * 新增宠物登记按钮
         */
        jButtonPetGet = new JButton(image1);
        jButtonPetGet.setBounds(20, 20, image1.getIconWidth() - 10, image1.getIconHeight() - 10);
        //  jButtondele.setBorder(myLineBorder3);
        jButtonPetGet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InsertPet insertPet = new InsertPet();
                insertPet.start(username);
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
         * 新增领养关系按钮
         */
        jButtonsetRSPet = new JButton(image2);
        jButtonsetRSPet.setBounds(20, 70, image2.getIconWidth() - 10, image2.getIconHeight() - 10);
        //  jButtondele.setBorder(myLineBorder3);
        jButtonsetRSPet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InsertAR insertAR = new InsertAR();
                insertAR.start(username);
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
         * 删除领养关系按钮
         */
        jButtondelRSPet = new JButton(image3);
        jButtondelRSPet.setBounds(20, 120, image3.getIconWidth() - 10, image3.getIconHeight() - 10);
        //  jButtondele.setBorder(myLineBorder3);
        jButtondelRSPet.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleAR deleAR = new DeleAR();
                deleAR.start(username);
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
         * 新增雇佣关系按钮
         */
        addemp = new JButton(image3b);
        addemp.setBounds(20, 170, image3b.getIconWidth() - 10, image3b.getIconHeight() - 10);
        //  jButtondele.setBorder(myLineBorder3);
        addemp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InsertEMP insertEMP = new InsertEMP();
                insertEMP.start(username);
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
         * 删除雇佣关系按钮
         */

        deleemp = new JButton(image4b);
        deleemp.setBounds(20, 220, image4b.getIconWidth() - 10, image4b.getIconHeight() - 10);
        //  jButtondele.setBorder(myLineBorder3);
        deleemp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DeleEMP deleEMP = new DeleEMP();
                deleEMP.start(username);
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


        panel_east.add(jButtonPetGet);
        panel_east.add(jButtonsetRSPet);
        panel_east.add(jButtondelRSPet);
        panel_east.add(addemp);
        panel_east.add(deleemp);
        adopterMain.add(panel_east, BorderLayout.EAST);


        /**
         * 南部面板
         */

        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(1200, 100));
        MyLineBorder myLineBorder2 = new MyLineBorder(new Color(192, 192, 192), 1, true);

        /**
         * 注销按钮
         */
        ImageIcon imageout = new ImageIcon("src\\Res\\Img\\loginoutbtn.png");
        jButtonloginout = new JButton(imageout);
        jButtonloginout.setBounds(440, 60, imageout.getIconWidth() - 10, imageout.getIconHeight() - 10);
        //  jButtonloginout.setBorder(myLineBorder2);
        jButtonloginout.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InitSysLogin initSysLogin = new InitSysLogin();
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
        jButtonexit.setBounds(820, 60, imageout.getIconWidth() - 10, imageout.getIconHeight() - 10);
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

        adopterMain.add(panel_south, BorderLayout.SOUTH);

        adopterMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adopterMain.setVisible(true);

    }


}


