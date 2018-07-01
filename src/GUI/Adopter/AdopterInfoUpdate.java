package GUI.Adopter;

import Datebase.SQLserver;
import GUI.Login.MyLineBorder;
import Res.Values.GetString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdopterInfoUpdate extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 700;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JButton jButtontrueAndBack,jButtonSaveAndRefresh;
    JLabel jLabelAnum,jLabelAname,jLabelAtel,jLabelAsex,jLabelAadress,jLabelAemail,jLabelAremarks,jLabelTips;
    JLabel setjLabelAname,setjLabelAtel,setjLabelAsex,setjLabelAadress,setjLabelAemail,setjLabelAremarks;
    JTextField setjTextFieldAname,setjTextFieldAtel,setjTextFieldAsex,setjTextFieldAadress,setjTextFieldAemail,setjTextFieldAremarks,setjTextFieldTips;




    public AdopterInfoUpdate(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                AdopterInfoUpdate.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                AdopterInfoUpdate.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }

    public  void  start(String name){

        AdopterInfoUpdate update = new AdopterInfoUpdate();
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
        panel_center.setPreferredSize(new Dimension(900,656));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);

        String[] data = SQLserver.getAdoInfo(name);


        jLabelAnum = new JLabel(GetString.Anum+":"+data[0]);
        jLabelAnum.setBounds(10,10 ,400,25);
        jLabelAnum.setFont(new Font("黑体",0,20));

        jLabelAname = new JLabel(GetString.Aname+":"+data[1]);
        jLabelAname.setBounds(410,10,400,25);
        jLabelAname.setFont(new Font("黑体",0,20));

        jLabelAtel = new JLabel(GetString.Atel+":"+data[2]);
        jLabelAtel.setBounds(10,35,400,25);
        jLabelAtel.setFont(new Font("黑体",0,20));

        jLabelAsex = new JLabel(GetString.Asex+":"+data[3]);
        jLabelAsex.setBounds(410,35,400,25);
        jLabelAsex.setFont(new Font("黑体",0,20));

        jLabelAadress = new JLabel(GetString.Aadress+":"+data[4]);
        jLabelAadress.setBounds(10,60,400,25);
        jLabelAadress.setFont(new Font("黑体",0,20));

        jLabelAemail = new JLabel(GetString.Aemail+":"+data[5]);
        jLabelAemail.setBounds(410,60,400,25);
        jLabelAemail.setFont(new Font("黑体",0,20));

        jLabelAremarks = new JLabel(GetString.Aremarks+":"+data[6]);
        jLabelAremarks.setBounds(10,85,800,40);
        jLabelAremarks.setFont(new Font("黑体",0,20));

        jLabelTips = new JLabel(GetString.updateTips);
        jLabelTips.setBounds(10,150,400,30);
        jLabelTips.setFont(new Font("黑体",0,20));



        setjLabelAname = new JLabel(GetString.Aname+":");
        setjLabelAname.setBounds(10,200,200,25);
        setjLabelAname.setFont(new Font("黑体",0,20));

        setjLabelAtel = new JLabel(GetString.Atel+":");
        setjLabelAtel.setBounds(10,250,200,25);
        setjLabelAtel.setFont(new Font("黑体",0,20));

        setjLabelAsex = new JLabel(GetString.Asex+":");
        setjLabelAsex.setBounds(10,300,200,25);
        setjLabelAsex.setFont(new Font("黑体",0,20));

        setjLabelAadress = new JLabel(GetString.Aadress+":");
        setjLabelAadress.setBounds(10,350,200,25);
        setjLabelAadress.setFont(new Font("黑体",0,20));

        setjLabelAemail = new JLabel(GetString.Aemail+":");
        setjLabelAemail.setBounds(10,400,200,25);
        setjLabelAemail.setFont(new Font("黑体",0,20));

        setjLabelAremarks = new JLabel(GetString.Aremarks+":");
        setjLabelAremarks.setBounds(10,450,200,40);
        setjLabelAremarks.setFont(new Font("黑体",0,20));


        setjTextFieldAname = new JTextField();
        setjTextFieldAname.setBounds(210,200,600,25);
        setjTextFieldAname.setBorder(myLineBorder);

        setjTextFieldAtel = new JTextField();
        setjTextFieldAtel.setBounds(210,250,600,25);
        setjTextFieldAtel.setBorder(myLineBorder);

        setjTextFieldAsex = new JTextField();
        setjTextFieldAsex.setBounds(210,300,600,25);
        setjTextFieldAsex.setBorder(myLineBorder);

        setjTextFieldAadress = new JTextField();
        setjTextFieldAadress.setBounds(210,350,600,25);
        setjTextFieldAadress.setBorder(myLineBorder);

        setjTextFieldAemail = new JTextField();
        setjTextFieldAemail.setBounds(210,400,600,25);
        setjTextFieldAemail.setBorder(myLineBorder);

        setjTextFieldAremarks = new JTextField();
        setjTextFieldAremarks.setBounds(210,450,600,25);
        setjTextFieldAremarks.setBorder(myLineBorder);



        panel_center.add(jLabelAnum);
        panel_center.add(jLabelAname);
        panel_center.add(jLabelAtel);
        panel_center.add(jLabelAsex);
        panel_center.add(jLabelAadress);
        panel_center.add(jLabelAemail);
        panel_center.add(jLabelAremarks);
        panel_center.add(jLabelTips);


        panel_center.add(setjLabelAname);
        panel_center.add(setjLabelAtel);
        panel_center.add(setjLabelAsex);
        panel_center.add(setjLabelAadress);
        panel_center.add(setjLabelAemail);
        panel_center.add(setjLabelAremarks);

        panel_center.add(setjTextFieldAname);
        panel_center.add(setjTextFieldAtel);
        panel_center.add(setjTextFieldAsex);
        panel_center.add(setjTextFieldAadress);
        panel_center.add(setjTextFieldAemail);
        panel_center.add(setjTextFieldAremarks);

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
                adopterNew(name);
                AdopterInfoUpdate adopterInfoUpdate = new AdopterInfoUpdate();
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

    public void adopterNew(String usernamue) {
        boolean sexFlag =false ;


        String name = "[\\u4e00-\\u9fa5]{2,5}";//姓名要求2-5;
        boolean nameFlag = setjTextFieldAname.getText().matches(name);

        String tel = "\\w{1,15}";//电话要求1-15
        boolean telFlag = setjTextFieldAtel.getText().matches(tel);

        String sex = "[\\u4e00-\\u9fa5]{1}";// "性别输入1个
        if ((setjTextFieldAsex.getText().matches("[\\u7537]")||setjTextFieldAsex.getText().matches("[\\u5973]"))&&setjTextFieldAsex.getText().matches(sex)) {
             sexFlag =true ;
        }else {
             sexFlag =false ;
        }


        String  adress = "[\\u4e00-\\u9fa5]{0,15}";// "请输入15位以下字符";
        boolean  adressFlag = setjTextFieldAadress.getText().matches(adress);

        String email = "\\w{0,15}";//"请输入15位以下字符";
        boolean emailFlag = setjTextFieldAemail.getText().matches(email);

        String remark = "[\\u4e00-\\u9fa5]{0,50}";//"请输入50位以下字符";
        boolean remarkFlag = setjTextFieldAremarks.getText().matches(remark);

        if (nameFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.nameErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAname.setText("");
        } else if (telFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.telErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAtel.setText("");
        } else if (adressFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.adressErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAadress.setText("");
        } else if (remarkFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.remarkErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAremarks.setText("");
        } else if (emailFlag == false) {
            JOptionPane.showMessageDialog(null, GetString.emailErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAemail.setText("");
        }
        else if (sexFlag ==false){
            JOptionPane.showMessageDialog(null, GetString.sexErr, GetString.TIP, JOptionPane.WARNING_MESSAGE);
            setjTextFieldAsex.setText("");
            }
            else {
            SQLserver sqLserver = new SQLserver();
            sqLserver.connectSQL();

            sqLserver.updateAdopter(usernamue,setjTextFieldAname.getText(), setjTextFieldAtel.getText(), setjTextFieldAsex.getText(), setjTextFieldAadress.getText(), setjTextFieldAemail.getText(),setjTextFieldAremarks.getText());


            this.setjTextFieldAname.setText("");
            this.setjTextFieldAtel.setText("");
            this.setjTextFieldAsex.setText("");
            this.setjTextFieldAadress.setText("");
            this.setjTextFieldAemail.setText("");
            this.setjTextFieldAremarks.setText("");



        }


    }
}

