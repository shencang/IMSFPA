package Datebase;

import GUI.Adopter.AdopterMain;
import GUI.Login.GeText;
import GUI.Login.InitSysLogin;
import GUI.Login.Register;
import GUI.Pss.PssMain;
import Other.GetTime;
import Res.Values.GetString;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SQLserver {
    String user,pwd;
    static String rootID="roots",rootpassword="12345";
    Connection cons;
    Statement stmt;//创建SQL命令对象
    static String URL ="jdbc:sqlserver://127.0.0.1:1433;databaseName=IMSFPA";
    String username,password,name,tel,email;

    public SQLserver(){
    }


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
                if(users.length()==5){
                    PssMain pssMain = new PssMain();
                    pssMain.pssStart(users);
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
    public static Object[][] findAP(String name){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select AdoptiveRel.Anum,Aname,AdoptiveRel.Pnum,Pname,ARDate from AdoptiveRel,Adopter,Pet where AdoptiveRel.Anum = Adopter.Anum AND  AdoptiveRel.Pnum = Pet.Pnum AND AdoptiveRel.Anum=?;";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,name);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][5];
            count = 0;
            while (reLoginAP.next()) {
                info[count][0] = reLoginAP.getString("Anum");
                info[count][1] = reLoginAP.getString("Aname");
                info[count][2] = reLoginAP.getString("Pnum");
                info[count][3] = reLoginAP.getString("Pname");
                info[count][4] = reLoginAP.getDate("ARDate");
                count++;
            }
            date = info;

        }
    catch(SQLException sqle){
                JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
            }
        return date;
    }

    public static Object[][] findPno(){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select * from  Pet  where Pstate != '已领养'";
            psLoginAP = conLoginAP.prepareStatement(sql);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][9];
            count = 0;
            while (reLoginAP.next()) {
                info[count][0] = reLoginAP.getString("Pnum");
                info[count][1] = reLoginAP.getString("Pname");
                info[count][2] = reLoginAP.getString("Ptype");
                info[count][3] = reLoginAP.getString("Pvarieties");
                info[count][4] = reLoginAP.getString("Pstate");
                info[count][5] = reLoginAP.getDate("PregistrationTime");
                info[count][6] = reLoginAP.getString("Page");
                info[count][7] = reLoginAP.getString("Psex");
                info[count][8] = reLoginAP.getString("Premarks");
                count++;
            }
            date = info;

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }
    public static Object[][] finfadoa(String name){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select * from IMSFPA.dbo.AdoApplication where Anum =?";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,name);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][3];
            count = 0;
            while (reLoginAP.next()) {
                info[count][0] = reLoginAP.getString("Pnum");
                info[count][1] = reLoginAP.getString("Anum");
                info[count][2] = reLoginAP.getDate("AADate");

                count++;
            }
            date = info;

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }

    public static Object[][] findAdoInfo(String name){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select Anum,Aname,Atel,Asex,Aadress,Aemail,Aremarks from Adopter where Anum = ? \n";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,name);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][7];
            count = 0;
            while (reLoginAP.next()) {
                info[count][0] = reLoginAP.getString("Anum");
                info[count][1] = reLoginAP.getString("Aname");
                info[count][2] = reLoginAP.getString("Atel");
                info[count][3] = reLoginAP.getString("Asex");
                info[count][4] = reLoginAP.getString("Aadress");
                info[count][5] = reLoginAP.getString("Aemail");
                info[count][6] = reLoginAP.getString("Aremarks");
                count++;
            }
            date = info;

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }

    public static String[] getAdoInfo(String name){
       String[] data=new String[7];
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select Anum,Aname,Atel,Asex,Aadress,Aemail,Aremarks from Adopter where Anum = ? \n";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,name);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            while (reLoginAP.next()) {
                data[0] = reLoginAP.getString("Anum");
                data[1] = reLoginAP.getString("Aname");
                data[2] = reLoginAP.getString("Atel");
                data[3] = reLoginAP.getString("Asex");
                data[4] = reLoginAP.getString("Aadress");
                data[5] = reLoginAP.getString("Aemail");
                data[6] = reLoginAP.getString("Aremarks");
            }



        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return  data;
    }


    public static void updatePet(){
        Connection con = null;
        ResultSet rs;
        try {
            con = DriverManager.getConnection(URL, rootID, rootpassword);
            String sql = "update Pet set Pstate = '已领养' where Pet.Pnum IN (Select Pnum from AdoptiveRel)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("领养关系已更新");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateAdopter(String name,String text, String text1, String text2, String text3, String text4, String text5) {

        Connection cons = null;
        ResultSet rs;
        try {
            cons = DriverManager.getConnection(URL, rootID, rootpassword);
            String sql = "update Adopter set Aname=?,Atel=?,Asex=?,Aadress=?,Aemail=?,Aremarks=? where Anum=?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1,text);
            ps.setString(2,text1);
            ps.setString(3,text2);
            ps.setString(4,text3);
            ps.setString(5,text4);
            ps.setString(6,text5);
            ps.setString(7,name);
            ps.executeUpdate();
            System.out.println("个人已更新");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void updatePss(String name,String text, String text1, String text2, String text3, String text4) {

        Connection cons = null;
        ResultSet rs;
        try {
            cons = DriverManager.getConnection(URL, rootID, rootpassword);
            String sql = "update PetShopStaff set PSSnume=?,PSStel=?,PSSsex=?,PSSemail=?,PSSremarks=? where PSSnum=?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setString(1,text);
            ps.setString(2,text1);
            ps.setString(3,text2);
            ps.setString(4,text3);
            ps.setString(5,text4);
            ps.setString(6,name);
            ps.executeUpdate();
            System.out.println("个人已更新");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public  void adoAppIn(String username ,String petnum){

        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        int result = 0;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select Pnum from  Pet  where Pnum = ?";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,petnum);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println("计算器:"+count);
            String[] pnum=new String[count+1];
            reLoginAP = psLoginAP.executeQuery();
            count = 0;
            while (reLoginAP.next()) {
                pnum[count] = reLoginAP.getString("Pnum");

                count++;
            }
            System.out.println(pnum[0]);
            if (pnum[0]==null){
                JOptionPane.showMessageDialog(null,"没有找到该宠物，请重新检查输入！","错误",JOptionPane.ERROR_MESSAGE);

            }else {
                GetTime getTime = new GetTime();
                String time =getTime.getYear()+"."+getTime.getMmonth()+"."+getTime.getDate();

                String sql2 = "insert into AdoApplication values(?,?,?)";
                psLoginAP = conLoginAP.prepareStatement(sql2);
                psLoginAP.setString(1,petnum);
                psLoginAP.setString(2,username);
                psLoginAP.setString(3,time);

                reLoginAP = psLoginAP.getResultSet();
                result = psLoginAP.executeUpdate();

                }
                if (result ==  1){
                System.out.println("查入成功");
                }
        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

    }
    public  void adoAppDele(String username ,String petnum){

        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        int result = 0;
        try {
            //查询
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select Pnum from  Pet  where Pnum = ?";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,petnum);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println("计算器:"+count);
            String[] pnum=new String[count+1];
            reLoginAP = psLoginAP.executeQuery();
            count = 0;
            while (reLoginAP.next()) {
                pnum[count] = reLoginAP.getString("Pnum");

                count++;
            }
            System.out.println(pnum[0]);
            if (pnum[0]==null){
                JOptionPane.showMessageDialog(null,"没有找到该宠物，请重新检查输入！","错误",JOptionPane.ERROR_MESSAGE);


            }else {
                System.out.println("删除");
                GetTime getTime = new GetTime();
               // String time =getTime.getYear()+"."+getTime.getMmonth()+"."+getTime.getDate();

                String sql2 = "delete from AdoApplication where Pnum=?";
                psLoginAP = conLoginAP.prepareStatement(sql2);
                psLoginAP.setString(1,petnum);


                reLoginAP = psLoginAP.getResultSet();
                result = psLoginAP.executeUpdate();

            }
            if (result ==  1){
                System.out.println("删除成功");
            }
        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

    }

    public static Object[][] findAnoAll(){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select * from  Adopter  ";
            psLoginAP = conLoginAP.prepareStatement(sql);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][7];
            count = 0;
            while (reLoginAP.next()) {

                info[count][0] = reLoginAP.getString("Anum");
                info[count][1] = reLoginAP.getString("Aname");
                info[count][2] = reLoginAP.getString("Atel");
                info[count][3] = reLoginAP.getString("Asex");
                info[count][4] = reLoginAP.getString("Aadress");
                info[count][5] = reLoginAP.getString("Aemail");
                info[count][6] = reLoginAP.getString("Aremarks");

                count++;
                System.out.println("查询全部中");
            }
            date = info;

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }



    public static Object[][] findPnoAll(){
        Object[][]  date=null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select * from  Pet  ";
            psLoginAP = conLoginAP.prepareStatement(sql);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println(count);
            reLoginAP = psLoginAP.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][9];
            count = 0;
            while (reLoginAP.next()) {

                info[count][0] = reLoginAP.getString("Pnum");
                info[count][1] = reLoginAP.getString("Pname");
                info[count][2] = reLoginAP.getString("Ptype");
                info[count][3] = reLoginAP.getString("Pvarieties");
                info[count][4] = reLoginAP.getString("Pstate");
                info[count][5] = reLoginAP.getDate("PregistrationTime");
                info[count][6] = reLoginAP.getString("Page");
                info[count][7] = reLoginAP.getString("Psex");
                info[count][8] = reLoginAP.getString("Premarks");
                count++;
                System.out.println("查询全部中");
            }
            date = info;

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }

    public static String[] getPSSdoInfo(String name){
        String[] data=new String[7];
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select PSSnum,PSSnume,PSStel,PSSsex,PSSemail,PSSremarks,PSSidentity from PetShopStaff where PSSnum = ? \n";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,name);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            while (reLoginAP.next()) {
                data[0] = reLoginAP.getString("PSSnum");
                data[1] = reLoginAP.getString("PSSnume");
                data[2] = reLoginAP.getString("PSStel");
                data[3] = reLoginAP.getString("PSSsex");
                data[4] = reLoginAP.getString("PSSemail");
                data[5] = reLoginAP.getString("PSSremarks");
                data[6] = reLoginAP.getString("PSSidentity");
            }



        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
        return  data;
    }

    public static   Object[][] adoPsInfo(String username){

        Object[][] data= null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        int result = 0;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select EmploymentRel.PSnum  from EmploymentRel where EmploymentRel.PSsnum= ?";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,username);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println("计算器:"+count);
            String[] psnum=new String[count];
            reLoginAP = psLoginAP.executeQuery();
            count = 0;
            while (reLoginAP.next()) {
                psnum[count] = reLoginAP.getString("PSnum");

                count++;
            }
            System.out.println(psnum[0]);
            if (psnum[0]==null){
                JOptionPane.showMessageDialog(null,"没有找到该店员的所属信息，请重新检查输入！","错误",JOptionPane.ERROR_MESSAGE);

            }else {

                String sql2 = "select EmploymentRel.PSnum,PSname,EmploymentRel.PSSnum,PSSnume,PSStel,ERdate  from EmploymentRel,PetShop,PetShopStaff where EmploymentRel.PSnum= PetShop.PSnum and  EmploymentRel.PSSnum=PetShopStaff.PSSnum AND EmploymentRel.PSnum=?";
                psLoginAP = conLoginAP.prepareStatement(sql2);
                psLoginAP.setString(1,psnum[0]);

                count=0;
                reLoginAP = psLoginAP.executeQuery();
                // 计算有多少条记录
                while (reLoginAP.next()) {
                    count++;
                }
                System.out.println(count);
                reLoginAP = psLoginAP.executeQuery();
                // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                Object[][] info = new Object[count][6];
                count = 0;
                while (reLoginAP.next()) {
                    info[count][0] = reLoginAP.getString(1);
                    info[count][1] = reLoginAP.getString(2);
                    info[count][2] = reLoginAP.getString(3);
                    info[count][3] = reLoginAP.getString(4);
                    info[count][4] = reLoginAP.getString(5);
                    info[count][5] = reLoginAP.getDate(6);
                    count++;
                }
                data = info;

            }

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        return data;

    }
    public static   Object[][] PetPsInfo(String username){

        Object[][] data= null;
        Connection conLoginAP= null;
        ResultSet reLoginAP =null;
        PreparedStatement psLoginAP = null;
        int result = 0;
        try {
            // 获得连接
            conLoginAP = DriverManager.getConnection(URL, rootID, rootpassword);
            // 建立查询条件
            String sql = "select EmploymentRel.PSnum  from EmploymentRel where EmploymentRel.PSsnum= ?";
            psLoginAP = conLoginAP.prepareStatement(sql);
            psLoginAP.setString(1,username);
            // 执行查询
            reLoginAP = psLoginAP.executeQuery();
            // 计算有多少条记录
            int count = 0;
            while (reLoginAP.next()) {
                count++;
            }
            System.out.println("计算器:"+count);
            String[] psnum=new String[count];
            reLoginAP = psLoginAP.executeQuery();
            count = 0;
            while (reLoginAP.next()) {
                psnum[count] = reLoginAP.getString("PSnum");

                count++;
            }
            System.out.println(psnum[0]);
            if (psnum[0]==null){
                JOptionPane.showMessageDialog(null,"没有找到该店员的所属信息，请重新检查输入！","错误",JOptionPane.ERROR_MESSAGE);

            }else {

                String sql2 = "select CommodityRel.Pnum,Pname,CommodityRel.PSnum,PSname,Ptype,CRdate from Pet,CommodityRel,PetShop where CommodityRel.Pnum= Pet.Pnum and CommodityRel.PSnum = PetShop.PSnum and CommodityRel.PSnum = ?";
                psLoginAP = conLoginAP.prepareStatement(sql2);
                psLoginAP.setString(1,psnum[0]);

                count=0;
                reLoginAP = psLoginAP.executeQuery();
                // 计算有多少条记录
                while (reLoginAP.next()) {
                    count++;
                }
                System.out.println(count);
                reLoginAP = psLoginAP.executeQuery();
                // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                Object[][] info = new Object[count][6];
                count = 0;
                while (reLoginAP.next()) {
                    info[count][0] = reLoginAP.getString(1);
                    info[count][1] = reLoginAP.getString(2);
                    info[count][2] = reLoginAP.getString(3);
                    info[count][3] = reLoginAP.getString(4);
                    info[count][4] = reLoginAP.getString(5);
                    info[count][5] = reLoginAP.getDate(6);
                    System.out.println("宠物所属");
                    count++;
                }
                data = info;

            }

        }
        catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }

        return data;

    }

}
