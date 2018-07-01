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

public class InsertPet extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 800;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JButton jButtontrueAndBack,jButtonSaveAndRefresh;
    JLabel jLabelTips;
    JTable jTablePet ;
    JTableHeader jTableHeader;
    JScrollPane  jScrollPanedatasave;


    JLabel setjLabelPnum,setjLabelPname,setjLabelPtype,setjLabelPvarieties,setjLabelPage,setjLabelPsex,setjLabelPremarks;
    JTextField setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,setjTextFieldPsex,setjTextFieldPremarks;




    public InsertPet(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                InsertPet.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                InsertPet.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public  void  start(String name){

        InsertPet update = new InsertPet();
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
        panel_center.setPreferredSize(new Dimension(900,756));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        String[] data = SQLserver.getPSSdoInfo(name);

        // PSSnum,PSSnume,PSStel,PSSsex,PSSemail,PSSremarks,PSSidentity
        jTablePet = new JTable();
        jScrollPanedatasave = new JScrollPane();
        jScrollPanedatasave.setBounds(0,0,900,400);



        // 定义表头
        String[] title = {GetString.Pnum,GetString.Pname,GetString.Ptype,GetString.Pvarieties,GetString.Pstate,GetString.PregistrationTime,GetString.Page,GetString.Psex,GetString.Premarks};
        // 创建JTable
        jTablePet = new JTable(SQLserver.findPnoAll(),title);
        jTableHeader = jTablePet.getTableHeader();
        jTableHeader.setBounds(0,0,900,30);
        jTablePet.setBounds(0,30,900,400);
        // 显示表头

        // 将JTable加入到带滚动条的面板中
        // jScrollPanedatasave.getViewport(jTablePet);


        jLabelTips = new JLabel(GetString.updateTips);
        jLabelTips.setBounds(10,430,400,30);
        jLabelTips.setFont(new Font("黑体",0,20));



        setjLabelPnum = new JLabel(GetString.Pnum+":");
        setjLabelPnum.setBounds(10,465,200,25);
        setjLabelPnum.setFont(new Font("黑体",0,20));

        setjLabelPname = new JLabel(GetString.Pname+":");
        setjLabelPname.setBounds(10,500,200,25);
        setjLabelPname.setFont(new Font("黑体",0,20));

        setjLabelPtype = new JLabel(GetString.Ptype+":");
        setjLabelPtype.setBounds(10,535,200,25);
        setjLabelPtype.setFont(new Font("黑体",0,20));


        setjLabelPvarieties = new JLabel(GetString.Pvarieties+":");
        setjLabelPvarieties.setBounds(10,570,200,25);
        setjLabelPvarieties.setFont(new Font("黑体",0,20));

        setjLabelPage = new JLabel(GetString.Page+":");
        setjLabelPage.setBounds(10,605,200,40);
        setjLabelPage.setFont(new Font("黑体",0,20));

        setjLabelPsex = new JLabel(GetString.Psex+":");
        setjLabelPsex.setBounds(10,640,200,40);
        setjLabelPsex.setFont(new Font("黑体",0,20));

        setjLabelPremarks = new JLabel(GetString.Premarks+":");
        setjLabelPremarks.setBounds(10,675,200,40);
        setjLabelPremarks.setFont(new Font("黑体",0,20));




        setjTextFieldPnum = new JTextField();
        setjTextFieldPnum.setBounds(210,465,600,25);
        setjTextFieldPnum.setBorder(myLineBorder);

        setjTextFieldPname = new JTextField();
        setjTextFieldPname.setBounds(210,500,600,25);
        setjTextFieldPname.setBorder(myLineBorder);

        setjTextFieldPtype = new JTextField();
        setjTextFieldPtype.setBounds(210,535,600,25);
        setjTextFieldPtype.setBorder(myLineBorder);


        setjTextFieldPvarieties = new JTextField();
        setjTextFieldPvarieties.setBounds(210,570,600,25);
        setjTextFieldPvarieties.setBorder(myLineBorder);

        setjTextFieldPage = new JTextField();
        setjTextFieldPage.setBounds(210,605,600,25);
        setjTextFieldPage.setBorder(myLineBorder);

        setjTextFieldPsex = new JTextField();
        setjTextFieldPsex.setBounds(210,640,600,25);
        setjTextFieldPsex.setBorder(myLineBorder);

        setjTextFieldPremarks = new JTextField();
        setjTextFieldPremarks.setBounds(210,675,600,25);
        setjTextFieldPremarks.setBorder(myLineBorder);





        panel_center.add(jLabelTips);


        panel_center.add(setjLabelPnum);
        panel_center.add(setjLabelPname);
        panel_center.add(setjLabelPtype);
        panel_center.add(setjLabelPvarieties);
        panel_center.add(setjLabelPage);

        panel_center.add(setjLabelPsex);
        panel_center.add(setjLabelPremarks);

        panel_center.add(setjTextFieldPnum);
        panel_center.add(setjTextFieldPname);
        panel_center.add(setjTextFieldPtype);
        panel_center.add(setjTextFieldPvarieties);
        panel_center.add(setjTextFieldPage);
        panel_center.add(setjTextFieldPsex);
        panel_center.add(setjTextFieldPremarks);

        panel_center.add(jTableHeader);
        panel_center.add(jTablePet);
        panel_center.add(jScrollPanedatasave);
        update.add(panel_center,BorderLayout.CENTER);
        //setjLabelPnum,setjLabelPname,setjLabelPtype,setjLabelPvarieties,setjLabelPage,setjLabelPsex,setjLabelPremarks;


        // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
        // setjTextFieldPsex,setjTextFieldPremarks;



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
                InsertPet insertPet = new InsertPet();
                insertPet.start(name);
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
        boolean sexFlag =false ;



        String num = "\\w{0,20}";//号码要求20以下;
        boolean numFlag = setjTextFieldPnum.getText().matches(num);

        String name = "[\\u4e00-\\u9fa5]{1,5}";//姓名要求2-5;
        boolean nameFlag = setjTextFieldPname.getText().matches(name);

        String type = "[\\u4e00-\\u9fa5]{1,5}";//种类要求2-5;
        boolean typeFlag = setjTextFieldPtype.getText().matches(type);

        String Pvarieties = "[\\u4e00-\\u9fa5]{1,5}";//品种要求2-5
        boolean PvarietiesFlag = setjTextFieldPvarieties.getText().matches(Pvarieties);

        String Page = "[0-9]{0,4}";//年龄要求4位含有以下
        boolean pageFlag = setjTextFieldPage.getText().matches(Page);


        String Psex ="[\\u4e00-\\u9fa5]{1}";//性别输入1个
        System.out.println(setjTextFieldPsex.getText().matches("[\\u516c]"));
        System.out.println(setjTextFieldPsex.getText()=="[\\u516c]");
        System.out.println(setjTextFieldPsex.getText()=="公");


        if (setjTextFieldPsex.getText().matches("[\\u516c]")||setjTextFieldPsex.getText().matches("[\\u6bcd]")||setjTextFieldPsex.getText().matches("[\\u65e0]")){
            sexFlag =true ;
        }else {
            sexFlag =false ;
        }

        String Premarks = "[\\u4e00-\\u9fa5]{0,50}";//备注0-50
        boolean remarksFlag = setjTextFieldPremarks.getText().matches(Premarks);



        if (numFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.errPnum, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPnum.setText("");
        } else if (nameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.chineseErr15, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPname.setText("");
        }  else if (typeFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.chineseErr15, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPtype.setText("");
        } else if (PvarietiesFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.chineseErr15, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPvarieties.setText("");
        }
        else if (pageFlag ==false){
            JOptionPane.showMessageDialog(null, GetString.agePnum, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPage.setText("");
        }
        else if (sexFlag ==false){
            JOptionPane.showMessageDialog(null, GetString.psexErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPsex.setText("");
        }
        else if (remarksFlag ==false){
            JOptionPane.showMessageDialog(null, GetString.remarkErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPremarks.setText("");

        }

        else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();

            sqLserver.insertPet(usernamue,setjTextFieldPnum.getText(), setjTextFieldPname.getText(), setjTextFieldPtype.getText(), setjTextFieldPvarieties.getText(),setjTextFieldPage.getText(),setjTextFieldPsex.getText(),setjTextFieldPremarks.getText());


            this.setjTextFieldPnum.setText("");
            this.setjTextFieldPname.setText("");
            this.setjTextFieldPtype.setText("");
            this.setjTextFieldPvarieties.setText("");
            this.setjTextFieldPage.setText("");
            this.setjTextFieldPsex.setText("");
            this.setjTextFieldPremarks.setText("");

            // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
            // setjTextFieldPsex,setjTextFieldPremarks;



        }


    }
}

