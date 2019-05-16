package Exetuxianghua;
//        System.out.print("请输入数组长度：");
//        Scanner input = new Scanner(System.in);
//        int size = input.nextInt();
//        int[] a = new int[size];
//
//        //好像不可以用foreach来输入数据，只能用来读取，不能用来改变对应的值
//
//        for (int i = 0;i<size;i++){
//            a[i] = input.nextInt();
//        }
//        System.out.println("数组的各个值为：");
//        for (int i1:a) {
//            System.out.print(i1+"\t");
//        }

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Date;

public class exetuxinghua extends JFrame{
    private DefaultTableModel model = null;
    private JTable table = null;
    private JButton addBtn = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/name?useSSL=false";
    static final String user = "root";
    static final String pass = "123456";
    public static void main(String[] args) {
        //JOptionPane.showMessageDialog(null,"欢迎来到小黄鸡的世界","小黄鸡的世界",0, new ImageIcon("imagines/123.jpg"));
        int result = First();
        if (result == 1) {
            JOptionPane.showMessageDialog(null, "已经退出游戏！！", "退出", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        choice();

    }


        public static int First() {
        Object[] obj = {"进入游戏", "退出游戏"};
        int i = JOptionPane.showOptionDialog(null, "欢迎来到小黄鸡的世界", "小黄鸡的世界", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, obj, obj[0]);
        return i;
    }

    public static void choice() {
        Object[] obj2 = {"登录", "注册", "退出游戏"};
        int i = JOptionPane.showOptionDialog(null, "欢迎来到小黄鸡的世界", "小黄鸡的世界", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
                null, obj2, obj2[0]);
        if (i == 2) {
            JOptionPane.showMessageDialog(null, "已经退出！", "退出", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        if (i == 1) {
            conn(1);
        }
        if (i == 0){
            conn(2);
        }
    }

    //管理员登录后的操作：查用户 删用户 改用户
    public static void minister(){
        Object[] obj ={"1、修改管理员密码","2、查看全部用户信息","3、删除用户"};
        String strChoice1 = (String) JOptionPane.showInputDialog(null,"进入管理者模式","ministerHLS在线",0,new ImageIcon("imagines/124.jpg"),obj,obj[0]);
        if (strChoice1==null){
            choice();
        }
        Connection conn;
        PreparedStatement stmt;
        if("1、修改管理员密码".equals(strChoice1)){

            String strNewPass = (String) JOptionPane.showInputDialog(null,"请输入新密码","改密码",0,new ImageIcon("imagines/kkk.jpg"),null,"在此处输入");
            if (strNewPass==null){
                minister();
            }
            String StringName = "ministerHLS";
            String sqlChangePass1 = "update info set sec='"+strNewPass+"' where name='"+StringName+"'";
            try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(URL,user,pass);
                stmt = conn.prepareStatement(sqlChangePass1);
                stmt.executeUpdate(sqlChangePass1);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"管理员密码修改成功","改密码",JOptionPane.WARNING_MESSAGE);
            minister();
        }
        if ("2、查看全部用户信息".equals(strChoice1)){
            new exetuxinghua();
        }
        if ("3、删除用户".equals(strChoice1)){
            minister();
        }
    }
    //用户登陆后的操作：吃饭 改密码 结婚
    public static void AfterSign(String strGetName, String strGetPass, String strGetSex, String strTimeBorn){
        Object[] obj ={"1、吃饭","2、休息","3、修改密码","4、结婚"};
        String objChoice =  (String)JOptionPane.showInputDialog(null,"欢迎您 " + strGetName,"小黄鸡 "+strGetName+" 登录了",0,new ImageIcon("imagines/123.jpg"),
                obj,obj[0]);
       if (objChoice==null){
           choice();
       }
//        System.out.println(objChoice);
        if ("1、吃饭".equals(objChoice)){
            JOptionPane.showMessageDialog(null,"吃了！（具体吃什么以后再选择）","吃饭",0,new ImageIcon("imagines/124.jpg"));
            AfterSign(strGetName,strGetPass,strGetSex,strTimeBorn);
        }
        if ("2、休息".equals(objChoice)){
            JOptionPane.showMessageDialog(null,"小黄鸡睡着了","睡觉",0,new ImageIcon("imagines/sleep.jpg"));
            AfterSign(strGetName,strGetPass,strGetSex,strTimeBorn);
        }
        if ("3、修改密码".equals(objChoice)){
            Connection conn;
            PreparedStatement stmt;
            String strNewPass = (String) JOptionPane.showInputDialog(null,"请输入新密码","改密码",0,new ImageIcon("imagines/kkk.jpg"),null,"在此处输入");
            if (strNewPass==null){
                AfterSign(strGetName,strGetPass,strGetSex,strTimeBorn);
            }
            String sqlChangePass = "update info set sec='"+strNewPass+"' where name='"+strGetName+"'";  // 更新某个数据时，sql="update info set sec=' "+变量+" '" where name='"+ 变量+"';
                                                                                                        //                                     先单引号再双引号，若有变量再夹在+  +中间
            try{
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(URL,user,pass);
                stmt = conn.prepareStatement(sqlChangePass);
                stmt.executeUpdate(sqlChangePass);
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"修改成功","改密码",JOptionPane.WARNING_MESSAGE);
            AfterSign(strGetName,strNewPass,strGetSex,strTimeBorn);
        }
        if("4、结婚".equals(objChoice)){
            JOptionPane.showMessageDialog(null,"敬请期待","结婚",JOptionPane.WARNING_MESSAGE);
            AfterSign(strGetName,strGetPass,strGetSex,strTimeBorn);
        }
    }

    //  用户增删改查封装 i判断进行什么操作（增 删 查 改）
    public static void conn(int i){
        Connection conn;
        PreparedStatement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL,user,pass);
            if(i==1){
                //注册//
                String sql;
                sql = "INSERT into info(name, sec, sex, birthday) value(?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                String strName = (String) JOptionPane.showInputDialog(null, "请填写小黄鸡的名字", "注册", 0, new ImageIcon("imagines/kkk.jpg"), null,
                        "在此处输入");
                if (strName==null){
                    choice();
                }
                //名字防重复
                String sql3 = "SELECT name from info order by birthday";
                ResultSet resultSet = stmt.executeQuery(sql3);
                while(resultSet.next()){
                    String FindSameName = resultSet.getString("name");
                    if (FindSameName.equals(strName)){
                        JOptionPane.showMessageDialog(null,"名字已被使用，请重新其他名字","注册",JOptionPane.WARNING_MESSAGE);
                        conn.close();
                        conn(1);
                    }
                }
                String strsec = (String) JOptionPane.showInputDialog(null,"请填写登录密码","注册",0,new ImageIcon("imagines/kkk.jpg"),null,
                        "在此处输入");
                if(strsec==null){
                    choice();
                }
                Object[] obj3 = {"男", "女"};
                String strSex = (String) JOptionPane.showInputDialog(null, "请选择性别", "注册", 0, new ImageIcon("imagines/kkk.jpg")
                        , obj3, obj3[0]);
                if(strSex==null){
                    choice();
                }
                String timeBorn = new Date().toLocaleString();
                JOptionPane.showMessageDialog(null, "小黄鸡创建成功\n" + "姓名：" + strName + "\n性别：" + strSex + "\n生日：" + timeBorn, "新的小黄鸡", 0,
                        new ImageIcon("imagines/123.jpg"));
                stmt.setString(1,strName);
                stmt.setString(2,strsec);
                stmt.setString(3,strSex);
                stmt.setString(4,timeBorn);
                stmt.executeUpdate();
                conn.close();
                choice();
            }
            if (i==2){
                //登录//
                String strInputName = (String) JOptionPane.showInputDialog(null,"请输入账号","登录",0,new ImageIcon("imagines/kkk.jpg"),null,
                        "在此处输入");
                if(strInputName==null){
                    choice();
                }
                String strInputPass = (String) JOptionPane.showInputDialog(null,"请输入密码","登录",0,new ImageIcon("imagines/kkk.jpg"),null,
                        "在此处输入");
                if (strInputPass==null){
                    choice();
                }
                String sql1 = "SELECT name,sec,sex,birthday from info order by birthday";
                stmt = conn.prepareStatement(sql1);
                ResultSet result = stmt.executeQuery(sql1);
                while(result.next()){
                    String strGetName = result.getString("name");
                    if (strGetName.equals(strInputName)){
                        String strGetPass = result.getString("sec");
                        if(strGetPass.equals(strInputPass)){
                            JOptionPane.showMessageDialog(null,"登录成功","登录",JOptionPane.WARNING_MESSAGE);
                            if (strInputName.equals("ministerHLS")){
                                minister();
                            }
                            String strGetSex = result.getString("sex");
                            String strTimeBorn = result.getString("birthday");
                            AfterSign(strGetName,strGetPass,strGetSex,strTimeBorn);
                        }
                    }
                }
                JOptionPane.showMessageDialog(null,"账号或密码错误！","登录",JOptionPane.WARNING_MESSAGE);
                conn(2);
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public exetuxinghua()
    {
        super("用户信息");
        Connection conn;
        PreparedStatement stmt;
        String sql1 = "SELECT name,sec,sex,birthday from info order by birthday";
        int Line= 0;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(URL,user,pass);
            stmt = conn.prepareStatement(sql1);
            ResultSet result = stmt.executeQuery(sql1);
            while(result.next()){
                Line++;
            }
            String[][] str = new String[Line][4];
            result = stmt.executeQuery(sql1);
            for (int i=0;result.next();i++){
                for (int j=0;j<4;j++){
                    str[i][j] = result.getString(j+1);
                }
            }
            String[] titles = { "用户名", "密码" ,"性别","生日"};
            model = new DefaultTableModel(str, titles);
            table = new JTable(model);
//            JButton b = new JButton();
//            add(b);
//            b.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });
            add(new JScrollPane(table));

            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
//            for (int i=0;i<Line;i++){
//                for (int j=0;j<4;j++){
//                    System.out.print(str[i][j]);
//                }
//                System.out.print("\n");
//            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
