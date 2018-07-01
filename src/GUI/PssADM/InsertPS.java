package GUI.PssADM;


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

public class InsertPS extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 800;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JButton jButtontrueAndBack, jButtonSaveAndRefresh;
    JLabel jLabelTips;
    JTable jTablePet;
    JTableHeader jTableHeader;
    JScrollPane jScrollPanedatasave;


    JLabel setjLabel1, setjLabel2, setjLabel3, setjLabel4, setjLabel5;
    JTextField setjTextField1, setjTextField2, setjTextField3, setjTextField4, setjTextField5;


    public InsertPS() {


        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                InsertPS.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                InsertPS.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public void start(String name) {

        InsertPS update = new InsertPS();
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
        JPanel panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(900, 756));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        String[] data = SQLserver.getPSSdoInfo(name);

        // PSSnum,PSSnume,PSStel,PSSsex,PSSemail,PSSremarks,PSSidentity
        jTablePet = new JTable();
        jScrollPanedatasave = new JScrollPane();
        jScrollPanedatasave.setBounds(0, 0, 900, 400);


        // 定义表头
        String[] title = {GetString.PSnum, GetString.PSname, GetString.PSaddress, GetString.PStel, GetString.PSremarks};
        // 创建JTable
        jTablePet = new JTable(SQLserver.findPs(), title);
        jTableHeader = jTablePet.getTableHeader();
        jTableHeader.setBounds(0, 0, 900, 30);
        jTablePet.setBounds(0, 30, 900, 400);
        // 显示表头

        // 将JTable加入到带滚动条的面板中
        // jScrollPanedatasave.getViewport(jTablePet);


        jLabelTips = new JLabel(GetString.updateTips);
        jLabelTips.setBounds(10, 430, 400, 30);
        jLabelTips.setFont(new Font("黑体", 0, 20));


        setjLabel1 = new JLabel(GetString.PSnum);
        setjLabel1.setBounds(10, 465, 200, 25);
        setjLabel1.setFont(new Font("黑体", 0, 20));

        setjLabel2 = new JLabel(GetString.PSname);
        setjLabel2.setBounds(10, 500, 200, 25);
        setjLabel2.setFont(new Font("黑体", 0, 20));

        setjLabel3 = new JLabel(GetString.PSaddress + ":");
        setjLabel3.setBounds(10, 535, 200, 25);
        setjLabel3.setFont(new Font("黑体", 0, 20));


        setjLabel4 = new JLabel(GetString.PStel + ":");
        setjLabel4.setBounds(10, 570, 200, 25);
        setjLabel4.setFont(new Font("黑体", 0, 20));

        setjLabel5 = new JLabel(GetString.PSaddress + ":");
        setjLabel5.setBounds(10, 605, 200, 40);
        setjLabel5.setFont(new Font("黑体", 0, 20));


        setjTextField1 = new JTextField();
        setjTextField1.setBounds(210, 465, 600, 25);
        setjTextField1.setBorder(myLineBorder);

        setjTextField2 = new JTextField();
        setjTextField2.setBounds(210, 500, 600, 25);
        setjTextField2.setBorder(myLineBorder);

        setjTextField3 = new JTextField();
        setjTextField3.setBounds(210, 535, 600, 25);
        setjTextField3.setBorder(myLineBorder);


        setjTextField4 = new JTextField();
        setjTextField4.setBounds(210, 570, 600, 25);
        setjTextField4.setBorder(myLineBorder);

        setjTextField5 = new JTextField();
        setjTextField5.setBounds(210, 605, 600, 25);
        setjTextField5.setBorder(myLineBorder);


        panel_center.add(jLabelTips);


        panel_center.add(setjLabel1);
        panel_center.add(setjLabel2);
        panel_center.add(setjLabel3);
        panel_center.add(setjLabel4);
        panel_center.add(setjLabel5);


        panel_center.add(setjTextField1);
        panel_center.add(setjTextField2);
        panel_center.add(setjTextField3);
        panel_center.add(setjTextField4);
        panel_center.add(setjTextField5);


        panel_center.add(jTableHeader);
        panel_center.add(jTablePet);
        panel_center.add(jScrollPanedatasave);
        update.add(panel_center, BorderLayout.CENTER);
        //setjLabelPnum,setjLabelPname,setjLabelPtype,setjLabelPvarieties,setjLabelPage,setjLabelPsex,setjLabelPremarks;


        // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
        // setjTextFieldPsex,setjTextFieldPremarks;


        /**
         * 南部面板
         */
        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(900, 44));
        MyLineBorder myLineBorder2 = new MyLineBorder(new Color(192, 192, 192), 1, true);
        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\backlittle.png");
        ImageIcon image3 = new ImageIcon("src\\Res\\Img\\take.png");
        jButtonSaveAndRefresh = new JButton(image3);
        jButtonSaveAndRefresh.setBounds((WINDOW_WIDTH / 4 - image2.getIconWidth() / 2), 0, image2.getIconWidth() - 10, image2.getIconHeight() - 10);
        jButtonSaveAndRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pssNew(name);
                InsertPS insertPet = new InsertPS();
                insertPet.start(name);
                update.dispose();
            }
        });


        jButtontrueAndBack = new JButton(image2);
        jButtontrueAndBack.setBounds((WINDOW_WIDTH / 4 * 3 - image2.getIconWidth() / 2), 0, image2.getIconWidth() - 10, image2.getIconHeight() - 10);
        jButtontrueAndBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                update.dispose();

            }
        });
        panel_south.add(jButtonSaveAndRefresh);
        panel_south.add(jButtontrueAndBack);
        update.add(panel_south, BorderLayout.SOUTH);

        update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        update.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
    // setjTextFieldPsex,setjTextFieldPremarks;


    public void pssNew(String usernamue) {
        boolean sexFlag = false;


        String num = "\\w{0,20}";//号码要求20以下;
        boolean numFlag = setjTextField1.getText().matches(num);

        String name = "[\\u4e00-\\u9fa5]{0,10}";//姓名要求2-10;
        boolean nameFlag = setjTextField2.getText().matches(name);

        String adress = "[\\u4e00-\\u9fa5]{0,10}";//2-10;
        boolean typeFlag = setjTextField3.getText().matches(adress);

        String tel = "\\w{0,20}";//品种要求2-5
        boolean telFlag = setjTextField4.getText().matches(tel);


        String remarks = "[\\u4e00-\\u9fa5]{0,50}";//备注0-50
        boolean remarksFlag = setjTextField5.getText().matches(remarks);


        if (numFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.errnum20, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextField1.setText("");
        } else if (nameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.chineseErr110, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextField2.setText("");
        } else if (typeFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.chineseErr110, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextField3.setText("");
        } else if (telFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.errnum20, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextField4.setText("");
        } else if (remarksFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.remarkErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextField5.setText("");
        } else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();

            sqLserver.insertPs(usernamue, setjTextField1.getText(), setjTextField2.getText(), setjTextField3.getText(), setjTextField4.getText(), setjTextField5.getText());


            this.setjTextField1.setText("");
            this.setjTextField2.setText("");
            this.setjTextField3.setText("");
            this.setjTextField4.setText("");
            this.setjTextField5.setText("");


            // setjTextFieldPnum,setjTextFieldPname,setjTextFieldPtype,setjTextFieldPvarieties,setjTextFieldPage,
            // setjTextFieldPsex,setjTextFieldPremarks;


        }


    }
}

