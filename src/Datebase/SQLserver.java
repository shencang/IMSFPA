package Datebase;

import GUI.Adopter.AdopterMain;
import GUI.Login.GeText;
import GUI.Login.InitSysLogin;
import GUI.Login.Register;
import Res.Values.GetString;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SQLserver {
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

        PreparedStatement ps = null;
        Connection conn = null;
        String sqlS = "insert into PetShopStaff values(?,?,'','',?,?,?,'')";
        String sqlA = "insert into Adopter      values(?,?,?,'','',?,?,'')";
        try {
             conn = DriverManager.getConnection(URL, rootID, rootpassword);
            if (username.length()==5){
                System.out.println(username+" "+password+" "+name+" "+tel+" "+email+" ");
                ps = conn.prepareStatement(sqlS);
                ps.setString(1,username);
                ps.setString(2,name);
                ps.setString(3,tel);
                ps.setString(4,email);
                ps.setString(5,password);
                ps.execute();
                JOptionPane.showMessageDialog(null, GetString.registerYes,GetString.TIP,JOptionPane.WARNING_MESSAGE);
            }
            if (username.length()>=6&&username.length()<=18){
                System.out.println(username+" "+password+" "+name+" "+tel+" "+email+" ");
                ps = conn.prepareStatement(sqlA);
                ps.setString(1,username);
                ps.setString(2,name);
                ps.setString(3,tel);
                ps.setString(4,email);
                ps.setString(5,password);
                ps.execute();
                JOptionPane.showMessageDialog(null, GetString.registerYes,GetString.TIP,JOptionPane.WARNING_MESSAGE);
                conn.close();
            }
            //执行
        }catch (SQLException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, GetString.registerNo,GetString.TIP,JOptionPane.ERROR_MESSAGE);
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
        Connection conLogin=null;
        PreparedStatement psLogin = null;
        ResultSet rsLogin= null;
        try {

            conLogin=DriverManager.getConnection(URL, rootID, rootpassword);
            if (users.length()==5){
                psLogin=conLogin.prepareStatement("select * from PetShopStaff where PSSnum=? and PSSpassword=? ");
                psLogin.setString(1,users);
                psLogin.setString(2,password);
                rsLogin =psLogin.executeQuery();
            }
            if (users.length()>=6&&users.length()<=18){

                psLogin=conLogin.prepareStatement("select * from Adopter where Anum=? and Apassword=? ");
                psLogin.setString(1,users);
                psLogin.setString(2,password);
                rsLogin =psLogin.executeQuery();
            }

            // ResultSet结果集,可以把ResultSet理解成返回一张表行的结果集

            if( rsLogin.next()){
                user =  rsLogin.getString(1);
                pwd =  rsLogin.getString(2);
              //  JOptionPane.showMessageDialog(null,"登陆成功","尚未完工",JOptionPane.WARNING_MESSAGE);
                if (users.length()>=6&&users.length()<=18){
                    AdopterMain adopterMain =new AdopterMain();
                    adopterMain.adopeterStart(users);
                }
                System.out.println("成功获取到密码和用户名from数据库");
                System.out.println(user + "\t" + pwd + "\t");


                conLogin.close();
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
        Connection conLoginR= null;
        ResultSet reLoginR =null;
        PreparedStatement psLoginR = null;

        try {
            conLoginR=DriverManager.getConnection(URL, rootID, rootpassword);
            if (name.length()==5){
                psLoginR = conLoginR.prepareStatement("select * from PetShopStaff where PSSnum=?");
            }
            if (name.length()>=6&&name.length()<=18){
                psLoginR = conLoginR.prepareStatement("select * from Adopter where Anum=?");
            }
            psLoginR.setString(1,name);
            reLoginR =psLoginR.executeQuery();
            if (reLoginR.next()){
                JOptionPane.showMessageDialog(null,GetString.SQLusernameErr,GetString.TIP,JOptionPane.WARNING_MESSAGE);
            }
            else {

                UserRegis(this.username,this.password,this.name,this.tel,this.email);
            }
            conLoginR.close();

        }catch (SQLException E){
            E.printStackTrace();
        }


    }

    public SQLserver(){

    }
}
