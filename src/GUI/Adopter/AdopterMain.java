package GUI.Adopter;

import GUI.Login.Register;
import Other.GetTime;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class AdopterMain extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 1200;
    public int WINDOW_HEIGHT = 600;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;

    JLabel jLabelwelcome,jLabelshowSystem,jLabelClose;
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public AdopterMain(){

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
        panel_north.add(jLabelClose, 0);
        panel_north.add(jLabelwelcome,0);
        adopterMain.add(panel_north,BorderLayout.NORTH);






        adopterMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adopterMain.setVisible(true);

    }



}
