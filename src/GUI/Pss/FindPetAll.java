
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

public class FindPetAll extends JFrame implements ActionListener {

    public int WINDOW_WIDTH = 900;
    public int WINDOW_HEIGHT = 700;
    public int LOCATION_X = 200;
    public int LOCATION_Y = 200;
    JTable jTablePet ;
    JTableHeader jTableHeader;
    JScrollPane  jScrollPanedatasave;
    JButton jButtontrueAndBack;
    String name;



    public FindPetAll(){




        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                LOCATION_X = e.getX();   //记录鼠标按下时的坐标
                LOCATION_Y = e.getY();
            }

            public void mouseClicked(MouseEvent e) {
                FindPetAll.this.setExtendedState(JFrame.ICONIFIED);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                int xOnScreen = e.getXOnScreen();
                int yOnScreen = e.getYOnScreen();
                int xNew = xOnScreen - LOCATION_X;
                int yNew = yOnScreen - LOCATION_Y;
                System.out.println("xx=" + xNew + "yy=" + yNew);
                FindPetAll.this.setLocation(xNew, yNew);  //设置拖拽后，窗口的位置
                System.out.println("查询框体正在移动");

            }
        });


    }






    public  void  start(){

        FindPetAll find = new FindPetAll();
        find.setTitle(GetString.messageTitle);
        find.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        find.setLocation(LOCATION_X, LOCATION_Y);
        find.setUndecorated(true);//边框不可见
        find.setResizable(true);//禁止改变大小
        find.setLayout(new BorderLayout());

        /**
         * 中部面板
         */
        JPanel  panel_center = new JPanel();
        panel_center.setLayout(null);
        panel_center.setPreferredSize(new Dimension(900,656));
        MyLineBorder myLineBorder = new MyLineBorder(new Color(192, 192, 192), 1, true);
        jTablePet = new JTable();
        jScrollPanedatasave = new JScrollPane();
        jScrollPanedatasave.setBounds(0,0,900,656);


        // 定义表头
        String[] title = {GetString.Pnum,GetString.Pname,GetString.Ptype,GetString.Pvarieties,GetString.Pstate,GetString.PregistrationTime,GetString.Page,GetString.Psex,GetString.Premarks};
        // 创建JTable
        jTablePet = new JTable(SQLserver.findPnoAll(),title);
        jTableHeader = jTablePet.getTableHeader();
        jTableHeader.setBounds(0,0,900,30);
        jTablePet.setBounds(0,30,900,656);
        // 显示表头

        // 将JTable加入到带滚动条的面板中
        // jScrollPanedatasave.getViewport(jTablePet);
        panel_center.add(jTableHeader);
        panel_center.add(jTablePet);
        panel_center.add(jScrollPanedatasave);
        find.add(panel_center,BorderLayout.CENTER);





        /**
         * 南部面板
         */
        JPanel panel_south = new JPanel();
        panel_south.setLayout(null);
        panel_south.setPreferredSize(new Dimension(900,44));
        //   MyLineBorder myLineBorder2 = new MyLineBorder(new Color(1, 11, 11), 1, true);
        ImageIcon image2 = new ImageIcon("src\\Res\\Img\\true&backbtn.png");
        jButtontrueAndBack = new JButton(image2);

        jButtontrueAndBack.setBounds((WINDOW_WIDTH/2-image2.getIconWidth()/2),0,image2.getIconWidth()-10,image2.getIconHeight()-10);
        jButtontrueAndBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                find.dispose();

            }
        });
        panel_south.add(jButtontrueAndBack);
        find.add(panel_south,BorderLayout.SOUTH);

        find.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        find.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
