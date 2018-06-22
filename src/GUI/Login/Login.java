package GUI.Login;

import Res.Values.GetString;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener{


    JFrame frame = new JFrame();
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;


    @Override
    public void actionPerformed(ActionEvent e) {

        System.out.println("启动了应用");
//        JFrame frame = new JFrame(GetString.logintitle);
//        frame.setContentPane(new Login().panel1);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
       //  initSysLogin =
         InitSysLogin initSysLogin=new InitSysLogin();
         initSysLogin.initLogin();
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}