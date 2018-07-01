import Datebase.SQLserver;
import GUI.First;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;

public  class Hello {
    public static void main(String[] args){


        //First.logo();
        // First.welcome();
        //Example.example1();
        //Example example = new Example();
        //example.setTitle("CHULISHIJIAN");
        //example.setBounds(100,100,310,260);


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                First first = new First();
                first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // first.setVisible(true);

            }
        });
        SQLserver sqLserver = new SQLserver();
        sqLserver.connectSQL();
        connectSQL();


    }

    //连接验证
    public static void connectSQL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://123.206.85.173:1433;databaseName=master";
            Connection con = DriverManager.getConnection(url, "webDB", "Qq1145534369");
            System.out.println("数据库连接成功");

            con.close();
        } catch (Exception e) {
            System.out.println("数据库连接失败\n" + e.toString());
        }
    }

}
