import Datebase.SQLserver;
import GUI.First;


import javax.swing.*;
import java.awt.*;

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
    }

}
