package Datebase;

import GUI.Login.GeText;
import GUI.Login.InitSysLogin;
import GUI.Login.Register;
import Res.Values.GetString;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.sql.*;

public class SQLserver {
    Connection ct;
    PreparedStatement ps;
    ResultSet rs;
    String user,pwd;
    String rootID="roots",rootpassword="12345";
    Connection cons;
    Statement stmt;//创建SQL命令对象
    String URL;
    String username,password,name,tel,email;



    public void getUserRegis(String username,String password,String name,String tel,String email){
        this.username=username;
        this.password=password;
        this.name=name;
        this.tel=tel;
        this.email=email;

    }

    //注册用户的方法
    public void UserRegis(String username,String password,String name,String tel,String email){
        System.out.println(username+" "+password+" "+name+" "+tel+" "+email+" ");
        try {

            if (name.length()==5){
                System.out.println(username+" "+password+" "+name+" "+tel+" "+email+" ");
                ps = ct.prepareStatement("insert into PetShopStaff values(?,?,?,?,?,?,?,?)");
                ps.setString(1,username);
                ps.setString(7,password);
                ps.setString(2,name);
                ps.setString(5,tel);
                ps.setString(6,email);
            }
            if (name.length()>=6&&name.length()<=18){
                System.out.println(username+" "+password+" "+name+" "+tel+" "+email+" ");
                ps = ct.prepareStatement("insert into Adopter values(?,?,?,?,?,?,?,?)");
                ps.setString(1,username);
                ps.setString(7,password);
                ps.setString(2,name);
                ps.setString(3,tel);
                ps.setString(6,email);
            }
            //执行
            int i=ps.executeUpdate();
            if(i==1) {
                JOptionPane.showMessageDialog(null, GetString.registerYes,GetString.TIP,JOptionPane.WARNING_MESSAGE);

            }else
            {
                JOptionPane.showMessageDialog(null, GetString.registerNo,GetString.TIP,JOptionPane.ERROR_MESSAGE);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }



    //连接验证
    public void connectSQL(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=IMSFPA";
            Connection con = DriverManager.getConnection(url,"roots","12345");
            System.out.println("数据库连接成功");
            URL=url;
            con.close();
        }
        catch(Exception e) {
            System.out.println("数据库连接失败\n" + e.toString());
        }
    }

    //登录验证方法
    public Boolean SQLverify(String users,String password){
        try {

            ct=DriverManager.getConnection(URL, rootID, rootpassword);
            if (users.length()==5){
                ps=ct.prepareStatement("select * from PetShopStaff where PSSnum=? and PSSpassword=? ");
                ps.setString(1,users);
                ps.setString(2,password);
            }
            if (users.length()>=6&&name.length()<=18){

                ps=ct.prepareStatement("select * from Adopter where Anum=? and Apassword=? ");
                ps.setString(1,users);
                ps.setString(2,password);
            }

            // ResultSet结果集,可以把ResultSet理解成返回一张表行的结果集
            rs =ps.executeQuery();
            if(rs.next()){
                user = rs.getString(1);
                pwd = rs.getString(2);
                JOptionPane.showMessageDialog(null,"登陆成功","尚未完工",JOptionPane.WARNING_MESSAGE);
                System.out.println("成功获取到密码和用户名from数据库");
                System.out.println(user + "\t" + pwd + "\t");
                return  true;


            }
            else {
                JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入", "提示消息", JOptionPane.ERROR_MESSAGE);


            }


        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("登录出错");
        }


        return false;
    }

    //登录注册验证方法
    public void RegisterVerify(String name){
        try {
            ct=DriverManager.getConnection(URL, rootID, rootpassword);
            if (name.length()==5){
                ps = ct.prepareStatement("select * from PetShopStaff where PSSnum=?");
            }
            if (name.length()>=6&&name.length()<=18){
                ps = ct.prepareStatement("select * from Adopter where Anum=?");
            }
            ps.setString(1,name);
            rs =ps.executeQuery();
            if (rs.next()){
                JOptionPane.showMessageDialog(null,GetString.SQLusernameErr,GetString.TIP,JOptionPane.WARNING_MESSAGE);
            }
            else {

                this.UserRegis(username,password,this.name,tel,email);

            }

        }catch (SQLException E){
            E.printStackTrace();
        }


    }

    public SQLserver(){

    }
}
