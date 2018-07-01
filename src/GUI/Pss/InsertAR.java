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

public class InsertAR extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 700;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JButton jButtontrueAndBack,jButtonSaveAndRefresh;
    JLabel jLabelTips;
    JTable jTablePet ;
    JTableHeader jTableHeader;
    JScrollPane  jScrollPanedatasave;


    JLabel setjLabelPnum,setjLabelAnum;
    JTextField setjTextFieldPnum,setjTextFieldAum;



    public InsertAR(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                InsertAR.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                InsertAR.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public  void  start(String name){

        InsertAR update = new InsertAR();
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
        panel_center.setPreferredSize(new Dimension(900,400));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        String[] data = SQLserver.getPSSdoInfo(name);

        jTablePet = new JTable();
        jScrollPanedatasave = new JScrollPane();
        jScrollPanedatasave.setBounds(0,0,900,400);



        // 定义表头
        String[] title = {GetString.Pnum,GetString.Anum,GetString.AAdate};
        // 创建JTable
        jTablePet = new JTable(SQLserver.findre(),title);
        jTableHeader = jTablePet.getTableHeader();
        jTableHeader.setBounds(0,0,900,30);
        jTablePet.setBounds(0,30,900,400);
        // 显示表头

        // 将JTable加入到带滚动条的面板中
        // jScrollPanedatasave.getViewport(jTablePet);


        jLabelTips = new JLabel(GetString.updateTips);
        jLabelTips.setBounds(10,465,400,30);
        jLabelTips.setFont(new Font("黑体",0,20));



        setjLabelPnum = new JLabel(GetString.Pnum+":");
        setjLabelPnum.setBounds(10,500,200,25);
        setjLabelPnum.setFont(new Font("黑体",0,20));

        setjLabelAnum = new JLabel(GetString.Anum+":");
        setjLabelAnum.setBounds(10,535,200,25);
        setjLabelAnum.setFont(new Font("黑体",0,20));



        setjTextFieldPnum = new JTextField();
        setjTextFieldPnum.setBounds(210,500,600,25);
        setjTextFieldPnum.setBorder(myLineBorder);

        setjTextFieldAum = new JTextField();
        setjTextFieldAum.setBounds(210,535,600,25);
        setjTextFieldAum.setBorder(myLineBorder);



        panel_center.add(jLabelTips);
        panel_center.add(setjLabelPnum);
        panel_center.add(setjLabelAnum);
        panel_center.add(setjTextFieldPnum);
        panel_center.add(setjTextFieldAum);


        panel_center.add(jTableHeader);
        panel_center.add(jTablePet);
        panel_center.add(jScrollPanedatasave);
        update.add(panel_center,BorderLayout.CENTER);




        /**
         * 南部面板
         */
        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(900,44));
        MyLineBorder myLineBorder2 = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\backlittle.png");
        ImageIcon image3 = new ImageIcon("src\\Res\\Img\\take.png");
        jButtonSaveAndRefresh = new JButton(image3);
        jButtonSaveAndRefresh.setBounds((WINDOW_WIDTH/4-image2.getIconWidth()/2),0,image2.getIconWidth()-10,image2.getIconHeight()-10);
        jButtonSaveAndRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pssNew(name);
                InsertAR insertAR = new InsertAR();
                insertAR.start(name);
                update.dispose();
            }
        });


        jButtontrueAndBack = new JButton(image2);
        jButtontrueAndBack.setBounds((WINDOW_WIDTH/4*3-image2.getIconWidth()/2),0,image2.getIconWidth()-10,image2.getIconHeight()-10);
        jButtontrueAndBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                update.dispose();

            }
        });
        panel_south.add(jButtonSaveAndRefresh);
        panel_south.add(jButtontrueAndBack);
        update.add(panel_south,BorderLayout.SOUTH);

        update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        update.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
    // setjTextFieldPsex,setjTextFieldPremarks;


    public void pssNew(String usernamue) {



        String pnum = "\\w{0,20}";//号码要求20以下;
        boolean pnumFlag = setjTextFieldPnum.getText().matches(pnum);

        String anum = "\\w{6,18}";//号码要求20以下;
        boolean anameFlag = setjTextFieldAum.getText().matches(anum);



        if (pnumFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.errPnum, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPnum.setText("");
        } else if (anameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.errAnum, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAum.setText("");
        }
        else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();
            sqLserver.insertAR(usernamue,setjTextFieldPnum.getText(),setjTextFieldAum.getText());


            this.setjTextFieldPnum.setText("");
            this.setjTextFieldAum.setText("");


            // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
            // setjTextFieldPsex,setjTextFieldPremarks;



        }


    }
}

