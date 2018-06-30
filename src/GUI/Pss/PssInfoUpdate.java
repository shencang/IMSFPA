package GUI.Pss;

        import Datebase.SQLserver;
        import GUI.Login.MyLineBorder;
        import GUI.Login.Register;
        import Res.Values.GetString;

        import javax.swing.*;
        import javax.swing.table.JTableHeader;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;

public class PssInfoUpdate extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 700;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JButton jButtontrueAndBack,jButtonSaveAndRefresh;
    JLabel jLabelPssnum,jLabelPssname,jLabelPsstel,jLabelPsssex,jLabelPssadress,jLabelPssemail,jLabelPssremarks,jLabelTips,jLabelPssIdentity;
    JLabel setjLabelPssname,setjLabelPsstel,setjLabelPsssex,setjLabelPssadress,setjLabelPssemail,setjLabelPssremarks,setjLabelPssIdentity;
    JTextField setjTextFieldPssname,setjTextFieldPsstel,setjTextFieldPsssex,setjTextFieldPssadress,setjTextFieldPssemail,setjTextFieldPssremarks,setjTextFieldTips;




    public PssInfoUpdate(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                PssInfoUpdate.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                PssInfoUpdate.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public  void  start(String name){

        PssInfoUpdate update = new PssInfoUpdate();
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
        panel_center.setPreferredSize(new Dimension(900,656));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        String[] data = SQLserver.getPSSdoInfo(name);

       // PSSnum,PSSnume,PSStel,PSSsex,PSSemail,PSSremarks,PSSidentity
        jLabelPssnum = new JLabel(GetString.Pssnum+":"+data[0]);
        jLabelPssnum.setBounds(10,10 ,400,25);
        jLabelPssnum.setFont(new Font("黑体",0,20));

        jLabelPssname = new JLabel(GetString.Pssname+":"+data[1]);
        jLabelPssname.setBounds(410,10,400,25);
        jLabelPssname.setFont(new Font("黑体",0,20));

        jLabelPsstel = new JLabel(GetString.Psstel+":"+data[2]);
        jLabelPsstel.setBounds(10,35,400,25);
        jLabelPsstel.setFont(new Font("黑体",0,20));

        jLabelPsssex = new JLabel(GetString.Psssex+":"+data[3]);
        jLabelPsssex.setBounds(410,35,400,25);
        jLabelPsssex.setFont(new Font("黑体",0,20));

        jLabelPssemail = new JLabel(GetString.Pssemail+":"+data[4]);
        jLabelPssemail.setBounds(10,60,400,25);
        jLabelPssemail.setFont(new Font("黑体",0,20));

        jLabelPssremarks = new JLabel(GetString.Pssremarks+":"+data[5]);
        jLabelPssremarks.setBounds(410,60,800,40);
        jLabelPssremarks.setFont(new Font("黑体",0,20));

        jLabelPssIdentity = new JLabel(GetString.Pssidentity+":"+data[6]);
        jLabelPssIdentity.setBounds(10,85,800,40);
        jLabelPssIdentity.setFont(new Font("黑体",0,20));



        jLabelTips = new JLabel(GetString.updateTips);
        jLabelTips.setBounds(10,150,400,30);
        jLabelTips.setFont(new Font("黑体",0,20));



        setjLabelPssname = new JLabel(GetString.Pssname+":");
        setjLabelPssname.setBounds(10,200,200,25);
        setjLabelPssname.setFont(new Font("黑体",0,20));

        setjLabelPsstel = new JLabel(GetString.Psstel+":");
        setjLabelPsstel.setBounds(10,250,200,25);
        setjLabelPsstel.setFont(new Font("黑体",0,20));

        setjLabelPsssex = new JLabel(GetString.Psssex+":");
        setjLabelPsssex.setBounds(10,300,200,25);
        setjLabelPsssex.setFont(new Font("黑体",0,20));


        setjLabelPssemail = new JLabel(GetString.Pssemail+":");
        setjLabelPssemail.setBounds(10,350,200,25);
        setjLabelPssemail.setFont(new Font("黑体",0,20));

        setjLabelPssremarks = new JLabel(GetString.Pssremarks+":");
        setjLabelPssremarks.setBounds(10,400,200,40);
        setjLabelPssremarks.setFont(new Font("黑体",0,20));


        setjTextFieldPssname = new JTextField();
        setjTextFieldPssname.setBounds(210,200,600,25);
        setjTextFieldPssname.setBorder(myLineBorder);

        setjTextFieldPsstel = new JTextField();
        setjTextFieldPsstel.setBounds(210,250,600,25);
        setjTextFieldPsstel.setBorder(myLineBorder);

        setjTextFieldPsssex = new JTextField();
        setjTextFieldPsssex.setBounds(210,300,600,25);
        setjTextFieldPsssex.setBorder(myLineBorder);


        setjTextFieldPssemail = new JTextField();
        setjTextFieldPssemail.setBounds(210,350,600,25);
        setjTextFieldPssemail.setBorder(myLineBorder);

        setjTextFieldPssremarks = new JTextField();
        setjTextFieldPssremarks.setBounds(210,400,600,25);
        setjTextFieldPssremarks.setBorder(myLineBorder);



        panel_center.add(jLabelPssnum);
        panel_center.add(jLabelPssname);
        panel_center.add(jLabelPsstel);
        panel_center.add(jLabelPsssex);
        panel_center.add(jLabelPssemail);
        panel_center.add(jLabelPssremarks);
        panel_center.add(jLabelPssIdentity);
        panel_center.add(jLabelTips);


        panel_center.add(setjLabelPssname);
        panel_center.add(setjLabelPsstel);
        panel_center.add(setjLabelPsssex);
        panel_center.add(setjLabelPssemail);
        panel_center.add(setjLabelPssremarks);

        panel_center.add(setjTextFieldPssname);
        panel_center.add(setjTextFieldPsstel);
        panel_center.add(setjTextFieldPsssex);
        panel_center.add(setjTextFieldPssemail);
        panel_center.add(setjTextFieldPssremarks);

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
                PssInfoUpdate adopterInfoUpdate = new PssInfoUpdate();
                adopterInfoUpdate.start(name);
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

    public void pssNew(String usernamue) {
        boolean sexFlag =false ;


        String name = "[\\u4e00-\\u9fa5]{2,5}";//姓名要求2-5;
        boolean nameFlag = setjTextFieldPssname.getText().matches(name);

        String tel = "\\w{1,15}";//电话要求1-15
        boolean telFlag = setjTextFieldPsstel.getText().matches(tel);

        String sex = "[\\u4e00-\\u9fa5]{1}";// "性别输入1个
        if ((setjTextFieldPsssex.getText().matches("[\\u7537]")||setjTextFieldPsssex.getText().matches("[\\u5973]"))&&setjTextFieldPsssex.getText().matches(sex)) {
            sexFlag =true ;
        }else {
            sexFlag =false ;
        }



        String email = "\\w{0,15}";//"请输入15位以下字符";
        boolean emailFlag = setjTextFieldPssemail.getText().matches(email);

        String remark = "[\\u4e00-\\u9fa5]{0,50}";//"请输入50位以下字符";
        boolean remarkFlag = setjTextFieldPssremarks.getText().matches(remark);

        if (nameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.nameErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPssname.setText("");
        } else if (telFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.telErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPsstel.setText("");
        }  else if (remarkFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.remarkErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPssremarks.setText("");
        } else if (emailFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.emailErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPssemail.setText("");
        }
        else if (sexFlag ==false){
            JOptionPane.showMessageDialog(null, GetString.sexErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldPsssex.setText("");
        }
        else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();

            sqLserver.updatePss(usernamue,setjTextFieldPssname.getText(), setjTextFieldPsstel.getText(), setjTextFieldPsssex.getText(), setjTextFieldPssemail.getText(),setjTextFieldPssremarks.getText());


            this.setjTextFieldPssname.setText("");
            this.setjTextFieldPsstel.setText("");
            this.setjTextFieldPsssex.setText("");
            this.setjTextFieldPssemail.setText("");
            this.setjTextFieldPssremarks.setText("");



        }


    }
}

