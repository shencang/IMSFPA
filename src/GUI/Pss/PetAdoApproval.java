package GUI.Pss;

import Datebase.SQLserver;
import GUI.Login.MyLineBorder;
import Res.Values.GetString;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PetAdoApproval extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 1200;
    public int WINDOW_HEIGHT = 800;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JTable jTablePet , jTablePet2;;
    JTableHeader jTableHeader,jTableHeader2;
    JScrollPane jScrollPanedatasave, jScrollPanedatasave2;
    JButton jButtontrueAndBack,jButtonSaveAndRefresh;
    JLabel jLabelPnum,jLabelTips;
    JTextField setPnum;





    public PetAdoApproval(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                PetAdoApproval.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                PetAdoApproval.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public  void  start(String name){

        PetAdoApproval update = new PetAdoApproval();
        update.setIconImage(Toolkit.getDefaultToolkit().getImage("src\\Res\\Img\\logo.png"));
        update.setTitle(GetString.messageTitle);
        update.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        update.setLocation(LOCATION_X, LOCATION_Y);
        update.setUndecorated(true);//边框不可见
        update.setResizable(true);//禁止改变大小
        update.setLayout(new BorderLayout());

        /**
         * 中部面板
         */
        JPanel  panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(900,500));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        jTablePet = new JTable();
        jScrollPanedatasave = new JScrollPane();
        jScrollPanedatasave.setBounds(0, 0, 600, 500);

        jScrollPanedatasave2 = new JScrollPane();
        jScrollPanedatasave2.setBounds(0, 605, 605, 500);

        jTablePet2 = new JTable();



        // 定义表头
        String[] title = {GetString.Pnum,GetString.Anum,GetString.AAdate};
        // 创建JTable
        jTablePet = new JTable(SQLserver.finfadoa(),title);
        jTablePet2 = new JTable(SQLserver.findre(),title);
        jTableHeader = jTablePet.getTableHeader();
        jTableHeader2 = jTablePet2.getTableHeader();
        jTableHeader.setBounds(0,0,595,30);
        jTableHeader2.setBounds(605,0,595,30);
        jTablePet.setBounds(0,30,595,500);
        jTablePet2.setBounds(605,30,595,500);


        // 显示表头

        // 将JTable加入到带滚动条的面板中
        // jScrollPanedatasave.getViewport(jTablePet);

        jLabelTips = new JLabel(GetString.updatePetTips);
        jLabelTips.setBounds(10,550,400,30);
        jLabelTips.setFont(new Font("黑体",0,20));

        jLabelPnum = new JLabel(GetString.Pnum+":");
        jLabelPnum.setBounds(10,650,200,25);
        jLabelPnum.setFont(new Font("黑体",0,20));

        setPnum = new JTextField();
        setPnum.setBounds(210,650,600,25);
        setPnum.setBorder(myLineBorder);


        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\backlittle.png");
        ImageIcon image3 = new ImageIcon("src\\Res\\Img\\take.png");
        jButtonSaveAndRefresh = new JButton(image3);
        jButtonSaveAndRefresh.setBounds((WINDOW_WIDTH/4-image2.getIconWidth()/2),730,image2.getIconWidth()-10,image2.getIconHeight()-10);
        jButtonSaveAndRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SQLserver  sqLserver = new SQLserver();
                sqLserver.removeInfo(setPnum.getText());

                PetAdoApproval petAdoApproval = new PetAdoApproval();
                petAdoApproval.start(name);
                update.dispose();
            }
        });


        jButtontrueAndBack = new JButton(image2);
        jButtontrueAndBack.setBounds((WINDOW_WIDTH/4*3-image2.getIconWidth()/2),730,image2.getIconWidth()-10,image2.getIconHeight()-10);
        jButtontrueAndBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                update.dispose();

            }
        });
        panel_center.add(jButtonSaveAndRefresh);
        panel_center.add(jButtontrueAndBack);


        panel_center.add(jTableHeader);
        panel_center.add(jTableHeader2);
        panel_center.add(jTablePet2);

        panel_center.add(jTablePet);
        panel_center.add(jScrollPanedatasave);
        panel_center.add(setPnum);
        panel_center.add(jLabelTips);
        panel_center.add(jLabelPnum);
        update.add(panel_center,BorderLayout.CENTER);


        update.add(panel_center,BorderLayout.CENTER);







        update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        update.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}

